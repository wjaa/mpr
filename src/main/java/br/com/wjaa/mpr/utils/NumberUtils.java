package br.com.wjaa.mpr.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * 
 * @author root
 *
 */
public class NumberUtils {


	private static DecimalFormat df = (DecimalFormat) DecimalFormat.getNumberInstance(new Locale("pt", "BR"));
	static{
		df.applyPattern("#,##0.00");
	}
	
	public static String formatDecimal(Double n){
		return df.format(n);
	}
	
	public static String formatDecimal(Long n){
		return df.format(n);
	}
	
	public static String formatDecimal(Float n){
		return df.format(n);
	}
	
	public static String formatDecimal( BigDecimal n){
		return df.format(n);
	}

	public static Double parseDecimal(String str) throws ParseException {
		return df.parse(str).doubleValue();
	}
}
