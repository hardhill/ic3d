package pfr.center.views.components;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

public class InfostatChart extends CustomComponent {
    ChartJs chart = new ChartJs();
    VerticalLayout layoutRoot = new VerticalLayout();
    HorizontalLayout panelGraph = new HorizontalLayout();
    HorizontalLayout panelTitle = new HorizontalLayout();
    Grid<DataGrid> grid;
    private VerticalLayout panelGrid = new VerticalLayout();
    private LinesDataChart dataSets;
    private String title;
    private LineChartConfig config;
    private int ActiveChartLine=0;
    private Label lblTitle;
    private List<DataGrid> lstDataGrid;

    //constructor
    public InfostatChart() {
        lstDataGrid = new ArrayList<>();
        grid = new Grid<>();
        grid.addColumn(DataGrid::getLabel).setWidth(100d).setCaption("дата");
        grid.addColumn(elem -> ConvertorProcess.ValueProcNew(elem.getVal01()), new HtmlRenderer()).setWidth(90d).setCaption("на исп.");
        grid.addColumn(elem -> ConvertorProcess.ValueProcOstat(elem.getVal02()), new HtmlRenderer()).setWidth(90d).setCaption("остат.");
        grid.addColumn(elem -> ConvertorProcess.ValueProcCompl(elem.getVal03()), new HtmlRenderer()).setWidth(90d).setCaption("выполн.");
        //Grid.Column column = grid.addColumn(element -> ConvertorDelta.ValueDelta(element.getDelta()), new HtmlRenderer());
        //column.setWidth(80d);
        grid.setHeaderVisible(true);
        //grid.setItems(dataSets.getLineData().get(ActiveChartLine).getLineDataGraph());
        panelGrid.addComponent(grid);
        grid.setWidth(370f, Unit.PIXELS);
        grid.setHeight(470f, Unit.PIXELS);
        grid.setStyleName(ValoTheme.TABLE_COMPACT);
        chart.setJsLoggingEnabled(true);
        chart.setWidth(740f, Unit.PIXELS);
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
        lstDataGrid.clear();
        if ((dataSets != null) && (dataSets.getLineData().size() > 0)) {
            int nlines = dataSets.getLineData().size();
            int nVolumeDataLine = dataSets.getLabels().size();
            for (int i = 0; i < nVolumeDataLine; i++) {
                DataGrid dataGrid = new DataGrid();
                dataGrid.setLabel(dataSets.getLabels().get(i));
                dataGrid.setVal01(dataSets.getLineData().get(2).getValues().get(i));
                dataGrid.setVal02(dataSets.getLineData().get(1).getValues().get(i));
                dataGrid.setVal03(dataSets.getLineData().get(0).getValues().get(i));
                lstDataGrid.add(dataGrid);
            }
        }
        grid.setItems(lstDataGrid);
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

    private static class ConvertorProcess {
        public static String ValueProcNew(int value) {
            String otvet = "";
            otvet = "<b style='color:black;'>" + String.valueOf(value) + "</b>";
            return otvet;
        }

        public static String ValueProcOstat(int value) {
            String otvet = "";
            otvet = "<b style='color:blue;'>" + String.valueOf(value) + "</b>";
            return otvet;
        }

        public static String ValueProcCompl(int value) {
            String otvet = "";
            otvet = "<b style='color:red;'>" + String.valueOf(value) + "</b>";
            return otvet;
        }
    }


    public static class DataGrid {
        private String label;
        private Integer val01;
        private Integer val02;
        private Integer val03;


        public DataGrid() {

        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Integer getVal01() {
            return val01;
        }

        public void setVal01(Double val01) {
            this.val01 = val01.intValue();
        }

        public Integer getVal02() {
            return val02;
        }

        public void setVal02(Double val02) {
            this.val02 = val02.intValue();
        }

        public Integer getVal03() {
            return val03;
        }

        public void setVal03(Double val03) {
            this.val03 = val03.intValue();
        }
    }

}