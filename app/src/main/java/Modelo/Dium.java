
package Modelo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dium {

    @SerializedName("probPrecipitacion")
    @Expose
    private List<ProbPrecipitacion> probPrecipitacion = null;
    @SerializedName("cotaNieveProv")
    @Expose
    private List<CotaNieveProv> cotaNieveProv = null;
    @SerializedName("estadoCielo")
    @Expose
    private List<EstadoCielo> estadoCielo = null;
    @SerializedName("viento")
    @Expose
    private List<Viento> viento = null;
    @SerializedName("rachaMax")
    @Expose
    private List<RachaMax> rachaMax = null;
    @SerializedName("temperatura")
    @Expose
    private Temperatura_old temperatura;
    @SerializedName("sensTermica")
    @Expose
    private SensTermica sensTermica;
    @SerializedName("humedadRelativa")
    @Expose
    private HumedadRelativa humedadRelativa;
    @SerializedName("uvMax")
    @Expose
    private Integer uvMax;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    public List<ProbPrecipitacion> getProbPrecipitacion() {
        return probPrecipitacion;
    }

    public void setProbPrecipitacion(List<ProbPrecipitacion> probPrecipitacion) {
        this.probPrecipitacion = probPrecipitacion;
    }

    public List<CotaNieveProv> getCotaNieveProv() {
        return cotaNieveProv;
    }

    public void setCotaNieveProv(List<CotaNieveProv> cotaNieveProv) {
        this.cotaNieveProv = cotaNieveProv;
    }

    public List<EstadoCielo> getEstadoCielo() {
        return estadoCielo;
    }

    public void setEstadoCielo(List<EstadoCielo> estadoCielo) {
        this.estadoCielo = estadoCielo;
    }

    public List<Viento> getViento() {
        return viento;
    }

    public void setViento(List<Viento> viento) {
        this.viento = viento;
    }

    public List<RachaMax> getRachaMax() {
        return rachaMax;
    }

    public void setRachaMax(List<RachaMax> rachaMax) {
        this.rachaMax = rachaMax;
    }

    public Temperatura_old getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura_old temperatura) {
        this.temperatura = temperatura;
    }

    public SensTermica getSensTermica() {
        return sensTermica;
    }

    public void setSensTermica(SensTermica sensTermica) {
        this.sensTermica = sensTermica;
    }

    public HumedadRelativa getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(HumedadRelativa humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public Integer getUvMax() {
        return uvMax;
    }

    public void setUvMax(Integer uvMax) {
        this.uvMax = uvMax;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Dium{" +
                "probPrecipitacion=" + probPrecipitacion +
                ", cotaNieveProv=" + cotaNieveProv +
                ", estadoCielo=" + estadoCielo +
                ", viento=" + viento +
                ", rachaMax=" + rachaMax +
                ", temperatura=" + temperatura +
                ", sensTermica=" + sensTermica +
                ", humedadRelativa=" + humedadRelativa +
                ", uvMax=" + uvMax +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
