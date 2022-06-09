package com.myapp.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayAction extends ActionSupport {
    @Override
    public String execute() throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String  id= request.getParameter("id");

        HttpSession session=request.getSession();
        request.setAttribute("id",id);
        System.out.println(id);

        return super.execute();
    }
}
