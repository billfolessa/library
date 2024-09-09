package com.micro.cors.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.cors.dao.MailDTO;
import com.micro.cors.entities.Customer;
import com.micro.cors.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

//import lombok.extern.log4j.Log4j;

@Log4j2
@RestController
@RequestMapping("/rest/customer/api")
@Api(value="Customer rest controller : all web service about of customer")
public class CustomerRestController {
	
	//private final static Logger LOGGER = LogManager.getLogger(CustomerRestController.class);

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//@RequestMapping(path="/addCustomer" , method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes= MediaType.APPLICATION_JSON)
	/*
	 * Add a customer in data base. if customer already existe then the code of conflit is return
	 * @param customer
	 * @return customer created
	 */
	@PostMapping("/addCustomer")
	@ApiOperation(value = " create a new customer", response=Customer.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = " customer is succes create"), 
			@ApiResponse(code=304, message="the infos customer is in conflit with another customer")
			})
	public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
		Customer customerItem = customerService.findByEmail(customer.getEmail());
		if(customerItem != null) {
			return new ResponseEntity("email is already take  ",HttpStatus.CONFLICT);
		}
		customer.setCreationDate(LocalDate.now());
		Customer result = customerService.saveCustomer(customer);
		return new ResponseEntity<Customer>(result, HttpStatus.CREATED);
	}
	
	//@RequestMapping(path="/updateCustomer" , method=RequestMethod.PUT, produces= MediaType.APPLICATION_JSON, consumes=MediaType.APPLICATION_JSON)
	/*
	 * Update customer if he existe and email is not on conflit  with other email customer
	 * @param customer
	 * @return customer update
	 */
	@PutMapping("/updateCustomer")
	@ApiOperation(value="update customer", response=Customer.class)
	@ApiResponses(value = { 
			@ApiResponse(code=404, message="customer can not found"),
			@ApiResponse(code=409, message="email of your customer not match with id of your customer"),
			@ApiResponse(code=201, message="customer is succes update")
		})
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		if(customerService.checkIfExistById(customer.getId())) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		Customer customerItem = customerService.findByEmail(customer.getEmail());
		if(customerItem != null && customer.getId() != customerItem.getId()) {
			return new ResponseEntity("email is already take  ",HttpStatus.CONFLICT);
		}
		customer.setCreationDate(LocalDate.now());
		Customer result = customerService.saveCustomer(customer);
		return new ResponseEntity<Customer>(result, HttpStatus.CREATED);
	}
	
	//@RequestMapping(path="/deleteCustomer", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON, consumes= MediaType.APPLICATION_JSON)
	/**
     * Supprime un client dans la base de données H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param customerId
     * @return
     */
	@DeleteMapping("/deletCustomer/{customerId}")
	public ResponseEntity deleteCustomer(@PathVariable int customerId) {
		try {
			customerService.deleteCustomer(customerId);
			return new ResponseEntity(HttpStatus.ACCEPTED);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
     * Retourne la liste des clients ayant le nom passé en paramètre.
     * @param lastName
     * @return
     */
	@GetMapping("/searchByMail")
	public ResponseEntity<Customer> searchCustomerByEmail(@RequestParam String email){
		Customer customer = customerService.findByEmail(email);
		if(customer != null) {
			return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	//@RequestMapping(path="/sensMail", method= RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON)
	/**
     * Envoie un mail à un client. L'objet MailDTO contient l'identifiant et l'email du client concerné, l'objet du mail et le contenu du message.
     * @param loanMailDto
     * @param uriComponentBuilder
     * @return
     */
	@PutMapping("/sendMail")
	public ResponseEntity<Boolean> sendMailToCustomer(@RequestBody MailDTO  mailDTO){
		Customer customer = customerService.findById(mailDTO.getCustomerId());
		if(customer == null) {
			String errorMessage = "Not found Customer for sending email to";
            log.info(errorMessage);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		if(customer.getEmail() == null) {
			String errorMessage = "No existing email for the selected Customer for sending email to";
            log.info(errorMessage);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(mailDTO.MAIL_FROM);
		mail.setTo(customer.getEmail());
		mail.setSentDate(new Date());
		mail.setSubject(mailDTO.getEmailSubject());
		mail.setText(mailDTO.getEmailContent());
		
		try {
			mailSender.send(mail);
		}catch(Exception e) {
			log.info("error during send mail");
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	
}
