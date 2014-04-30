package br.com.wjaa.mpr.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import br.com.wjaa.mpr.controller.helper.CarrinhoHelper;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.service.AdminService;
import br.com.wjaa.mpr.service.PedidoService;

/**
 * Servlet implementation class UploadController
 */
@Controller
public class UploadController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4338622235712629552L;
	
	private static final Log LOG = LogFactory.getLog(UploadController.class);
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private AdminService adminService;
	
    /**
        * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
        * 
        */
    @RequestMapping(value = "/uploadFoto", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	File fileUploadPath = new File(adminService.getConfig().getPathUpload());
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
    
    @RequestMapping(value = "/uploadFoto", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(value = "files") MultipartFile files) throws ServletException, IOException {
    	File fileUploadPath = new File(adminService.getConfig().getPathUpload());
    	LOG.info("Iniciando o upload...");
        if (!ServletFileUpload.isMultipartContent(request)) {
        	LOG.error("request nao é multipart");
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        Boolean isAlterarFoto = new Boolean(request.getParameter("isAlterarFoto"));
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JSONArray json = new JSONArray();
        try {
    		Pedido pedido = CarrinhoHelper.createUpdatePedido(fileUploadPath, files.getOriginalFilename(), request, pedidoService);
    		File file = new File(pedido.getPathImage());
            IOUtils.write(files.getBytes(), new FileOutputStream(file));
            JSONObject jsono = new JSONObject();
            jsono.put("name", file.getName());
            jsono.put("size", files.getSize());
            //String param = java.net.URLEncoder.encode("uploadFoto?getfile=" + file.getName(), "ISO-8859-1");
            if (!isAlterarFoto){
            	jsono.put("url","listarPr?listPr=NORMAL");
            }else{
            	jsono.put("url","preview");
            }
            jsono.put("thumbnail_url", "uploadFoto?getthumb=" + file.getName());
            jsono.put("delete_url", "uploadFoto?delfile=" + file.getName());
            jsono.put("delete_type", "GET");
            json.put(jsono);
            LOG.info("Upload concluido com sucesso.");
        } catch (Exception e) {
        	LOG.error("Erro no upload",e);
                throw new RuntimeException(e);
        } finally {
            writer.write(json.toString());
            writer.close();
        }

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


