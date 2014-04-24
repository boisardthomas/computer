package com.excilys.computerDatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	@Autowired
	private ComputerService cs;
		
	@RequestMapping(method = RequestMethod.GET)
	protected String getListComputer(ModelMap map, @RequestParam(value = "search",required = false) String search
										, @RequestParam(value = "typeOrd",required = false) String typeOrd
										, @RequestParam(value = "ord",required = false) String ord
										, @RequestParam(value = "page",required = false) Integer page)										
	{
			Page<Computer> computerPage;
			
			if(page==null)
			{
				page=1;
			}
			if(ord==null || ord.equals("") || typeOrd==null)
			{
				ord="asc";
				typeOrd="";
			}
			if(search!=null)
			{
				computerPage = cs.getList(search,typeOrd,ord,page);
			}
			else
			{
				computerPage = cs.getList("",typeOrd,ord,page);
			}
						
			map.addAttribute("page",computerPage);
		
			return "dashboard";
	
	}
	
	
		
}
