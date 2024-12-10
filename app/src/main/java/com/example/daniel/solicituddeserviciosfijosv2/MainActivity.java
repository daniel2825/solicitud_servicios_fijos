package com.example.daniel.solicituddeserviciosfijosv2;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button registrar, consultar;
    EditText cedulacliente;
    TextView resultado;

    //direccion equipo del hosting http://tesisdaniel.net16.net/
    //Rutas de los web services//
    String GET_BY_ID = "http://tesisdaniel.net16.net/obtener_informacion_por_cedulacliente.php";
    ProgressDialog PD;

    ObtenerWebService conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);

        registrar = (Button) findViewById(R.id.registrar);
        consultar = (Button) findViewById(R.id.consultar);
        cedulacliente = (EditText) findViewById(R.id.tvcedulacliente);
        resultado = (TextView) findViewById(R.id.tvresultado);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registrar = new Intent(MainActivity.this, ValidarCedula.class);
                startActivity(registrar);}
        });
        consultar.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Formulario digital solicitud de servicios fijos", Snackbar.LENGTH_LONG)
                        .    setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.consultar:

                if(cedulacliente.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Digite su cedula",Toast.LENGTH_LONG).show();
                }
                else{
                conexion = new ObtenerWebService();
                String cadenallamada = GET_BY_ID + "?cedula_cliente=" + cedulacliente.getText().toString();
                conexion.execute(cadenallamada, "2");}
                break;

            default:

                break;
        }

    }


    public class ObtenerWebService extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null;
            String devuelve = "";

            if(params[1] == "2") { /* Consulta cedula cliente*/
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
                                // JSONArray informacionJSON= respuestaJSON.getJSONArray("informacion");

                                devuelve = "Sus datos son:"+ "\n\n" +"Su cedula es: "+respuestaJSON.getJSONObject("informacion").getString("cedula_cliente") + "\n" +
                                       "Sus nombres son: "+ respuestaJSON.getJSONObject("informacion").getString("nombre_cliente") + "\n" +
                                        "Su apellido es: "+ respuestaJSON.getJSONObject("informacion").getString("apellido_cliente") + "\n" +
                                        "Solicitado el: "+ respuestaJSON.getJSONObject("informacion").getString("fecha_solicitud") + "\n" +
                                       "Fecha de nacimiento: "+ respuestaJSON.getJSONObject("informacion").getString("fecha_nacimiento")+ "\n" +
                                        "Correo: "+ respuestaJSON.getJSONObject("informacion").getString("correo_electronico")+ "\n" +
                                        "Independiente: "+ respuestaJSON.getJSONObject("informacion").getString("independiente")+"\n" +
                                        "Empresa donde labora: "+ respuestaJSON.getJSONObject("informacion").getString("empresa")+"\n" +
                                        "Antiguedad: "+ respuestaJSON.getJSONObject("informacion").getString("antiguedad")+"\n" +
                                        "Salario: "+ respuestaJSON.getJSONObject("informacion").getString("salario")+"\n\n" +
                                        "Datos del vendedor:"+"\n\n" +
                                        "Cedula vendedor: "+ respuestaJSON.getJSONObject("informacion").getString("cedula_vendedor")+"\n" +
                                        "Nombres vendedor : "+ respuestaJSON.getJSONObject("informacion").getString("nombres_vendedor")+"\n" +
                                        "Apellidos vendedor: "+ respuestaJSON.getJSONObject("informacion").getString("apellidos_vendedor")+"\n\n" +
                                        "Datos de su vivienda:"+"\n\n" +
                                        "Direccion predio: "+ respuestaJSON.getJSONObject("informacion").getString("direccion_instalacion")+"\n" +
                                        "Ciudad: "+ respuestaJSON.getJSONObject("informacion").getString("ciudad")+"\n" +
                                        "Tipo vivienda: "+ respuestaJSON.getJSONObject("informacion").getString("tipo_casa")+"\n" +
                                        "Barrio: "+ respuestaJSON.getJSONObject("informacion").getString("barrio")+"\n" +
                                        "Bloque apartamento: "+ respuestaJSON.getJSONObject("informacion").getString("bloque_apartamento")+"\n" +
                                        "Numero apartamento: "+ respuestaJSON.getJSONObject("informacion").getString("numero_apartamento")+"\n" +
                                        "Nombre conjunto: "+ respuestaJSON.getJSONObject("informacion").getString("nombre_conjunto")+"\n" +
                                        "Envio factura: "+ respuestaJSON.getJSONObject("informacion").getString("envio_factura")+"\n\n" +
                                        "Referencia personal:"+"\n\n" +
                                        "Nombres referencia: "+ respuestaJSON.getJSONObject("informacion").getString("nombre1_referencia")+"\n" +
                                        "Apellidos referencia: "+ respuestaJSON.getJSONObject("informacion").getString("apellido1_referencia")+"\n" +
                                        "Direccion referencia: "+ respuestaJSON.getJSONObject("informacion").getString("direccion1_referencia")+"\n" +
                                        "Telefono fijo: "+ respuestaJSON.getJSONObject("informacion").getString("telefono1_fijo")+"\n" +
                                        "Telefono movil: "+ respuestaJSON.getJSONObject("informacion").getString("telefono1_movil")+"\n" +
                                        "Ciudad: "+ respuestaJSON.getJSONObject("informacion").getString("ciudad1")+"\n\n" +
                                        "Servicio de telefonia fija"+"\n\n" +
                                        "Aplica oferta: "+ respuestaJSON.getJSONObject("informacion").getString("aplica_ofertat")+"\n" +
                                        "Descripcion: "+ respuestaJSON.getJSONObject("informacion").getString("descripciont")+"\n" +
                                        "Vigencia: "+ respuestaJSON.getJSONObject("informacion").getString("vigenciat")+"\n" +
                                        "Linea nueva: "+ respuestaJSON.getJSONObject("informacion").getString("linea_nueva")+"\n" +
                                        "Plan linea: "+ respuestaJSON.getJSONObject("informacion").getString("plan_linea")+"\n" +
                                        "Valor linea: "+ respuestaJSON.getJSONObject("informacion").getString("valor_linea")+"\n\n" +
                                        "Plan larga distancia:"+"\n\n" +
                                        "Nacional: "+ respuestaJSON.getJSONObject("informacion").getString("nacional")+"\n" +
                                        "Internacional: "+ respuestaJSON.getJSONObject("informacion").getString("internacional")+"\n" +
                                        "Valor larga distancia: "+ respuestaJSON.getJSONObject("informacion").getString("valor_adicional1")+"\n\n" +
                                        "Servicios adicionales:"+"\n\n"+
                                        "Llamada en espera: "+ respuestaJSON.getJSONObject("informacion").getString("llamada_espera")+"\n" +
                                        "Identificador de llamada: "+ respuestaJSON.getJSONObject("informacion").getString("identificador_llamada")+"\n" +
                                        "Buzon de voz: "+ respuestaJSON.getJSONObject("informacion").getString("buzon")+"\n" +
                                        "Otros: "+ respuestaJSON.getJSONObject("informacion").getString("otros")+"\n" +
                                        "Valor servicios adicionales: "+ respuestaJSON.getJSONObject("informacion").getString("valor_adicional2")+"\n\n"+
                                        "Valor total servicio TV: "+ respuestaJSON.getJSONObject("informacion").getString("valor_totalfija")+"\n\n" +
                                        "Servicio de internet fijo"+"\n\n"+
                                        "Aplica oferta internet: "+ respuestaJSON.getJSONObject("informacion").getString("aplica_ofertain")+"\n" +
                                        "Descripcion oferta: "+ respuestaJSON.getJSONObject("informacion").getString("descripcionin")+"\n" +
                                        "Vigencia oferta: "+ respuestaJSON.getJSONObject("informacion").getString("vigenciain")+"\n" +
                                        "Velocidad navegacion: "+ respuestaJSON.getJSONObject("informacion").getString("velocidad_navegacion")+"\n" +
                                        "Valor navegacion: "+ respuestaJSON.getJSONObject("informacion").getString("valor_navegacion")+"\n\n" +
                                        "Servicios adicionales"+"\n\n" +
                                        "Centro de seguridad: "+ respuestaJSON.getJSONObject("informacion").getString("centro_seguridad")+"\n" +
                                        "Aula 365: "+ respuestaJSON.getJSONObject("informacion").getString("aula_365")+"\n" +
                                        "Otros servicios: "+ respuestaJSON.getJSONObject("informacion").getString("otrosin")+"\n" +
                                        "Valor Otros servicios: "+ respuestaJSON.getJSONObject("informacion").getString("valorotros")+"\n" +
                                        "Valor servicios adicionales: "+ respuestaJSON.getJSONObject("informacion").getString("valor_adicionalin")+"\n\n" +
                                        "Valor total internet: "+ respuestaJSON.getJSONObject("informacion").getString("valor_totalin")+"\n\n" +
                                        "Servicios de television digital"+"\n\n" +
                                       "Aplica oferta Tv: "+ respuestaJSON.getJSONObject("informacion").getString("aplica_ofertatv")+"\n" +
                                        "Descripcion: "+ respuestaJSON.getJSONObject("informacion").getString("descripciontv")+"\n" +
                                        "Vigencia: "+ respuestaJSON.getJSONObject("informacion").getString("vigenciatv")+"\n" +
                                        "Plan Tv: "+ respuestaJSON.getJSONObject("informacion").getString("plan_tv")+"\n" +
                                        "Valor plan Tv: "+ respuestaJSON.getJSONObject("informacion").getString("valor_tv")+"\n\n" +
                                        "Decodificadores:"+"\n\n" +
                                        "Cantidad decos: "+ respuestaJSON.getJSONObject("informacion").getString("cantidad_decos")+"\n" +
                                        "Valor C/U decos: "+ respuestaJSON.getJSONObject("informacion").getString("valorcu_decos")+"\n" +
                                        "Valor total decos: "+ respuestaJSON.getJSONObject("informacion").getString("valortotal_decos")+"\n\n" +
                                        "Paquete HD: "+ respuestaJSON.getJSONObject("informacion").getString("paquete_hd")+"\n" +
                                        "Plan HD: "+ respuestaJSON.getJSONObject("informacion").getString("plan_hd")+"\n" +
                                        "Valor HD: "+ respuestaJSON.getJSONObject("informacion").getString("valor_hd")+"\n" +
                                        "Cantidad decos HD: "+ respuestaJSON.getJSONObject("informacion").getString("cantidad_decoshd")+"\n" +
                                        "Valor C/U decos HD: "+ respuestaJSON.getJSONObject("informacion").getString("valorcu_decoshd")+"\n" +
                                        "Valor total decos HD: "+ respuestaJSON.getJSONObject("informacion").getString("valortotal_decoshd")+"\n\n" +
                                        "Paquete premium:"+"\n\n" +
                                        "HBO: "+ respuestaJSON.getJSONObject("informacion").getString("hbo")+"\n" +
                                        "Movie city: "+ respuestaJSON.getJSONObject("informacion").getString("movie_city")+"\n" +
                                        "Plan cine: "+ respuestaJSON.getJSONObject("informacion").getString("plan_cine")+"\n" +
                                        "Canal adulto: "+ respuestaJSON.getJSONObject("informacion").getString("canal_adulto")+"\n" +
                                        "Valor paquete premium: "+ respuestaJSON.getJSONObject("informacion").getString("valor_paquete")+"\n\n" +
                                        "Valor total TV: "+ respuestaJSON.getJSONObject("informacion").getString("valor_totaltv")+"\n\n" +
                                        "Valor a pagar mensual: "+ respuestaJSON.getJSONObject("informacion").getString("total_pagar")+"\n" +
                                        "Respuesta del area de validacion: "+ respuestaJSON.getJSONObject("informacion").getString("respuesta")+"\n"
                                        +"\n" +"\n" +"\n" +"\n"



                                ;

                            } else if (resultJSON.equals("2")) {

                                devuelve = "Usted no se encuentra registrado en nuestra base de datos";


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
                return devuelve;


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
            resultado.setText(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

}
