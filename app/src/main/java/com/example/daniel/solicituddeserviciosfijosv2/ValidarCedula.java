package com.example.daniel.solicituddeserviciosfijosv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ValidarCedula extends AppCompatActivity {
    String GET_BY_ID = "http://tesisdaniel.net16.net/obtener_informacion_por_cedulacliente.php";
    EditText cedula;
    Button avanzar;
    ObtenerWebService conexion;
    String opcion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_cedula);
        cedula=(EditText)findViewById(R.id.etcedula);
        avanzar=(Button) findViewById(R.id.enviar);
    }


    public void enviarClicked(View v){


        if(cedula.getText().toString().equals("")){
            Toast.makeText(ValidarCedula.this,"Digite su cedula",Toast.LENGTH_LONG).show();
        }
       else{
        conexion = new ObtenerWebService();
        String cadenallamada = GET_BY_ID + "?cedula_cliente=" + cedula.getText().toString();
        conexion.execute(cadenallamada, "2");
        }
    }



    public class ObtenerWebService extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null;
            String devuelve = "";
            String opcion="";



            if (params[1] == "1") {


            } else if (params[1] == "2") { /* Consulta cedula cliente*/
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();//abrir conexion con el protocolo
                    connection.setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Acer; 4738; " +
                                    "Intel Windows 10; es-ES; rv) Conexion hosting");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {

                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null) {

                            result.append(line);

                            JSONObject respuestaJSON = new JSONObject(result.toString());
                            String resultJSON = respuestaJSON.getString("estado");


                            if (resultJSON.equals("1")) {
                                opcion="1";

                            }
                            else if (resultJSON.equals("2")) {
                                opcion="2";

                            }


                        }
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();

                }
                return opcion;


            }


            return null;
        }


        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {

            if(s.equals("1")){

                Toast.makeText(ValidarCedula.this,"Esta cedula ya esta registrada",Toast.LENGTH_LONG).show();
                cedula.setText("");

            }

            if(s.equals("2")){
                String cedulaceptada = cedula.getText().toString();
                Intent registrar = new Intent(ValidarCedula.this, insertar.class);
                registrar.putExtra("cedula",cedulaceptada);
                startActivity(registrar);

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }



}
