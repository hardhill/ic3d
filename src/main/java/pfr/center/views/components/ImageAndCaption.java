package pfr.center.views.components;

import com.vaadin.event.LayoutEvents;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;

public class ImageAndCaption extends CustomComponent {
    private VerticalLayout panel = new VerticalLayout();

    private Label label = new Label();
    private Image img = new Image();

    public ImageAndCaption(String pathResource, String caption) {

        img.setSource(new ClassResource(pathResource));
        panel.setWidth("130px");
        panel.setMargin(false);
        label.setValue(caption);
        label.setStyleName("ic-panelcaption-off");
        panel.setStyleName("ic-hoveronpanel");
        panel.addComponents(img, label);
        panel.setComponentAlignment(img, Alignment.MIDDLE_CENTER);
        panel.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        setCompositionRoot(panel);
    }

    public void addOnClickListener(LayoutEvents.LayoutClickListener listener) {
        panel.addLayoutClickListener(listener);
    }

    public void setActiveProperty(boolean b){
        if(b){
            label.setStyleName("ic-panelcaption-on");
            panel.setStyleName("ic-selector-active");
        }else {
            label.setStyleName("ic-panelcaption-off");
            panel.setStyleName("ic-selector-unactive");
        }

    }


}
