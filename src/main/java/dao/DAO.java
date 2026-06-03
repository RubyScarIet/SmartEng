package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    protected Connection con;

    public DAO() {
        String dbUrl = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databaseName=SmartEngDB;encrypt=true;trustServerCertificate=true;";
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try {
            Class.forName(driverClass);
            this.con = DriverManager.getConnection(dbUrl, "sa", "1");
            // System.out.println("Kết nối CSDL SmartEngDB thành công!");
        } catch (Exception e) {
            System.out.println("Chưa kết nối được CSDL (Có thể thiếu thư viện mssql-jdbc.jar hoặc sai cấu hình): " + e.getMessage());
        }
    }
}
