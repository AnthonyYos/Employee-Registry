package com.anthony.employeeCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anthony.employeeCrud.entity.Employee;
import com.anthony.employeeCrud.service.EmployeeService;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("list")
	public String listEmployees(Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		return "list-employees";
	}

	@GetMapping("add")
	public String showEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee-form";
	}

	@GetMapping("update")
	public String showEmployeeForm(@RequestParam("id") int id, Model model) {
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		return "employee-form";
	}

	@PostMapping("save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees/list";
	}

	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("id") int id) {
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
	
	@GetMapping("search")
	public String searchEmployee(@RequestParam("employeeNameOrEmail") String nameOrEmail, Model model) {
		List<Employee> employees = employeeService.searchBy(nameOrEmail);
		model.addAttribute("employees", employees);
		return "list-employees";
	}
}
