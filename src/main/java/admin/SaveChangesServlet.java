package admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.WatchBeen;
import storage.WatchModel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class SaveChangesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newName = req.getParameter("newName");
        String newBrand = req.getParameter("newName");
        String newDescription = req.getParameter("newName");
        Double newPrice = Double.parseDouble(req.getParameter("newPrice"));
        String newMaterial = req.getParameter("newName");
        Integer newStock = Integer.parseInt(req.getParameter("newStock"));
        Double newDimension = Double.parseDouble(req.getParameter("newDimension"));;
        Integer newIVA = Integer.parseInt(req.getParameter("newIVA"));;
        String newSex = req.getParameter("newSex");
        Integer newVisible = Integer.parseInt(req.getParameter("newVisible"));;
        //BufferedImage image;

        WatchBeen modifiedWatch = new WatchBeen();
        modifiedWatch.setName(newName);
        modifiedWatch.setBrand(newBrand);
        modifiedWatch.setDescription(newDescription);
        modifiedWatch.setPrice(newPrice);
        modifiedWatch.setMaterial(newMaterial);
        modifiedWatch.setStock(newStock);
        modifiedWatch.setDimension(newDimension);
        modifiedWatch.setIVA(newIVA);
        modifiedWatch.setSex(newSex);

        WatchModel watchModel = new WatchModel();
        try{
            watchModel.updateWatch(modifiedWatch);
            // reindirizzamento ad una pagina di conferma

        }catch (SQLException e){
            //gestione errori di aggiornamento del database
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }

    }
}
