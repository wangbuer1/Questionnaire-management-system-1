package com.myapp.actions;



import com.myapp.dao.QuestionsDao;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Tj extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题

        String id = request.getParameter("q_id");
        String tk = request.getParameter("tk");
//        System.out.println("填空" + tk);
        int itk = Integer.parseInt(tk);
        for (int i = 0; i < itk; i++) {
            String dtk = request.getParameter("tk" + i);
            String qtkid = request.getParameter("tkqid" + i);
            String answer = new QuestionsDao().getAnswers(id, qtkid);
            answer = answer + "&" + dtk;
            new QuestionsDao().UpdateAnswers(id, qtkid, answer);
        }
        String dx = request.getParameter("dx");
//        System.out.println("单选" + dx);
        int idx = Integer.parseInt(dx);
        for (int i = 0; i < idx; i++) {
            String ddxqid = request.getParameter("ddxqid" + i);
            String dfen = "";
            String[] answers = null;
            String answer = new QuestionsDao().getAnswers(id, ddxqid);
            String content = new QuestionsDao().getOption(ddxqid);
            if (answer != null) {
                answers = answer.split("&");
            }
//            System.out.println(answers.length);
            String[] contens = content.split("#");
            String ddx = request.getParameter("dx" + i);
            //        System.out.println("多选" + ddx);
            for (int j = 1; j < contens.length; j++) {
                if (j == 1) {
                    if (contens[j].substring(4).equals(ddx)) {
                        int ifen = Integer.parseInt(answers[j]) + 1;
                        dfen = dfen + "&" + ifen;
                    } else {
                        dfen = dfen + "&" + answers[j];
                    }
                } else {
                    if (contens[j].equals(ddx)) {
                        int ifen = Integer.parseInt(answers[j]) + 1;
                        dfen = dfen + "&" + ifen;
                    } else {
                        dfen = dfen + "&" + answers[j];

                    }
                }
            }
            new QuestionsDao().UpdateAnswers(id, ddxqid, dfen);
            System.out.println(ddx);
        }


        String mx = request.getParameter("mx");
        int imx = Integer.parseInt(mx);
        for (int i = 0; i < imx; i++) {
            String dmx = request.getParameter("imx" + i);
            String dxqid = request.getParameter("dxqid" + i);
            String answer = new QuestionsDao().getAnswers(id, dxqid);
            int idmx = Integer.parseInt(dmx);
            String[] dxanswer = null;
            String dfen = "";
            if (answer != null) {
                dxanswer = answer.split("&");
            } else {
                dxanswer = new String[idmx];
            }
            for (int j = 0; j < idmx; j++) {
                String iddmx = request.getParameter("mx" + i + j);
                if (iddmx != null) {
                    String fen = dxanswer[j + 1];
//                    System.out.println(fen + "   ");
                    int ifen = Integer.parseInt(fen) + 1;
                    dfen = dfen + "&" + ifen;
                } else {
                    dfen = dfen + "&"+dxanswer[j + 1];
                }
//                System.out.println(iddmx);
            }
            new QuestionsDao().UpdateAnswers(id, dxqid, dfen);
        }
        return "success";
    }
}
