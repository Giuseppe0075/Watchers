package Control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.Beans.PurchaseBean;
import Model.Beans.WatchBean;
import Model.Beans.CartElementBean;
import Model.Models.CartElementModel;
import Model.Models.PurchaseModel;
import Model.Models.WatchModel;

import java.io.IOException;
import java.util.*;


@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WatchModel watchModel = new WatchModel();
        PurchaseModel purchaseModel = new PurchaseModel();
        CartElementModel cartElementModel = new CartElementModel();

        HttpSession session = req.getSession();

        if(req.getParameterValues("watchId") == null || req.getParameterValues("quantity") == null) {
            String message = "Can't checkout on an empty cart";
            resp.sendRedirect("../homepage.jsp?error=1&message="+message);
            return;
        }

        Long userId = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;
        Long[] watchIds = Arrays.stream(req.getParameterValues("watchId")).mapToLong(Long::parseLong).boxed().toArray(Long[]::new);
        Integer[] quantities = Arrays.stream(req.getParameterValues("quantity")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);



        //id of the order: starts from one if it is the first order of the user, otherwise it is the id of the last order + 1
        long id_order = 1L;
        try {
            Collection<PurchaseBean> temp = purchaseModel.doRetrieveByCond("WHERE user = ?\nGROUP BY id\nORDER BY id DESC", List.of(userId));
            //Take the id of the last order of this user and increment it by 1
            if(!temp.isEmpty())
                id_order = temp.iterator().next().getId() + 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Check if all the watches have enough stock to be bought
        List<WatchBean> watchBeans = new ArrayList<>();
        for(int i = 0; i < watchIds.length; i++) {
            try {
                WatchBean watchBean = watchModel.doRetrieveByKey(List.of(watchIds[i]));
                if(watchBean.getStock() < quantities[i]) {
                    //Redirect to the checkout page passing the id of the watch that doesn't have enough stocks and the stocks available
                    resp.sendRedirect(req.getContextPath() + "/user/checkout/checkout.jsp?error=quantity&watch=" + watchBean.getId() + "&stock=" + watchBean.getStock());
                    return;
                }
                watchBeans.add(watchBean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //All watches have enough stock to be bought
        for(int i = 0; i < watchIds.length; i++) {
            try {
                //Update the stock of the watch
                watchBeans.get(i).setStock(watchBeans.get(i).getStock() - quantities[i]);
                watchModel.doSaveOrUpdate(watchBeans.get(i));


                Date date = new Date();

                //Save the purchase
                PurchaseBean purchaseBean = new PurchaseBean(id_order, userId, watchIds[i], quantities[i], watchBeans.get(i).getIVA(), watchBeans.get(i).getPrice(), date);
                purchaseModel.doSave(purchaseBean);

                //Remove the watch from the cart
                CartElementBean cartElementBean = new CartElementBean(userId, watchIds[i], 0);
                cartElementModel.doDelete(cartElementBean);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        resp.sendRedirect("../homepage.jsp");
    }
}
