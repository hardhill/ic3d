package pfr.center.models;

import pfr.center.views.components.DataGraph;

import java.util.ArrayList;
import java.util.List;

//для добавлени данных в график выполненных процессов
public class ProcessCompl {

    List<DataGraph> dataGraphList = new ArrayList<>();

    public List<DataGraph> getDataGraphList(){
        return dataGraphList;
    }

    public void Add(String label,Integer value){
        dataGraphList.add(new DataGraph(label, (double) value, 0));
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



}
