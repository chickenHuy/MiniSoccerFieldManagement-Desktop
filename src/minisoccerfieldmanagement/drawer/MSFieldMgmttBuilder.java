package minisoccerfieldmanagement.drawer;

import minisoccerfieldmanagement.form.Dashboard;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import minisoccerfieldmanagement.form.TestForm;
import minisoccerfieldmanagement.main.Main;
import raven.swing.AvatarIcon;
import minisoccerfieldmanagement.tabbed.WindowsTabbed;

/**
 *
 * @author RAVEN
 */
public class MSFieldMgmttBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("/minisoccerfieldmanagement/image/profile.png"), 60, 60, 999))
                .setTitle("Khang Nè")
                .setDescription("0397490429");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String menus[][] = {
            {"~~"},
            {"Dashboard"},
            {"~-------------------------------------------------------------------------------~"},
            {"Customer"},
            {"Service"},
            {"Booking Schedule"},
            {"~MANAGEMENT~"},
            {"Operations", "Account", "Field", "Service"},
            {"Invoice"},
            {"~OTHER~"},
            {"Statistics", "Field", "Service", "Customer", "Employee", "Revenue"},
            {"Setting", "Membership", "Price List"},
            {"My Account"},
            {"Logout"}};

        String icons[] = {
            "dashboard.svg",
            "customer.svg",
            "service.svg",
            "calendar.svg",
            "ui.svg",
            "forms.svg",
            "chart.svg",
            "icon.svg",
            "account.svg",
            "logout.svg"};

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("minisoccerfieldmanagement/drawer/icon")
                .setIconScale(0.45f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction action, int index, int subIndex) {
                        if (index == 0) {
                            WindowsTabbed.getInstance().addTab("Dashboard", new Dashboard());
                        } else if (index == 9) {
                            Main.main.login();
                        }
                        System.out.println("Menu selected " + index + " " + subIndex);
                    }
                })
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
//                        if(index==0){
//                            return false;
//                        }else if(index==3){
//                            return false;
//                        }
                        return true;
                    }

                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("MSFM")
                .setDescription("Version 1.0.0");
    }

    @Override
    public int getDrawerWidth() {
        return 275;
    }
}
