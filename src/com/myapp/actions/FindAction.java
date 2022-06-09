package com.myapp.actions;


import com.myapp.bean.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/questions?useUnicode=true&characterEncoding=UTF8&useSSL=true", "root", "root");
            Statement stmt = conn.createStatement();
            String sql = "select * from user";
            ResultSet rs = stmt.executeQuery(sql);
            List<User> list  = new ArrayList<User>();
            while(rs.next()){
				String username=null;
				String password=null;
                User user = new User();
                user.setU_id(rs.getInt("u_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                list.add(user);
            }

            request.setAttribute("list", list);
            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("home1_1.jsp").forward(request, response);
        return "success";
    }
}
