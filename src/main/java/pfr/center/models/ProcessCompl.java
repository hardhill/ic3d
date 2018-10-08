package pfr.center.models;

import java.util.ArrayList;
import java.util.List;

//для добавлени данных в график выполненных процессов
public class ProcessCompl {

    List<DataGraph> dataGraphList = new ArrayList<>();

    public List<DataGraph> getDataGraphList(){
        return dataGraphList;
    }

    public void Add(String label,Integer value){
        dataGraphList.add(new DataGraph(label,value,0));
    }

    public void CalculateDelta() {
        List<ProcessCompl.DataGraph> lstProc = this.getDataGraphList();
        for (int i = 1; i < lstProc.size(); i++) {
            lstProc.get(i).delta = lstProc.get(i).value - lstProc.get(i - 1).value;
        }
    }

    public List<String> getAllLabels(){
        List<String> lst = new ArrayList<>();
        for (DataGraph prc : dataGraphList) {
            lst.add(prc.getLabel());
        }
        return lst;
    }

    public int Length() {
        return dataGraphList.size();
    }

    public double getValueByIndex(int i) {
        return getDataGraphList().get(i).getValue();
    }

    public void DeleteAll() {
        dataGraphList.clear();
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
