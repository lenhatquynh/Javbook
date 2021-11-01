package com.group1.test.nganhvu;

import com.group1.controller.BaseServlet;
import com.group1.misc.PathInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class Account {
    String name;
    int age;
}

/**
 *
 * @author Anh Vu Nguyen {@literal <nganhvu>}
 */
@WebServlet("/test/nganhvu/")
public class TestServlet extends BaseServlet {
    int cnt = 0;
    
    @Override
    protected void processGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(++cnt);
        PathInfo path = (PathInfo)request.getAttribute("pathInfo");
        System.out.println(path.path + " " + path.size);
        request.getRequestDispatcher("/test/nganhvu/websocket.jsp").forward(request, response);
    }

    @Override
    protected void processPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
