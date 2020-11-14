package com.example.weathercompare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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




        //Enlace del atributo con el textView del layout
        //myJsonTextView = findViewById(R.id.JsonTextView);

        //Paso del objeto prediccion desde la clase comparacion_activity
        PrediccionMunicipio prediccion1 = (PrediccionMunicipio) getIntent().getExtras().getSerializable("prediccionMunicipio1");
        PrediccionMunicipio prediccion2 = (PrediccionMunicipio) getIntent().getExtras().getSerializable("prediccionMunicipio2");
        //String prediccionProvincia = (String) getIntent().getExtras().getSerializable("prediccionMunicipio");

        //myJsonTextView.append("Comparacion: " +
          //      prediccion1.getProvincia() + "-" + prediccion1.getNombre() +
            //    " vs " +
              //  prediccion2.getProvincia() + "-" + prediccion2.getNombre() +
                //"\n");

        //Obtencion de datos relevantes prediccion1
        int tmax1 = prediccion1.getPrediccion().getDia().get(1).getTemperatura().getMaxima();
        int tmin1 = prediccion1.getPrediccion().getDia().get(1).getTemperatura().getMinima();
        float tmed1 = (tmax1+tmin1)/2;
        int probPrec1 = getProbPrecMax(prediccion1);
        int vientoMedia1 = prediccion1.getPrediccion().getDia().get(0).getViento().get(0).getVelocidad();
        int vientoMax1 = getVientoMax(prediccion1);
        int vientoMin1 = getVientoMin(prediccion1);
        String estadoCielo1 = prediccion1.getPrediccion().getDia().get(1).getEstadoCielo().get(0).getDescripcion();

        //Obtencion de datos relevantes prediccion2
        int tmax2 = prediccion2.getPrediccion().getDia().get(1).getTemperatura().getMaxima();
        int tmin2 = prediccion2.getPrediccion().getDia().get(1).getTemperatura().getMinima();
        float tmed2 = (tmax2+tmin2)/2;
        int probPrec2 = getProbPrecMax(prediccion2);
        int vientoMedia2 = prediccion2.getPrediccion().getDia().get(0).getViento().get(0).getVelocidad();
        int vientoMax2 = getVientoMax(prediccion2);
        int vientoMin2 = getVientoMin(prediccion2);
        String estadoCielo2 = prediccion2.getPrediccion().getDia().get(1).getEstadoCielo().get(0).getDescripcion();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date fecha1 = new Date();
        try {
            fecha1 = dateFormat.parse(prediccion1.getPrediccion().getDia().get(1).getFecha());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        tvFecha.setText(fecha1.toString());
        tvlocalidad1.setText(prediccion1.getNombre());
        tvlocalidad2.setText( prediccion2.getNombre());
        tvTempMax1.setText(""+tmax1+"°C");
        tvTempMax2.setText(""+tmax2+"°C");
        tvTempMin1.setText(""+tmin1+"°C");
        tvTempMin2.setText(""+tmin2+"°C");
        tvTempMed1.setText(""+tmed1+"°C");
        tvTempMed2.setText(""+tmed2+"°C");
        tvVRacha1.setText(""+vientoMax1+"km/h");
        tvVRacha2.setText(""+vientoMax2+"km/h");
        tvVMed1.setText(""+vientoMedia1+"km/h");
        tvVMed2.setText(""+vientoMedia2+"km/h");
        tvVPrec1.setText(""+probPrec1+"%");
        tvVPrec2.setText(""+probPrec2+"%");
        tvEstadoCielo1.setText(estadoCielo1);
        tvEstadoCielo2.setText(estadoCielo2);

        /*myJsonTextView.append("Probabilidad de precipitación en " + prediccion1.getNombre() + ": " + probPrec1 + "%\n");
        myJsonTextView.append("Estado del cielo en: " + prediccion1.getNombre() + ": " + estadoCielo1 + "\n");
        myJsonTextView.append("Racha de viento en: " + prediccion1.getNombre() +": " + vientoMax1 + "km/h\n");
        myJsonTextView.append("Velocidad media de viento en: " + prediccion1.getNombre() + ": " + vientoMedia1 + "km/h\n\n");

        myJsonTextView.append("Tmax en " + prediccion2.getNombre() + ": " + tmax2 + "ºC\n");
        myJsonTextView.append("Tmin en " + prediccion2.getNombre() + ": " + tmin2 + "ºC\n");
        myJsonTextView.append("Tmed en " + prediccion2.getNombre() + ": " + tmed2 + "ºC\n");
        myJsonTextView.append("Probabilidad de precipitación en " + prediccion2.getNombre() + ": " + probPrec2 + "%\n");
        myJsonTextView.append("Estado del cielo en: " + prediccion2.getNombre() + ": " + estadoCielo2 + "\n");
        myJsonTextView.append("Racha de viento en: " + prediccion2.getNombre() +": " + vientoMax2 + "km/h\n");
        myJsonTextView.append("Velocidad media de viento en: " + prediccion2.getNombre() + ": " + vientoMedia2 + "km/h\n");*/
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