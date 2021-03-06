/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group1.controller;

import com.group1.misc.PathInfo;
import com.group1.misc.Secret;
import com.group1.model.ProfileUserAbout;
import com.group1.model.dao.FriendDAO;
import com.group1.model.dao.ProfileUserAboutDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mr Khang
 */
@WebServlet("/profile/*")
public class ProfileServlet extends BaseServlet {
    
    @Override
    protected void processGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        int uid = (Integer) ses.getAttribute("uid");
        PathInfo path = (PathInfo) request.getAttribute("pathInfo");
        IndexServlet.setInfoHeader(request);
        String curID = Secret.decode2(path.path[0]);
        request.setAttribute("curId", path.path[0]);
        ProfileUserAbout profileUser = ProfileUserAboutDAO.getProfileUser(Integer.parseInt(curID));
        request.setAttribute("profileUser", profileUser);
        if (curID.equals(String.valueOf(uid))) {
            request.getRequestDispatcher("/client/profile/profile.jsp").forward(request, response);
        } else {
            int i = -2;
            int otherID = profileUser.getAccountID();
            if (FriendDAO.isFriend(uid, otherID)) {
                i = 2;
            } else if (FriendDAO.isRequestedFriend(otherID, uid)) {
                i = 1;
            } else if (FriendDAO.isRequestedFriend(uid, otherID)) {
                i = -1;
            }
            request.setAttribute("isFriend", i);
            request.setAttribute("yourID", uid);
            request.setAttribute("otherssID", curID);
            request.setAttribute("otherID", Secret.encode2(curID));
            request.getRequestDispatcher("/client/otherProfile/profile.jsp").forward(request, response);
        }
        
    }
    
    @Override
    protected void processPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
