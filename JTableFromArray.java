package JTableHW;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URI;
import java.net.URLEncoder;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

// заполнение таблицы из массива
public class JTableFromArray {

    public static void main(String[] args) {

        Object[] columnHeaders = {"Фамилия", "Имя", "Отчество", "email"}; // массив с названиями столбцов

        //Массив с данными для таблицы
        Object[][] tableData = {
            {"Иванов", "Иван", "Иванович", "test1@mail.ru"},
            {"Петров", "Олег", "Петрович", "test2@mail.ru"},
            {"Алексеев", "Алексей", "Алексеевич", "test3@mail.ru"},
            {"Сидоров", "Сидор", "Сидорович", "test4@mail.ru"},
            {"Олегов", "Олег", "Олегович", "test5@mail.ru"}
        };


        JFrame frame = new JFrame("Пример данных из массива");

        frame.getContentPane().setLayout(new FlowLayout());

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTable jtableFIO = new JTable(tableData, columnHeaders);

        jtableFIO.setRowHeight(30);

        jtableFIO.setDefaultRenderer(Object.class, new MyTableRenderer());

        jtableFIO.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    String value = target.getValueAt(target.getSelectedRow(), target.getSelectedColumn()).toString();

                    try {
                        if (value.contains("@")) {
                            Desktop.getDesktop().mail(new URI("mailto:" + value + "?SUBJECT=Служебное%20письмо&body=Текст%20письма"));
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        jtableFIO.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                if (target.columnAtPoint(e.getPoint())==3){
                    target.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }else{
                    target.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        

        JScrollPane scroll = new JScrollPane(jtableFIO);

        // размеры прокручиваемой области
        jtableFIO.setPreferredScrollableViewportSize(new Dimension(400, 200));

        frame.getContentPane().add(scroll);

        frame.setVisible(true);

    }
}
