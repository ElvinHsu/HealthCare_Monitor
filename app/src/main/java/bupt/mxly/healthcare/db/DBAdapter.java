package bupt.mxly.healthcare.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
    private static final int TEST_USER_SELECT = 1;
    UserInfo user = new UserInfo();

    List<DataInfo> datalist = new ArrayList<DataInfo>();


    public UserInfo queryUserInfo(final String phone){
        Thread sqlthread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "select * from userInfo where phone='"+phone+"'";
                Statement st;
                try {
                    st = (Statement) conn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()){
                        //因为查出来的数据试剂盒的形式，所以我们新建一个javabean存储
                        user.setPhone(rs.getString(1));
                        user.setPwd(rs.getString(2));
                        user.setName(rs.getString(3));
                        user.setAge(rs.getInt(4));
                        user.setHeight(rs.getDouble(5));
                        user.setWeight(rs.getDouble(6));
                        user.setSex(rs.getString(7));
                        user.setBlood(rs.getString(8));
                        user.setHistory(rs.getString(9));
                        user.setAddress(rs.getString(10));
                        i++;
                    }
                    st.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sqlthread.start();
        try {
            sqlthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }//查询用户信息

    public void deleteUserInfo(final String phone){
        Thread sqlthread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "delete from userInfo where phone='"+phone+"'";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sqlthread.start();
    }//删除用户信息

    public void insertUserInfo(final UserInfo info){
        final Thread sqlthread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into userInfo (phone,pwd,name,age,height,weight,sex,blood,history,address) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    //将输入的edit框的值获取并插入到数据库中
                    pst.setString(1,info.getPhone());
                    pst.setString(2,info.getPwd());
                    pst.setString(3,info.getName());
                    pst.setInt(4,info.getAge());
                    pst.setDouble(5,info.getHeight());
                    pst.setDouble(6,info.getWeight());
                    pst.setString(7,info.getSex());
                    pst.setString(8,info.getBlood());
                    pst.setString(9,info.getHistory());
                    pst.setString(10,info.getAddress());

                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sqlthread.start();
    }//添加用户信息

    public void updateUserInfo(final UserInfo info){
        final Thread sqlthread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "update userInfo set pwd=?,name=?,age=?,height=?,weight=?,sex=?,blood=?,history=?,address=? where phone=?";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1,info.getPwd());
                    pst.setString(2,info.getName());
                    pst.setInt(3,info.getAge());
                    pst.setDouble(4,info.getHeight());
                    pst.setDouble(5,info.getWeight());
                    pst.setString(6,info.getSex());
                    pst.setString(7,info.getBlood());
                    pst.setString(8,info.getHistory());
                    pst.setString(9,info.getAddress());
                    pst.setString(10,info.getPhone());
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sqlthread.start();
    }//更新用户信息


    public List<DataInfo> queryDataInfo(final String userId,final String dataType){
        //根据电话号码（主键）查询用户信息
        Thread sqlthread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "select * from dataInfo where userId='"+userId+"' and dataType = '"+dataType+"'" ;
                Statement st;
                try {
                    st = (Statement) conn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()){
                        //因为查出来的数据试剂盒的形式，所以我们新建一个javabean存储
                        DataInfo data = new DataInfo();
                        data.setUserId(rs.getString(1));
                        data.setCollectTime(rs.getTime(2));
                        data.setHealthData(rs.getString(3));
                        data.setDataType(rs.getString(4));
                        data.setExcp(rs.getInt(5));
                        data.setDataId(rs.getInt(6));
                        datalist.add(data);
                    }
                    st.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sqlthread.start();
        try {
            sqlthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return datalist;
    }//查询健康数据

    public void deleteDataInfo(final String phone){
        Thread sqlthread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u = 0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "delete from userInfo where phone='"+phone+"'";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sqlthread.start();
    }//删除健康数据

    public void insertDataInfo(final DataInfo info){
        final Thread sqlthread = new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                int u=0;
                conn =(Connection) DBOpenHelper.getConn();
                String sql = "insert into dataInfo (userId,collectTime,healthData,dataType,excp) values(?,?,?,?,?)";
                PreparedStatement pst;
                try {
                    pst = (PreparedStatement) conn.prepareStatement(sql);
                    //将输入的edit框的值获取并插入到数据库中
                    pst.setString(1,info.getUserId());
                    pst.setDate(2, (Date) info.getCollectTime());
                    pst.setString(3,info.getHealthData());
                    pst.setString(4,info.getDataType());
                    pst.setInt(5,info.getExcp());

                    u = pst.executeUpdate();
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        sqlthread.start();
    }//添加健康数据

//    public void updateDataInfo(final UserInfo info){
//        final Thread sqlthread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Connection conn = null;
//                int u = 0;
//                conn =(Connection) DBOpenHelper.getConn();
//                String sql = "update userInfo set pwd=?,name=?,age=?,height=?,weight=?,sex=?,blood=?,history=?,address=? where phone=?";
//                PreparedStatement pst;
//                try {
//                    pst = (PreparedStatement) conn.prepareStatement(sql);
//                    pst.setString(1,info.getPwd());
//                    pst.setString(2,info.getName());
//                    pst.setInt(3,info.getAge());
//                    pst.setDouble(4,info.getHeight());
//                    pst.setDouble(5,info.getWeight());
//                    pst.setString(6,info.getSex());
//                    pst.setString(7,info.getBlood());
//                    pst.setString(8,info.getHistory());
//                    pst.setString(9,info.getAddress());
//                    pst.setString(10,info.getPhone());
//                    u = pst.executeUpdate();
//                    pst.close();
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        sqlthread.start();
//    }


}
