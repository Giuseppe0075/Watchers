package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import Model.Beans.UserBean;
import Model.Models.UserModel;

import java.io.IOException;
import java.util.List;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try{
            HttpSession session = request.getSession(false);

            if(session != null && session.getAttribute("user") != null ){
                Long userId = Long.parseUnsignedLong(String.valueOf(session.getAttribute("user")));
                UserModel model = new UserModel();
                UserBean user = model.doRetrieveByKey(List.of(userId));

                if(!user.getAdmin()){
                 response.sendRedirect(request.getContextPath() + "/user/personalArea.jsp");
                }

                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

        }catch (Exception e){
            Logger.warn(e,"test");
        }

        System.out.println("not authenticated");
        response.sendRedirect(request.getContextPath() + "/user/login.jsp");
    }
}
