package pfr.center.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;
import pfr.center.MainUI;
import pfr.center.UserInfo;
import pfr.center.models.*;
import pfr.center.util.Util;
import pfr.center.views.components.InfostatChart;
import pfr.center.views.components.LinesDataChart;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainView extends VerticalLayout implements View {
    InfostatChart chart;
    private InfocenterDAO infocenterDAO;
    private MainUI main;
    private HorizontalLayout panelMenu = new HorizontalLayout();
    private HorizontalLayout panelGraph = new HorizontalLayout();
    private MenuBar menuMain = new MenuBar();
    private Link linkPortal = new Link("ИНФОЦЕНТР-ПОРТАЛ", new ExternalResource("http://10.3.59.113/"));
    private ComboBox<Department> selectorDepart = new ComboBox<>();
    private Processes processCompl = new Processes();
    private Processes processOstat = new Processes();
    private Processes processNew = new Processes();

    public MainView(MainUI main) {
        this.main = main;
        this.setMargin(true);
        infocenterDAO = new InfocenterDAO();
        List<Department> departments = new ArrayList<>();
        //последние 10 дней
        ArrayList<Date> dates10 = Util.GetLastDates(9);
        departments = infocenterDAO.getAllDepartment();
        selectorDepart.setItemCaptionGenerator(Department::getName_reg);
        selectorDepart.setWidth("250px");
        selectorDepart.setItems(departments);
        selectorDepart.setEmptySelectionAllowed(false);
        selectorDepart.setEmptySelectionCaption("ЦУВП ПФР в РБ");

        //изменение списка районов
        selectorDepart.addValueChangeListener(event->{
            if(event.getSource().isEmpty()){

            } else if (event.getOldValue() == null) {    //впервые
                // поменять график
               Department dep = event.getValue();

                GenerateGrafikbyDepart(dates10, dep);

            }else{
                // поменять график
                Department dep = event.getValue();

                GenerateGrafikbyDepart(dates10, dep);
            }
        });

        Department dep = departments.stream().filter(element -> element.getId_depart() == 35).findAny().orElse(null);
        //selectorDepart.setSelectedItem(dep);
        GenerateGrafikbyDepart(dates10, dep);

//------------------------------------------------------------------------------------------
        panelMenu.setWidth("100%");
        panelMenu.addComponent(menuMain);
        panelMenu.setComponentAlignment(menuMain, Alignment.MIDDLE_LEFT);

        panelMenu.addComponent(linkPortal);
        panelMenu.setComponentAlignment(linkPortal, Alignment.MIDDLE_RIGHT);
        linkPortal.setTargetName("_blank");
        addComponent(panelMenu);
        addComponent(selectorDepart);
        DrawMainMenu(menuMain);

        //panelGraph.setWidth(800f,Unit.PIXELS);
        panelGraph.setSizeUndefined();
        panelGraph.addComponent(chart);
        //panelGraph.setComponentAlignment(chart,Alignment.MIDDLE_CENTER);
        chart.setWidth("100%");

        addComponent(panelGraph);
        setComponentAlignment(panelGraph, Alignment.MIDDLE_CENTER);
        Page.getCurrent().setTitle("ИНФОЦЕНТР 3.0");

    }

    private void GenerateGrafikbyDepart(ArrayList<Date> dates10, Department dep) {
        processCompl.DeleteAll();
        processOstat.DeleteAll();
        processNew.DeleteAll();
        for (Date d : dates10) {
            ProcessOne processAsNew = infocenterDAO.getProcessNew(dep.getId_depart(), d);
            ProcessOne processOne = infocenterDAO.getOstatok(dep.getId_depart(), d);
            ProcessOne processOut = infocenterDAO.getProcessCompl(dep.getId_depart(), d);
            processCompl.Add(d.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MMM")), processOut.getSumm());
            processOstat.Add(d.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MMM")), processOne.getSumm());
            processNew.Add(d.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MMM")), processAsNew.getSumm());
        }
        //исолненные процессы
        LinesDataChart linesDataChart = new LinesDataChart();
        linesDataChart.setLabels(processCompl.getAllLabels());
        LinesDataChart.LineData lineData = new LinesDataChart.LineData();
        lineData.setColorLine("red");
        lineData.setLineDataGraph(processCompl.getDataGraphList());
        lineData.CalculateDelta();
        linesDataChart.addNewLine(lineData);
        //теперь линия графика остатков
        lineData = new LinesDataChart.LineData();
        lineData.setColorLine("blue");
        lineData.setLineDataGraph(processOstat.getDataGraphList());
        lineData.CalculateDelta();
        linesDataChart.addNewLine(lineData);
        //пришедшие дела
        lineData = new LinesDataChart.LineData();
        lineData.setColorLine("gray");
        lineData.setLineDataGraph(processNew.getDataGraphList());
        lineData.CalculateDelta();
        linesDataChart.addNewLine(lineData);
        if (chart == null) {
            chart = new InfostatChart();
        }
        chart.setTitle("Динамика выполнения процессов и остатков в " + dep.getName_dep());
        //рисуем график по данным
        chart.UpdateDataSet(linesDataChart);
        chart.ChartRefresh();
        chart.GridUpdate(linesDataChart);
    }

    private void DrawMainMenu(MenuBar menuMain) {
        MenuItem menuItemProdPok = menuMain.addItem("Производственные показатели");
        MenuItem menuItemSetup = menuMain.addItem("Настройки");
        MenuItem menuItemExit = menuMain.addItem("Выход");
        menuItemExit.setCommand(new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                //сброс данных зарегистрированного пользователя
                UserInfo.getInstance().ResetUserInfo();
                main.navigator.navigateTo("login");
            }
        });
        MenuItem menuItemPlan = menuItemProdPok.addItem("Планирование");
        MenuItem menuItemStatist = menuItemProdPok.addItem("Статистика");
        MenuItem menuItemOstatki = menuItemProdPok.addItem("Остатки ЦУВП");
        MenuItem menuItemPrivlech = menuItemProdPok.addItem("Привлеченные");
        MenuItem menuItemUBER = menuItemProdPok.addItem("UBER");
        MenuItem menuItemSprSotr = menuItemSetup.addItem("Справочник сотрудников");
        MenuItem menuItemPrice = menuItemSetup.addItem("Стоимость процессов");
        MenuItem menuItemGroups = menuItemSetup.addItem("Группы");
        MenuBar.Command commGrid = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                main.navigator.navigateTo("grid");
            }
        };
        menuItemStatist.setCommand(commGrid);


    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (VaadinSession.getCurrent().getSession().getId() == UserInfo.getInstance().getUserID()) {
            Notification.show("Данные графика демонстрационные.");
        } else {
            this.main.navigator.navigateTo("login");
        }


    }
}
