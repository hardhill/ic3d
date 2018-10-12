package pfr.center.views.components;

import com.sun.xml.internal.ws.developer.Serialization;

@Serialization
public class DataGraph {
    private String label;
    private Double value;
    private Integer delta;

    public DataGraph(String label, Double value, Integer delta) {
        this.label = label;
        this.value = value;
        this.delta = delta;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }
}
