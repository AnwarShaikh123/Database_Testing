package org.example;
import org.testng.annotations.*;
import java.sql.*;

public class BaseClass {

        public static Connection con;

        @BeforeMethod
        public Connection setUp () throws SQLException {
            try {
                String dburl = "jdbc:mysql://localhost:3306/CRUD";
                String Username = "root";
                String password = "Anwarshaikh@8652741234";
                con = DriverManager.getConnection(dburl, Username, password);
            } catch (SQLException sqe) {
                System.out.println(sqe.getErrorCode());
                System.out.println(sqe.getSQLState());
                System.out.println(sqe.getMessage());
                sqe.printStackTrace();
            }
            return con;
        }

    @Test
    public void insert_Data() throws SQLException {
        con = this.setUp();
        PreparedStatement ps = con.prepareStatement("insert into user (user_id,User_name) values(?,?)");
        ps.setString(1, "1");
        ps.setString(2, "Aryan");
        ps.addBatch();

        ps.setString(1, "2");
        ps.setString(2, "shaun");
        ps.addBatch();

        ps.setString(1, "3");
        ps.setString(2,"Laksh");
        ps.addBatch();
        ps.executeBatch();
        read_Data();
    }
    @Test
    public void read_Data() throws SQLException {
        con = this.setUp();
        Statement ps = con.createStatement();
        ResultSet rs = ps.executeQuery("Select * from user");
        while (rs.next()) {
            String userId = rs.getString(1);
            String userName = rs.getString(2);
            System.out.println(" " + userId + " " + userName);
        }
        }
    @Test
    public void update_Data() throws SQLException {
        con = this.setUp();
        PreparedStatement ps = con.prepareStatement("update user set User_name='Anwar_Shaikh' where user_id=2");
        ps.executeUpdate();
        read_Data();
        }
    @Test
    public void delete_Data() throws SQLException {
        con = this.setUp();
        PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE user");
        ps.executeUpdate();
        read_Data();

    }
        @AfterClass
        public void tear_Down() throws SQLException {
            con.close();
    }
}
