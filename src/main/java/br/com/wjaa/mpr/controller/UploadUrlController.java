package br.com.wjaa.mpr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.wjaa.mpr.controller.helper.CarrinhoHelper;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.service.AdminService;
import br.com.wjaa.mpr.service.PedidoService;

/**
 * 
 * @author root
 *
 */
@Controller
public class UploadUrlController {

	@Autowired
	private PedidoService pedidoService;
	private File fileUploadPath;
	
	@Autowired
	public UploadUrlController(AdminService adminService) {
        fileUploadPath = new File(adminService.getConfig().getPathUpload());
	}

	@RequestMapping(value = "/uploadUrl", method = RequestMethod.POST )
	public ModelAndView upload(@RequestParam String url, @RequestParam String listPr, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:/listarPr?listPr" + listPr);
		try {
			URL wget = new URL(url);
			InputStream in = wget.openStream();
			String fileName = wget.getFile();
			Pedido pedido = CarrinhoHelper.createUpdatePedido(this.fileUploadPath, fileName, request, this.pedidoService);
    		
            File file = new File(pedido.getPathImage());
			//... e de escrita.  
			FileOutputStream fos = new FileOutputStream(file);  
			
			//Le e grava byte a byte. Voce pode (e deve) usar buffers para  
			//melhor performance (BufferedReader).  
			int lidos = 0;
			byte [] BUFFER = new byte[512]; 
			while ((lidos = in.read(BUFFER)) != -1){  
				fos.write(BUFFER,0, lidos);  
			}  
			
			//Nao se esqueca de sempre fechar as streams apos seu uso!  
			in.close();  
			fos.close();  
		} catch (IOException e) {
			mav = new ModelAndView("erro");
			//TODO CRIAR TELA DE ERRO GENERICA.
			mav.addObject("erro", "Erro ao buscar a imagem do instagram");
		}
		return mav;
		
	}
}
