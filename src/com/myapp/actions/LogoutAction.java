package com.myapp.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends ActionSupport{
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        request.setAttribute("sessionId", session.getId());
        session.removeAttribute("user");
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/login2.jsp");
        return "success";
    }
}
