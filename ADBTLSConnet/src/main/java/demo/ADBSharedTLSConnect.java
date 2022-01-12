/**
 * @Author Pallab (pallab.rath@gmail.com)
 */
package demo;

import java.sql.*;
import java.util.Properties;

public class ADBSharedTLSConnect {
    private static String atps_tls = "(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1521)(host=adb.ap-mumbai-1.oraclecloud.com))(connect_data=(service_name=iro8q5fzknp5ge4_demodb_medium.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)(ssl_server_cert_dn=\"CN=adb.ap-mumbai-1.oraclecloud.com, OU=Oracle ADB INDIA, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))";
    private static String db_url = "jdbc:oracle:thin:@" + atps_tls;
    private static String dbUser = "admin";
    private static String dbPwd = "demo@ATP12345";

    public static void main(String[] args) {
        System.out.println("Connecting to ATPS over TLS...");
        ResultSet rs = null;
        Statement stmt = null;
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Properties props = new Properties();
            props.setProperty("user", dbUser);
            props.setProperty("password", dbPwd);
            props.setProperty("oracle.jdbc.fanEnabled", "false");
            con = DriverManager.getConnection(db_url, props);
            stmt = con.createStatement();
            rs = stmt.executeQuery("select sysdate from dual");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            System.out.println("Demo Over...");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

