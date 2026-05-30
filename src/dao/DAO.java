package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DAO<T> {

    public DAO() {
    }

    /**
     * Ghi danh sách đối tượng vào file
     */
    public void writeDataToFile(List<T> list, String fileName) {
        try (FileOutputStream fos = new FileOutputStream("data/" + fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Đọc danh sách đối tượng từ file
     */
    @SuppressWarnings("unchecked")
    public List<T> readDataFromFile(String fileName) {
        List<T> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("data/" + fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                list = (List<T>) obj;
            }
        } catch (Exception e) {
            // Có thể file chưa tồn tại ở lần chạy đầu tiên
            System.out.println("Lưu ý: Không tìm thấy file hoặc file trống (" + fileName + ")");
        }
        return list;
    }
}