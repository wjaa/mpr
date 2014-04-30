package br.com.wjaa.mpr.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.wjaa.mpr.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/adicionarEmail", method = RequestMethod.POST )
	public @ResponseBody String adicionarEmail(@RequestParam String email, HttpServletRequest request){
		if (StringUtils.isNotBlank(email)){
			email = email.trim().toLowerCase();
			this.clienteService.adicionarEmailPromocional(email);
		}
		return "ok";
		
	}
	
	
}
