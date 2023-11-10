package com.example.flashlightvibro;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private String cameraId;
    private static boolean isFlashLightOn = false;
    private final char[]  codeChar =
            {'a','b','c','d','e','f','g',
                    'h','i','j','k','l','m','n',
                    'o','p','q','r','s','t','u',
                    'v','w','x','y','z'
    };
    private final String []  codeMorse =
            {".-","-...","-.-.","-..",".","..-.","--.",
                    "--.","..",".---","-.-",".-..","--","-.",
                    "---",".--.","--.-",".-.","...","-","..-",
                    "...-",".--","-..-","-.--","--.."
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText txtEdit = (TextInputEditText) findViewById(R.id.textMessage);
        TextInputEditText txtMorse = (TextInputEditText) findViewById(R.id.textMorse);

        txtEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String temp = txtEdit.getText().toString();
                    String morse = "";
                    if(temp.length()>0)
                    {
                        int charId = 0;
                        for(int i = 0; i<temp.length();i++)
                        {
                            //Нашли айди буквы
                            for(int k=0;k<codeChar.length;k++)
                            {
                                if(codeChar[k]==temp.charAt(i)){
                                    charId = k;
                                    break;
                                }
                            }
                            //Ищем символ Морзе
                            morse += codeMorse[charId] ;
                        }
                        txtMorse.setText(morse);
                    }
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void turnFlashLight(View v) {
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        String s = "123";
        try {
            assert cameraManager != null;
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        if (!isFlashLightOn) {
            try {
                cameraManager.setTorchMode(cameraId, true);
                isFlashLightOn = !isFlashLightOn;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                cameraManager.setTorchMode(cameraId, false);
                isFlashLightOn = !isFlashLightOn;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
    public void vibroMessage(View v){
        TextInputEditText txtMorse = (TextInputEditText) findViewById(R.id.textMorse);
        String text = txtMorse.getText().toString();
        long [] pattern= new long[text.length()*text.length()];

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationEffect ve1 = VibrationEffect.createOneShot(500,50);
        VibrationEffect ve2 = VibrationEffect.createOneShot(1000,150);

        for(int i = 0; i<text.length();i++)
        {
            if(text.charAt(i)=='-')
            {
                vibrator.vibrate(ve2);
                try {
                    sleep(1200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                vibrator.cancel();
            }
            else{
                vibrator.vibrate(ve1);
                try {
                    sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                vibrator.cancel();
            }

        }
     }
    public void vibroMessage2(View v){



        TextInputEditText txtMorse = (TextInputEditText) findViewById(R.id.textMorse);
        String text = txtMorse.getText().toString();
        long [] pattern= new long[text.length()*text.length()];

        return;
        /*
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            assert cameraManager != null;
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


            try {
                cameraManager.setTorchMode(cameraId, false);
                isFlashLightOn = !isFlashLightOn;
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        for(int i = 0; i<text.length();i++)
        {
            if(text.charAt(i)=='-')
            {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cameraManager.setTorchMode(cameraId, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(1200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cameraManager.setTorchMode(cameraId, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cameraManager.setTorchMode(cameraId, true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                try {
                    sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    cameraManager.setTorchMode(cameraId, false);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

        }
         */
    }
}