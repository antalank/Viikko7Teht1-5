package com.example.l07_t05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText kirjoitus;
    EditText kirjoitettava;
    TextView teksti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        System.out.println("Tiedosto sijaitsee kansiossa: " + context.getFilesDir());

        //Rakentajat
        teksti = (TextView) findViewById((R.id.tekstiLaatikko));
        teksti.setText("Tähän tulee tekstit.");

        kirjoitus = (EditText) findViewById(R.id.syotto);
        kirjoitus.setText("Kirjoita tiedoston nimi");

        kirjoitettava = (EditText) findViewById((R.id.kirjoitettava));
        kirjoitettava.setText("Sisältö");

    }

    public void readFile(View v) {
        try {
            InputStream ins = context.openFileInput(kirjoitus.getText().toString()); //TODO Kysyttään käyttäjältä.
            BufferedReader buf = new BufferedReader(new InputStreamReader(ins));
            String merkkijono = "";

            while ((merkkijono=buf.readLine()) != null) {
                //System.out.println(merkkijono);
                teksti.setText(merkkijono);

            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Syötettä ei voi lukea.");
        } finally {
            System.out.println("Luettu.");
        }

    }
    public void writeFile(View v) {
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(kirjoitus.getText().toString(), Context.MODE_PRIVATE));


            String merkkijono = "";

            merkkijono = kirjoitettava.getText().toString();
            ows.write(merkkijono);
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Syötettä ei voi lukea.");
        } finally {
            System.out.println("Kirjoitettu.");
        }
    }
}
