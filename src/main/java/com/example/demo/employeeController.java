package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value ="/employees")
public class employeeController {
    static Map<Long,employee> employees = Collections.synchronizedMap(new HashMap<Long, employee>());


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<employee> getEmployees(){
        List<employee> rs = new ArrayList<employee>(employees.values());
        return rs;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postEmployees(@ModelAttribute employee emp){
        employees.put(emp.getId(),emp);
        return "post success";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public employee getEmployee(@PathVariable Long id){
        return employees.get(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String putEmployee(@PathVariable Long id,@ModelAttribute employee emp){
        employee e = employees.get(id);
        e.setName(emp.getName());
        e.setAge(emp.getAge());
        e.setGender(emp.getGender());
        employees.put(id,e);
        return "put success";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Long id){
        employees.remove(id);
        return "delete success";
    }
}
