package com.shiwang.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiwang.springdemo.entity.Customer;
import com.shiwang.springdemo.service.CustomerService;

@RestController
@RequestMapping("api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	public CustomerController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/customers")
	public List<Customer> listCustomers(){
		
		return customerService.getCustomers();
		
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId){
		
		Customer customer=customerService.getCustomer(customerId);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer not found with id "+customerId);
		}
		return customer;
		
	}
	
	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer){
		
		customer.setId(0);
		customerService.saveCustomer(customer);
		
		return customer;
		
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer){
		
		customerService.saveCustomer(customer);
		
		return customer;
		
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId){
		
		Customer customer=customerService.getCustomer(customerId);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer not found with id "+customerId);
		}
		customerService.deleteCustomer(customerId);
		
		return "customer detail deleted for id "+customerId;
		
	}
	
}
