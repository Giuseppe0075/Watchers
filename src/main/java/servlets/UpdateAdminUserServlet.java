package servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.Beans.UserBean;
import storage.Models.UserModel;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "UpdateAdminUserServlet", value = "/admin/updateUser")
public class UpdateAdminUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera i parametri dal form
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String cap = request.getParameter("cap");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

        UserModel userModel = new UserModel();
        try {
            // Recupera l'utente dal database
            UserBean user = userModel.doRetrieveByKey(List.of(id));
            if (user != null) {
                // Aggiorna i campi dell'utente
                user.setEmail(email);
                user.setName(name);
                user.setSurname(surname);
                user.setCAP(cap);
                user.setAdmin(admin);

                // Salva o aggiorna l'utente nel database
                userModel.doSaveOrUpdate(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Errore durante l'aggiornamento dei dati dell'utente", e);
        }
        // Reindirizza nuovamente alla pagina del profilo utente
        response.sendRedirect("/admin/userProfile.jsp?id=" + id);
    }
}
