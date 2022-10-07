import javax.swing.*;
import java.awt.*;

public class Mytext extends JFrame{


    protected JMenuBar menuBar;
    protected JMenu File,Search,View,Manage,Help;
    //菜单栏内的菜单
    protected JMenuItem file_new;
    protected JMenuItem file_open;
    protected static JMenuItem file_save;
    protected static JMenuItem file_saveAs;
    protected JMenuItem file_exit;
    //对于file菜单的子项
    protected static JMenuItem manage_select;
    protected static JMenuItem manage_cut;
    protected static JMenuItem manage_copy;
    protected static JMenuItem manage_paste;
    protected static JMenuItem manage_undo;
    protected static JMenuItem manage_redo;
    //对于edit菜单的子项
    protected JMenuItem help_about;
    //对于help菜单的子项
    protected static JTextArea myTextArea;
    protected JScrollPane my;

    protected static JTextArea edit_text_area;


    public Mytext(){
        myTextArea=new JTextArea();
        my=new JScrollPane(myTextArea);
        menuBar=new JMenuBar();

        File=new JMenu("File");
        Search=new JMenu("Search");
        View=new JMenu("View");
        Manage=new JMenu("Manage");
        Help=new JMenu("Help");
        //menu
        file_new=new JMenuItem("New");
        file_open=new JMenuItem("Open");
        file_save=new JMenuItem("Save");
        file_exit=new JMenuItem("Exit");
        file_saveAs=new JMenuItem("SaveAs");

        File.add(file_new);
        File.add(file_open);
        File.add(file_save);
        File.add(file_saveAs);
        File.add(file_exit);
        //file
        manage_select=new JMenuItem("Select");
        manage_cut=new JMenuItem("Cut");
        manage_copy=new JMenuItem("Copy");
        manage_paste=new JMenuItem("Paste");
        manage_undo=new JMenuItem("Undo");
        manage_redo=new JMenuItem("Redo");

        Manage.add(manage_select);
        Manage.add(manage_cut);
        Manage.add(manage_copy);
        Manage.add(manage_paste);
        Manage.add(manage_undo);
        Manage.add(manage_redo);
        //manage
        help_about=new JMenuItem("About");
        Help.add(help_about);
        //help
        menuBar.add(File);
        menuBar.add(Search);
        menuBar.add(View);
        menuBar.add(Manage);
        menuBar.add(Help);
        //menuBar
        my.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTextArea.setFont(new Font("Arial",Font.PLAIN, 15));
        myTextArea.setForeground(Color.black);
        //text area


        new manage();
        new file();

        this.add(my);
        this.setJMenuBar(menuBar);
        this.setSize(800,600);
        this.setTitle("Test Editor");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JTextArea getEdit_text_area() {
        return edit_text_area;
    }

}

