package pfr.center;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import pfr.center.util.ICPropertyApp;
import pfr.center.views.LoginView;
import pfr.center.views.MainView;
import pfr.center.views.StatView;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import java.util.Properties;

@Theme("mytheme")
public class MainUI extends UI {


    public Navigator navigator = new Navigator(this, this);


    static public DataSource getDataSource() {
        ICPropertyApp icProperty = new ICPropertyApp("application.properties");
        String pass = icProperty.getProperties().getProperty("spring.datasource.password","Inf0Center");
        String user = icProperty.getProperties().getProperty("spring.datasource.username","icadmin");
        String url = icProperty.getProperties().getProperty("spring.datasource.url","jdbc:mysql://localhost:3306/infocenter3_test?useSSL=false");
        String driver = icProperty.getProperties().getProperty("spring.datasource.driver-class-name","com.mysql.jdbc.Driver");
        DataSource dataSource;
        dataSource = DataSourceBuilder.create().driverClassName(driver)
                .password(pass)
                .username(user)
                .url(url)
                .build();
        return dataSource;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        navigator.addView("login", new LoginView(this));
        navigator.addView("main", new MainView(this));
        navigator.addView("grid", new StatView(this));
        navigator.navigateTo("main");
    }

    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
    }
}
