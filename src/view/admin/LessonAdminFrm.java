package view.admin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.LessonDAO;

public class LessonAdminFrm extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Search components
    private JTextField txtName;
    private JTextField txtCode;
    private JButton btnSearch;
    private JTable tblLessons;
    private DefaultTableModel tableModel;

    public LessonAdminFrm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel initialCard = createInitialCard();
        JPanel searchCard = createSearchCard();

        mainPanel.add(initialCard, "Initial");
        mainPanel.add(searchCard, "Search");

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInitialCard() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));

        JButton btnAdd = createActionBtn("Add");
        JButton btnEdit = createActionBtn("Edit");
        JButton btnDelete = createActionBtn("Delete");
        
        btnEdit.addActionListener(e -> {
            cardLayout.show(mainPanel, "Search");
            // Do NOT load data automatically. User must search first.
            tableModel.setRowCount(0);
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        p.add(buttonPanel, BorderLayout.SOUTH);
        return p;
    }

    private JPanel createSearchCard() {
        JPanel p = new JPanel(new BorderLayout(0, 20));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Search Panel
        JPanel searchTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        searchTop.setBackground(Color.WHITE);

        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(200, 40));
        txtName.setBorder(BorderFactory.createCompoundBorder(
            new view.admin.RoundedBorder(20, new Color(150, 150, 160), 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        
        // Placeholder simulation
        txtName.setText("Enter a name");
        txtName.setForeground(Color.GRAY);
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtName.getText().equals("Enter a name")) {
                    txtName.setText("");
                    txtName.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtName.getText().isEmpty()) {
                    txtName.setText("Enter a name");
                    txtName.setForeground(Color.GRAY);
                }
            }
        });

        txtCode = new JTextField();
        txtCode.setPreferredSize(new Dimension(200, 40));
        txtCode.setBorder(BorderFactory.createCompoundBorder(
            new view.admin.RoundedBorder(20, new Color(150, 150, 160), 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        
        txtCode.setText("Enter a code");
        txtCode.setForeground(Color.GRAY);
        txtCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtCode.getText().equals("Enter a code")) {
                    txtCode.setText("");
                    txtCode.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtCode.getText().isEmpty()) {
                    txtCode.setText("Enter a code");
                    txtCode.setForeground(Color.GRAY);
                }
            }
        });

        btnSearch = createActionBtn("Search");
        btnSearch.addActionListener(e -> {
            String n = txtName.getText().equals("Enter a name") ? "" : txtName.getText().trim();
            String c = txtCode.getText().equals("Enter a code") ? "" : txtCode.getText().trim();
            
            if (n.isEmpty() && c.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên hoặc mã để tìm kiếm!");
                tableModel.setRowCount(0); // clear table if empty search
                return;
            }
            
            performSearch(n, c);
        });

        searchTop.add(txtName);
        searchTop.add(txtCode);
        searchTop.add(btnSearch);

        p.add(searchTop, BorderLayout.NORTH);

        // Center Table Panel
        String[] columns = {"ID", "Name", "Code", "Content", "Describe"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0; // All columns editable except ID
            }
        };
        tblLessons = new JTable(tableModel);
        tblLessons.setRowHeight(35);
        tblLessons.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tblLessons.getTableHeader().setBackground(new Color(245, 248, 250));
        tblLessons.getTableHeader().setReorderingAllowed(false);
        tblLessons.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tblLessons.setGridColor(new Color(220, 220, 220));
        
        JScrollPane scrollPane = new JScrollPane(tblLessons);
        scrollPane.setBorder(new view.admin.RoundedBorder(10, new Color(150, 150, 160), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        p.add(scrollPane, BorderLayout.CENTER);

        // Bottom right Update button
        JPanel botPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        botPanel.setBackground(Color.WHITE);
        JButton btnUpdate = createActionBtn("Update");
        btnUpdate.addActionListener(e -> {
            if (tblLessons.isEditing()) {
                tblLessons.getCellEditor().stopCellEditing();
            }
            
            LessonDAO dao = new LessonDAO();
            boolean success = true;
            for(int i = 0; i < tableModel.getRowCount(); i++) {
                int id = (Integer)tableModel.getValueAt(i, 0);
                String name = (String)tableModel.getValueAt(i, 1);
                String code = (String)tableModel.getValueAt(i, 2);
                String content = (String)tableModel.getValueAt(i, 3);
                String describe = (String)tableModel.getValueAt(i, 4);
                
                if (!dao.updateLesson(id, name, code, content, describe)) {
                    success = false;
                }
            }
            
            if(success) {
                JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công!");
                // Clear inputs
                txtName.setText("Enter a name");
                txtName.setForeground(Color.GRAY);
                txtCode.setText("Enter a code");
                txtCode.setForeground(Color.GRAY);
                tableModel.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật!");
            }
        });
        botPanel.add(btnUpdate);
        p.add(botPanel, BorderLayout.SOUTH);

        return p;
    }

    private void performSearch(String name, String code) {
        LessonDAO dao = new LessonDAO();
        List<Object[]> data = dao.searchLessons(name, code);
        tableModel.setRowCount(0);
        for(Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    private JButton createActionBtn(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.setForeground(new Color(30, 40, 60));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(100, 40));
        btn.setBorder(new view.admin.RoundedBorder(20, new Color(100, 100, 110), 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
