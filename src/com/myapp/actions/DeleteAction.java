package com.myapp.actions;



import com.myapp.bean.User;
import com.myapp.bean.Wenjuan;
import com.myapp.dao.LRDao;
import com.myapp.dao.WenjuanDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        int userid = Integer.parseInt(request.getParameter("u_id"));
       // System.out.println(id);
        System.out.println(userid);

        LRDao lr= new LRDao();
        User u = new User();
        int u_id = u.getU_id();
        lr.delete(u_id);

        WenjuanDao wd = new WenjuanDao();
        Wenjuan w  = new Wenjuan();

        response.sendRedirect("FindServlet");
        return "success";
    }
}
