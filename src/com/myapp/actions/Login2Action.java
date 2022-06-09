package com.myapp.actions;


import com.myapp.bean.User_g;
import com.myapp.dao.LRDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class Login2Action extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题
        String name = request.getParameter("username");
        String psw = request.getParameter("password");
        RequestDispatcher d = request.getRequestDispatcher("home1.jsp");
        String code = request.getParameter("rand");
        String session_code = (String) request.getSession().getAttribute("Code");


//        System.out.println(psw);


        User_g u = new User_g(name, psw);
        LRDao logindao = new LRDao();
        boolean flag = logindao.Login_g(u);
        
        request.getSession().setAttribute("user_g", u);

        if (flag && session_code.equals(code)) {
            System.out.println("登录成功");
            d.forward(request, response);

        } else {
            System.out.println("登录失败");

            PrintWriter out = response.getWriter();
//            out.print("<script>alert('账号或密码错误！！！')</script>");
            out.print("<script>window.location.href='login2.jsp'</script>");
            out.flush();
            out.close();
            return null;
//            response.getWriter()
//                    .println("<script>window.alert('请输入正确的信息！！！');window.location.href='login2.jsp';</script>");
            // response.sendRedirect("login1.jsp");
        }
        return "success";
    }
}
