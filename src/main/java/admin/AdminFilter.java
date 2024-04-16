package admin;

import jakarta.servlet.*;

import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try{
            Object admin =  servletRequest.getAttribute("admin");
            if(admin != null && admin.equals(true)){
                System.out.println("authenticated");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

        }catch (Exception e){}
        System.out.println("not authenticated");
        servletRequest.getRequestDispatcher("/admin-login-servlet").forward(servletRequest, servletResponse);

    }
}
