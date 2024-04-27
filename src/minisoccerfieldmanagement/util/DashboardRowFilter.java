/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minisoccerfieldmanagement.util;

import javax.swing.RowFilter;

/**
 *
 * @author 84931
 */
public class DashboardRowFilter extends RowFilter {
    private String searchText;
    private int filterColumn;
    
    public DashboardRowFilter(String searchText, int filterColumn) {
        this.searchText = searchText;
        this.filterColumn = filterColumn;
    }
    
    @Override
    public boolean include(Entry entry) {
        return entry.getStringValue(filterColumn).toLowerCase().indexOf(searchText.toLowerCase()) >= 0;
    }
}
