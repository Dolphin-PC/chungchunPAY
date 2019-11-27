package com.example.chungchunpay.activity;

import android.app.Dialog;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.FireCloud_Data.DataMap;
import com.example.chungchunpay.NaverJSON;
import com.example.chungchunpay.R;
import com.example.chungchunpay.menu.BoomMenuBuilderManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    FirebaseFirestore FirebaseDB = FirebaseFirestore.getInstance();

    public StorageReference StorageRef = FirebaseStorage.getInstance().getReference();
    public StorageReference TourImageRef = StorageRef.child("맵/tour");

    private String NaverMap_API_JSON, url = "https://naveropenapi.apigw.ntruss.com/map-place/v1/search?query={스카이워크}&coordinate=127.1054328,37.3595963" +
            "&X-NCP-APIGW-API-KEY-ID: {hgzmsoibxy}" +
            "&X-NCP-APIGW-API-KEY: {jbxQt9XogoWWqZ5IMKvRwetpr1ZXwVqXZq4DTWV5}";

    HashMap<String,Object> marker_position = new HashMap<>();
    private MapView mapView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    EditText SearchEditText;
    Button MapListButton,SearchButton, DialogButton;
    ImageView ProfileImageView ,DialogImageView;
    TextView DialogTitleText;

    //Naver 지도 검색
    String latitude = "37.898943";
    String longitude = "127.728868";

    String SearchKeyword = "관광";
    String SearchPosition = latitude + ", " + longitude;
    String NaverClientIDValue = "hgzmsoibxy";
    String NaverClientSecretValue = "jbxQt9XogoWWqZ5IMKvRwetpr1ZXwVqXZq4DTWV5";

    //Naver 지도 정보창
    InfoWindow infoWindow = new InfoWindow();

    //BoomMenu
    private BoomMenuButton bmb;
    private ArrayList<Pair> piecesAndButtons = new ArrayList<>();

    Marker[] markers = new Marker[10];
    Marker marker = new Marker();

    Dialog dialog;
    String MarkerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        SearchEditText = findViewById(R.id.SearchEditText);
        MapListButton = findViewById(R.id.MapListButton);
        SearchButton = findViewById(R.id.SearchButton);
        ProfileImageView = findViewById(R.id.ProfileImageView);


        BoomMenu();


        //현재 내 위치
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        init();
//        DB();

        mapView.getMapAsync(this);

        //TODO : 내부 DB(SQLite)
        //TODO : 검색 기능(장소 검색)
//        getJsonString();
    }
    void BoomMenu(){
        bmb = findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_6_1);

        for(int i = 1;i<=6;i++) {
            bmb.addBuilder(BoomMenuBuilderManager.getTextOutsideCircleButtonBuilder(i));
        }


    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = new URL(url).openStream();
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        Log.e("test",json);
        return json;
    }

    private void jsonParsing(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray movieArray = jsonObject.getJSONArray("Movies");

            for(int i=0; i<movieArray.length(); i++)
            {
                JSONObject movieObject = movieArray.getJSONObject(i);

                NaverJSON movie = new NaverJSON();

                movie.setTitle(movieObject.getString("title"));
                movie.setGrade(movieObject.getString("grade"));
                movie.setCategory(movieObject.getString("category"));

                Log.e("JSON test [" + i + "] : " ,
                        movie.getTitle() + ", " +movie.getGrade() + ", " + movie.getCategory());
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void init() {
        NaverMap_API_JSON = "\"https://naveropenapi.apigw.ntruss.com/map-place/v1/search?query=";
        NaverMap_API_JSON += SearchKeyword + "&coordinate=";
        NaverMap_API_JSON += SearchPosition + "\" \\\n\t-H \"X-NCP-APIGW-API-KEY-ID: {";
        NaverMap_API_JSON += NaverClientIDValue + "}\" \\\n\t-H \"X-NCP-APIGW-API-KEY: {";
        NaverMap_API_JSON += NaverClientSecretValue + "}\" -v";

        marker_position.put("SoyangRiverPark",new LatLng(37.893460, 127.726002));

        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getApplicationContext()) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return (CharSequence)infoWindow.getMarker().getTag();
            }
        });

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        FirebaseDB.setFirestoreSettings(settings);

    }
    void Map_DB(String category, NaverMap naverMap){
        //Database
        FirebaseDB.collection("map").document("춘천").collection(category)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            int i = 0;
                            final int index = i;
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                DataMap dataMap = documentSnapshot.toObject(DataMap.class);
                                markers[i] = new Marker();
                                markers[i].setPosition(new LatLng(dataMap.getLatitude(),dataMap.getLongitude()));
                                markers[i].setTag(dataMap.getName());
                                markers[i].setMap(naverMap);

                                Overlay.OnClickListener listener = overlay -> {
                                    Marker marker = (Marker)overlay;

                                    if(marker.getInfoWindow() == null){
                                        //현재 마커에 정보 창이 열려있지 않을 경우 엶
                                        infoWindow.open(marker);
                                        MarkerName = marker.getTag()+"";
//                                        TitleText.setText(marker.getTag()+"");
                                    }else{
                                        //이미 현재 마커에 정보 창이 열려있을 경우 닫음
                                        infoWindow.close();
                                    }
                                    return true;
                                };

                                markers[i].setOnClickListener(listener);

                                infoWindow.setOnClickListener(new Overlay.OnClickListener() {
                                    @Override
                                    public boolean onClick(@NonNull Overlay overlay) {
                                        dialog = new Dialog(MapActivity.this);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        dialog.setContentView(R.layout.activity_map_dialog);
                                        dialog.show();

                                        DialogTitleText = dialog.findViewById(R.id.Dialog_TitleText);
                                        DialogImageView = dialog.findViewById(R.id.Dialog_ImageView);
                                        DialogButton = dialog.findViewById(R.id.Dialog_Button);
                                        click(MarkerName);
                                        DialogTitleText.setText(MarkerName);
                                        return true;
                                    }
                                });

                                i++;
                                Log.e("test DB",documentSnapshot.getId() + " => 이름 : " + dataMap.getName() + ", 좌표 : " + dataMap.getLatitude() + dataMap.getLongitude());


                            }
                        }else{
                            Log.e("test DB","Error",task.getException());
                        }
                    }
                });
    }

    void click(String MarkerName) {
        StorageReference MarkerImageofMarkerName = TourImageRef.child(MarkerName);
        Glide.with(this)
                .load(MarkerImageofMarkerName)
                .into(DialogImageView);

        DialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); //다이얼로그 종료
            }
        });
    }
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setMapType(NaverMap.MapType.Basic);
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        bmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                switch (index){
                    case 0 : Map_DB("tour",naverMap); break;
                    case 1 : Map_DB("food",naverMap); break;
                    case 2 : Map_DB("bus",naverMap); break;
                    case 3 : Map_DB("taxi",naverMap); break;
                    case 4 : Map_DB("gs",naverMap); break;
                    case 5 : Map_DB("bank",naverMap); break;
                }


            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });

//        markers[i].setOnClickListener(overlay -> {
//            infoWindow.open(markers[i]);
//            return true;
//        });

        naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                infoWindow.close();
            }
        });







    }

    @Override protected void onStart() { super.onStart();mapView.onStart(); }
    @Override public void onLowMemory() { super.onLowMemory();mapView.onLowMemory(); }
    @Override protected void onDestroy() { super.onDestroy();mapView.onDestroy(); }
    @Override protected void onStop() { super.onStop();mapView.onStop(); }
    @Override protected void onSaveInstanceState(@NonNull Bundle outState) { super.onSaveInstanceState(outState);mapView.onSaveInstanceState(outState); }
    @Override protected void onPause() { super.onPause();mapView.onPause(); }
    @Override protected void onResume() { super.onResume();mapView.onResume(); }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
