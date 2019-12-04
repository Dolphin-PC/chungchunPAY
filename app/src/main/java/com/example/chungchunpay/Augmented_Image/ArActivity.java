package com.example.chungchunpay.Augmented_Image;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chungchunpay.FireCloud_Data.DataUser;
import com.example.chungchunpay.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Scene;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class ArActivity extends AppCompatActivity {
    public static final String TAG = ArActivity.class.getName();
    FirebaseFirestore FirebaseDB = FirebaseFirestore.getInstance();

    String MungmuiName = "춤추는 멍무이";
    String ID;
    int current_point;

    // Rendering. The Renderers are created here, and initialized when the GL surface is created.
    private ArSceneView arSceneView;

    private boolean installRequested;

    private Session session;
    private final SnackbarHelper messageSnackbarHelper = new SnackbarHelper();

    private boolean shouldConfigureSession = false;

    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        arSceneView = findViewById(R.id.surfaceview);

        installRequested = false;

        initializeSceneView();
        dialog = new AlertDialog.Builder(this);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        FirebaseDB.setFirestoreSettings(settings);
        SharedPreferences positionDATA = getSharedPreferences("UserData",MODE_PRIVATE);
        ID = positionDATA.getString("ID","");

        FirebaseDB.collection("users").document(ID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                DataUser dataUser = documentSnapshot.toObject(DataUser.class);
                current_point = dataUser.getPoint();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (session == null) {
            Exception exception = null;
            String message = null;
            try {
                switch (ArCoreApk.getInstance().requestInstall(this, !installRequested)) {
                    case INSTALL_REQUESTED:
                        installRequested = true;
                        return;
                    case INSTALLED:
                        break;
                }

                // ARCore requires camera permissions to operate. If we did not yet obtain runtime
                // permission on Android M and above, now is a good time to ask the user for it.
                if (!CameraPermissionHelper.hasCameraPermission(this)) {
                    CameraPermissionHelper.requestCameraPermission(this);
                    return;
                }

                session = new Session(/* context = */ this);
            } catch (UnavailableArcoreNotInstalledException
                    | UnavailableUserDeclinedInstallationException e) {
                message = "Please install ARCore";
                exception = e;
            } catch (UnavailableApkTooOldException e) {
                message = "Please update ARCore";
                exception = e;
            } catch (UnavailableSdkTooOldException e) {
                message = "Please update this app";
                exception = e;
            } catch (Exception e) {
                message = "This device does not support AR";
                exception = e;
            }

            if (message != null) {
                messageSnackbarHelper.showError(this, message);
                Log.e(TAG, "Exception creating session", exception);
                return;
            }

            shouldConfigureSession = true;
        }

        if (shouldConfigureSession) {
            configureSession();
            shouldConfigureSession = false;
            arSceneView.setupSession(session);
        }

        // Note that order matters - see the note in onPause(), the reverse applies here.
        try {
            session.resume();
            arSceneView.resume();
        } catch (CameraNotAvailableException e) {
            // In some cases (such as another camera app launching) the camera may be given to
            // a different app instead. Handle this properly by showing a message and recreate the
            // session at the next iteration.
            messageSnackbarHelper.showError(this, "Camera not available. Please restart the app.");
            session = null;
            return;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (session != null) {
            // Note that the order matters - GLSurfaceView is paused first so that it does not try
            // to query the session. If Session is paused before GLSurfaceView, GLSurfaceView may
            // still call session.update() and get a SessionPausedException.
            arSceneView.pause();
            session.pause();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(
                    this, "Camera permissions are needed to run this application", Toast.LENGTH_LONG)
                    .show();
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                // Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(this);
            }
            finish();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenHelper.setFullScreenOnWindowFocusChanged(this, hasFocus);
    }

    private void initializeSceneView() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = simpleDateFormat.format(mDate);

        HashMap<String, Object> user_report = new HashMap<>();
        user_report.put("point",2000);
        user_report.put("ID",ID);
        user_report.put("time",currentTime);

        arSceneView.getScene().addOnUpdateListener((this::onUpdateFrame));
        arSceneView.getScene().addOnPeekTouchListener(new Scene.OnPeekTouchListener() {
            @Override
            public void onPeekTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
                dialog.setTitle(MungmuiName).setMessage("멍무이를 획득하시겠습니까?")
                        .setPositiveButton("획득", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                Toast.makeText(getApplicationContext(),"현재 AR에서는 포인트 적립만 가능합니다.",Toast.LENGTH_LONG).show();
                                HashMap<String, Object> user = new HashMap<>();
                                user.put("Point",current_point + 2000);
                                FirebaseDB.collection("users").document(ID).update(user);
                                FirebaseDB.collection("report").document(ID).collection("get_point").add(user_report);
                            }
                        }).show();
//                            Toast.makeText(getApplicationContext(),MungmuiName,Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void onUpdateFrame(FrameTime frameTime) {
        Frame frame = arSceneView.getArFrame();
        Collection<AugmentedImage> updatedAugmentedImages =
                frame.getUpdatedTrackables(AugmentedImage.class);

        for (AugmentedImage augmentedImage : updatedAugmentedImages) {
            if (augmentedImage.getTrackingState() == TrackingState.TRACKING) {
                // Check camera image matches our reference image
                if (augmentedImage.getName().equals(MungmuiName)) {
                    AugmentedImageNode node = new AugmentedImageNode(this, "Dog.sfb");
                    node.setImage(augmentedImage);

                    arSceneView.getScene().addChild(node);


                }

            }
        }
    }

    private void configureSession() {
        Config config = new Config(session);
        if (!setupAugmentedImageDb(config)) {
            messageSnackbarHelper.showError(this, "Could not setup augmented image database");
        }
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        session.configure(config);
    }

    private boolean setupAugmentedImageDb(Config config) {
        AugmentedImageDatabase augmentedImageDatabase;

        Bitmap augmentedImageBitmap = loadAugmentedImage();
        if (augmentedImageBitmap == null) {
            return false;
        }

        augmentedImageDatabase = new AugmentedImageDatabase(session);
        augmentedImageDatabase.addImage(MungmuiName, augmentedImageBitmap);

        config.setAugmentedImageDatabase(augmentedImageDatabase);
        return true;
    }

    private Bitmap loadAugmentedImage() {
        try (InputStream is = getAssets().open("qrcode.png")) {
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            Log.e(TAG, "IO exception loading augmented image bitmap.", e);
        }
        return null;
    }
}