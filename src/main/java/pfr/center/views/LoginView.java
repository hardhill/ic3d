package pfr.center.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import pfr.center.MainUI;
import pfr.center.UserInfo;
import pfr.center.views.components.RoleSelector;

import java.util.Locale;

public class LoginView extends VerticalLayout implements View {
    //panel on center all screen
    final String PASSW_DORGA = "`123qwer";
    final String PASSW_NOION = "qwerasdf";
    final String PASSW_LAMA = "12121212";

    VerticalLayout layoutLogin = new VerticalLayout();

    RoleSelector roleSelector = new RoleSelector();


    Label caption = new Label();
    HorizontalLayout blockPassword = new HorizontalLayout();
    private PasswordField textPassword = new PasswordField();
    private Button bLogin = new Button("ВОЙТИ");

    //constructor
    public LoginView(MainUI main) {
        setLocale(Locale.forLanguageTag("ru"));
        setSizeFull();
        Page.getCurrent().setTitle("ИНФОЦЕНТР 3.0");
        caption.setValue("Добро пожаловать!");
        caption.setStyleName("ic-login-caption");

        layoutLogin.setWidth("640px");
        layoutLogin.setStyleName("ic-login-panel");
        blockPassword.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        blockPassword.addComponents(new Label("Пароль"), textPassword);
        layoutLogin.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        for (Component comp : new Component[]{caption, roleSelector, blockPassword, bLogin}) {
            layoutLogin.addComponent(comp);
        }
        layoutLogin.setStyleName("ic-login-panel");
        roleSelector.setIdxselector((short) 0);
        roleSelector.addOnSelectorListener(new RoleSelector.IRoleSelectorListener() {
            @Override
            public void OnSelectRole(int idx) {
                textPassword.setValue("");
                blockPassword.setVisible(idx < 3);
            }
        });

        //add panel on layout
        addComponent(layoutLogin);
        this.setComponentAlignment(layoutLogin, Alignment.MIDDLE_CENTER);
        layoutLogin.setComponentAlignment(caption, Alignment.TOP_CENTER);
        //handler
        bLogin.addClickListener(e -> {
            if (GoTobyRole(roleSelector.getIdxselector(), textPassword.getValue())) {
                UserInfo.getInstance().setUserID(VaadinSession.getCurrent().getSession().getId());
                main.navigator.navigateTo("main");

            } else
                Notification.show("Неверный пароль", Notification.Type.WARNING_MESSAGE);
        });
//        Page.Styles styles = Page.getCurrent().getStyles();
//        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
//        HttpServletRequest httpServletRequest = ((VaadinServletRequest)vaadinRequest).getHttpServletRequest();
//        String requestUrl = httpServletRequest.getRequestURL().toString();

        //String pathImage = "http://localhost/local/img/sarma.jpg";
//        String pathImage = "images/sarma.jpg";
//        styles.add(".ic-background{ background-image: url(\""+pathImage+"\"); background-size: cover; }");
        //Page.getCurrent().getStyles().add("");

        this.setStyleName("ic-background");


    }

    private boolean GoTobyRole(int idx, String password) {
        boolean pass = false;
        switch (idx) {
            case 0:
                pass = (password.equals(PASSW_DORGA));
                break;
            case 1:
                pass = (password.equals(PASSW_NOION));
                break;
            case 2:
                pass = (password.equals(PASSW_LAMA));
                break;
            case 3:
                return true;
            default:
                return false;
        }

        return pass;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //Notification.show("Welcome to the Animal Farm");

    }

}
