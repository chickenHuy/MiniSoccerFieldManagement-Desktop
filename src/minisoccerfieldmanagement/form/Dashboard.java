/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package minisoccerfieldmanagement.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import minisoccerfieldmanagement.model.Field;
import minisoccerfieldmanagement.service.FieldServiceImpl;
import minisoccerfieldmanagement.service.IFieldService;
import minisoccerfieldmanagement.tabbed.TabbedForm;

/**
 *
 * @author trank
 */
public class Dashboard extends TabbedForm {

    /**
     * Creates new form Dashboard
     */
    IFieldService fieldService;

    public Dashboard() {
        initComponents();
        fieldService = new FieldServiceImpl();
        loadField();
        applyIcon();
    }

    private void applyIcon() {
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/search.svg", 0.35f));
        btnOpenCalendar.setIcon(new FlatSVGIcon("minisoccerfieldmanagement/drawer/icon/calendar.svg", 0.35f));
        txtDate.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Called when text is inserted into the document
                changeDay();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Called when text is removed from the document
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Called when attributes of the text (like style) change
                // This is less common in JTextField
            }
        });
        txtDate.setText(txtDate.getText());
    }

    private void loadField() {
        List<Field> fields = fieldService.findAll();
        fieldSection1.addData(fields);
    }

    private void changeDayLabel(int index, LocalDate date, LocalDate mainDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Border selectedBorder = new LineBorder(Color.RED, 1);

        switch (index) {
            case 0:
                if (mainDay.toString().equals(date.toString())) {
                    pnlMonday.setBorder(selectedBorder);
                } else {
                    pnlMonday.setBorder(null);
                }
                if (formatter.format(date).equals(formatter.format(LocalDate.now()))) {
                    lblMonday.setText("TODAY");
                } else {
                    lblMonday.setText(formatter.format(date));
                }
                break;
            case 1:
                if (mainDay.toString().equals(date.toString())) {
                    pnlTuesday.setBorder(selectedBorder);
                } else {
                    pnlTuesday.setBorder(null);
                }
                if (formatter.format(date).equals(formatter.format(LocalDate.now()))) {
                    lblTuesday.setText("TODAY");
                } else {
                    lblTuesday.setText(formatter.format(date));
                }
                break;
            case 2:
                if (mainDay.toString().equals(date.toString())) {
                    pnlWednesday.setBorder(selectedBorder);
                } else {
                    pnlWednesday.setBorder(null);
                }
                if (formatter.format(date).equals(formatter.format(LocalDate.now()))) {
                    lblWednesday.setText("TODAY");
                } else {
                    lblWednesday.setText(formatter.format(date));
                }
                break;
            case 3:
                if (mainDay.toString().equals(date.toString())) {
                    pnlThursday.setBorder(selectedBorder);
                } else {
                    pnlThursday.setBorder(null);
                }
                if (formatter.format(date).equals(formatter.format(LocalDate.now()))) {
                    lblThursday.setText("TODAY");
                } else {
                    lblThursday.setText(formatter.format(date));
                }
                break;
            case 4:
                if (mainDay.toString().equals(date.toString())) {
                    pnlFriday.setBorder(selectedBorder);
                } else {
                    pnlFriday.setBorder(null);
                }
                if (formatter.format(date).equals(formatter.format(LocalDate.now()))) {
                    lblFriday.setText("TODAY");
                } else {
                    lblFriday.setText(formatter.format(date));
                }
                break;
            case 5:
                if (mainDay.toString().equals(date.toString())) {
                    pnlSaturday.setBorder(selectedBorder);
                } else {
                    pnlSaturday.setBorder(null);
                }
                if (formatter.format(date).equals(formatter.format(LocalDate.now()))) {
                    lblSaturday.setText("TODAY");
                } else {
                    lblSaturday.setText(formatter.format(date));
                }
                break;
            case 6:
                if (mainDay.toString().equals(date.toString())) {
                    pnlSunday.setBorder(selectedBorder);
                } else {
                    pnlSunday.setBorder(null);
                }
                if (formatter.format(date).equals(formatter.format(LocalDate.now()))) {
                    lblSunday.setText("TODAY");
                } else {
                    lblSunday.setText(formatter.format(date));
                }
                break;
            default:
                System.out.println("Invalid day of the week");
        }
    }

    private void changeDay() {
        try {
            List<String> week = new ArrayList<>(Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"));
            String chooseDate = txtDate.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            LocalDate date = dateFormat.parse(chooseDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            DayOfWeek chooseDay = date.getDayOfWeek();
            int dayIndex = week.indexOf(chooseDay.toString());
            for (int i = dayIndex; i >= 0; i--) {
                LocalDate previousDate = date.minusDays(dayIndex - i);
                changeDayLabel(i, previousDate, date);
            }
            for (int i = dayIndex + 1; i < week.size(); i++) {
                LocalDate nextDate = date.plusDays(i - dayIndex);
                changeDayLabel(i, nextDate, date);
            }

        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        crazyPanelField = new raven.crazypanel.CrazyPanel();
        fieldSection1 = new minisoccerfieldmanagement.form.FieldSection();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtDate = new javax.swing.JTextField();
        btnOpenCalendar = new javax.swing.JButton();
        pnlMonday = new minisoccerfieldmanagement.util.PanelRound();
        lblMonday = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTuesday = new minisoccerfieldmanagement.util.PanelRound();
        lblTuesday = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pnlWednesday = new minisoccerfieldmanagement.util.PanelRound();
        lblWednesday = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pnlThursday = new minisoccerfieldmanagement.util.PanelRound();
        lblThursday = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlFriday = new minisoccerfieldmanagement.util.PanelRound();
        lblFriday = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pnlSaturday = new minisoccerfieldmanagement.util.PanelRound();
        lblSaturday = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlSunday = new minisoccerfieldmanagement.util.PanelRound();
        lblSunday = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        dateChooser1.setDateFormat("dd/MM/yyyy");
        dateChooser1.setTextRefernce(txtDate);

        setPreferredSize(new java.awt.Dimension(1188, 696));

        crazyPanelField.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        javax.swing.GroupLayout crazyPanelFieldLayout = new javax.swing.GroupLayout(crazyPanelField);
        crazyPanelField.setLayout(crazyPanelFieldLayout);
        crazyPanelFieldLayout.setHorizontalGroup(
            crazyPanelFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanelFieldLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldSection1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        crazyPanelFieldLayout.setVerticalGroup(
            crazyPanelFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crazyPanelFieldLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fieldSection1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));

        txtSearch.setText("Search");

        jLabel1.setText("Live (1)");

        btnOpenCalendar.setText("View Calendar");
        btnOpenCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenCalendarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDate)
            .addComponent(btnOpenCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOpenCalendar))
        );

        pnlMonday.setRoundBottomLeft(20);
        pnlMonday.setRoundBottomRight(20);
        pnlMonday.setRoundTopLeft(20);
        pnlMonday.setRoundTopRight(20);
        pnlMonday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMondayMouseClicked(evt);
            }
        });

        lblMonday.setText("01/01/2024");

        jLabel3.setText("Monday");

        javax.swing.GroupLayout pnlMondayLayout = new javax.swing.GroupLayout(pnlMonday);
        pnlMonday.setLayout(pnlMondayLayout);
        pnlMondayLayout.setHorizontalGroup(
            pnlMondayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMondayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMondayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMondayLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3))
                    .addComponent(lblMonday))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnlMondayLayout.setVerticalGroup(
            pnlMondayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMondayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMonday)
                .addGap(5, 5, 5))
        );

        pnlTuesday.setPreferredSize(new java.awt.Dimension(80, 49));
        pnlTuesday.setRoundBottomLeft(20);
        pnlTuesday.setRoundBottomRight(20);
        pnlTuesday.setRoundTopLeft(20);
        pnlTuesday.setRoundTopRight(20);
        pnlTuesday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTuesdayMouseClicked(evt);
            }
        });

        lblTuesday.setText("01/01/2024");

        jLabel5.setText("Tuesday");

        javax.swing.GroupLayout pnlTuesdayLayout = new javax.swing.GroupLayout(pnlTuesday);
        pnlTuesday.setLayout(pnlTuesdayLayout);
        pnlTuesdayLayout.setHorizontalGroup(
            pnlTuesdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTuesdayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTuesdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTuesdayLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5))
                    .addComponent(lblTuesday))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnlTuesdayLayout.setVerticalGroup(
            pnlTuesdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTuesdayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTuesday)
                .addGap(5, 5, 5))
        );

        pnlWednesday.setPreferredSize(new java.awt.Dimension(80, 49));
        pnlWednesday.setRoundBottomLeft(20);
        pnlWednesday.setRoundBottomRight(20);
        pnlWednesday.setRoundTopLeft(20);
        pnlWednesday.setRoundTopRight(20);
        pnlWednesday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlWednesdayMouseClicked(evt);
            }
        });

        lblWednesday.setText("01/01/2024");

        jLabel7.setText("Wednesday");

        javax.swing.GroupLayout pnlWednesdayLayout = new javax.swing.GroupLayout(pnlWednesday);
        pnlWednesday.setLayout(pnlWednesdayLayout);
        pnlWednesdayLayout.setHorizontalGroup(
            pnlWednesdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWednesdayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlWednesdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWednesday)
                    .addComponent(jLabel7))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlWednesdayLayout.setVerticalGroup(
            pnlWednesdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWednesdayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWednesday)
                .addGap(5, 5, 5))
        );

        pnlThursday.setPreferredSize(new java.awt.Dimension(80, 49));
        pnlThursday.setRoundBottomLeft(20);
        pnlThursday.setRoundBottomRight(20);
        pnlThursday.setRoundTopLeft(20);
        pnlThursday.setRoundTopRight(20);
        pnlThursday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlThursdayMouseClicked(evt);
            }
        });

        lblThursday.setText("01/01/2024");

        jLabel9.setText("Thursday");

        javax.swing.GroupLayout pnlThursdayLayout = new javax.swing.GroupLayout(pnlThursday);
        pnlThursday.setLayout(pnlThursdayLayout);
        pnlThursdayLayout.setHorizontalGroup(
            pnlThursdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThursdayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThursdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblThursday)
                    .addComponent(jLabel9))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnlThursdayLayout.setVerticalGroup(
            pnlThursdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThursdayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThursday)
                .addGap(5, 5, 5))
        );

        pnlFriday.setPreferredSize(new java.awt.Dimension(80, 49));
        pnlFriday.setRoundBottomLeft(20);
        pnlFriday.setRoundBottomRight(20);
        pnlFriday.setRoundTopLeft(20);
        pnlFriday.setRoundTopRight(20);
        pnlFriday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlFridayMouseClicked(evt);
            }
        });

        lblFriday.setText("01/01/2024");

        jLabel11.setText("Friday");

        javax.swing.GroupLayout pnlFridayLayout = new javax.swing.GroupLayout(pnlFriday);
        pnlFriday.setLayout(pnlFridayLayout);
        pnlFridayLayout.setHorizontalGroup(
            pnlFridayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFridayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFridayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFridayLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel11))
                    .addComponent(lblFriday))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnlFridayLayout.setVerticalGroup(
            pnlFridayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFridayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFriday)
                .addGap(5, 5, 5))
        );

        pnlSaturday.setPreferredSize(new java.awt.Dimension(80, 49));
        pnlSaturday.setRoundBottomLeft(20);
        pnlSaturday.setRoundBottomRight(20);
        pnlSaturday.setRoundTopLeft(20);
        pnlSaturday.setRoundTopRight(20);
        pnlSaturday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSaturdayMouseClicked(evt);
            }
        });

        lblSaturday.setText("01/01/2024");

        jLabel13.setText("Saturday");

        javax.swing.GroupLayout pnlSaturdayLayout = new javax.swing.GroupLayout(pnlSaturday);
        pnlSaturday.setLayout(pnlSaturdayLayout);
        pnlSaturdayLayout.setHorizontalGroup(
            pnlSaturdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaturdayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSaturdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSaturday)
                    .addComponent(jLabel13))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnlSaturdayLayout.setVerticalGroup(
            pnlSaturdayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaturdayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSaturday)
                .addGap(5, 5, 5))
        );

        pnlSunday.setPreferredSize(new java.awt.Dimension(80, 50));
        pnlSunday.setRoundBottomLeft(20);
        pnlSunday.setRoundBottomRight(20);
        pnlSunday.setRoundTopLeft(20);
        pnlSunday.setRoundTopRight(20);
        pnlSunday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSundayMouseClicked(evt);
            }
        });

        lblSunday.setText("01/01/2024");

        jLabel15.setText("Sunday");

        javax.swing.GroupLayout pnlSundayLayout = new javax.swing.GroupLayout(pnlSunday);
        pnlSunday.setLayout(pnlSundayLayout);
        pnlSundayLayout.setHorizontalGroup(
            pnlSundayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSundayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSundayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSundayLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15))
                    .addComponent(lblSunday))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        pnlSundayLayout.setVerticalGroup(
            pnlSundayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSundayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(lblSunday)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout crazyPanel1Layout = new javax.swing.GroupLayout(crazyPanel1);
        crazyPanel1.setLayout(crazyPanel1Layout);
        crazyPanel1Layout.setHorizontalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel1Layout.createSequentialGroup()
                        .addComponent(pnlMonday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTuesday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlWednesday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlThursday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlFriday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSaturday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlSunday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(crazyPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        crazyPanel1Layout.setVerticalGroup(
            crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crazyPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crazyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnlThursday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(pnlWednesday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(pnlTuesday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(pnlMonday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlFriday, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addComponent(pnlSaturday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlSunday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(274, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crazyPanelField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(381, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(crazyPanelField, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenCalendarActionPerformed
        // TODO add your handling code here:
        dateChooser1.showPopup();
    }//GEN-LAST:event_btnOpenCalendarActionPerformed

    private void pnlMondayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMondayMouseClicked
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate;
            if (lblMonday.getText().equals("TODAY")) {
                strDate = formatter.format(LocalDate.now());
            } else {
                strDate = lblMonday.getText();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(strDate);
            dateChooser1.setSelectedDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlMondayMouseClicked

    private void pnlTuesdayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTuesdayMouseClicked
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate;
            if (lblTuesday.getText().equals("TODAY")) {
                strDate = formatter.format(LocalDate.now());
            } else {
                strDate = lblTuesday.getText();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(strDate);
            dateChooser1.setSelectedDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlTuesdayMouseClicked

    private void pnlWednesdayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlWednesdayMouseClicked
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate;
            if (lblWednesday.getText().equals("TODAY")) {
                strDate = formatter.format(LocalDate.now());
            } else {
                strDate = lblWednesday.getText();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(strDate);
            dateChooser1.setSelectedDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlWednesdayMouseClicked

    private void pnlThursdayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlThursdayMouseClicked
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate;
            if (lblThursday.getText().equals("TODAY")) {
                strDate = formatter.format(LocalDate.now());
            } else {
                strDate = lblThursday.getText();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(strDate);
            dateChooser1.setSelectedDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlThursdayMouseClicked

    private void pnlFridayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlFridayMouseClicked
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate;
            if (lblFriday.getText().equals("TODAY")) {
                strDate = formatter.format(LocalDate.now());
            } else {
                strDate = lblFriday.getText();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(strDate);
            dateChooser1.setSelectedDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlFridayMouseClicked

    private void pnlSaturdayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSaturdayMouseClicked
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate;
            if (lblSaturday.getText().equals("TODAY")) {
                strDate = formatter.format(LocalDate.now());
            } else {
                strDate = lblSaturday.getText();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(strDate);
            dateChooser1.setSelectedDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlSaturdayMouseClicked

    private void pnlSundayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSundayMouseClicked
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate;
            if (lblSunday.getText().equals("TODAY")) {
                strDate = formatter.format(LocalDate.now());
            } else {
                strDate = lblSunday.getText();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(strDate);
            dateChooser1.setSelectedDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pnlSundayMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenCalendar;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanelField;
    private com.raven.datechooser.DateChooser dateChooser1;
    private minisoccerfieldmanagement.form.FieldSection fieldSection1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblFriday;
    private javax.swing.JLabel lblMonday;
    private javax.swing.JLabel lblSaturday;
    private javax.swing.JLabel lblSunday;
    private javax.swing.JLabel lblThursday;
    private javax.swing.JLabel lblTuesday;
    private javax.swing.JLabel lblWednesday;
    private minisoccerfieldmanagement.util.PanelRound pnlFriday;
    private minisoccerfieldmanagement.util.PanelRound pnlMonday;
    private minisoccerfieldmanagement.util.PanelRound pnlSaturday;
    private minisoccerfieldmanagement.util.PanelRound pnlSunday;
    private minisoccerfieldmanagement.util.PanelRound pnlThursday;
    private minisoccerfieldmanagement.util.PanelRound pnlTuesday;
    private minisoccerfieldmanagement.util.PanelRound pnlWednesday;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
