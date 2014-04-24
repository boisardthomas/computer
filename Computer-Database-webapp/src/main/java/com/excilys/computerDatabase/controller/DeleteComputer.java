package com.excilys.computerDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerDatabase.service.ComputerService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputer {
   
	@Autowired
	private ComputerService cs;	
	    

	@RequestMapping(method = RequestMethod.GET)
	protected String deleteComputer(ModelMap map, @RequestParam(value = "id",required = true) Long id) {
								
		cs.deleteComputer(id); 
			
		return "redirect:ListComputer?page=1";
	}

	
	
}
