package com.example.chungchunpay.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chungchunpay.FireCloud_Data.DataUser;
import com.example.chungchunpay.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PayActivity extends AppCompatActivity {

    TextView SecondsText;
    Vibrator vibrator;

    TimerTask timerTask;
    Timer timer = new Timer();

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;

    FirebaseFirestore FirebaseDB = FirebaseFirestore.getInstance();
    String ID;
    int point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        init();

        SharedPreferences positionDATA = getSharedPreferences("UserData",MODE_PRIVATE);
        ID = positionDATA.getString("ID","");

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        FirebaseDB.setFirestoreSettings(settings);

        FirebaseDB.collection("users").document(ID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        DataUser dataUser = documentSnapshot.toObject(DataUser.class);
                        point = dataUser.getPoint();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {1500, 1};  // 대기, 진동
        vibrator.vibrate(pattern, 0);
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        vibrator.cancel();
        super.onDestroy();
    }

    void init(){
        SecondsText = findViewById(R.id.SecondsText);

        startTimerTask();

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Intent intent = new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this,0, intent,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(nfcAdapter != null)
            nfcAdapter.enableForegroundDispatch(this,pendingIntent,null,null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nfc(intent);
    }

    void startTimerTask(){
        stopTimerTask();

        timerTask = new TimerTask()
        {
            int count = 60;

            @Override
            public void run()
            {
                if(count <= 0){
                    finish();
                }
                count--;
                SecondsText.post(new Runnable() {
                    @Override
                    public void run() {
                        SecondsText.setText(count + "");
                    }
                });
            }
        };
        timer.schedule(timerTask,0 ,1000);
    }

    private void stopTimerTask()
    {
        if(timerTask != null)
        {
            SecondsText.setText("60");
            timerTask.cancel();
            timerTask = null;
        }
    }

    void nfc(Intent intent){
        Map<String, Object> user  = new HashMap<>();

        String s = ""; // 글씨를 띄우는데 사용
        Parcelable[] data = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES); // EXTRA_NDEF_MESSAGES : 여분의 배열이 태그에 존재한다.
        if(data != null)
        {
            try{
                for (int i =0; i<data.length; i++){
                    NdefRecord[] recs = ((NdefMessage)data[i]).getRecords();
                    for(int j = 0; j<recs.length; j++)
                    {
                        if(recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(recs[j].getType(), NdefRecord.RTD_TEXT)){
                            byte[] payload = recs[j].getPayload();
                            String textEncoding = ((payload[0] & 0200)==0)?"UTF-8":"UTF-16";
                            int langCodeLen = payload[0] & 0077;
                            s += ("\n"+ new String(payload, langCodeLen + 1, payload.length - langCodeLen -1, textEncoding));
                        }
                    }
                }
            }
            catch(Exception e) { }
        }else{
            finish();
            Toast.makeText(getApplicationContext(),"결제에 실패하였습니다.",Toast.LENGTH_SHORT).show();
            return;


        }
        //TODO : 결제 처리, point - pay요금

        long[] pattern = {0, 1000};  // 대기, 진동
        vibrator.vibrate(pattern, -1);

        s = s.split("\n")[1];
        int pay = Integer.parseInt(s);
        if(point >= pay){
            user.put("Point",point-pay);
            FirebaseDB.collection("users").document(ID)
                    .update(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),pay+"원이 결제 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        }
                    });

        }else{
            Toast.makeText(getApplicationContext(),"결제 실패 : 금액 부족",Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
