
package Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CotaNieveProv {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("periodo")
    @Expose
    private String periodo;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "CotaNieveProv{" +
                "value='" + value + '\'' +
                ", periodo='" + periodo + '\'' +
                '}';
    }
}
