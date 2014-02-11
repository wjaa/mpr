package br.com.wjaa.mpr.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoader;

import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.service.AdminService;
import br.com.wjaa.mpr.service.PedidoService;
import br.com.wjaa.mpr.service.PortaRetratoService;

/**
 * Servlet implementation class UploadController
 */
public class UploadController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4338622235712629552L;
	
	private File fileUploadPath;
	private static final Log LOG = LogFactory.getLog(UploadController.class);
	private PortaRetratoService portaRetratoService;
	private PedidoService pedidoService;
	
	
    @Override
    public void init(ServletConfig config) {
    	AdminService adminService = (AdminService) ContextLoader.getCurrentWebApplicationContext().getBean("adminService");
    	portaRetratoService = (PortaRetratoService) ContextLoader.getCurrentWebApplicationContext().getBean("portaRetratoService");
    	pedidoService = (PedidoService) ContextLoader.getCurrentWebApplicationContext().getBean("pedidoService");
        fileUploadPath = new File(adminService.getConfig().getPathUpload());
    }
        
    /**
        * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
        * 
        */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("getfile") != null 
                && !request.getParameter("getfile").isEmpty()) {
            File file = new File(fileUploadPath,
                    request.getParameter("getfile"));
            
            if (file.exists()) {
            	int bytes = 0;
            	ServletOutputStream op = response.getOutputStream();
            	
            	response.setContentType(getMimeType(file));
            	response.setContentLength((int) file.length());
            	response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
            	
            	byte[] bbuf = new byte[1024];
            	DataInputStream in = new DataInputStream(new FileInputStream(file));
            	
            	while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
            		op.write(bbuf, 0, bytes);
            	}
            	
            	in.close();
            	op.flush();
            	op.close();
            }	
            
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
            File file = new File(fileUploadPath, request.getParameter("delfile"));
            if (file.exists()) {
                file.delete(); // TODO:check and report success
            } 
        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
            File file = new File(fileUploadPath, request.getParameter("getthumb"));
                if (file.exists()) {
                    String mimetype = getMimeType(file);
                    if (mimetype.endsWith("png") || mimetype.endsWith("jpeg") || mimetype.endsWith("gif")) {
                        BufferedImage im = ImageIO.read(file);
                        if (im != null) {
                            BufferedImage thumb = Scalr.resize(im, 75); 
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            if (mimetype.endsWith("png")) {
                                ImageIO.write(thumb, "PNG" , os);
                                response.setContentType("image/png");
                            } else if (mimetype.endsWith("jpeg")) {
                                ImageIO.write(thumb, "jpg" , os);
                                response.setContentType("image/jpeg");
                            } else {
                                ImageIO.write(thumb, "GIF" , os);
                                response.setContentType("image/gif");
                            }
                            ServletOutputStream srvos = response.getOutputStream();
                            response.setContentLength(os.size());
                            response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
                            os.writeTo(srvos);
                            srvos.flush();
                            srvos.close();
                        }
                    }
            } // TODO: check and report success
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("call POST with multipart form data");
        }
    }
    
    /**
        * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
        * 
        */
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOG.info("Iniciando o upload...");
        if (!ServletFileUpload.isMultipartContent(request)) {
        	LOG.error("request nao é multipart");
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        
        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                		File folder = new File(fileUploadPath.getPath());
                			
                		if(!folder.exists()){
                			folder.mkdirs();
                		}
                		Carrinho carrinho  = (Carrinho) request.getSession().getAttribute("carrinho");
                		Pedido pedido;
                		if (this.novoPedidoOuPedidoFinalizado(carrinho)){
                			pedido = pedidoService.criar(fileUploadPath.getPath(), this.getExtensao(item.getName()),
                					carrinho.getPortaRetrato().getId());
                		}else{
                			pedido = carrinho.getPedido();
                			pedido = pedidoService.alterar(pedido, fileUploadPath.getPath(), 
                					this.getExtensao(item.getName()), carrinho.getPortaRetrato().getId());
                		}
                		carrinho.setPedido(pedido);
                        File file = new File(pedido.getPathImage());
                        item.write(file);
                        JSONObject jsono = new JSONObject();
                        jsono.put("name", item.getName());
                        jsono.put("size", item.getSize());
                        String param = java.net.URLEncoder.encode(request.getContextPath() + "?getfile=" + file.getName(), "ISO-8859-1");
                        jsono.put("url","preview?imgUrl="+param);
                        jsono.put("thumbnail_url", request.getContextPath() + "?getthumb=" + item.getName());
                        jsono.put("delete_url", request.getContextPath() + "?delfile=" + item.getName());
                        jsono.put("delete_type", "GET");
                        json.put(jsono);
                        LOG.info("Upload concluido com sucesso.");
                }
            }
        } catch (FileUploadException e) {
        	LOG.error("Erro no upload",e);
        	throw new RuntimeException(e);
        } catch (Exception e) {
        	LOG.error("Erro no upload",e);
                throw new RuntimeException(e);
        } finally {
            writer.write(json.toString());
            writer.close();
        }

    }

    private boolean novoPedidoOuPedidoFinalizado(Carrinho carrinho) {
		//se nao tem pedido ou é um pedido finalizado.
    	return 	carrinho.getPedido() == null || 
				PedidoStatus.CONCLUIDO.equals(carrinho.getPedido().getStatus());
	}

	private String getExtensao(String name) {
		return name.substring(name.lastIndexOf(".")+1);
	}

	private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
//            URLConnection uc = new URL("file://" + file.getAbsolutePath()).openConnection();
//            String mimetype = uc.getContentType();
//            MimetypesFIleTypeMap gives PNG as application/octet-stream, but it seems so does URLConnection
//            have to make dirty workaround
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            } else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype  = mtMap.getContentType(file);
            }
        }
        System.out.println("mimetype: " + mimetype);
        return mimetype;
    }



    private String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        System.out.println("suffix: " + suffix);
        return suffix;
    }
}


