package com.myapp.dao;

import com.myapp.bean.User;
import com.myapp.bean.User_g;
import com.myapp.util.BaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class LRDao {
    User user;
    User_g user_g;

    public String Login(User u) {
        String id = null;
        String sql = "select * from user where username=? and password=?";
        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getString("u_id");
            return id;
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception

        }
        return null;
    }
       public String Update(User u) {
       String id =null;
        String sql = "update user set password=? where username=?";
        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getPassword());
            ps.setString(2, u.getUsername());
            int a = ps.executeUpdate();
            if (a>0)
            {
            id ="12";
            }
            
            return id;
        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception

        }
        return id;
    }


    public boolean Login_g(User_g u) {
        String sql = "select count(*) as count from user_g where username_g=? and password_g=?";
        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUsername_g());
            ps.setString(2, u.getPassword_g());
            ResultSet rs = ps.executeQuery();
            rs.next();

            if (rs.getInt("count") != 0)
                return true;
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
            return false;
        }

    }
    

    //?????????????????????????????????news?????????????????? ????????????????????????????????????????????????????????????
    public boolean insert(User u) {
        boolean b = false;
        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "insert into user (username,password) " +
                    "values (?,?)";//?????????

            ps = conn.prepareStatement(sql);//????????????????????????????????????
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());

            int a = ps.executeUpdate();//???????????????????????????????????????
            if (a > 0) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public ArrayList<User> getListAll() throws Exception {
        //1.??????ArrayList???????????????????????????????????????
        ArrayList<User> ar = new ArrayList<User>();
        //2.???????????????Connection????????????????????????????????????Java.sql??????
        Connection conn = BaseConnection.getConnection();
        //3.SQL??????????????????PreparedStatement??? Statement?????????
        Statement ps = null;
        //??????ResultSet??????????????????????????????
        ResultSet rs = null;
        try {
            String sql = "select * from user"; //+
            //" where user.u_id = wenjuan.w_title";

            ps = conn.createStatement();
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                User u = new User(null, null);
                u.setU_id(rs.getInt("u_id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                ar.add(u);


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return ar;

    }

    public boolean delete(int id) {
        boolean b = false;
        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "delete from user where u_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int a = ps.executeUpdate();
            if (a > 0) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //BaseConnection.closeRes( ps, conn);
        }
        return b;
    }


}
