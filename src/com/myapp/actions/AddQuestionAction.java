
package com.myapp.actions;

import com.myapp.bean.User;
import com.myapp.dao.LRDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddQuestionAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");

        response.getWriter().println("<script>window.alert('添加成功！！');window.location.href='view_QO.jsp';</script>");
        return "success";
    }
}
