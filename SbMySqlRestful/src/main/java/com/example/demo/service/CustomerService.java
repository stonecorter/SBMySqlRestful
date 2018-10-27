package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.ServiceResult.Status;
import com.example.demo.entities.Customer;
@Service
public class CustomerService {
  @Autowired
  CustomerRepository customerRepo;
  public ServiceResult findAll() {
    ServiceResult result = new ServiceResult();
    result.setData(customerRepo.findAll());
    return result;
  }
  public ServiceResult findById(int id) {
    ServiceResult result = new ServiceResult();
    Customer customer = customerRepo.findById(id).orElse(null);
    result.setData(customer);
    return result;
  }
  public ServiceResult create(Customer customer) {
    ServiceResult result = new ServiceResult();
    result.setData(customerRepo.save(customer));
    return result;
  }
  public ServiceResult update(Customer customer) {
    ServiceResult result = new ServiceResult();
    if (!customerRepo.findById(customer.getId()).isPresent()) {
      result.setStatus(Status.FAILED);
      result.setMessage("Customer Not Found");
    } else {
      result.setData(customerRepo.save(customer));
    }
    return result;
  }
  public ServiceResult delete(int id) {
    ServiceResult result = new ServiceResult();
    Customer customer = customerRepo.findById(id).orElse(null);
    if (customer == null) {
      result.setStatus(Status.FAILED);
      result.setMessage("Customer Not Found");
    } else {
      customerRepo.delete(customer);
      result.setMessage("success");
    }
    return result;
  }
}