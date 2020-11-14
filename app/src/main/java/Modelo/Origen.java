
package Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Origen implements Serializable {

    @SerializedName("productor")
    @Expose
    private String productor;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("enlace")
    @Expose
    private String enlace;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("notaLegal")
    @Expose
    private String notaLegal;

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getNotaLegal() {
        return notaLegal;
    }

    public void setNotaLegal(String notaLegal) {
        this.notaLegal = notaLegal;
    }

    @Override
    public String toString() {
        return "Origen{" +
                "productor='" + productor + '\'' +
                ", web='" + web + '\'' +
                ", enlace='" + enlace + '\'' +
                ", language='" + language + '\'' +
                ", copyright='" + copyright + '\'' +
                ", notaLegal='" + notaLegal + '\'' +
                '}';
    }
}
