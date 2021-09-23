/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group1.controller.processor;

import com.google.gson.JsonParser;
import com.group1.model.db.IO;
import com.group1.rest.BaseProcessor;
import com.group1.rest.ServeAt;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anh Vu Nguyen {@literal <nganhvu>}
 */
@WebServlet("/process/test/*")
public class TestProcessor extends BaseProcessor {
    @ServeAt("")
    public void serveIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.getWriter().println(JsonParser.parseReader(IO.getReader("notify/user1.json")).getAsJsonObject().toString());
        request.setAttribute("name", "Nguyễn Anh Vũ");
        request.getRequestDispatcher("/test/nganhvu/send.jsp").include(request, response);
    }
    
    @ServeAt("/login")
    public void serveLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("login");
    }
}
