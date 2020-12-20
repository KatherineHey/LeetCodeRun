package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

public class EmployeeImportance {

    
    public int getImportance(List<Employee> employees, int id) {
        List<Integer> sub = new ArrayList<Integer>();
        List<Integer> allsub =  new ArrayList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer, Integer> employeeImportanceMap = new HashMap<Integer, Integer>();
        HashMap<Integer, List<Integer>> employeesSub = new HashMap<Integer, List<Integer>>();
        
        for (int i = 0 ; i < employees.size(); i++) {
            employeeImportanceMap.put(employees.get(i).id, employees.get(i).importance);
            employeesSub.put(employees.get(i).id, employees.get(i).subordinates);
        }
        
        
        sub = employeesSub.get(id);
        if (sub != null) {
            for (int s : sub) {
                q.add(s);
            }
        }
        
        while (!q.isEmpty()) {
            int subEmployee = q.poll();
            allsub.add(subEmployee);
            sub = employeesSub.get(subEmployee);
            if (sub != null) {
                for (int s : sub) {
                    q.add(s);
                }
            }
        }
        
        int result = 0;
        result += employeeImportanceMap.get(id);
        
        if (allsub != null) {
            for (int j = 0; j < allsub.size(); j++) {
                result += employeeImportanceMap.get(allsub.get(j));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        /**
[[1,2,[2]], [2,3,[]]]
         */
        Employee e1 = new Employee();
        e1.id = 1; e1.importance = 2; e1.subordinates = new ArrayList<>(); e1.subordinates.add(2);
        Employee e2 = new Employee();
        e2.id = 2; e2.importance = 3;
        
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(e1); employees.add(e2);
        
        EmployeeImportance ei = new EmployeeImportance();
        System.out.println(ei.getImportance(employees, 1));
        
    }
}
