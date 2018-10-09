package pfr.center.views.components;

import java.util.List;

public class LinesDataChart {
    private List<String> labels;
    private List<LineData> lineData;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<LineData> getLineData() {
        return lineData;
    }

    public void setLineData(List<LineData> lineData) {
        this.lineData = lineData;
    }

    private class LineData{
        private List<DataGraph> lineDataGraph;
        private String colorLine;
        void  AddPoint(DataGraph dataGraph){
            lineDataGraph.add(dataGraph);
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
    }
}
