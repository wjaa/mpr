package br.com.wjaa.mpr.entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * 
 * @author root
 *
 */
public class Carrinho implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -282258100013270422L;
	private PortaRetrato portaRetrato;
	private String imgPath;
	private String imgUrl;
	//TODO UM CARRINHO TEM QUE TER MAIS DE UM PEDIDO. PORQUE UM PEDIDO PODE SUBSTITUIR O OUTRO SEM QUERER NO UPLOAD.
	private Pedido pedido;
	private Cupom cupom;
	
	
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public PortaRetrato getPortaRetrato() {
		return portaRetrato;
	}
	public void setPortaRetrato(PortaRetrato portaRetrato) {
		this.portaRetrato = portaRetrato;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Boolean getPaisagem(){
		PedidoItem item = pedido.getItemSelecionado();
		try {
			if (item != null){
				BufferedImage bi = ImageIO.read(new File(item.getPathImage()));
				if (bi != null){   
					return bi.getWidth() >  bi.getHeight();  
				}  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
		return false;
	}
	
	
	public Double getValor(){
		if (this.cupom != null ){
			return this.pedido.getValorTotal() - this.getDesconto(); 
		}
		return this.pedido.getValorTotal();
	}
	
	public Double getDesconto(){
		if (this.cupom != null ){
			return this.pedido.getValorTotal() * this.cupom.getPorcentagem() / 100; 
		}
		return 0.0;
	}
	public Cupom getCupom() {
		return cupom;
	}
	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
}
