package com.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myapp.bean.Wenjuan;
import com.myapp.util.BaseConnection;


public class WenjuanDao {

    Wenjuan wenjuan;

    public boolean insert(Wenjuan wj) {
        boolean b = false;

        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "insert into wenjuan (w_title, w_type, w_endtime, u_id)" +
                    "values (?,?,?,?)";//占位符
            ps = conn.prepareStatement(sql);
            ps.setString(1, wj.getW_title());
            ps.setString(2, wj.getW_type());
            ps.setDate(3, wj.getW_endtime());
            ps.setString(4, wj.getU_id());

            int a = ps.executeUpdate();
            if (a > 0) {
                b = true;
            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //BaseConnection.closeRes(ps, conn);
        }

        return b;
    }

    public ArrayList<Wenjuan> getListAll() throws Exception{
		ArrayList<Wenjuan> ar = new ArrayList<Wenjuan>();
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<List> list= new ArrayList<List>();
		try {
			String sql = "select * from wenjuan";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Wenjuan w = new Wenjuan(null, null,null,null);
				List<String> questions = new ArrayList<String>();
				String sql2 = "select * from questions WHERE ?";
				PreparedStatement stmt = conn.prepareStatement(sql2);
				stmt.setInt(1, rs.getInt("w_id"));
				ResultSet set = stmt.executeQuery();
				/*while (set.next()){
					questions.add(set.getString("q_id"));
					questions.add(set.getString("q_content"));
					questions.add(set.getString("o_content"));
				}*/
				w.setW_id(rs.getInt("w_id"));
				w.setW_title(rs.getString("w_title"));
				w.setW_type(rs.getString("w_type"));
				w.setW_endtime(rs.getDate("w_endtime"));
				ar.add(w);
				list.add(ar);
//				list.add(questions);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//BaseConnection.closeRes(rs, ps, conn);
		}
		return ar;

	}


    public int getID() throws Exception {
        //1.创建ArrayList用于存储数据库中传来的数据
        ArrayList<Wenjuan> ar2 = new ArrayList<>();
        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn2 = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        Statement ps2 = null;
        //声明ResultSet的对象（结果集对象）
        ResultSet rs = null;
        int id = 0;
        try {
            String sql2 = "select * from wenjuan order  by w_id desc limit 0,1 ;";
            ps2 = conn2.createStatement();
            rs = ps2.executeQuery(sql2);
            if (rs.next()) {
                id = rs.getInt("w_id");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        //BaseConnection.closeRes(rs, ps, conn);
        }
        return 0;

    }

    public ArrayList<Wenjuan> getListAll1() throws Exception {
        //1.创建ArrayList用于存储数据库中传来的数据
        ArrayList<Wenjuan> ar2 = new ArrayList<>();
        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn2 = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        Statement ps2 = null;
        //声明ResultSet的对象（结果集对象）
        ResultSet rs = null;
        try {
            String sql2 = "select * from wenjuan"; //+
            //" where user.u_id = wenjuan.w_title";
            ps2 = conn2.createStatement();
            rs = ps2.executeQuery(sql2);
            while (rs.next()) {
                Wenjuan w2 = new Wenjuan(null, null, null, null);
                w2.setW_title(rs.getString("w_title"));
                w2.setW_type(rs.getString("w_type"));
                w2.setW_endtime(rs.getDate("w_endtime"));
                ar2.add(w2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //BaseConnection.closeRes(rs, ps, conn);
        }
        return ar2;

    }

    public ArrayList<Wenjuan> getMyWenjuan(String u_id) throws Exception {
        ArrayList<Wenjuan> ar5 = new ArrayList<>();
        //2.通过写好的Connection对象获取数据库连接（引入Java.sql包）
        Connection conn5 = BaseConnection.getConnection();
        //3.SQL执行器对象（PreparedStatement比 Statement更好）
        Statement ps5 = null;
        //声明ResultSet的对象（结果集对象）
        ResultSet rs5 = null;
        try {
            String sql5 = "select *  from wenjuan where u_id=" + u_id;
            ps5 = conn5.createStatement();
            rs5 = ps5.executeQuery(sql5);
            while (rs5.next()) {
                Wenjuan w5 = new Wenjuan(null, null, null, null);
                w5.setW_title(rs5.getString("w_title"));
                w5.setW_type(rs5.getString("w_type"));
                w5.setW_endtime(rs5.getDate("w_endtime"));
                w5.setW_id(rs5.getInt("w_id"));
                ar5.add(w5);
            }
            return ar5;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //BaseConnection.closeRes(rs, ps, conn);
        }
        return ar5;

    }

    public boolean delete(int id) {
        boolean b = false;
        try {
            Connection conn = BaseConnection.getConnection();
            PreparedStatement ps = null;
            String sql = "delete from wenjuan where w_id = ?";

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
