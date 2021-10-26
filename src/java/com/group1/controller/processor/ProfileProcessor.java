/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group1.controller.processor;

import com.group1.controller.ServerInit;
import static com.group1.controller.ServerInit.gson;
import com.group1.model.dao.StatusDAO;
import com.group1.model.db.IO;
import com.group1.rest.BaseProcessor;
import com.group1.rest.ServeAt;
import com.group1.rest.ServeMethod;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mr Khang
 */
@WebServlet("/process/profile/*")
public class ProfileProcessor extends BaseProcessor {

    @ServeAt("/changeItem")
    public void changeTimeline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("key");
        if (value.equals("Photo")) {
            request.getRequestDispatcher("/client/profile/profilePhoto.jsp").forward(request, response);
        }
        if (value.equals("Post")) {
            request.getRequestDispatcher("/client/profile/profilePost.jsp").forward(request, response);
        }
        if (value.equals("About")) {
           
            request.getRequestDispatcher("/process/profileUser/index").forward(request, response);
        }
        if (value.equals("Friends")) {
            request.getRequestDispatcher("/client/profile/profilePost.jsp").forward(request, response);
        }

    }

    @ServeAt("/changeJs")
    public void changeJS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("key");
        if (key.equals("Photo")) {
            List<String> list = IO.getFilesName(ServerInit.webPath, "assets/js/profile/Photo");
            response.getWriter().print(gson.toJson(list));
            System.out.println(list);
        }
        if (key.equals("Post")) {
            List<String> list = IO.getFilesName(ServerInit.webPath, "assets/js/profile/Post");
            response.getWriter().print(gson.toJson(list));
        }
        if (key.equals("About")) {

            List<String> list = IO.getFilesName(ServerInit.webPath, "assets/js/profile/About");
            response.getWriter().print(gson.toJson(list));
        }
        if (key.equals("Friends")) {
            List<String> list = IO.getFilesName(ServerInit.webPath, "assets/js/profile/");
            response.getWriter().print(gson.toJson(list));
        }
    }

    @ServeAt(value = "/crateStatus", method = ServeMethod.POST)
    public void serveCreateStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String content = request.getParameter("content");
        String link = request.getParameter("link");
        String audience = request.getParameter("audience");
        int accountId = (int) request.getSession().getAttribute("uid");
        StatusDAO.createStatus(accountId, content, link, audience);

    }

    @ServeAt(value = "/editStatus", method = ServeMethod.POST)
    public void serveEditStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String statusId = request.getParameter("StatusId");
//        StatusDAO.editStatus(Integer.parseInt(statusId));

    }

}
