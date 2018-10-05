package pfr.center.views;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import pfr.center.MainUI;
import pfr.center.UserInfo;
import pfr.center.models.Staff;
import pfr.center.models.StaffDAO;
import pfr.center.util.Util;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class StatView extends VerticalLayout implements View {

    StaffDAO staffDAO;
    MainUI main;
    public StatView(MainUI main)  {
        this.main = main;
        staffDAO = new StaffDAO();
        List<Staff> people = new ArrayList<>();
        people = staffDAO.getAllStaff();

        Grid<Staff> grid = new Grid<>();
        grid.addColumn(Staff::getFa).setCaption("Фамилия");
        grid.addColumn(Staff::getIm).setCaption("Имя");
        grid.addColumn(Staff::getOt).setCaption("Отчество");
        grid.addColumn(Staff::getDate_begin).setCaption("Принят");
        grid.addColumn(Staff::getDate_end).setCaption("Уволен");
        grid.addColumn(Staff::getActive).setCaption("Дейст.");
        grid.addColumn(Staff::getId_depart).setCaption("Подр.");
        grid.setItems(people);
        grid.setWidth("100%");

        addComponent(grid);
        grid.setStyleName("ic-grid2");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (VaadinSession.getCurrent().getSession().getId() == UserInfo.getInstance().getUserID()){

        }else{
            this.main.navigator.navigateTo("login");
        }
    }
}
