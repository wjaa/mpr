package br.com.wjaa.mpr.entity;

/**
 * 
 * @author root
 *
 */
public class Carrinho {

	
	private PortaRetrato portaRetrato;
	private String imgPath;
	private String imgUrl;
	//TODO UM CARRINHO TEM QUE TER MAIS DE UM PEDIDO. PORQUE UM PEDIDO PODE SUBSTITUIR O OUTRO SEM QUERER NO UPLOAD.
	private Pedido pedido;
	
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
	
}
