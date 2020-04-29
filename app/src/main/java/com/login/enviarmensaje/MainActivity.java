package com.login.enviarmensaje;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtnumero, txtasunto, txtdescripcion;
    Button Enviarsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnumero = (EditText) findViewById(R.id.txtnumero);
        txtasunto = (EditText) findViewById(R.id.txtasunto);
        txtdescripcion = (EditText) findViewById(R.id.txtdescripcion);
        Enviarsms = (Button) findViewById(R.id.btnenviar);
    }

    public void enviarsms(View view) {
        String numTel = txtnumero.getText().toString();
        String datosCompletos = "Asunto: " +  txtasunto.getText().toString() + "\n " + txtdescripcion.getText().toString();

        try {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS
            );
            if (permissionCheck != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "No se tiene permiso para enviar SMS", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},225);
            }else{
                Log.i("Mensaje", "Se tiene permiso");
            }
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numTel, null, datosCompletos, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos." + e.getMessage().toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
