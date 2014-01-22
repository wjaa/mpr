package br.com.wjaa.mpr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author root
 *
 */
@Controller
public class UploadUrlController {

	
	
	public ModelAndView upload(String end){
		ModelAndView mav = new ModelAndView();
		try {
			URL url = new URL(end);
			InputStream in = url.openStream();
			//... e de escrita.  
			FileOutputStream fos = new FileOutputStream("/tmp/file.jpg");  
			
			//Le e grava byte a byte. Voce pode (e deve) usar buffers para  
			//melhor performance (BufferedReader).  
			int umByte = 0;  
			while ((umByte = in.read()) != -1){  
				fos.write(umByte);  
			}  
			
			//Nao se esqueca de sempre fechar as streams apos seu uso!  
			in.close();  
			fos.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
		
	}
	
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		
	}
	
	
	
}
