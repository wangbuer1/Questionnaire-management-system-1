package com.myapp.bean;

public class Questions {

    private int q_id;
    private String q_content;
    private  String o_content;
    private String answers;
    private int w_id;

    public String getO_content() {
        return o_content;
    }

    public void setO_content(String o_content) {
        this.o_content = o_content;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public Questions(String q_content, String o_content,String answers) {
        this.q_content = q_content;
        this.o_content = o_content;
        this.answers = answers;
    }

    public Questions(String q_content, String o_content) {
        this.q_content = q_content;
        this.o_content = o_content;

    }

    public Questions() {

    }

}
