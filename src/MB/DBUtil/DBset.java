package MB.DBUtil;

import java.sql.*;

public class DBset {
    private Connection conn = null;
    private PreparedStatement prep = null;
    private ResultSet rs = null;
    //创建与数据库的连接
    public Connection getConn(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Successful!");
                String url = "cc-mysqlconnection-insideweb-999root.mysql.rds.aliyuncs.com/mybetters";
                String uname = "cc_root_mysql999";
                String upwd = "qazXcsdfGxiaoCHANG@0427XixOXU";
                this.conn = DriverManager.getConnection(url,uname,upwd);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("加载类没有找到！");
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Fail!");
            }
            return conn;
    }
    //关闭数据流以及断开与数据库的连接
    public void closeAll(Connection conn,PreparedStatement prep,ResultSet rs){
        this.conn = conn;
        this.prep = prep;
        this.rs = rs;
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(prep != null){
            try{
                prep.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    //创建数据库操作对象PreparedStatement
    public PreparedStatement getPrep(Connection conn, String sql){
        this.conn = conn;
        try{
            this.prep = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prep;
    }
    //封装DML语句
    public int excuteDML(String sql, Object...objs) {
        int n = 0;
        try {
            this.conn = getConn();
            this.prep = getPrep(conn, sql);
            for (int i = 0; i < objs.length; i++) {
                prep.setObject(i+1, objs[i]);
            }
            n = prep.executeUpdate();	//返回改变记录的条数
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,prep,null);
        }
        return n;
    }
    public ResultSet getRs() {
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
