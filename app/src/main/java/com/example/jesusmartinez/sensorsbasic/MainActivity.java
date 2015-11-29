package com.example.jesusmartinez.sensorsbasic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView labelX;
    private TextView labelY;
    private TextView labelZ;
    private TextView labelSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labelX=(TextView) findViewById(R.id.labelX);
        labelY=(TextView) findViewById(R.id.labelY);
        labelZ=(TextView) findViewById(R.id.labelZ);
        labelSum=(TextView) findViewById(R.id.labelSum);

        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensor==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder (MainActivity.this);
            builder.setMessage("Sensor no disponible").setTitle("Atención!").setCancelable(false).setNeutralButton("aceptar",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id){
                    dialog.cancel();
                }
            });

            AlertDialog alert =builder.create();
            alert.show();

        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        labelX.setText("X: "+sensorEvent.values[0]);
        labelY.setText("Y: "+sensorEvent.values[1]);
        labelZ.setText("Z: "+sensorEvent.values[2]);
        labelSum.setText("Sum: "+(sensorEvent.values[0] +
                sensorEvent.values[1] + sensorEvent.values[2]));    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


