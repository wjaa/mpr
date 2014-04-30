package br.com.wjaa.mpr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;

import br.com.wjaa.mpr.utils.EmailUtils;
import br.com.wjaa.mpr.vo.EmailParamVO;

public class Spammer {

	public static void main(String[] args) throws IOException {
		EmailParamVO p = new EmailParamVO();
		p.setEmail("feehpinazo@gmail.com");
		p.setFrom("pedido@meuportaretrato.com");
		p.setName("Novidades - MeuPortaRetrato.com");
		p.setTitle("Decore sua casa ou escritório");
		
		InputStream in = new FileInputStream(new File("/home/wagner/dev/portaretrato.com/html/email_fly.html"));
		List<String> linesCan = IOUtils.readLines(in);
		StringBuilder sb = new StringBuilder();
		for (String l : linesCan) {
			sb.append(l);
		}
		
		p.setBody(sb.toString());
		try {
			EmailUtils.send(p,null);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
