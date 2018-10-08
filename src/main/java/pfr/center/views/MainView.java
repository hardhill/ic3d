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

import java.sql.Date;
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
        Date[] dates10 = Util.GetLastTenDates();
        departments = infocenterDAO.getAllDepartment();
        selectorDepart.setItemCaptionGenerator(Department::getName_reg);
        selectorDepart.setWidth("250px");
        selectorDepart.setItems(departments);
        selectorDepart.setEmptySelectionAllowed(false);
        selectorDepart.setEmptySelectionCaption("ЦУВП ПФР в РБ");
        selectorDepart.addValueChangeListener(event->{
            if(event.getSource().isEmpty()){

            }else if(event.getOldValue()==null){
                // поменять график
               Department dep = event.getValue();

                processCompl.DeleteAll();
                for(Date d:dates10){
                    ProcessEnd processEnd = infocenterDAO.getProcesses(dep.getId_depart(),d);
                    processCompl.Add(processEnd.getDateofcomp().toString(),processEnd.getSumm());
                }


            }else{

            }
        });
        //по-умолчанию взял ЦУВП
        for(Date d:dates10){
            ProcessEnd processEnd = infocenterDAO.getProcesses(25,d);
            processCompl.Add(processEnd.getDateofcomp().toString(),processEnd.getSumm());
        }

        //selectorDepart.
//        Ostatki ostatki = new Ostatki();
//
//        ostatki.Add("сен 04", 1024);
//        ostatki.Add("сен 05", 760);
//        ostatki.Add("сен 06", 804);
//        ostatki.Add("сен 07", 835);
//        ostatki.Add("сен 10", 990);
//        ostatki.Add("сен 11", 870);
//        ostatki.Add("сен 12", 882);
//        ostatki.Add("сен 13", 837);
//        ostatki.Add("сен 14", 809);
//        ostatki.Add("сен 15", 943);

        chart = new InfostatChart(processCompl, "Динамика выполнения процессов ЦУВП ПФР в Республике Бурятия", 1300f);

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
