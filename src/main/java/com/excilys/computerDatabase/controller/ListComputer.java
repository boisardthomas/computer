package com.excilys.computerDatabase.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerDatabase.bean.Computer;
import com.excilys.computerDatabase.service.ComputerService;

@Controller
@RequestMapping("/ListComputer")
public class ListComputer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ComputerService cs;
		
	@RequestMapping(method = RequestMethod.GET)
	protected String getListComputer(ModelMap map, @RequestParam(value = "search",required = false) String search
										, @RequestParam(value = "typeOrd",required = false) String typeOrd
										, @RequestParam(value = "ord",required = false) String ord
										, @RequestParam(value = "page",required = false) int page)										
	{
			ArrayList<Computer> computerArray;
			int nbComputer;
			if(page==0)
				page = 1;
			if(search!=null)
			{
				computerArray = cs.getList(search,typeOrd,ord,page);
				nbComputer = cs.nbComputer(search);
			}
			else
			{
				computerArray = cs.getList("",typeOrd,ord,page);
				nbComputer = cs.nbComputer("");
			}
			
			map.addAttribute("computerList", computerArray);
			map.addAttribute("nbOfComputer", nbComputer);
								
			return "dashboard";
			
	}
	
	
		
}
