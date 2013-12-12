package br.com.wjaa.mpr.utils;

import java.io.UnsupportedEncodingException;

import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.entity.PortaRetrato.PortaRetratoType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	private static GsonBuilder builder = new GsonBuilder();
	private static Gson gson;
	static{
		gson = builder.excludeFieldsWithoutExposeAnnotation().create();
	}
	
	public static String toJson(Object o){
		return gson.toJson(o);
	}
	
	public static String toJsonEncode(Object o){
		try {
			return java.net.URLEncoder.encode(gson.toJson(o), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}
