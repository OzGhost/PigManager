package mlib;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Static class holding connection to db during app life circle
 *
 * author: duc
 * create: 15-04-2017
 */
public abstract class db {
    private static Connection con = null;
    private static Statement stm = null;
    private static ResultSet rs = null;
    /**
     * Prepare for connect and use
     */
    public static void init (String ORACLE_SID, String username, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            db.con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:" + ORACLE_SID,
                        username, password
                    );
            db.stm = db.con.createStatement();
        } catch (Exception e) {
            System.out.println("---- Prepare connection failure !");
            e.printStackTrace();
        } finally {
            if (db.con != null && db.stm != null) {
                System.out.println("---- Connection ready to use <");
            }
        }
    }
    /**
     * Destroy connection
     */
    public static void destroy () {
        try {
            if (db.rs != null) {
                db.rs.close();
            }
            if (db.stm != null) {
                db.stm.close();
            }
            if (db.con != null) {
                db.con.close();
            }
        } catch (Exception e) {
            System.out.println("---- Something went wrong -> close connection failure");
            e.printStackTrace();
        }
    }
    /**
     * Query sender
     */
    public static List<Object> send (String sqlcmd) {
        // for debug only
        System.out.println("---- db.send(?) : " + sqlcmd);
        ArrayList<Object> result = new ArrayList<>();
        try {
            db.rs = db.stm.executeQuery(sqlcmd);
            while (db.rs.next()) {
                System.out.println(
                            db.rs.getString(1) + ", "
                            + db.rs.getString(2) + ", "
                            + db.rs.getString(3) + ", "
                            + db.rs.getInt(4)
                        );
            }
        } catch (Exception e) {
            System.out.println("---- Execute query failure !");
            e.printStackTrace();
        }
        return result;
    }
}
