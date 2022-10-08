import javax.swing.*;
import java.awt.*;

public class Mytext extends JFrame{


    protected JMenuBar menuBar;
    protected JMenu File,Search,View,Manage,Format,Help;
    //菜单栏内的菜单
    protected static JMenuItem file_new;
    protected static JMenuItem file_open;
    protected static JMenuItem file_save;
    protected static JMenuItem file_saveAs;
    protected static JMenuItem file_exit;
    protected static JMenuItem file_print;
    //file
    protected static JMenuItem Find,Replace;
    //search
    protected static JMenuItem manage_select;
    protected static JMenuItem manage_cut;
    protected static JMenuItem manage_copy;
    protected static JMenuItem manage_paste;
    protected static JMenuItem manage_undo;
    protected static JMenuItem manage_redo;
    protected static JMenuItem manage_time;
    //manage
    protected static JMenuItem autoLine,FontSet;
    //source
    protected static JMenuItem help_about;
    //help
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
        Format=new JMenu("Format");
        Help=new JMenu("Help");
        File.setMnemonic('f');
        Search.setMnemonic('s');
        View.setMnemonic('v');
        Manage.setMnemonic('m');
        Format.setMnemonic('o');
        Help.setMnemonic('h');
        //menu
        file_new=new JMenuItem("New");
        file_open=new JMenuItem("Open");
        file_save=new JMenuItem("Save");
        file_exit=new JMenuItem("Exit");
        file_saveAs=new JMenuItem("SaveAs");
        file_print=new JMenuItem("Print");

        File.add(file_new);
        File.add(file_open);
        File.add(file_save);
        File.add(file_saveAs);
        File.add(file_print);
        File.add(file_exit);

        //file
        Find=new JMenuItem("Find");
        Replace=new JMenuItem("Replace");
        Search.add(Find);
        Search.add(Replace);
        //search
        manage_select=new JMenuItem("Select");
        manage_cut=new JMenuItem("Cut");
        manage_copy=new JMenuItem("Copy");
        manage_paste=new JMenuItem("Paste");
        manage_undo=new JMenuItem("Undo");
        manage_redo=new JMenuItem("Redo");
        manage_time=new JMenuItem("Time and Date");
        Manage.add(manage_select);
        Manage.add(manage_cut);
        Manage.add(manage_copy);
        Manage.add(manage_paste);
        Manage.add(manage_undo);
        Manage.add(manage_redo);
        Manage.add(manage_time);
        //manage
        autoLine=new JMenuItem("AutoLine");
        FontSet=new JMenuItem("Fontset");
        Format.add(autoLine);
        Format.add(FontSet);
        //source
        help_about=new JMenuItem("About");
        Help.add(help_about);
        //help
        menuBar.add(File);
        menuBar.add(Search);
        menuBar.add(View);
        menuBar.add(Manage);
        menuBar.add(Format);
        menuBar.add(Help);
        //menuBar
        my.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
        myTextArea.setForeground(Color.black);
        //text area


        new manage();
        new file();
        new about();
        new format();
        new search();
        new Font_Format(Mytext.myTextArea.getFont());
        this.add(my);
        this.setJMenuBar(menuBar);
        this.setSize(800,600);
        this.setTitle("Test Editor");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        file.isChanged();
    }

    public static JTextArea getEdit_text_area() {
        return edit_text_area;
    }

}

