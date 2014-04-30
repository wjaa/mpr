package br.com.wjaa.mpr.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.ContextLoader;

import br.com.wjaa.mpr.service.AdminService;

public class ConfigFilter implements Filter {

	private static boolean ligaGoogleAnalytics = false;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		AdminService adminService = (AdminService) ContextLoader.getCurrentWebApplicationContext().getBean("adminService");
		ligaGoogleAnalytics = adminService.getConfig().getLigaGoogleAnalytics();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setAttribute("ligaGoogleAnalytics", ligaGoogleAnalytics);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
