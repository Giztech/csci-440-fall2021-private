package edu.montana.csci.csci440.helpers;

import edu.montana.csci.csci440.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeHelper {

    public static String makeEmployeeTree() {

        Employee employee = Employee.find(1); // root employee
        // and use this data structure to maintain reference information needed to build the tree structure

        Map<Long, List<Employee>> employeeMap = new HashMap<>();
        List<Employee> all = employee.all();

        for(Employee employee1 : all) {
            long reportsTo = employee1.getReportsTo();
            List employees = employeeMap.get(reportsTo);

            if(employees == null) { //check null
                employees = new ArrayList<>();
                employeeMap.put(employee1.getReportsTo(), employees);
            }
            employees.add(employee1);
        }
        return "<ul>" + makeTree(employee, employeeMap) + "</ul>";
    }

    // TODO - currently this method just uses the employee.getReports() function, which
    //  issues a query.  Change that to use the employeeMap variable instead

    public static String makeTree(Employee employee, Map<Long, List<Employee>> employeeMap) {
        String list = "<li><a href='/employees" + employee.getEmployeeId() + "'>"
                + employee.getEmail() + "</a></ul>";

        List<Employee> reports = employeeMap.get(employee.getEmployeeId());

        if(reports != null) {
            for (Employee report : reports) {

                list += makeTree(report, employeeMap);
                // recursive
            }
        }
        return list + "</ul></li>";
    }
}