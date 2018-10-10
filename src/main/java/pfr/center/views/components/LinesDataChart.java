package pfr.center.views.components;

import java.util.ArrayList;
import java.util.List;

public class LinesDataChart {
    private List<String> labels;
    private List<LineData> linesData;

    public LinesDataChart() {
        linesData = new ArrayList<>();
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<LineData> getLineData() {
        return linesData;
    }

    public void setLineData(List<LineData> linesData) {
        this.linesData = linesData;
    }

    public void addNewLine(LineData line) {
        linesData.add(line);
    }

    public static class LineData {
        private List<DataGraph> lineDataGraph;
        private String colorLine;
        private int borderWidth = 2;

        void  AddPoint(DataGraph dataGraph){
            lineDataGraph.add(dataGraph);
        }

        public List<Double> getValues() {
            List<Double> lst = new ArrayList<>();
            for (DataGraph dg : getLineDataGraph()) {
                lst.add(dg.getValue());
            }

            return lst;
        }
        public List<DataGraph> getLineDataGraph() {
            return lineDataGraph;
        }

        public void setLineDataGraph(List<DataGraph> lineDataGraph) {
            this.lineDataGraph = lineDataGraph;
        }

        public String getColorLine() {
            return colorLine;
        }

        public void setColorLine(String colorLine) {
            this.colorLine = colorLine;
        }

        public void CalculateDelta() {
            List<DataGraph> lst = this.getLineDataGraph();
            for (int i = 1; i < lst.size(); i++) {
                lst.get(i).setDelta((int) (lst.get(i).getValue() - lst.get(i - 1).getValue()));
            }
        }

        public int getBorderWidth() {
            return borderWidth;
        }

        public void setBorderWidth(int borderWidth) {
            this.borderWidth = borderWidth;
        }
    }
}
