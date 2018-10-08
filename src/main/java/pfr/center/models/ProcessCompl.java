package pfr.center.models;

import java.util.ArrayList;
import java.util.List;

public class ProcessCompl {

    List<DataGraph> dataGraphList = new ArrayList<>();

    public List<DataGraph> getDataGraphList(){
        return dataGraphList;
    }

    public void Add(String label,Integer value){
        dataGraphList.add(new DataGraph(label,value,0));
    }

    public class DataGraph{
        private String label;
        private Integer value;
        private Integer delta;

        public DataGraph(String label, Integer value, Integer delta) {
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

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getDelta() {
            return delta;
        }

        public void setDelta(Integer delta) {
            this.delta = delta;
        }
    }

}
