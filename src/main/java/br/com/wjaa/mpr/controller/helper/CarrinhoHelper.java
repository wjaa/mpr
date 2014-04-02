package br.com.wjaa.mpr.controller.helper;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.service.PedidoService;

/**
 * 
 * @author root
 *
 */
public class CarrinhoHelper {

	
	public static Pedido createUpdatePedido(File fileUploadPath, String fileName, HttpServletRequest request, PedidoService pedidoService){
		File folder = new File(fileUploadPath.getPath());
		
		if(!folder.exists()){
			folder.mkdirs();
		}
		Carrinho carrinho  = (Carrinho) request.getSession().getAttribute("carrinho");
		Pedido pedido;
		if (carrinho == null){
			carrinho = new Carrinho();
			request.getSession().setAttribute("carrinho", carrinho);
			pedido = pedidoService.iniciarPedido(fileUploadPath.getPath(), fileName);
		}else{
			pedido = carrinho.getPedido();
			
			//se o pedido for iniciado altera apenas a imagem
			if (pedido.getStatusEnum().equals(PedidoStatus.INICIADO)){
				if (carrinho.getPortaRetrato() != null){
					pedido = pedidoService.alterar(pedido, carrinho.getPortaRetrato().getId());
				}else{
					pedido = pedidoService.alterarImagemPedido(pedido, fileUploadPath.getPath(), fileName);
				}
			}else{
				//criando um novo pedido, se o status for diferente de iniciado quer dizer que o ultimo pedido foi concluido.
				pedido = pedidoService.iniciarPedido(fileUploadPath.getPath(), fileName);
			}
		}
		carrinho.setPedido(pedido);
		carrinho.setImgUrl("uploadFoto?getfile=" + pedido.getImageName());
        return pedido;
	}
	
	
	public static void alterarPortaRetrato(Carrinho carrinho, PortaRetrato pr, PedidoService pedidoService){
		carrinho.setPortaRetrato(pr);
		Pedido pedido = carrinho.getPedido();
		File f = new File(pedido.getPathImage());
		pedido = pedidoService.alterar(pedido, pr.getId());
		File fDest = new File(pedido.getPathImage());
		f.renameTo(fDest);
		carrinho.setPedido(pedido);
		carrinho.setImgUrl("uploadFoto?getfile=" + fDest.getName());
	}
	
}
