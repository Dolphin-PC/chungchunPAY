package com.example.chungchunpay.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chungchunpay.FireCloud_Data.DataUser;
import com.example.chungchunpay.R;
import com.example.chungchunpay.fragment.CartFragment;
import com.example.chungchunpay.fragment.HomeFragment;
import com.example.chungchunpay.fragment.SettingFragment;
import com.example.chungchunpay.fragment.WalletFragment;
import com.example.chungchunpay.menu.DrawerAdapter;
import com.example.chungchunpay.menu.DrawerItem;
import com.example.chungchunpay.menu.SimpleItem;
import com.example.chungchunpay.menu.SpaceItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{
    private static final String TAG = "MainActivity";
    FirebaseFirestore FireDB = FirebaseFirestore.getInstance();


    String UserName,ID,ProfileURL,Gender,Hobby,Age;
    private SlidingUpPanelLayout mLayout;
    TextView BottomNameText,BottomPointText,LeftDrawerPointText;

    private static final int POS_HOME = 0;
    private static final int POS_WALLET = 1;
    private static final int POS_SETTING = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingUpPanel();
        slide_Root_Nav(savedInstanceState);

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        FireDB.setFirestoreSettings(settings);

        init();

    }

    void slide_Root_Nav(Bundle savedInstanceState){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_WALLET),
                createItemFor(POS_SETTING),
                createItemFor(POS_CART),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        LeftDrawerPointText = findViewById(R.id.LeftDrawerPointText);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_HOME);
    }

    /*
     * @param UserName : 카카오톡 닉네임, LoginDATA[USERNAME]
     * @param Id : 카카오톡 일련번호, LoginDATA[ID]
     * @param ProfileURL : 카카오톡 프로필 url, LoginDATA[PROFILE]
     */

    void init(){
        //상태바 없애기(FullScreen)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //사용자 정보 불러오기(핸드폰 저장소에서)
        SharedPreferences LoginData = getSharedPreferences("UserData",MODE_PRIVATE);
        UserName = LoginData.getString("USERNAME","");
        ID = LoginData.getString("ID","");
        ProfileURL = LoginData.getString("PROFILE","");

        BottomNameText = findViewById(R.id.NameText);
        BottomPointText = findViewById(R.id.PointText);


        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        FirebaseDB();
    }

    void FirebaseDB(){
        DocumentReference documentReference = FireDB.collection("users").document(ID);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Timestamp timestamp = documentSnapshot.getTimestamp("created_at");
                        Date date = timestamp.toDate();

                        DataUser dataUser = documentSnapshot.toObject(DataUser.class);
                        LeftDrawerPointText.setText(dataUser.getPoint()+"");
                        BottomPointText.setText(dataUser.getPoint()+"");
                        BottomNameText.setText(dataUser.getUserName());
                        Gender = dataUser.getGender();
                        Age = dataUser.getAge();
                        Hobby = dataUser.getHobby();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    void SlidingUpPanel(){
        mLayout = findViewById(R.id.activity_main);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
        mLayout.setPanelState(PanelState.EXPANDED);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.slidingup, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mLayout != null) {
            if (mLayout.getPanelState() == PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toggle: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != PanelState.HIDDEN) {
                        mLayout.setPanelState(PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mLayout.setPanelState(PanelState.COLLAPSED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mLayout != null) {
                    if (mLayout.getAnchorPoint() == 1.0f) {
                        mLayout.setAnchorPoint(0.7f);
                        mLayout.setPanelState(PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_disable);
                    } else {
                        mLayout.setAnchorPoint(1.0f);
                        mLayout.setPanelState(PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_enable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED)) {
            mLayout.setPanelState(PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemSelected(int position) {
        switch (position){
            case POS_HOME :
                showFragment(new HomeFragment());
                break;

            case POS_WALLET :
                showFragment(new WalletFragment());
                break;

            case POS_SETTING :
                showFragment(new SettingFragment());
                break;

            case POS_CART :
                showFragment(new CartFragment());
                break;

            case POS_LOGOUT :
                finish();

                //TODO : 로그아웃 처리하기
                Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(intent);
        }
        slidingRootNav.closeMenu();
//        Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);

    }

    public void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.textColorSecondary))
                .withTextTint(color(R.color.textColorPrimary))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }
    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    void click(){

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.custom_actionbar, null);
        ImageView userIcon = actionbar.findViewById(R.id.UserButton);
        Glide.with(this).load(ProfileURL).into(userIcon);
        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;

    }*/
}
