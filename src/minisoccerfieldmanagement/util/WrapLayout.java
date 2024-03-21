package minisoccerfieldmanagement.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class WrapLayout extends FlowLayout {

    public WrapLayout() {
        super();
    }

    public WrapLayout(int align) {
        super(align);
    }

    public WrapLayout(int align, int hgap, int vgap) {
        super(align, hgap, vgap);
    }

    @Override
    public Dimension preferredLayoutSize(Container target) {
        return layoutSize(target, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container target) {
        Dimension minimum = layoutSize(target, false);
        minimum.height -= (getVgap() + 1);
        return minimum;
    }

    private Dimension layoutSize(Container target, boolean preferred) {
        synchronized (target.getTreeLock()) {
            int targetHeight = target.getSize().height;

            if (targetHeight == 0) {
                targetHeight = Integer.MAX_VALUE;
            }

            int hgap = getHgap();
            int vgap = getVgap();
            Insets insets = target.getInsets();
            int verticalInsetsAndGap = insets.top + insets.bottom + (vgap * 2);
            int maxHeight = targetHeight - verticalInsetsAndGap;

            Dimension dim = new Dimension(0, 0);
            int columnWidth = 0;
            int columnHeight = 0;

            int nmembers = target.getComponentCount();

            for (int i = 0; i < nmembers; i++) {
                Component m = target.getComponent(i);

                if (m.isVisible()) {
                    Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

                    if (columnHeight + d.height > maxHeight) {
                        addColumn(dim, columnWidth, columnHeight);
                        columnWidth = 0;
                        columnHeight = 0;
                    }

                    if (columnHeight != 0) {
                        columnHeight += vgap;
                    }

                    columnHeight += d.height;
                    columnWidth = Math.max(columnWidth, d.width);
                }
            }

            addColumn(dim, columnWidth, columnHeight);

            dim.height += verticalInsetsAndGap;
            dim.width += insets.left + insets.right + hgap * 2;

            Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, target);
            if (scrollPane != null) {
                dim.height -= (vgap + 1);
            }

            return dim;
        }
    }

    private void addColumn(Dimension dim, int columnWidth, int columnHeight) {
        dim.height = Math.max(dim.height, columnHeight);

        if (dim.width > 0) {
            dim.width += getHgap();
        }

        dim.width += columnWidth;
    }
}
