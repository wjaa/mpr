package br.com.wjaa.mpr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author root
 *
 */
public final class DateUtils {

	private static final SimpleDateFormat ddMMyyyyHHmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static String formatddMMyyyyHHmmss(Date date){
		return ddMMyyyyHHmmss.format(date);
	}
}
