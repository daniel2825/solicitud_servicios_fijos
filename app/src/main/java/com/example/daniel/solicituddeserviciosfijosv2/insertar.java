package com.example.daniel.solicituddeserviciosfijosv2;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import java.io.IOException;

public class insertar extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    EditText cedulacliente;
    EditText nombrecliente;
    EditText apellidocliente;
    EditText fechasolicitud;
    EditText fechanacimiento;
    int año;
    int mes;
    int dia;
    Button elegirfecha;
    private static final int TIPO_DIALOGO=0;
    EditText correoelectronico;
    RadioGroup independiente;
    EditText empresa;
    EditText antiguedad;
    EditText salario;
    RadioButton si;
    RadioButton no;
    RadioButton confirmar;

    EditText cedulavendedor;
    EditText nombresvendedor;
    EditText apellidosvendedor;
    EditText direccioninstalacion;
    EditText ciudad;
    RadioGroup tipocasa;
    RadioButton casa;
    RadioButton apartamento;
    RadioButton confirmartc;
    EditText barrio;
    EditText bloqueapartamento;
    EditText numeroapartamento;
    EditText nombreconjunto;
    RadioGroup enviofactura;
    RadioButton correo;
    RadioButton fisico;
    RadioButton confirmaref;
    EditText nombresreferencia;
    EditText apellidosreferencia;
    EditText direccionreferencia;
    EditText telefonofijo;
    EditText telefonomovil;
    EditText ciudadreferencia;
    RadioGroup aplicaoferta;
    RadioButton si1;
    RadioButton no1;
    RadioButton confirmarap;
    EditText descripciont;
    EditText vigentat;
    RadioGroup lineanueva;
    RadioButton si2;
    RadioButton no2;
    RadioButton confirmarln;
    RadioGroup deseaserviciotelefoniafija;
    RadioButton si3;
    RadioButton no3;
    EditText planlinea;
    EditText valorlinea;
    RadioGroup nacional;
    RadioButton si5;
    RadioButton no5;
    RadioButton confirnacional;
    RadioGroup internacional;
    RadioButton si6;
    RadioButton no6;
    RadioButton confirinternacional;
    EditText valorlargadistancia;
    RadioGroup llamadaespera;
    RadioButton si7;
    RadioButton no7;
    RadioButton confirllamadaespera;
    RadioGroup identificador;
    RadioButton si8;
    RadioButton no8;
    RadioButton confiridentificador;
    RadioGroup buzon;
    RadioButton si9;
    RadioButton no9;
    RadioButton confirbuzon;
    CheckBox otros;
    EditText otrodigitar;
    EditText valorservicio;
    Button vertelefonia;
    EditText valortotalfija;
    RadioGroup desearserviciointernet;
    RadioButton si11;
    RadioButton no11;
    RadioGroup aplicaofertain;
    RadioButton si12;
    RadioButton no12;
    RadioButton confirmarapint;
    EditText descripcionint;
    EditText vigenciaint;
    RadioGroup velocidadnavegacion;
    RadioButton quincemegas;
    RadioButton diezmegas;
    RadioButton cincomegas;
    RadioButton tresmegas;
    RadioButton dosmegas;
    RadioButton noaplicaint;
    RadioButton confirvelocidad;
    EditText valornavegacion;
    RadioGroup serviciosadicionales;
    RadioButton si13;
    RadioButton no13;
    EditText valorcentrodeseguridad;
    EditText valoraula;
    RadioGroup otrosservicios;
    RadioButton si14;
    RadioButton no14;
    RadioButton confirotros;
    EditText valorotrosservicios;
    EditText valorserviciosadicionales;
    EditText valortotalinternet;
    Button vertotalin;
    RadioGroup deseaserviciotv;
    RadioButton si15;
    RadioButton no15;
    RadioGroup aplicaofertatv;
    RadioButton si16;
    RadioButton no16;
    RadioButton confiraplicatv;
    EditText descripciontv;
    EditText vigenciatv;
    EditText plantv;
    EditText valorplantv;
    RadioGroup cantidaddecos;
    RadioButton unotv;
    RadioButton dostv;
    RadioButton trestv;
    RadioButton noaplicatv1;
    RadioButton confircantidaddecos;
    EditText valorcudecos;
    EditText valortotaldecos;
    RadioGroup paquetenhd;
    RadioButton si17;
    RadioButton no17;
    RadioButton confirpaqhd;
    EditText planhd;
    EditText valorplanhd;
    RadioGroup cantidaddecoshd;
    RadioButton unohd;
    RadioButton doshd;
    RadioButton treshd;
    RadioButton noaplicatv2;
    RadioButton confircantidaddecoshd;
    EditText valorcudecoshd;
    EditText valortotaldecoshd;
    RadioGroup hbo;
    RadioButton si18;
    RadioButton no18;
    RadioButton confirhbo;
    RadioGroup moviecity;
    RadioButton si19;
    RadioButton no19;
    RadioButton confirmoviecity;
    RadioGroup plancine;
    RadioButton si20;
    RadioButton no20;
    RadioButton confirplancine;
    RadioGroup planadulto;
    RadioButton si22;
    RadioButton no22;
    RadioButton confirplanadulto;
    EditText valoradicionaltv;
    EditText valortotaltv;
    Button vervalortv;
    EditText totalpagarmensual;
    Button vertotalpagarmensual;


    String cedula;
    Bundle b;
    Button enviarregistro;
    HttpClient cliente;
    HttpPost post;
    List<NameValuePair> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);


        cedulacliente = (EditText) findViewById(R.id.etcedulacliente);
        nombrecliente = (EditText) findViewById(R.id.etnombrecliente);
        apellidocliente= (EditText) findViewById(R.id.etapellidocliente);
        fechasolicitud = (EditText) findViewById(R.id.etfechasolicitud);
        fechanacimiento =(EditText)findViewById(R.id.etfechanacimiento);
        Calendar calendario= Calendar.getInstance();
        año= calendario.get(Calendar.YEAR);
        mes= calendario.get(Calendar.MONTH);
        dia= calendario.get(Calendar.DAY_OF_MONTH);
        showDialog();
        correoelectronico=(EditText)findViewById(R.id.etcorreo);
        independiente =(RadioGroup) findViewById(R.id.rbindependiente);
        empresa=(EditText)findViewById(R.id.etempresa);
        antiguedad=(EditText)findViewById(R.id.etantiguedad);
        salario=(EditText)findViewById(R.id.etsalario);
        si=(RadioButton)findViewById(R.id.si);
        no=(RadioButton)findViewById(R.id.no);
        cedulavendedor=(EditText)findViewById(R.id.etcedulavendedor);
        nombresvendedor=(EditText)findViewById(R.id.etnombrevendedor);
        apellidosvendedor=(EditText)findViewById(R.id.etapellidovendedor);
        direccioninstalacion=(EditText)findViewById(R.id.etdireccioninstalacion);
        ciudad=(EditText)findViewById(R.id.etciudad);
        tipocasa=(RadioGroup)findViewById(R.id.rgtipocasa);
        casa=(RadioButton)findViewById(R.id.rbcasa);
        apartamento=(RadioButton)findViewById(R.id.rbapartamento);
        tipocasa.setOnCheckedChangeListener(this);
        barrio=(EditText)findViewById(R.id.etbarrio);
        bloqueapartamento=(EditText)findViewById(R.id.etbloqueapartamento);
        numeroapartamento=(EditText)findViewById(R.id.etnumeroapartamento);
        nombreconjunto=(EditText)findViewById(R.id.etnombreconjunto);
        enviofactura=(RadioGroup)findViewById(R.id.rgenviofactura);
        correo=(RadioButton)findViewById(R.id.rbcorreo);
        fisico=(RadioButton)findViewById(R.id.rbfisico);
        nombresreferencia=(EditText)findViewById(R.id.etnombrereferencia);
        apellidosreferencia=(EditText)findViewById(R.id.etapellidoreferencia);
        direccionreferencia=(EditText)findViewById(R.id.etdireccionreferencia);
        telefonofijo=(EditText)findViewById(R.id.ettelefonofijo);
        telefonomovil=(EditText)findViewById(R.id.ettelefonomovil);
        ciudadreferencia=(EditText)findViewById(R.id.etciudadreferencia);
        deseaserviciotelefoniafija=(RadioGroup)findViewById(R.id.rgserviciotelefonia);
        si3=(RadioButton)findViewById(R.id.si3);
        no3=(RadioButton)findViewById(R.id.no3);
        deseaserviciotelefoniafija.setOnCheckedChangeListener(this);
        aplicaoferta=(RadioGroup)findViewById(R.id.rgaplicaoferta);
        si1=(RadioButton)findViewById(R.id.si1);
        si1.setEnabled(false);
        no1=(RadioButton)findViewById(R.id.no1);
        no1.setEnabled(false);
        aplicaoferta.setOnCheckedChangeListener(this);
        descripciont=(EditText)findViewById(R.id.etdescripciont);
        vigentat=(EditText)findViewById(R.id.etvigenciat);
        descripciont.setEnabled(false);
        vigentat.setEnabled(false);
        lineanueva=(RadioGroup)findViewById(R.id.rglineanueva);
        si2=(RadioButton)findViewById(R.id.si2);
        si2.setEnabled(false);
        no2=(RadioButton)findViewById(R.id.no2);
        no2.setEnabled(false);
        planlinea=(EditText)findViewById(R.id.etplanlinea);
        planlinea.setEnabled(false);
        planlinea.setText("no aplica");
        valorlinea=(EditText)findViewById(R.id.etvalorlinea);
        valorlinea.setEnabled(false);
        nacional=(RadioGroup) findViewById(R.id.cbnacional);
        si5=(RadioButton)findViewById(R.id.si5);
        si5.setEnabled(false);
        no5=(RadioButton)findViewById(R.id.no5);
        no5.setEnabled(false);
        nacional.setOnCheckedChangeListener(this);
        internacional=(RadioGroup) findViewById(R.id.cbinternacional);
        si6=(RadioButton)findViewById(R.id.si6);
        si6.setEnabled(false);
        no6=(RadioButton)findViewById(R.id.no6);
        no6.setEnabled(false);
        internacional.setOnCheckedChangeListener(this);
        valorlargadistancia=(EditText) findViewById(R.id.etvalorlargadistancia);
        valorlargadistancia.setEnabled(false);
        llamadaespera=(RadioGroup) findViewById(R.id.cbllamadaespera);
        si7=(RadioButton)findViewById(R.id.si7);
        si7.setEnabled(false);
        no7=(RadioButton)findViewById(R.id.no7);
        no7.setEnabled(false);
        llamadaespera.setOnCheckedChangeListener(this);
        identificador=(RadioGroup) findViewById(R.id.cbidentificador);
        si8=(RadioButton)findViewById(R.id.si8);
        si8.setEnabled(false);
        no8=(RadioButton)findViewById(R.id.no8);
        no8.setEnabled(false);
        identificador.setOnCheckedChangeListener(this);
        buzon=(RadioGroup) findViewById(R.id.cbbuzon);
        si9=(RadioButton)findViewById(R.id.si9);
        si9.setEnabled(false);
        no9=(RadioButton)findViewById(R.id.no9);
        no9.setEnabled(false);
        buzon.setOnCheckedChangeListener(this);
        otros=(CheckBox)findViewById(R.id.cbotros);
        otros.setEnabled(false);
        otrodigitar=(EditText)findViewById(R.id.etotros);
        otrodigitar.setEnabled(false);
        otrodigitar.setText("no aplica");
        otros.setOnCheckedChangeListener(new myCheckBoxChnageClicker());
        valorservicio=(EditText)findViewById(R.id.etvalorservicios);
        valorservicio.setEnabled(false);
        valortotalfija=(EditText)findViewById(R.id.etvalortotalfija);
        valortotalfija.setEnabled(false);

        vertelefonia=(Button)findViewById(R.id.vertelefonia);
        vertelefonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorlinea.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor linea", Toast.LENGTH_LONG).show();
                }else  if (valorlargadistancia.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor larga distancia", Toast.LENGTH_LONG).show();
                }else if (valorservicio.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor servicios adicionales linea fija", Toast.LENGTH_LONG).show();
                }else
                sumatelefoniafija();
            }
        });

        desearserviciointernet=(RadioGroup)findViewById(R.id.rgserviciointernet);
        si11=(RadioButton)findViewById(R.id.si11);
        no11=(RadioButton)findViewById(R.id.no11);
        desearserviciointernet.setOnCheckedChangeListener(this);
        aplicaofertain=(RadioGroup)findViewById(R.id.rgaplicaofertainternet);
        si12=(RadioButton)findViewById(R.id.si12);
        si12.setEnabled(false);
        no12=(RadioButton)findViewById(R.id.no12);
        no12.setEnabled(false);
        aplicaofertain.setOnCheckedChangeListener(this);
        descripcionint=(EditText)findViewById(R.id.etdescripcionin);
        vigenciaint=(EditText)findViewById(R.id.etvigenciain);
        descripcionint.setEnabled(false);
        vigenciaint.setEnabled(false);
        velocidadnavegacion=(RadioGroup)findViewById(R.id.rgvelocidadnavegacion);
        quincemegas=(RadioButton)findViewById(R.id.quince);
        quincemegas.setEnabled(false);
        diezmegas=(RadioButton)findViewById(R.id.diez);
        diezmegas.setEnabled(false);
        cincomegas=(RadioButton)findViewById(R.id.cinco);
        cincomegas.setEnabled(false);
        tresmegas=(RadioButton)findViewById(R.id.tres);
        tresmegas.setEnabled(false);
        dosmegas=(RadioButton)findViewById(R.id.dos);
        dosmegas.setEnabled(false);
        noaplicaint=(RadioButton)findViewById(R.id.noaplicaint);
        noaplicaint.setEnabled(false);
        valornavegacion=(EditText)findViewById(R.id.etvalornavegacion);
        valornavegacion.setEnabled(false);
        serviciosadicionales=(RadioGroup)findViewById(R.id.rgserviciosadicionales);
        si13=(RadioButton)findViewById(R.id.si13);
        si13.setEnabled(false);
        no13=(RadioButton)findViewById(R.id.no13);
        no13.setEnabled(false);
        serviciosadicionales.setOnCheckedChangeListener(this);
        valorcentrodeseguridad=(EditText)findViewById(R.id.etcentroseguridad);
        valorcentrodeseguridad.setEnabled(false);
        valoraula=(EditText)findViewById(R.id.etaula);
        valoraula.setEnabled(false);
        otrosservicios=(RadioGroup)findViewById(R.id.rgotros);
        si14=(RadioButton)findViewById(R.id.si14);
        si14.setEnabled(false);
        no14=(RadioButton)findViewById(R.id.no14);
        no14.setEnabled(false);
        otrosservicios.setOnCheckedChangeListener(this);
        valorotrosservicios=(EditText)findViewById(R.id.etotrosin);
        valorotrosservicios.setEnabled(false);
        valorserviciosadicionales=(EditText)findViewById(R.id.etserviciosadicionales);
        valortotalinternet=(EditText)findViewById(R.id.ettotalinternet);
        vertotalin=(Button)findViewById(R.id.btvalortotalin);
        vertotalin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valornavegacion.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor navegacion", Toast.LENGTH_LONG).show();
                }else  if (valorcentrodeseguridad.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor centro de seguridad", Toast.LENGTH_LONG).show();
                }else  if (valoraula.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor aula 365", Toast.LENGTH_LONG).show();
                }else  if (valorotrosservicios.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor aula 365", Toast.LENGTH_LONG).show();
                }else
                sumainternet();
            }
        });

        deseaserviciotv=(RadioGroup)findViewById(R.id.rgdeseaserviciotv);
        si15=(RadioButton)findViewById(R.id.si15);
        no15=(RadioButton)findViewById(R.id.no15);
        deseaserviciotv.setOnCheckedChangeListener(this);
        aplicaofertatv=(RadioGroup)findViewById(R.id.rgaplicaofertatv);
        si16=(RadioButton)findViewById(R.id.si16);
        si16.setEnabled(false);
        no16=(RadioButton)findViewById(R.id.no16);
        no16.setEnabled(false);
        aplicaofertatv.setOnCheckedChangeListener(this);
        descripciontv=(EditText)findViewById(R.id.etdescripciontv);
        descripciontv.setEnabled(false);
        vigenciatv=(EditText)findViewById(R.id.etvigenciatv);
        vigenciatv.setEnabled(false);
        plantv=(EditText)findViewById(R.id.etplantv);
        plantv.setEnabled(false);
        valorplantv=(EditText)findViewById(R.id.etvalorplantv);
        valorplantv.setEnabled(false);
        cantidaddecos=(RadioGroup)findViewById(R.id.rgcantidaddecos);
        unotv=(RadioButton)findViewById(R.id.rbuno);
        unotv.setEnabled(false);
        dostv=(RadioButton)findViewById(R.id.rbdos);
        dostv.setEnabled(false);
        trestv=(RadioButton)findViewById(R.id.rbtres);
        trestv.setEnabled(false);
        noaplicatv1=(RadioButton)findViewById(R.id.noaplicatv1);
        noaplicatv1.setEnabled(false);
        valorcudecos=(EditText)findViewById(R.id.etvalorcudecos);
        valorcudecos.setEnabled(false);
        valortotaldecos=(EditText)findViewById(R.id.etvalortotaldecos);
        valortotaldecos.setEnabled(false);
        paquetenhd=(RadioGroup)findViewById(R.id.rgpaquetehd);
        si17=(RadioButton)findViewById(R.id.si17);
        si17.setEnabled(false);
        no17=(RadioButton)findViewById(R.id.no17);
        no17.setEnabled(false);
        paquetenhd.setOnCheckedChangeListener(this);
        planhd=(EditText)findViewById(R.id.etplanhd);
        planhd.setEnabled(false);
        valorplanhd=(EditText)findViewById(R.id.etvalorhd);
        valorplanhd.setEnabled(false);
        cantidaddecoshd=(RadioGroup)findViewById(R.id.rgcantidaddecoshd);
        unohd=(RadioButton)findViewById(R.id.uno1);
        unohd.setEnabled(false);
        doshd=(RadioButton)findViewById(R.id.dos1);
        doshd.setEnabled(false);
        treshd=(RadioButton)findViewById(R.id.tres1);
        treshd.setEnabled(false);
        noaplicatv2=(RadioButton)findViewById(R.id.noaplicatv2);
        noaplicatv2.setEnabled(false);
        valorcudecoshd=(EditText)findViewById(R.id.etvalorcudecoshd);
        valorcudecoshd.setEnabled(false);
        valortotaldecoshd=(EditText)findViewById(R.id.etvalortotaldecohd);
        valortotaldecoshd.setEnabled(false);
        hbo=(RadioGroup)findViewById(R.id.rghbo);
        si18=(RadioButton)findViewById(R.id.si19);
        si18.setEnabled(false);
        no18=(RadioButton)findViewById(R.id.no19);
        no18.setEnabled(false);
        hbo.setOnCheckedChangeListener(this);
        moviecity=(RadioGroup)findViewById(R.id.rgmoviecity);
        si19=(RadioButton)findViewById(R.id.si20);
        si19.setEnabled(false);
        no19=(RadioButton)findViewById(R.id.no20);
        no19.setEnabled(false);
        moviecity.setOnCheckedChangeListener(this);
        plancine=(RadioGroup) findViewById(R.id.rgplancine);
        si20=(RadioButton)findViewById(R.id.si21);
        si20.setEnabled(false);
        no20=(RadioButton)findViewById(R.id.no21);
        no20.setEnabled(false);
        plancine.setOnCheckedChangeListener(this);
        planadulto=(RadioGroup)findViewById(R.id.rgcanaladulto);
        si22=(RadioButton)findViewById(R.id.si22);
        si22.setEnabled(false);
        no22=(RadioButton)findViewById(R.id.no22);
        no22.setEnabled(false);
        planadulto.setOnCheckedChangeListener(this);
        valoradicionaltv=(EditText)findViewById(R.id.etvaloradicionestv);
        valoradicionaltv.setEnabled(false);
        valortotaltv=(EditText)findViewById(R.id.etvalortotaltv);
        valortotaltv.setEnabled(false);
        vervalortv=(Button)findViewById(R.id.btvertotaltv);
        vervalortv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valorplantv.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor plan de television", Toast.LENGTH_LONG).show();
                }else  if (valorcudecos.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor C/U decos", Toast.LENGTH_LONG).show();
                }else  if (valorplanhd.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor plan HD", Toast.LENGTH_LONG).show();
                }else  if (valorcudecoshd.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor C/U decos HD", Toast.LENGTH_LONG).show();
                }else  if (valoradicionaltv.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Digite valor adicional tv", Toast.LENGTH_LONG).show();
                }else
                sumatv();
            }
        });
        totalpagarmensual=(EditText)findViewById(R.id.ettotalpagar);
        vertotalpagarmensual=(Button)findViewById(R.id.btvermensual);
        vertotalpagarmensual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valortotalfija.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Presione boton ver total linea fija ", Toast.LENGTH_LONG).show();
                }else  if (valortotalinternet.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Presione boton ver total internet", Toast.LENGTH_LONG).show();
                }else  if (valortotaltv.getText().toString().equals("")) {
                    Toast.makeText(insertar.this, "Presione boton ver total television", Toast.LENGTH_LONG).show();
                }else sumtotal();
            }
        });


        long ahora = System.currentTimeMillis();
        Date fecha = new Date(ahora);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String salida = df.format(fecha);
        fechasolicitud.setText(salida);

        enviarregistro= (Button)findViewById(R.id.enviar);
        b=getIntent().getExtras();
        cedula=b.getString("cedula");
        cedulacliente.setText(cedula);


        enviarregistro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int seleccion = independiente.getCheckedRadioButtonId();
                int selecciontc= tipocasa.getCheckedRadioButtonId();
                int seleccionef= enviofactura.getCheckedRadioButtonId();
                int seleccionap= aplicaoferta.getCheckedRadioButtonId();
                int seleccionln= lineanueva.getCheckedRadioButtonId();
                int selecciona = nacional.getCheckedRadioButtonId();
                int seleccioninter= internacional.getCheckedRadioButtonId();
                int seleccionlla = llamadaespera.getCheckedRadioButtonId();
                int seleccionide= identificador.getCheckedRadioButtonId();
                int seleccionbu= buzon.getCheckedRadioButtonId();
                int seleccionapint=aplicaofertain.getCheckedRadioButtonId();
                int seleccionvel=velocidadnavegacion.getCheckedRadioButtonId();
                int seleccionotros=otrosservicios.getCheckedRadioButtonId();
                int seleccionaptv=aplicaofertatv.getCheckedRadioButtonId();
                int seleccioncantdecos=cantidaddecos.getCheckedRadioButtonId();
                int seleccionpaqhd=paquetenhd.getCheckedRadioButtonId();
                int seleccioncantdecoshd=(cantidaddecoshd).getCheckedRadioButtonId();
                int seleccionhbo=hbo.getCheckedRadioButtonId();
                int seleccionmoviecity=moviecity.getCheckedRadioButtonId();
                int seleccionplancine=plancine.getCheckedRadioButtonId();
                int seleccionplanadulto=planadulto.getCheckedRadioButtonId();

                confirmar= (RadioButton) findViewById(seleccion);
                confirmartc=(RadioButton)findViewById(selecciontc);
                confirmaref=(RadioButton)findViewById(seleccionef);
                confirmarap=(RadioButton)findViewById(seleccionap);
                confirmarln=(RadioButton)findViewById(seleccionln);
                confirnacional=(RadioButton)findViewById(selecciona);
                confirinternacional=(RadioButton)findViewById(seleccioninter);
                confirllamadaespera=(RadioButton)findViewById(seleccionlla);
                confiridentificador=(RadioButton)findViewById(seleccionide);
                confirbuzon=(RadioButton)findViewById(seleccionbu);
                confirmarapint=(RadioButton)findViewById(seleccionapint);
                confirvelocidad=(RadioButton)findViewById(seleccionvel);
                confirotros=(RadioButton)findViewById(seleccionotros);
                confiraplicatv=(RadioButton)findViewById(seleccionaptv);
                confircantidaddecos=(RadioButton)findViewById(seleccioncantdecos);
                confirpaqhd=(RadioButton)findViewById(seleccionpaqhd);
                confircantidaddecoshd=(RadioButton)findViewById(seleccioncantdecoshd);
                confirhbo=(RadioButton)findViewById(seleccionhbo);
                confirmoviecity=(RadioButton)findViewById(seleccionmoviecity);
                confirplancine=(RadioButton)findViewById(seleccionplancine);
                confirplanadulto=(RadioButton)findViewById(seleccionplanadulto);

                espaciosblanco();

                {


                }




            }
        });




    }

    public void showDialog(){
        elegirfecha=(Button)findViewById(R.id.fecha);
        elegirfecha.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(TIPO_DIALOGO);
                    }
                }

        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == TIPO_DIALOGO){
            return new DatePickerDialog(this,escogerfecha,año,mes,dia);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener escogerfecha
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            año=year;
            mes=monthOfYear+1;
            dia=dayOfMonth;

            fechanacimiento.setText((dia)+"/"+(mes)+"/"+(año));
        }
    };



    public void mostrarcalendario(View control){
        showDialog(TIPO_DIALOGO);

    }

    public void sumatelefoniafija(){

        String sumvalorlinea=valorlinea.getText().toString();
        String sumvalorlargadistancia=valorlargadistancia.getText().toString();
        String sumvaloradicional=valorservicio.getText().toString();
        int valor1=Integer.parseInt(sumvalorlinea);
        int valor2=Integer.parseInt(sumvalorlargadistancia);
        int valor3=Integer.parseInt(sumvaloradicional);
        int totallineafija= valor1+valor2+valor3;
        String resultado=String.valueOf(totallineafija);

        valortotalfija.setText(resultado);
    }

    public void sumainternet(){

        String sumvalornavegacion=valornavegacion.getText().toString();
        String sumvalorcentroseguridad=valorcentrodeseguridad.getText().toString();
        String sumvaloraula=valoraula.getText().toString();
        String sumvalorotros=valorotrosservicios.getText().toString();
        String sumadicionalesin=valorserviciosadicionales.getText().toString();

        int valor1in=Integer.parseInt(sumvalornavegacion);
        int valor2in=Integer.parseInt(sumvalorcentroseguridad);
        int valor3in=Integer.parseInt(sumvaloraula);
        int valor4in=Integer.parseInt(sumvalorotros);


        int totaladicionales=valor2in+valor3in+valor4in;
        String resultadoadicionales=String.valueOf(totaladicionales);
        int totalsuminternet= valor1in+totaladicionales;
        String resultadointernet=String.valueOf(totalsuminternet);
        valorserviciosadicionales.setText(resultadoadicionales);
        valortotalinternet.setText(resultadointernet);
    }

    public void sumatv(){

        String sumvalorcudecos=valorcudecos.getText().toString();
        String sumvalorcudecoshd=valorcudecoshd.getText().toString();
        int valor2tv=Integer.parseInt(sumvalorcudecos);
        int valor5tv=Integer.parseInt(sumvalorcudecoshd);
        int sumvalor2tv;
        int sumvalor5tv;
        String resultvalor2tv;
        String resultvalor5tv;

        if(unotv.isChecked()){
            sumvalor2tv=valor2tv*1;
            resultvalor2tv=String.valueOf(sumvalor2tv);
            valortotaldecos.setText(resultvalor2tv);
        }
        else
        if(dostv.isChecked()){
            sumvalor2tv=valor2tv*2;
            resultvalor2tv=String.valueOf(sumvalor2tv);
            valortotaldecos.setText(resultvalor2tv);
        }
        else
        if(trestv.isChecked()){
            sumvalor2tv=valor2tv*3;
            resultvalor2tv=String.valueOf(sumvalor2tv);
            valortotaldecos.setText(resultvalor2tv);
        }

        if(unohd.isChecked()){
            sumvalor5tv=valor5tv*1;
            resultvalor5tv=String.valueOf(sumvalor5tv);
            valortotaldecoshd.setText(resultvalor5tv);
        }
        else
        if(doshd.isChecked()){
            sumvalor5tv=valor5tv*2;
            resultvalor5tv=String.valueOf(sumvalor5tv);
            valortotaldecoshd.setText(resultvalor5tv);

        }
        else
        if(treshd.isChecked()){
            sumvalor5tv=valor5tv*3;
            resultvalor5tv=String.valueOf(sumvalor5tv);
            valortotaldecoshd.setText(resultvalor5tv);

        }

        String sumvalortv=valorplantv.getText().toString();
        String finalsumvalorcudecos=valorcudecos.getText().toString();
        String sumvalortotaldecos=valortotaldecos.getText().toString();
        String sumvalorplanhd=valorplanhd.getText().toString();
        String finalsumvalorcudecoshd=valorcudecoshd.getText().toString();
        String sumvalortotaldecoshd=valortotaldecoshd.getText().toString();
        String sumvaloradicionaltv=valoradicionaltv.getText().toString();

        int valor1tv=Integer.parseInt(sumvalortv);
        int valor3tv=Integer.parseInt(sumvalortotaldecos);
        int valor4tv=Integer.parseInt(sumvalorplanhd);
        int valor6tv=Integer.parseInt(sumvalortotaldecoshd);
        int valor7tv=Integer.parseInt(sumvaloradicionaltv);


        int sumvalortotaltv=valor1tv+valor3tv+valor4tv+valor6tv+valor7tv;
        String resultadototaltv=String.valueOf(sumvalortotaltv);
        valortotaltv.setText(resultadototaltv);

    }


    public void sumtotal(){

        String totallinea=valortotalfija.getText().toString();
        String totalinternet=valortotalinternet.getText().toString();
        String totaltelevision=valortotaltv.getText().toString();

        int total1=Integer.parseInt(totallinea);
        int total2=Integer.parseInt(totalinternet);
        int total3=Integer.parseInt(totaltelevision);
        int sumtotal= total1+total2+total3;
        String resultadototalpagar=String.valueOf(sumtotal);

        totalpagarmensual.setText(resultadototalpagar);

    }




    @Override
    public void onClick(View v) {

    }



    class myCheckBoxChnageClicker implements CheckBox.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

            if(otros.isChecked()){
                otrodigitar.setEnabled(true);
                otrodigitar.setText("");
            }
            if(!otros.isChecked()){
                otrodigitar.setEnabled(false);
                otrodigitar.setText("no aplica");
            }

        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        switch (checkedId){

            case R.id.rbcasa:
                bloqueapartamento.setText("no aplica");
                numeroapartamento.setText("no aplica");
                nombreconjunto.setText("no aplica");
                bloqueapartamento.setEnabled(false);
                numeroapartamento.setEnabled(false);
                nombreconjunto.setEnabled(false);
                break;

            case R.id.rbapartamento:
                bloqueapartamento.setText("");
                numeroapartamento.setText("");
                nombreconjunto.setText("");
                bloqueapartamento.setEnabled(true);
                numeroapartamento.setEnabled(true);
                nombreconjunto.setEnabled(true);
                break;

            case R.id.si1:
                descripciont.setEnabled(true);
                vigentat.setEnabled(true);
                descripciont.setText("");
                vigentat.setText("");


                break;

            case R.id.no1:
                descripciont.setText("no aplica");
                vigentat.setText("no aplica");
                descripciont.setEnabled(false);
                vigentat.setEnabled(false);

                break;

            case R.id.si3:
                valorlinea.setEnabled(true);
                valorlinea.setText("");
                planlinea.setText("Telefonia fija");
                planlinea.setEnabled(true);
                valortotalfija.setEnabled(true);
                valortotalfija.setText("");
                si1.setEnabled(true);
                no1.setEnabled(true);
                si2.setEnabled(true);
                no2.setEnabled(true);
                si5.setEnabled(true);
                no5.setEnabled(true);
                si6.setEnabled(true);
                no6.setEnabled(true);
                si7.setEnabled(true);
                no7.setEnabled(true);
                si8.setEnabled(true);
                no8.setEnabled(true);
                si9.setEnabled(true);
                no9.setEnabled(true);
                otros.setEnabled(true);
                 break;

            case R.id.no3:
                valorlinea.setEnabled(false);
                valorlinea.setText("0");
                planlinea.setText("no aplica");
                planlinea.setEnabled(false);
                valortotalfija.setEnabled(false);
                valortotalfija.setText("0");
                si1.setEnabled(false);
                no1.setEnabled(false);
                si2.setEnabled(false);
                no2.setEnabled(false);
                si5.setEnabled(false);
                no5.setEnabled(false);
                si6.setEnabled(false);
                no6.setEnabled(false);
                si7.setEnabled(false);
                no7.setEnabled(false);
                si8.setEnabled(false);
                no8.setEnabled(false);
                si9.setEnabled(false);
                no9.setEnabled(false);
                otros.setEnabled(false);
                break;

            case R.id.si5:
                if(si5.isChecked()|| si6.isChecked()){
                    valorlargadistancia.setText("");
                    valorlargadistancia.setEnabled(true);

                }
                break;

            case R.id.si6:
                if(si5.isChecked()|| si6.isChecked()){
                    valorlargadistancia.setText("");
                    valorlargadistancia.setEnabled(true);

                }
                break;

            case R.id.no5:
                if(no5.isChecked()&& no6.isChecked()){
                    valorlargadistancia.setText("0");
                    valorlargadistancia.setEnabled(false);
                }

                break;

            case R.id.no6:
                if(no5.isChecked()&& no6.isChecked()){
                    valorlargadistancia.setText("0");
                    valorlargadistancia.setEnabled(false);
                }

                break;
            case R.id.no7:
                if(no7.isChecked() || no8.isChecked()|| no9.isChecked()){
                    valorservicio.setText("0");
                    valorservicio.setEnabled(false);
                }

                break;
            case R.id.no8:
                if(no7.isChecked() || no8.isChecked()|| no9.isChecked()){
                    valorservicio.setText("0");
                    valorservicio.setEnabled(false);
                }

                break;
            case R.id.no9:
                if(no7.isChecked() || no8.isChecked()|| no9.isChecked()){
                    valorservicio.setText("0");
                    valorservicio.setEnabled(false);
                }

                break;
            case R.id.si7:
                if(si7.isChecked() || si8.isChecked()|| si9.isChecked()){
                    valorservicio.setText("");
                    valorservicio.setEnabled(true);
                }
                break;
            case R.id.si8:
                if(si7.isChecked() || si8.isChecked()|| si9.isChecked()){
                    valorservicio.setText("");
                    valorservicio.setEnabled(true);
                }
                break;
            case R.id.si9:
                if(si7.isChecked() || si8.isChecked()|| si9.isChecked()){
                    valorservicio.setText("");
                    valorservicio.setEnabled(true);
                }
                break;
            case R.id.si11:
                valornavegacion.setText("");
                valornavegacion.setEnabled(true);
                valortotalinternet.setText("");
                quincemegas.setEnabled(true);
                diezmegas.setEnabled(true);
                cincomegas.setEnabled(true);
                tresmegas.setEnabled(true);
                dosmegas.setEnabled(true);
                dosmegas.setChecked(true);
                si12.setEnabled(true);
                no12.setEnabled(true);
                si13.setEnabled(true);
                no13.setEnabled(true);
                si14.setEnabled(true);
                no14.setEnabled(true);

                break;
            case R.id.no11:
                valornavegacion.setText("0");
                valornavegacion.setEnabled(false);
                valortotalinternet.setText("0");
                quincemegas.setEnabled(false);
                diezmegas.setEnabled(false);
                cincomegas.setEnabled(false);
                tresmegas.setEnabled(false);
                dosmegas.setEnabled(false);
                noaplicaint.setChecked(true);
                si12.setEnabled(false);
                no12.setEnabled(false);
                si13.setEnabled(false);
                no13.setEnabled(false);
                si14.setEnabled(false);
                no14.setEnabled(false);
                break;
            case R.id.si12:
                descripcionint.setText("");
                descripcionint.setEnabled(true);
                vigenciaint.setText("");
                vigenciaint.setEnabled(true);
                break;
            case R.id.no12:
                descripcionint.setText("no aplica");
                descripcionint.setEnabled(false);
                vigenciaint.setText("no aplica");
                vigenciaint.setEnabled(false);
                break;
            case R.id.si13:
                valorcentrodeseguridad.setText("");
                valorcentrodeseguridad.setEnabled(true);
                valoraula.setText("");
                valoraula.setEnabled(true);
                if(si13.isChecked()||si14.isChecked()){
                    valorserviciosadicionales.setText("");
                }
                break;

            case R.id.no13:
                valorcentrodeseguridad.setText("0");
                valorcentrodeseguridad.setEnabled(false);
                valoraula.setText("0");
                valoraula.setEnabled(false);
                if(no13.isChecked()&& no14.isChecked()){
                    valorserviciosadicionales.setText("0");
                }
                break;
            case R.id.si14:
                valorotrosservicios.setText("");
                valorotrosservicios.setEnabled(true);
                if(si13.isChecked()||si14.isChecked()){
                    valorserviciosadicionales.setText("");

                }
                break;
            case R.id.no14:
                valorotrosservicios.setText("0");
                valorotrosservicios.setEnabled(false);
                if(no13.isChecked()&& no14.isChecked()){
                    valorserviciosadicionales.setText("0");
                }
                break;

            case R.id.si15:
                si16.setEnabled(true);
                no16.setEnabled(true);
                plantv.setText("");
                plantv.setEnabled(true);
                valorplantv.setText("");
                valorplantv.setEnabled(true);
                valorcudecos.setText("");
                valorcudecos.setEnabled(true);
                valortotaldecos.setText("");
                valortotaldecos.setEnabled(true);
                si17.setEnabled(true);
                no17.setEnabled(true);
                valortotaltv.setText("");
                unotv.setEnabled(true);
                unotv.setChecked(true);
                dostv.setEnabled(true);
                trestv.setEnabled(true);
                si18.setEnabled(true);
                no18.setEnabled(true);
                si19.setEnabled(true);
                no19.setEnabled(true);
                si20.setEnabled(true);
                no20.setEnabled(true);
                si22.setEnabled(true);
                no22.setEnabled(true);

                break;

            case R.id.no15:
                si16.setEnabled(false);
                no16.setEnabled(false);
                plantv.setText("no aplica");
                plantv.setEnabled(false);
                valorplantv.setText("0");
                valorplantv.setEnabled(false);
                valorcudecos.setText("0");
                valorcudecos.setEnabled(false);
                valortotaldecos.setText("0");
                valortotaldecos.setEnabled(false);
                si17.setEnabled(false);
                no17.setEnabled(false);
                valortotaltv.setText("0");
                unotv.setEnabled(false);
                dostv.setEnabled(false);
                trestv.setEnabled(false);
                noaplicatv1.setChecked(true);
                si18.setEnabled(false);
                no18.setEnabled(false);
                si19.setEnabled(false);
                no19.setEnabled(false);
                si20.setEnabled(false);
                no20.setEnabled(false);
                si22.setEnabled(false);
                no22.setEnabled(false);
                break;

            case R.id.si16:
                descripciontv.setText("");
                descripciontv.setEnabled(true);
                vigenciatv.setText("");
                vigenciatv.setEnabled(true);
                break;

            case R.id.no16:
                descripciontv.setText("no aplica");
                descripciontv.setEnabled(false);
                vigenciatv.setText("no aplica");
                vigenciatv.setEnabled(false);
                break;

            case R.id.si17:
                planhd.setText("");
                planhd.setEnabled(true);
                unohd.setEnabled(true);
                unohd.setChecked(true);
                doshd.setEnabled(true);
                treshd.setEnabled(true);
                valorplanhd.setText("");
                valorplanhd.setEnabled(true);
                valorcudecoshd.setText("");
                valorcudecoshd.setEnabled(true);
                valortotaldecoshd.setText("");
                valortotaldecoshd.setEnabled(true);
                break;

            case R.id.no17:
                planhd.setText("no aplica");
                planhd.setEnabled(false);
                valorplanhd.setText("0");
                unohd.setEnabled(false);
                doshd.setEnabled(false);
                treshd.setEnabled(false);
                noaplicatv2.setChecked(true);
                valorplanhd.setEnabled(false);
                valorcudecoshd.setText("0");
                valorcudecoshd.setEnabled(false);
                valortotaldecoshd.setText("0");
                valortotaldecoshd.setEnabled(false);
                break;
            case R.id.si19:
                if(si18.isChecked()||si19.isChecked()||si20.isChecked()||si22.isChecked()){
                    valoradicionaltv.setText("");
                    valoradicionaltv.setEnabled(true);
                }
                break;
            case R.id.si20:
                if(si18.isChecked()||si19.isChecked()||si20.isChecked()||si22.isChecked()){
                    valoradicionaltv.setText("");
                    valoradicionaltv.setEnabled(true);
                }
                break;
            case R.id.si21:
                if(si18.isChecked()||si19.isChecked()||si20.isChecked()||si22.isChecked()){
                    valoradicionaltv.setText("");
                    valoradicionaltv.setEnabled(true);
                }

                break;
            case R.id.si22:
                if(si18.isChecked()||si19.isChecked()||si20.isChecked()||si22.isChecked()){
                    valoradicionaltv.setText("");
                    valoradicionaltv.setEnabled(true);
                }

                break;
            case R.id.no19:
                if(no18.isChecked()&&no19.isChecked()&&no20.isChecked()&&no22.isChecked()){
                    valoradicionaltv.setText("0");
                    valoradicionaltv.setEnabled(false);
                }

                break;
            case R.id.no20:
                if(no18.isChecked()&&no19.isChecked()&&no20.isChecked()&&no22.isChecked()){
                    valoradicionaltv.setText("0");
                    valoradicionaltv.setEnabled(false);
                }
                break;
            case R.id.no21:
                if(no18.isChecked()&&no19.isChecked()&&no20.isChecked()&&no22.isChecked()){
                    valoradicionaltv.setText("0");
                    valoradicionaltv.setEnabled(false);
                }
                break;
            case R.id.no22:
                if(no18.isChecked()&&no19.isChecked()&&no20.isChecked()&&no22.isChecked()){
                    valoradicionaltv.setText("0");
                    valoradicionaltv.setEnabled(false);
                }
                break;





        }


    }

    class EnviarRegistro extends AsyncTask<String, String , String>{


        private Activity texto;


        EnviarRegistro(Activity accion){

            this.texto= accion;

        }

        @Override
        protected String doInBackground(String... params) {
            if(datos())
            {
                texto.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(texto,"Datos enviados correctamente",Toast.LENGTH_SHORT).show();
                        cedulacliente.setText("");
                        nombrecliente.setText("");
                        apellidocliente.setText("");
                        fechasolicitud.setText("");
                        fechanacimiento.setText("");
                        correoelectronico.setText("");
                    /*    cedulavendedor.setText("");
                        nombresvendedor.setText("");
                        apellidosvendedor.setText("");*/


                        Intent devolver= new Intent(insertar.this, MainActivity.class);
                        startActivity(devolver);


                    }
                });
            }

            else {

                texto.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(texto,"Datos no enviados",Toast.LENGTH_SHORT).show();

                    }
                });

            }
            return null;
        }


    }

    private boolean datos()
    {
        cliente = new DefaultHttpClient();
        post = new HttpPost("http://tesisdaniel.net16.net/insertar.php");
        lista = new ArrayList<NameValuePair>(7);
        lista.add(new BasicNameValuePair("cedula_cliente",cedulacliente.getText().toString().trim()));
        lista.add(new BasicNameValuePair("nombre_cliente",nombrecliente.getText().toString().trim()));
        lista.add(new BasicNameValuePair("apellido_cliente",apellidocliente.getText().toString().trim()));
        lista.add(new BasicNameValuePair("fecha_solicitud",fechasolicitud.getText().toString().trim()));
        lista.add(new BasicNameValuePair("fecha_nacimiento",fechanacimiento.getText().toString().trim()));
        lista.add(new BasicNameValuePair("correo_electronico",correoelectronico.getText().toString().trim()));
        lista.add(new BasicNameValuePair("independiente",confirmar.getText().toString().trim()));
        lista.add(new BasicNameValuePair("empresa",empresa.getText().toString().trim()));
        lista.add(new BasicNameValuePair("antiguedad",antiguedad.getText().toString().trim()));
        lista.add(new BasicNameValuePair("salario",salario.getText().toString().trim()));
        lista.add(new BasicNameValuePair("cedula_vendedor",cedulavendedor.getText().toString().trim()));
        lista.add(new BasicNameValuePair("nombres_vendedor",nombresvendedor.getText().toString().trim()));
        lista.add(new BasicNameValuePair("apellidos_vendedor",apellidosvendedor.getText().toString().trim()));
        lista.add(new BasicNameValuePair("direccion_instalacion",direccioninstalacion.getText().toString().trim()));
        lista.add(new BasicNameValuePair("ciudad",ciudad.getText().toString().trim()));
        lista.add(new BasicNameValuePair("tipo_casa",confirmartc.getText().toString().trim()));
        lista.add(new BasicNameValuePair("barrio",barrio.getText().toString().trim()));
        lista.add(new BasicNameValuePair("bloque_apartamento",bloqueapartamento.getText().toString().trim()));
        lista.add(new BasicNameValuePair("numero_apartamento",numeroapartamento.getText().toString().trim()));
        lista.add(new BasicNameValuePair("nombre_conjunto",nombreconjunto.getText().toString().trim()));
        lista.add(new BasicNameValuePair("envio_factura",confirmaref.getText().toString().trim()));
        lista.add(new BasicNameValuePair("nombre1_referencia",nombresreferencia.getText().toString().trim()));
        lista.add(new BasicNameValuePair("apellido1_referencia",apellidosreferencia.getText().toString().trim()));
        lista.add(new BasicNameValuePair("direccion1_referencia",direccionreferencia.getText().toString().trim()));
        lista.add(new BasicNameValuePair("telefono1_fijo",telefonofijo.getText().toString().trim()));
        lista.add(new BasicNameValuePair("telefono1_movil",telefonomovil.getText().toString().trim()));
        lista.add(new BasicNameValuePair("ciudad1",ciudadreferencia.getText().toString().trim()));
        lista.add(new BasicNameValuePair("aplica_ofertat",confirmarap.getText().toString().trim()));
        lista.add(new BasicNameValuePair("descripciont",descripciont.getText().toString().trim()));
        lista.add(new BasicNameValuePair("vigenciat",vigentat.getText().toString().trim()));
        lista.add(new BasicNameValuePair("linea_nueva",confirmarln.getText().toString().trim()));
        lista.add(new BasicNameValuePair("plan_linea",planlinea.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_linea",valorlinea.getText().toString().trim()));
        lista.add(new BasicNameValuePair("nacional",confirnacional.getText().toString().trim()));
        lista.add(new BasicNameValuePair("internacional",confirinternacional.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_adicional1",valorlargadistancia.getText().toString().trim()));
        lista.add(new BasicNameValuePair("llamada_espera",confirllamadaespera.getText().toString().trim()));
        lista.add(new BasicNameValuePair("identificador_llamada",confiridentificador.getText().toString().trim()));
        lista.add(new BasicNameValuePair("buzon",confirbuzon.getText().toString().trim()));
        lista.add(new BasicNameValuePair("otros",otrodigitar.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_adicional2",valorservicio.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_totalfija",valortotalfija.getText().toString().trim()));
        lista.add(new BasicNameValuePair("aplica_ofertain",confirmarapint.getText().toString().trim()));
        lista.add(new BasicNameValuePair("descripcionin",descripcionint.getText().toString().trim()));
        lista.add(new BasicNameValuePair("vigenciain",vigenciaint.getText().toString().trim()));
        lista.add(new BasicNameValuePair("velocidad_navegacion",confirvelocidad.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_navegacion",valornavegacion.getText().toString().trim()));
        lista.add(new BasicNameValuePair("centro_seguridad",valorcentrodeseguridad.getText().toString().trim()));
        lista.add(new BasicNameValuePair("aula_365",valoraula.getText().toString().trim()));
        lista.add(new BasicNameValuePair("otrosin",confirotros.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valorotros",valorotrosservicios.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_adicionalin",valorserviciosadicionales.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_totalin",valortotalinternet.getText().toString().trim()));
        lista.add(new BasicNameValuePair("aplica_ofertatv",confiraplicatv.getText().toString().trim()));
        lista.add(new BasicNameValuePair("descripciontv",descripciontv.getText().toString().trim()));
        lista.add(new BasicNameValuePair("vigenciatv",vigenciatv.getText().toString().trim()));
        lista.add(new BasicNameValuePair("plan_tv",plantv.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_tv",valorplantv.getText().toString().trim()));
        lista.add(new BasicNameValuePair("cantidad_decos",confircantidaddecos.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valorcu_decos",valorcudecos.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valortotal_decos",valortotaldecos.getText().toString().trim()));
        lista.add(new BasicNameValuePair("paquete_hd",confirpaqhd.getText().toString().trim()));
        lista.add(new BasicNameValuePair("plan_hd",planhd.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_hd",valorplanhd.getText().toString().trim()));
        lista.add(new BasicNameValuePair("cantidad_decoshd",confircantidaddecoshd.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valorcu_decoshd",valorcudecoshd.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valortotal_decoshd",valortotaldecoshd.getText().toString().trim()));
        lista.add(new BasicNameValuePair("hbo",confirhbo.getText().toString().trim()));
        lista.add(new BasicNameValuePair("movie_city",confirmoviecity.getText().toString().trim()));
        lista.add(new BasicNameValuePair("plan_cine",confirplancine.getText().toString().trim()));
        lista.add(new BasicNameValuePair("canal_adulto",confirplanadulto.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_paquete",valoradicionaltv.getText().toString().trim()));
        lista.add(new BasicNameValuePair("valor_totaltv",valortotaltv.getText().toString().trim()));
        lista.add(new BasicNameValuePair("total_pagar",totalpagarmensual.getText().toString().trim()));



        try{
            post.setEntity(new UrlEncodedFormEntity(lista));
            cliente.execute(post);
            return true;
        }

        catch (UnsupportedEncodingException e){
            e.printStackTrace();

        }

        catch (ClientProtocolException e){
            e.printStackTrace();
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public void espaciosblanco() {

        if (cedulacliente.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "No se puede realizar este proceso", Toast.LENGTH_LONG).show();
        } else if (nombrecliente.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite sus nombres", Toast.LENGTH_LONG).show();
        } else if (apellidocliente.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite sus apellidos", Toast.LENGTH_LONG).show();
        } else if (fechanacimiento.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite la fecha nacimiento", Toast.LENGTH_LONG).show();
        } else if (correoelectronico.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite su correo electronico", Toast.LENGTH_LONG).show();
        }  else if (empresa.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite empresa donde trabaja", Toast.LENGTH_LONG).show();
        } else if (antiguedad.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite antiguedad en la empresa", Toast.LENGTH_LONG).show();
        } else if (salario.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite su salario", Toast.LENGTH_LONG).show();
        } else if (cedulavendedor.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite cedula vendedor", Toast.LENGTH_LONG).show();
        } else if (nombresvendedor.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite nombres del vendedor", Toast.LENGTH_LONG).show();
        } else if (apellidosvendedor.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite apellidos del vendedor", Toast.LENGTH_LONG).show();
        } else if (direccioninstalacion.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite direccion de instalacion", Toast.LENGTH_LONG).show();
        } else if (ciudad.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite ciudad de instalacion", Toast.LENGTH_LONG).show();
        } else if (barrio.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite barrio de residencia", Toast.LENGTH_LONG).show();
        } else if (bloqueapartamento.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite bloque de apartamento", Toast.LENGTH_LONG).show();
        } else if (numeroapartamento.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite numero de apartamento", Toast.LENGTH_LONG).show();
        } else if (nombreconjunto.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite nombre conjunto residencial", Toast.LENGTH_LONG).show();
        } else if (nombresreferencia.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite nombres referenica personal ", Toast.LENGTH_LONG).show();
        } else if (apellidosreferencia.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite apellidos referenica personal ", Toast.LENGTH_LONG).show();
        } else if (direccionreferencia.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite direccion de referencia personal ", Toast.LENGTH_LONG).show();
        } else if (telefonofijo.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite numero fijo referencia personal ", Toast.LENGTH_LONG).show();
        } else if (telefonomovil.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite numero celular referencia personal", Toast.LENGTH_LONG).show();
        } else if (ciudadreferencia.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite ciudad residencia referencia personal ", Toast.LENGTH_LONG).show();
        } else if (descripciont.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite descripcion oferta telefonia fija ", Toast.LENGTH_LONG).show();
        } else if (vigenciatv.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite vigencia oferta telefonia fija", Toast.LENGTH_LONG).show();
        } else if (valorlinea.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite el valor de la linea fija ", Toast.LENGTH_LONG).show();
        } else if (valorlargadistancia.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor plan larga distancia", Toast.LENGTH_LONG).show();
        } else if (valorservicio.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor servicios adicionales linea fija ", Toast.LENGTH_LONG).show();
        } else if (valortotalfija.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Presione el boton ver total telefonia fija ", Toast.LENGTH_LONG).show();
        } else if (descripcionint.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite descripcion oferta internet ", Toast.LENGTH_LONG).show();
        } else if (vigenciaint.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite vigencia oferta internet", Toast.LENGTH_LONG).show();
        } else if (valornavegacion.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor velocidad de navegacion", Toast.LENGTH_LONG).show();
        } else if (valorcentrodeseguridad.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor centro de seguridad ", Toast.LENGTH_LONG).show();
        } else if (valoraula.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor aula 365", Toast.LENGTH_LONG).show();
        } else if (valorotrosservicios.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor otros servicios internet ", Toast.LENGTH_LONG).show();
        } else if (valorserviciosadicionales.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Presione ver total internet ", Toast.LENGTH_LONG).show();
        } else if (valortotalinternet.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Presione Ver total internet ", Toast.LENGTH_LONG).show();
        }else if (vigenciatv.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite vigencia oferta television", Toast.LENGTH_LONG).show();
        } else if (descripciontv.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite descripcion oferta television", Toast.LENGTH_LONG).show();
        } else if (plantv.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite plan de television", Toast.LENGTH_LONG).show();
        } else if (valorplantv.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor del plan de television ", Toast.LENGTH_LONG).show();
        } else if (valorcudecos.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor cada uno de decos", Toast.LENGTH_LONG).show();
        } else if (valortotaldecos.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Presione el boton ver total tv", Toast.LENGTH_LONG).show();
        } else if (planhd.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite plan HD", Toast.LENGTH_LONG).show();
        } else if (valorplanhd.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor plan HD", Toast.LENGTH_LONG).show();
        }else if (valorcudecoshd.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor C/U decos HD", Toast.LENGTH_LONG).show();
        }else if (valortotaldecoshd.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Presione el boton ver total tv", Toast.LENGTH_LONG).show();
        } else if (valoradicionaltv.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Digite valor adicional servicios Tv", Toast.LENGTH_LONG).show();
        } else if (valortotaltv.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Presione el boton ver total tv", Toast.LENGTH_LONG).show();
        }else if (totalpagarmensual.getText().toString().equals("")) {
            Toast.makeText(insertar.this, "Presione el boton ver pagar mensualmente", Toast.LENGTH_LONG).show();
        } else
            new EnviarRegistro (insertar.this).execute();
    }

}
