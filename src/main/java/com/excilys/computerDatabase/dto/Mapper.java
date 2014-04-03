package com.excilys.computerDatabase.dto;



import com.excilys.computerDatabase.bean.Computer;

public class Mapper {

	
	
	public static ComputerDTO convertToComputerDTO(Computer cdto)
	{
		Long id = cdto.getId();
		String name = cdto.getName().toString();
		String intro = (cdto.getIntroducedDate()!=null)?cdto.getIntroducedDate().toString():null;
		String disc = (cdto.getDiscontinuedDate()!=null)?cdto.getDiscontinuedDate().toString():null;
		Long id_comp = (cdto.getId_Company()!=null)?cdto.getId_Company():null;
		return new ComputerDTO(id,name,intro,disc,id_comp);
	
	}
	
}
