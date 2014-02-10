package br.com.wjaa.mpr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeralController {


	@RequestMapping(value = "/contato", method = RequestMethod.GET)
	public String contato(){
		return "contato";
	}
	
	@RequestMapping(value = "/sobre", method = RequestMethod.GET)
	public String sobre(){
		return "sobre";
	}
	
}
