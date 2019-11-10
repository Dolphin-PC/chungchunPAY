package com.example.chungchunpay;

import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        //현재 내 위치
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        init();
        click();
    }

    void init() {

    }

    void click() {

    }

    public class MapFragmentActivity extends FragmentActivity implements OnMapReadyCallback {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_map);

            FragmentManager fm = getSupportFragmentManager();
            MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
            if (mapFragment == null) {
                mapFragment = MapFragment.newInstance();
                fm.beginTransaction().add(R.id.map, mapFragment).commit();
            }

            mapFragment.getMapAsync(this);
        }
        @Override
        public void onMapReady(@NonNull NaverMap naverMap) {
            Marker marker = new Marker();

            //네이버 지도 타입(일반, 위성, 일반+위성(hybrid)
//            naverMap.setMapType(NaverMap.MapType.Hybrid);

            //네이버 지도 건물 레이어 그룹
//            naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, false);
//            naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, true);

            //야간 모드
//            naverMap.setNightModeEnabled(true);

            //카메라 현재 위치
//            CameraPosition cameraPosition = naverMap.getCameraPosition();

            //지도 위치와 카메라 위치
//            NaverMapOptions options = new NaverMapOptions().camera(new CameraPosition(new LatLng(35.1798159, 129.0750222), 8));

            //지도 아래 패딩
            naverMap.setContentPadding(0, 0, 0, 200);

            //지도 UI setting
            UiSettings uiSettings = naverMap.getUiSettings();

            //지도 UI - Compass, Location
            uiSettings.setCompassEnabled(true);
            uiSettings.setLocationButtonEnabled(true);

            //현재 내 위치
            naverMap.setLocationSource(locationSource);

            //위치 추적
//            naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            //.None : 위치 추적 안함 / .NoFollow : 위치추적과 오버레이가 움직이나, 지도는 움직이지 않음.
            //.Follow : 위치 추적과 지도 움직임 / .Face : 위치 추적과 베링어 움직

            //카메라 줌 제한
//            naverMap.setMinZoom(5.0);
//            naverMap.setMaxZoom(18.0);

            //지도에 Marker 추가
//            marker.setPosition(new LatLng(37.5670135, 126.9783740));
//            marker.setMap(naverMap);

            //위치 변경 이벤트
            naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
                @Override
                public void onLocationChange(@NonNull Location location) {

                }
            });

            //UI click event
            naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {

                }
            });

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (locationSource.onRequestPermissionsResult(
                    requestCode, permissions, grantResults)) {
                return;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override protected void onStart() { super.onStart();mapView.onStart(); }
    @Override public void onLowMemory() { super.onLowMemory();mapView.onLowMemory(); }
    @Override protected void onDestroy() { super.onDestroy();mapView.onDestroy(); }
    @Override protected void onStop() { super.onStop();mapView.onStop(); }
    @Override protected void onSaveInstanceState(@NonNull Bundle outState) { super.onSaveInstanceState(outState);mapView.onSaveInstanceState(outState); }
    @Override protected void onPause() { super.onPause();mapView.onPause(); }
    @Override protected void onResume() { super.onResume();mapView.onResume(); }


}
