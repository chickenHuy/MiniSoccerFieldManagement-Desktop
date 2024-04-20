package minisoccerfieldmanagement.util;

import java.awt.Component;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.model.Service;

public interface EventItem {

    public void fieldClick(Component com, Field field);

    public void serviceItemClick(Component com, Service service);
}
