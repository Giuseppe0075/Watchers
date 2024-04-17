package admin;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try{
            HttpSession session = request.getSession(false);

            if(session != null && (Boolean) session.getAttribute("admin")){
                System.out.println("authenticated");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("not authenticated");
        response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");

    }
}