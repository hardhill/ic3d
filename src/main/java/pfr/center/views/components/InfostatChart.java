package pfr.center.views.components;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;
import pfr.center.models.ProcessCompl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InfostatChart extends CustomComponent {
    ChartJs chart = new ChartJs();
    HorizontalLayout panelGraph = new HorizontalLayout();
    private VerticalLayout panelGrid = new VerticalLayout();
    private LinesDataChart dataSets;
    private String title;
    private LineChartConfig config;
    private int ActiveChartLine=0;
    public InfostatChart(){
        Grid<DataGraph> grid = new Grid<>();
        grid.addColumn(DataGraph::getLabel).setWidth(100d);
        grid.addColumn(DataGraph::getValue).setWidth(80d);
        Grid.Column column = grid.addColumn(element -> ConvertorDelta.ValueDelta(element.getDelta()), new HtmlRenderer());
        column.setWidth(80d);
        grid.setHeaderVisible(false);
        grid.setItems((Collection<DataGraph>) dataSets.getLineData().get(ActiveChartLine));
        panelGrid.addComponent(grid);
        grid.setWidth(260f, Unit.PIXELS);
        grid.setStyleName(ValoTheme.TABLE_COMPACT);
        chart.setJsLoggingEnabled(true);
        chart.setWidth(720f, Unit.PIXELS);
        chart.setHeight(500f, Unit.PIXELS);
        panelGraph.setWidthUndefined();
        config = new LineChartConfig();
        LineDataset lineDataset = new LineDataset();
        lineDataset.borderColor("red");
        lineDataset.fill(false);
        lineDataset.borderWidth(2);
        lineDataset.getDataLabels();
        config.data().addDataset(lineDataset);
        config.options().responsive(true);
        config.options().title().text(title);
        config.options().title().position(Position.TOP);
        config.options().title().display(true);
        config.options().title().and();
        config.options().legend().display(false);
        config.options().done();
        chart.configure(config);
        panelGraph.addComponent(chart);
        panelGraph.addComponent(panelGrid);
        setCompositionRoot(panelGraph);
    }
    public InfostatChart(ProcessCompl processCompl, String title, float widthChart) {


        //processCompl.CalculateDelta();

        //grid.addColumn(Ostatok::getDelta).setWidth(80d);

        UpdateDataSet(dataSets);


    }

    public void UpdateDataSet(LinesDataChart dataSets) {

//        for (Dataset<?, ?> ds : config.data().getDatasets()) {
//
//            config.data().labelsAsList(processCompl.getAllLabels());
//
//            List<Double> data = new ArrayList<>();
//            for (int i = 0; i < processCompl.Length(); i++) {
//                data.add(Double.valueOf(processCompl.getValueByIndex(i)));
//            }
//            if (ds instanceof LineDataset) {
//                LineDataset lds = (LineDataset) ds;
//                lds.dataAsList(data);
//            }
//        }
    }

    private static class ConvertorDelta {
        public static String ValueDelta(int value) {
            String otvet;
            otvet = value > 0 ? "<b style='color:green;'>" + String.valueOf(value) + "</b>" : "<b style='color:red;'>" + String.valueOf(value) + "</b>";
            return otvet;
        }
    }
    public class DataSets extends ArrayList<DataGraph> {
        public List<DataGraph>  CalculateDelta(){
            return null;
        }
    }
}