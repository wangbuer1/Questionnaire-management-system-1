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
    

    //该方法负责将传递过来的news对象中的数据 存入数据库中。方法二：（推荐使用占位符）
    public boolean insert(User u) {
        boolean b = false;
        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "insert into user (username,password) " +
                    "values (?,?)";//占位符

            ps = conn.prepareStatement(sql);//将写好的数据传递到数据库
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());

            int a = ps.executeUpdate();//这个方法用于改变数据库数据
            if (a > 0) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public ArrayList<User> getListAll() throws Exception {
        //1.创建ArrayList用于存储数据库中传来的数据
        ArrayList<User> ar = new ArrayList<User>();
        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        Statement ps = null;
        //声明ResultSet的对象（结果集对象）
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
