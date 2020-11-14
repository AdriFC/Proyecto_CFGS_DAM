package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Interface.JsonPlaceHolderApi;
import Modelo.Model200;
import Modelo.PrediccionMunicipio;
import Modelo.PrediccionMunicipio_old;
import Modelo.ProbPrecipitacion;
import Modelo.Viento;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultadoComparacionActivity extends AppCompatActivity {

    //Atributo del TextView para recibir la información
    private TextView myJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadocomparacion);

        //Enlace del atributo con el textView del layout
        myJsonTextView = findViewById(R.id.JsonTextView);

        //Paso del objeto prediccion desde la clase comparacion_activity
        PrediccionMunicipio prediccion1 = (PrediccionMunicipio) getIntent().getExtras().getSerializable("prediccionMunicipio1");
        PrediccionMunicipio prediccion2 = (PrediccionMunicipio) getIntent().getExtras().getSerializable("prediccionMunicipio2");
        //String prediccionProvincia = (String) getIntent().getExtras().getSerializable("prediccionMunicipio");

        myJsonTextView.append("Comparacion: " +
                prediccion1.getProvincia() + "-" + prediccion1.getNombre() +
                " vs " +
                prediccion2.getProvincia() + "-" + prediccion2.getNombre() +
                "\n");

        //Obtencion de datos relevantes prediccion1
        int tmax1 = prediccion1.getPrediccion().getDia().get(1).getTemperatura().getMaxima();
        int tmin1 = prediccion1.getPrediccion().getDia().get(1).getTemperatura().getMinima();
        int probPrec1 = getProbPrecMax(prediccion1);
        int  vientoMedia1 = prediccion1.getPrediccion().getDia().get(0).getViento().get(0).getVelocidad();
        int  vientoMax1 = getVientoMax(prediccion1);
        int vientoMin1 = getVientoMin(prediccion1);
        //int  vientoMin1 = getVientoMin(prediccion1);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date fecha1 = new Date();
        try {
            fecha1 = dateFormat.parse(prediccion1.getPrediccion().getDia().get(0).getFecha());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        myJsonTextView.append("Fecha de prediccion: " + fecha1.toString() + "\n");
        myJsonTextView.append("Tmax en " + prediccion1.getNombre() + ": " + tmax1 + "ºC\n");
        myJsonTextView.append("Tmin en " + prediccion1.getNombre() + ": " + tmin1 + "ºC\n");
        myJsonTextView.append("Probabilidad de precipitación en " + prediccion1.getNombre() + ": " + probPrec1 + "%\n");
        myJsonTextView.append("\n" + prediccion1.getPrediccion().getDia().get(1).getProbPrecipitacion().toString());
        //myJsonTextView.append("Probabilidad de precipitación en " + prediccion1.getNombre() + ": " + probPrec1 + "%\n");
        //myJsonTextView.append("Velocidad media de viento en " + prediccion1.getNombre() + ": " + vientoMedia1 + "km/h\n");
        //myJsonTextView.append("Velocidad máxima de viento en " + prediccion1.getNombre() + ": " + vientoMax1 + "km/h\n");

        //myJsonTextView.setText(prediccion.toString());
    }

    private int getVientoMax(PrediccionMunicipio prediccion){
        List<Viento> vientoList = prediccion.getPrediccion().getDia().get(0).getViento();
        int vientoMax = vientoList.get(0).getVelocidad();

        for(Viento viento : vientoList)
        {
            if (viento.getVelocidad() > vientoMax)
            {
                vientoMax = viento.getVelocidad();
            }
        }
        return vientoMax;
    }

    private int getVientoMin(PrediccionMunicipio prediccion) {
        List<Viento> vientoList = prediccion.getPrediccion().getDia().get(0).getViento();
        int vientoMin = vientoList.get(0).getVelocidad();

        for(Viento viento : vientoList)
        {
            if (viento.getVelocidad() < vientoMin)
            {
                vientoMin = viento.getVelocidad();
            }
        }
        return vientoMin;
    }

    private int getProbPrecMax(PrediccionMunicipio prediccion){
        List<ProbPrecipitacion> probPrecipitacionList = prediccion.getPrediccion().getDia().get(0).getProbPrecipitacion();
        int probPrecipitacion = probPrecipitacionList.get(0).getValue();

        for(ProbPrecipitacion precipitacion : probPrecipitacionList)
        {
            if (precipitacion.getValue() > probPrecipitacion)
            {
                probPrecipitacion = precipitacion.getValue();
            }
        }
        return probPrecipitacion;
    }
}