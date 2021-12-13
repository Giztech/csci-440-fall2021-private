package edu.montana.csci.csci440.helpers;

import edu.montana.csci.csci440.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeHelper {

    public static String makeEmployeeTree() {
        List<Employee> employeeAll = Employee.all(); // all employees
        // and use this data structure to maintain reference information needed to build the tree structure
        Map<Long, List<Employee>> employeeMap = new HashMap<>();
        employeeMap.put(employeeAll.get(1).getEmployeeId(), employeeAll);
        return "<ul>" + makeTree(employeeAll.get(1), employeeMap) + "</ul>";
    }

    // TODO - currently this method just uses the employee.getReports() function, which
    //  issues a query.  Change that to use the employeeMap variable instead
    public static String makeTree(Employee employee, Map<Long, List<Employee>> employeeMap) {
        String list = "<li><a href='/employees" + employee.getEmployeeId() + "'>"
                + employee.getEmail() + "</a><ul>";

        List<Employee> reports =  employeeMap.get(1);

        for (Employee report : reports) {
            list += makeTree(report, employeeMap);
        }
        return list + "</ul></li>";
    }
}