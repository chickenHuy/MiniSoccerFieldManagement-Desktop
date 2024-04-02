package minisoccerfieldmanagement.util;

import java.awt.event.MouseEvent;
import minisoccerfieldmanagement.model.ModelDate;
/**
 *
 * @author Raven
 */
public interface CalendarSelectedListener {

    public void selected(MouseEvent evt, ModelDate date);
}
