import javax.swing.*;
import java.awt.*;

public class Mytext extends JFrame{
    protected JMenuBar menuBar;
    protected JMenu File,Search,View,Manage,Help;
    //菜单栏内的菜单
    protected JMenuItem file_new,file_open,file_save,file_exit;
    //对于file菜单的子项
    protected JMenuItem manage_undo,manage_select,manage_cut,manage_copy,manage_paste,manage_delete;
    //对于edit菜单的子项
    protected JMenuItem help_about;
    //对于help菜单的子项
    protected JTextArea myTextArea;
    protected JScrollPane my;
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
        File.add(file_new);
        File.add(file_open);
        File.add(file_save);
        File.add(file_exit);
        //file
        manage_undo=new JMenuItem("Undo");
        manage_select=new JMenuItem("Select");
        manage_cut=new JMenuItem("Cut");
        manage_copy=new JMenuItem("Copy");
        manage_paste=new JMenuItem("Paste");
        manage_delete=new JMenuItem("Delete");
        Manage.add(manage_undo);
        Manage.add(manage_select);
        Manage.add(manage_cut);
        Manage.add(manage_copy);
        Manage.add(manage_paste);
        Manage.add(manage_delete);
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
        this.add(my);
        this.setJMenuBar(menuBar);
        this.setSize(800,600);
        this.setTitle("Test Editor");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
