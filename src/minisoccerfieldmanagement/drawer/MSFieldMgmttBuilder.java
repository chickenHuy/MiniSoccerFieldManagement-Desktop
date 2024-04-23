package minisoccerfieldmanagement.drawer;

import java.awt.Component;
import minisoccerfieldmanagement.form.AccountInformation;
import minisoccerfieldmanagement.form.CustomerManagement;
import minisoccerfieldmanagement.form.Dashboard;
import minisoccerfieldmanagement.form.EmployeeManagement;
import minisoccerfieldmanagement.form.FieldManagement;
import minisoccerfieldmanagement.form.InvoiceManagement;
import minisoccerfieldmanagement.form.MembershipSetting;
import minisoccerfieldmanagement.form.PriceListSetting;
import minisoccerfieldmanagement.form.ServiceForm;
import minisoccerfieldmanagement.form.ServiceManagement;
import minisoccerfieldmanagement.form.StaffBooking;
import minisoccerfieldmanagement.form.Statistics;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import minisoccerfieldmanagement.main.Main;
import raven.swing.AvatarIcon;
import minisoccerfieldmanagement.tabbed.WindowsTabbed;
import minisoccerfieldmanagement.util.ThemesChange;

/**
 *
 * @author RAVEN
 */
public class MSFieldMgmttBuilder extends SimpleDrawerBuilder {

    private  final  ThemesChange themesChange;
    public  MSFieldMgmttBuilder()
    {
        themesChange = new  ThemesChange();
    }
    
    @Override
    public Component getFooter()
    {
        return themesChange;
    }
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
            {"Operations", "Account", "Field", "Service", "Employee"},
            {"Invoice"},
            {"~OTHER~"},
            {"Statistics"},
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
                        else if (index == 1) {
                            WindowsTabbed.getInstance().addTab("Customer", new CustomerManagement());
                        }
                        else if (index == 8) {
                            WindowsTabbed.getInstance().addTab("AccountInformation", new AccountInformation());
                        }
                        else if (index == 4 && subIndex == 3 )
                        {
                            WindowsTabbed.getInstance().addTab("Service", new ServiceManagement());
                        }
                        else if (index == 4 && subIndex == 2 )
                        {
                            WindowsTabbed.getInstance().addTab("Field", new FieldManagement());
                        }
                        else if (index == 4 && subIndex == 4 )
                        {
                            WindowsTabbed.getInstance().addTab("Empolyee", new EmployeeManagement());
                        }
                        else if (index == 7 && subIndex == 1 )
                        {
                            WindowsTabbed.getInstance().addTab("Membership", new MembershipSetting());
                        }
                        else if (index == 7 && subIndex == 2 )
                        {
                            WindowsTabbed.getInstance().addTab("Price List", new PriceListSetting());
                        }
                        else if (index == 2)
                        {
                            WindowsTabbed.getInstance().addTab("Service", new ServiceForm());
                        }
                        else if (index == 3)
                        {
                            WindowsTabbed.getInstance().addTab("Booking", new StaffBooking());
                        }
                        else if (index == 5)
                        {
                            WindowsTabbed.getInstance().addTab("Invoice", new InvoiceManagement());
                        }
                        else if (index == 6)
                        {
                            WindowsTabbed.getInstance().addTab("Statistics", new Statistics());
                        }
                        System.out.println("Menu selected " + index + " " + subIndex);
                    }
                })
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
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
