package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;

import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if((req.getServletPath().contains("user/login") || req.getServletPath().contains("user/signup") && req.getSession(false).getAttribute("user") == null)){
            filterChain.doFilter(request, response);
            return;
        }
        if(req.getServletPath().contains("user/logout")){
            filterChain.doFilter(request, response);
            return;
        }
        try{
            HttpSession session = req.getSession(false);
            if(session != null && session.getAttribute("user") != null){
                filterChain.doFilter(request, response);
                return;
            }

        }catch (Exception e){
            Logger.warn(e,"Filter User | User tried to access user pages without being authenticated");
        }

        resp.sendRedirect(req.getContextPath() + "/user/login.jsp");
    }
}
