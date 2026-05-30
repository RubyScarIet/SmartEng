package dao;
import model.User;
public class UserDAO extends DAO {
    public UserDAO() { super(); }
    public boolean checkLogin(User user) { return true; }
}