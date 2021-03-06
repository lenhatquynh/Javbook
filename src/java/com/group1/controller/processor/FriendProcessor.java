/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group1.controller.processor;

import com.group1.misc.Secret;
import com.group1.model.dao.FriendDAO;
import com.group1.model.dao.NotificationDAO;
import com.group1.rest.BaseProcessor;
import com.group1.rest.ServeAt;
import com.group1.rest.ServeMethod;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nhat quynh
 */
@WebServlet("/process/friend/*")
public class FriendProcessor  extends BaseProcessor {
    
    @ServeAt(value = "/insertFriend", method = ServeMethod.POST)
    public void serveInsertFriend(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int A = (int) request.getSession().getAttribute("uid");
        String B = Secret.decode2(request.getParameter("B"));
        FriendDAO.insertFriend(A, Integer.parseInt(B));
    }
    
    @ServeAt(value="/friendRequest", method=ServeMethod.POST)
    public void serveFriendRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        
        HttpSession ses = request.getSession();
        int uid = (Integer) ses.getAttribute("uid");      
        
        int otherID = Integer.parseInt(Secret.decode2(request.getParameter("receiverID")));
        int index = Integer.parseInt(request.getParameter("index"));
        
        switch (index) {
            case 2:
                FriendDAO.deleteFriend(otherID, uid);
            case 1:
                NotificationDAO.deleteFriendRequest(otherID, uid);
                break;
            case -1:
                NotificationDAO.deleteFriendRequest(uid, otherID);
                FriendDAO.insertFriend(uid, otherID);
                break;
            case -2:
                FriendDAO.requestFriend(otherID, uid);
                break;
            default:
                break;
        }
        
                
        out.print("success");
    }
    
}
