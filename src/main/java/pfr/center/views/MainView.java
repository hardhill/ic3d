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
import java.util.Collection;
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
    private ProcessCompl processCompl = new ProcessCompl();

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

                processCompl.DeleteAll();
                for(Date d:dates10){
                    ProcessEnd processEnd = infocenterDAO.getProcesses(dep.getId_depart(),d);
                    processCompl.Add(d.toLocalDate().format(DateTimeFormatter.ofPattern("MMM-dd")), processEnd.getSumm());
                }

                LinesDataChart linesDataChart = new LinesDataChart();
                linesDataChart.setLabels(processCompl.getAllLabels());
                LinesDataChart.LineData lineData = new LinesDataChart.LineData();
                lineData.setColorLine("blue");
                lineData.setLineDataGraph(processCompl.getDataGraphList());
                lineData.CalculateDelta();
                linesDataChart.addNewLine(lineData);
                chart.setTitle("Динамика выполнения процессов и остатков в " + dep.getName_dep());


                //рисуем график по данным
                chart.UpdateDataSet(linesDataChart);
                chart.ChartRefresh();
                chart.GridUpdate(linesDataChart);

            }else{
                // поменять график
                Department dep = event.getValue();

                processCompl.DeleteAll();
                for (Date d : dates10) {
                    ProcessEnd processEnd = infocenterDAO.getProcesses(dep.getId_depart(), d);
                    processCompl.Add(d.toLocalDate().format(DateTimeFormatter.ofPattern("MMM-dd")), processEnd.getSumm());
                }

                LinesDataChart linesDataChart = new LinesDataChart();
                linesDataChart.setLabels(processCompl.getAllLabels());
                LinesDataChart.LineData lineData = new LinesDataChart.LineData();
                lineData.setColorLine("red");
                lineData.setLineDataGraph(processCompl.getDataGraphList());
                lineData.CalculateDelta();
                linesDataChart.addNewLine(lineData);
                chart.setTitle("Динамика выполнения процессов в " + dep.getName_dep());
                chart.UpdateDataSet(linesDataChart);
                chart.ChartRefresh();
                chart.GridUpdate(linesDataChart);
            }
        });

        //по-умолчанию взял ЦУВП
        for(Date d:dates10){
            ProcessEnd processEnd = infocenterDAO.getProcesses(35,d);
            String dateStr = d.toLocalDate().format(DateTimeFormatter.ofPattern("MMM-dd"));
            processCompl.Add(dateStr,processEnd.getSumm());
        }

        //приготовить структуру данных для графика
        LinesDataChart linesDataChart = new LinesDataChart();
        linesDataChart.setLabels(processCompl.getAllLabels());
        LinesDataChart.LineData lineData = new LinesDataChart.LineData();
        lineData.setColorLine("red");
        lineData.setLineDataGraph(processCompl.getDataGraphList());
        lineData.CalculateDelta();
        linesDataChart.addNewLine(lineData);
        chart = new InfostatChart(linesDataChart);
        chart.setTitle("Динамика выполнения процессов в ЦУВП по РБ");
        chart.UpdateDataSet(linesDataChart);

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
