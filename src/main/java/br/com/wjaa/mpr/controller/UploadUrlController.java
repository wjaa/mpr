package br.com.wjaa.mpr.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;

import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.service.AdminService;
import br.com.wjaa.mpr.service.PedidoService;
import br.com.wjaa.mpr.service.PortaRetratoService;

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
	public ModelAndView upload(@RequestParam String url, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("preview");
		try {
			URL wget = new URL(url);
			InputStream in = wget.openStream();
			
			File folder = new File(fileUploadPath.getPath());
			
    		if(!folder.exists()){
    			folder.mkdirs();
    		}
    		Carrinho carrinho  = (Carrinho) request.getSession().getAttribute("carrinho");
    		Pedido pedido;
    		if (this.novoPedidoOuPedidoFinalizado(carrinho)){
    			pedido = pedidoService.criar(fileUploadPath.getPath(), "jpg",
    					carrinho.getPortaRetrato().getId());
    		}else{
    			pedido = carrinho.getPedido();
    			pedido = pedidoService.alterar(pedido, fileUploadPath.getPath(), 
    					"jpg", carrinho.getPortaRetrato().getId());
    		}
    		carrinho.setPedido(pedido);
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
            carrinho.setImgUrl("upload?getfile=" + file.getName());
		} catch (IOException e) {
			mav = new ModelAndView("erro");
			//TODO CRIAR TELA DE ERRO GENERICA.
			mav.addObject("erro", "Erro ao buscar a imagem do instagram");
		}
		return mav;
		
	}
	
	private boolean novoPedidoOuPedidoFinalizado(Carrinho carrinho) {
		//se nao tem pedido ou é um pedido finalizado.
    	return 	carrinho.getPedido() == null || 
				PedidoStatus.CONCLUIDO.equals(carrinho.getPedido().getStatus());
	}
	
}
