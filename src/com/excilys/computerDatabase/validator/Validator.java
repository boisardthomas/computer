package com.excilys.computerDatabase.validator;

import java.util.HashMap;
import java.util.Map;

import com.excilys.computerDatabase.dto.ComputerDTO;

public class Validator
{

	private Map<String,Boolean> validation;
	
	private static final int[] jourDuMois = {31,28,31,30,31,30,31,31,30,31,30,31};	
	
	public Validator()
	{
		validation = new HashMap<>();
	}
	
	public Map<String,Boolean> verification(ComputerDTO computer)
	{
		validation.put("name", nameValidation(computer.getNom()));
		validation.put("introduction", dateValidation(computer.getIntroduced()));
		validation.put("discontinued", dateValidation(computer.getDiscontinued()));
		return validation;		
	}
	
	public boolean nameValidation(String name)
	{
		if(name.equals("") || name==null)
			return false;
		return true;
	}
	
	public boolean dateValidation(String date)
	{
		if(date==null || date.equals(""))
			return true;
		
		String[] tabDate = date.split("-");
		
		if(tabDate.length!=3)
			return false;
		
		int annee = Integer.parseInt(tabDate[0]);
		int mois = Integer.parseInt(tabDate[1]);
		int jours = Integer.parseInt(tabDate[2]);
		System.out.println(bissextile(annee));
		if(annee<1920 || annee > 2099)
		{
			System.out.println(1);
			return false;
		}
		
		if(mois<1 || mois>12)
		{
			System.out.println(2);
			return false;
		}
					
		if((jours<1 || jours>jourDuMois[mois-1]))
		{
			if(mois!=2)
			{
				System.out.println(3);
				return false;
			}
			else
			{
				if(bissextile(annee) && jours>29)
				{
					System.out.println(4);
					return false;
				}
				else if(!bissextile(annee) && jours>28)
				{
					System.out.println(5);
					return false;
				}
					
			}
		}
		
		return true;
	}
	
	public boolean bissextile(int annee)
	{
		if(annee%4 == 0 && annee%100 != 0)
			return true;
		if(annee%400 == 0)
			return true;
		return false;
	}
	
}
