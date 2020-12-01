package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.dao.DepartmentDao;
import com.bjpowernode.springboot.dao.EmployeeDao;
import com.bjpowernode.springboot.domain.Department;
import com.bjpowernode.springboot.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {


    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Map<String,Object> map){
        Collection<Employee> emps = employeeDao.getAll();
        map.put("emps",emps);
        return "/emp/list";

    }

    //跳转到添加页面
    @GetMapping(value = "/emp")
    public String toAddPage(Map<String,Object> map){
        //添加页面需要部门信息做回显
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("depts",depts);
        return "/emp/add";
    }

    //添加的页面
    @PostMapping("/emp")
    public String AddEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        //这里重点注意!!!!:
            /*
            转发与重定向有一点区别:转发的页面请求方式为接收的请求方式,所以这个方法中,接收时Post,那么发送也会是post也就到了错误的页面,所以只能用重定向
             */
        return "redirect:/emps";
    }

    //跳转到修改页面(与添加页面时同一个页面,在页面做不同判断来适应不同的请求,修改页面需要进行回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Map<String,Object> map){
        Employee emp = employeeDao.get(id);
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("emp",emp);
        map.put("depts",depts);
        return "/emp/add";
    }


    //修改的方法
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }



//    //根据id删除员工操作
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
