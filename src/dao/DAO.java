package dao;
import java.sql.Connection;
import java.sql.DriverManager;
public class DAO {
    protected Connection con;
    public DAO() {
        String dbUrl = "jdbc:sqlserver:
        String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl, "sa", "000569"); 
        } catch (Exception e) {
            System.out.println("Chưa kết nối được CSDL (Có thể thiếu thư viện mssql-jdbc.jar hoặc sai pass sa): "
                    + e.getMessage());
        }
    }
}
