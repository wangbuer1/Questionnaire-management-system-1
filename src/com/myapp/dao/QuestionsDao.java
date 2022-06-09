package com.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.myapp.bean.Questions;
import com.myapp.bean.Wenjuan;
import com.myapp.util.BaseConnection;

public class QuestionsDao {
    Questions question;

    public boolean insert(Questions qst) {
        boolean b = false;

        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "insert into questions (q_content,o_content,answers,w_id) " +
                    "values (?,?,?,?)";//占位符
            ps = conn.prepareStatement(sql);
            ps.setString(1, qst.getQ_content());
            ps.setString(2, qst.getO_content());
            ps.setString(3, qst.getAnswers());
            ps.setInt(4, qst.getW_id());
            int a = ps.executeUpdate();
            if (a > 0) {
                b = true;
            }
			
			/*ps.close();
			conn.close();*/
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //BaseConnection.closeRes(ps, conn);
        }

        return b;
    }


    public boolean delete(int id) {
        boolean b = false;
        try {

            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "delete from questions where q_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int a = ps.executeUpdate();
            if (a > 0) {
                b = true;
            }
			
			/*ps.close();
			conn.close();*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //BaseConnection.closeRes( ps, conn);
        }
        return b;
    }

    public ArrayList select(String id) {
        boolean b = false;
        ResultSet rs = null;

        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "select * from questions  where w_id= " + id;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList ar = new ArrayList();

            while (rs.next()) {
                Questions q = new Questions(null, null);
                q.setQ_content(rs.getString("q_content"));
                q.setO_content(rs.getString("o_content"));
                q.setQ_id(rs.getInt("q_id"));
                q.setAnswers(rs.getString("answers"));
                ar.add(q);
            }
            System.out.println(ar.size());
            return ar;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //BaseConnection.closeRes(rs, ps, conn);
        }

        return null;
    }

    public ArrayList<Questions> getListAll() throws Exception {
        //1.创建ArrayList用于存储数据库中传来的数据
        ArrayList<Questions> ar = new ArrayList<Questions>();
        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        PreparedStatement ps = null;
        //声明ResultSet的对象（结果集对象）
        ResultSet rs = null;
        try {
            String sql = "select * from questions";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Questions q = new Questions();
                q.setQ_id(rs.getInt("q_id"));
                q.setQ_content(rs.getString("q_content"));
                ar.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //BaseConnection.closeRes(rs, ps, conn);
        }
        return ar;
    }

    public String getAnswers(String w_id, String q_id) throws Exception {

        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn3 = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        Statement ps3 = null;
        //声明ResultSet的对象（结果集对象）
        ResultSet rs3 = null;

        String answer = null;
        try {
            String sql3 = "select *  from questions where w_id=" + w_id + "  and  q_id=" + q_id;

            ps3 = conn3.createStatement();

            rs3 = ps3.executeQuery(sql3);
            if (rs3.next()) {

                answer = rs3.getString("answers");

            }
            System.out.println("答案" + answer);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return answer;

    }


    public List<String> UpdateAnswers(String w_id, String q_id, String answers) throws Exception {
        //1.创建ArrayList用于存储数据库中传来的数据
        ArrayList<String> ar4 = new ArrayList<>();
        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn4 = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        Statement ps4 = null;
        //声明ResultSet的对象（结果集对象）
        ResultSet rs4 = null;


        try {
            String sql4 = "update  questions  set answers= '" + answers + "'  where   q_id=" + q_id;

            ps4 = conn4.createStatement();

            ps4.executeUpdate(sql4);


            return ar4;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return ar4;

    }

    public String getOption(String q_id) throws Exception {

        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn5 = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        Statement ps5 = null;
        //声明ResultSet的对象（结果集对象）
        ResultSet rs5 = null;

        String answer = null;
        try {
            String sql3 = "select *  from questions where q_id=" + q_id;

            ps5 = conn5.createStatement();

            rs5 = ps5.executeQuery(sql3);
            if (rs5.next()) {
                answer = rs5.getString("o_content");
            }
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return answer;

    }
}
