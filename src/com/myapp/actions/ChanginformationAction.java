package com.myapp.actions;

import com.myapp.bean.User;
import com.myapp.dao.LRDao;

import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;


public class ChanginformationAction extends ActionSupport {

    public String execute() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题
        String name = request.getParameter("username");
        String psw1 = request.getParameter("oldpassword");
        String psw0 = request.getParameter("newpassword");
        PrintWriter out = response.getWriter();

        User u = new User(name, psw1);
        User u1 = new User(name, psw0);
        LRDao dao = new LRDao();
        String s = dao.Login(u);

        if (s != null) {
            String t = dao.Update(u1);
            if (t != null) {
                System.out.println("修改成功！");

                out.print("<script>alert('修改成功！！')</script>");
                out.print("<script>window.location.href='home.jsp'</script>");

                out.flush();
                out.close();

                return "success";
            } else {
                out.print("<script>alert('修改失败')</scriot>");
               
                return null;
            }

        } else 

            System.out.println("密码不正确");
            out.print("<script>alert('密码和原密码不相等')</script>");
             out.print("<script>window.location.href='changinformation.jsp'</script>");
//            out.print("<script>window.location.reload()</script>");
            
//         out.print("<script> window.location.replace(\"http://www.runoob.com\")</script>");
        
            
//            out.print("<script>window.location.reload()</script>");
            return null;
    }
}
