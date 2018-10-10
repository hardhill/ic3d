package pfr.center.views.components;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;
import pfr.center.models.ProcessCompl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InfostatChart extends CustomComponent {
    ChartJs chart = new ChartJs();
    VerticalLayout layoutRoot = new VerticalLayout();
    HorizontalLayout panelGraph = new HorizontalLayout();
    HorizontalLayout panelTitle = new HorizontalLayout();
    Grid<DataGraph> grid;
    private VerticalLayout panelGrid = new VerticalLayout();
    private LinesDataChart dataSets;
    private String title;
    private LineChartConfig config;
    private int ActiveChartLine=0;
    private Label lblTitle;

    //constructor
    public InfostatChart(LinesDataChart linesDataChart) {
        this.dataSets = linesDataChart;
        grid = new Grid<>();
        grid.addColumn(DataGraph::getLabel).setWidth(100d);
        grid.addColumn(DataGraph::getValue).setWidth(80d);
        Grid.Column column = grid.addColumn(element -> ConvertorDelta.ValueDelta(element.getDelta()), new HtmlRenderer());
        column.setWidth(80d);
        grid.setHeaderVisible(false);
        grid.setItems(dataSets.getLineData().get(ActiveChartLine).getLineDataGraph());
        panelGrid.addComponent(grid);
        grid.setWidth(260f, Unit.PIXELS);
        grid.setStyleName(ValoTheme.TABLE_COMPACT);
        chart.setJsLoggingEnabled(true);
        chart.setWidth(720f, Unit.PIXELS);
        chart.setHeight(500f, Unit.PIXELS);
        panelGraph.setWidthUndefined();
        //первоначальный конфиг
        config = new LineChartConfig();
        config.
                options()
                .responsive(true)
                .legend().display(false).and()
                .title()
                .display(false)
                .position(Position.TOP)
                .text(title)
                .fontColor("blue")
                .and()
                .done();
        panelTitle.setSpacing(false);
        panelTitle.setWidth("100%");
        panelTitle.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        lblTitle = new Label();
        lblTitle.setContentMode(ContentMode.HTML);
        lblTitle.setValue("<center>" + getTitle() + "</center>");
        panelTitle.setSpacing(false);
        panelTitle.addComponent(lblTitle);
        layoutRoot.addComponent(panelTitle);
        panelGraph.addComponent(chart);
        panelGraph.addComponent(panelGrid);
        layoutRoot.addComponent(panelGraph);
        panelGraph.setSpacing(false);
        setCompositionRoot(layoutRoot);
    }

    public void UpdateDataSet(LinesDataChart dataSets) {
        config.data().clear();
        //
        config.data().labelsAsList(dataSets.getLabels());
        for (LinesDataChart.LineData lineData : dataSets.getLineData()) {
            LineDataset lineDataset = new LineDataset();
            lineDataset.dataAsList(lineData.getValues());
            lineDataset.borderColor(lineData.getColorLine());
            lineDataset.borderWidth(lineData.getBorderWidth());
            config.data().addDataset(lineDataset);
        }

        config.
                options()
                .responsive(true)
                .title()
                .display(false)
                .position(Position.TOP)
                .text(title)
                .and()
                .done();
        chart.configure(config);

    }

    public void ChartRefresh() {

        //график перерисовать
        chart.update();
    }

    public void GridUpdate(LinesDataChart dataSets) {
        dataSets.getLineData().get(ActiveChartLine).CalculateDelta();
        grid.setItems(dataSets.getLineData().get(ActiveChartLine).getLineDataGraph());
    }

    public LinesDataChart getDataSets() {
        return dataSets;
    }

    public void setDataSets(LinesDataChart dataSets) {
        this.dataSets = dataSets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        lblTitle.setValue("<center>" + getTitle() + "</center>");
    }

    public int getActiveChartLine() {
        return ActiveChartLine;
    }

    public void setActiveChartLine(int activeChartLine) {
        ActiveChartLine = activeChartLine;
    }

    private static class ConvertorDelta {
        public static String ValueDelta(int value) {
            String otvet;
            otvet = value > 0 ? "<b style='color:green;'>" + String.valueOf(value) + "</b>" : "<b style='color:red;'>" + String.valueOf(value) + "</b>";
            return otvet;
        }
    }

}