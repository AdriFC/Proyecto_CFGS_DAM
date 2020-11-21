package com.example.weathercompare;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import Modelo.PrediccionMunicipio;

public class ResultadoHistorial extends AppCompatActivity {

    //Atributos del TextView para mostrar la información
    private TextView tvFecha;
    private TextView tvlocalidad1;
    private TextView tvlocalidad2;
    private TextView tvTempMax1;
    private TextView tvTempMax2;
    private TextView tvTempMin1;
    private TextView tvTempMin2;
    private TextView tvTempMed1;
    private TextView tvTempMed2;
    private TextView tvVRacha1;
    private TextView tvVRacha2;
    private TextView tvVMed1;
    private TextView tvVMed2;
    private TextView tvVPrec1;
    private TextView tvVPrec2;
    private TextView tvEstadoCielo1;
    private TextView tvEstadoCielo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadocomparacion);

        //Asocio los textView con el layout
        tvFecha = findViewById(R.id.tvFecha);
        tvlocalidad1 = findViewById(R.id.tvlocalidad1);
        tvlocalidad2 = findViewById(R.id.tvlocalidad2);
        tvTempMax1 = findViewById(R.id.tvTempMax1);
        tvTempMax2 = findViewById(R.id.tvTempMax2);
        tvTempMin1 = findViewById(R.id.tvTempMin1);
        tvTempMin2 = findViewById(R.id.tvTempMin2);
        tvTempMed1 = findViewById(R.id.tvTempMed1);
        tvTempMed2 = findViewById(R.id.tvTempMed2);
        tvVRacha1 = findViewById(R.id.tvVRacha1);
        tvVRacha2 = findViewById(R.id.tvVRacha2);
        tvVMed1 = findViewById(R.id.tvVMed1);
        tvVMed2 = findViewById(R.id.tvVMed2);
        tvVPrec1 = findViewById(R.id.tvVPrec1);
        tvVPrec2 = findViewById(R.id.tvVPrec2);
        tvEstadoCielo1 = findViewById(R.id.tvEstadoCielo1);
        tvEstadoCielo2 = findViewById(R.id.tvEstadoCielo2);

        //Obtengo los datos de comparación enviados con el get extras
        Comparacion comparacion = (Comparacion) getIntent().getExtras().getSerializable("comparacion");

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

        tvFecha.setText("Predicciones para el día " + dateFormat2.format(comparacion.getBusqueda1().getFecha()));
        tvlocalidad1.setText(comparacion.getBusqueda1().getUbicacion());
        tvTempMax1.setText(""+comparacion.getBusqueda1().getTempMax()+"°C");
        tvTempMin1.setText(""+comparacion.getBusqueda1().getTempMin()+"°C");
        tvTempMed1.setText(""+comparacion.getBusqueda1().getTempMedia()+"°C");
        tvVRacha1.setText(""+comparacion.getBusqueda1().getVientoRacha()+"Km/h");
        tvVMed1.setText(""+comparacion.getBusqueda1().getVientoMedia()+"Km/h");
        tvVPrec1.setText(""+comparacion.getBusqueda1().getPrecipitaciones()+"%");
        tvEstadoCielo1.setText(""+comparacion.getBusqueda1().getEstadoCielo());

        tvlocalidad2.setText(comparacion.getBusqueda2().getUbicacion());
        tvTempMax2.setText(""+comparacion.getBusqueda2().getTempMax()+"°C");
        tvTempMin2.setText(""+comparacion.getBusqueda2().getTempMin()+"°C");
        tvTempMed2.setText(""+comparacion.getBusqueda2().getTempMedia()+"°C");
        tvVRacha2.setText(""+comparacion.getBusqueda2().getVientoRacha()+"Km/h");
        tvVMed2.setText(""+comparacion.getBusqueda2().getVientoMedia()+"Km/h");
        tvVPrec2.setText(""+comparacion.getBusqueda2().getPrecipitaciones()+"%");
        tvEstadoCielo2.setText(""+comparacion.getBusqueda2().getEstadoCielo());
    }
}
