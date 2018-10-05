package pfr.center.models;

import java.util.ArrayList;
import java.util.List;

public class Ostatki {


    private List<Ostatok> listOstatok = new ArrayList<>();

    public Ostatki() {

    }

    public List<Ostatok> getListOstatok() {
        return listOstatok;
    }

    public void Add(String dt, Integer val) {
        listOstatok.add(new Ostatok(dt, val));
    }

    public Integer getValueByIndex(int idx) {
        return listOstatok.get(idx).getValue();
    }

    public int Length() {
        return listOstatok.size();
    }

    public List<String> getAllLabels() {
        List<String> lst = new ArrayList<>();
        for (Ostatok ost : listOstatok) {

            lst.add(ost.getLabel());
        }
        return lst;
    }

    public void CalculateDelta() {
        List<Ostatok> lstOstat = this.getListOstatok();
        for (int i = 1; i < lstOstat.size(); i++) {
            lstOstat.get(i).delta = lstOstat.get(i).value - lstOstat.get(i - 1).value;
        }
    }

    public class Ostatok {
        private String label;
        private Integer value;
        private Integer delta;

        public Ostatok(String label, Integer value) {
            this.label = label;
            this.value = value;
            this.delta = 0;
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
    }

}