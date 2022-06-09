package com.myapp.actions;



import com.myapp.bean.User;
import com.myapp.dao.LRDao;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class Login1Action extends ActionSupport {
    User u;   

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题
        String name = request.getParameter("username");
        String psw0= request.getParameter("password");
        User u1 =new User(name,psw0);

        request.getSession().setAttribute("user", u1);
        RequestDispatcher d = request.getRequestDispatcher("home.jsp");
        String code = request.getParameter("rand");
        String session_code = (String) request.getSession().getAttribute("Code");
   
        User u = new User(name, psw0);
        LRDao logindao = new LRDao();
       

        String flag = logindao.Login(u);

        PrintWriter out = response.getWriter();

        if (flag != null && session_code.equals(code)) {
            System.out.println("登录成功");
            request.getSession().setAttribute("ID", flag);
            request.getSession().setAttribute("Name", flag);
            d.forward(request, response);

        } else {
            System.out.println("登录失败");
//            out.print("<script>alert('请输入正确的信息！！！\\n \\n没有账号请注册！')</script>");
            out.print("<script>window.location.href='login1.jsp'</script>");
            out.flush();
            out.close();
            return null;
//            out.println(
//                    "<script>window.alert('请输入正确的信息！！！\\n \\n没有账号请注册！');window.location.href='login1.jsp';</script>");
            // response.sendRedirect("login1.jsp");
        }
        return "success";
    }
}
