package BackingBeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class XBackingbean {

    private String show;
    private String show2;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}
