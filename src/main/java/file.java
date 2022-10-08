import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class file extends JFrame implements ActionListener{
    public static int flag=0;
    String currentPath=null;
    String currentFileName=null;
    PrintJob p=null;
    Graphics g=null;

    FileDialog open=new FileDialog(this,"Open",FileDialog.LOAD);
    FileDialog save=new FileDialog(this,"Save",FileDialog.SAVE);


    public file(){
        Mytext.file_save.addActionListener(this);
        Mytext.file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        Mytext.file_saveAs.addActionListener(this);
        Mytext.file_saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        Mytext.file_open.addActionListener(this);
        Mytext.file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

        Mytext.file_new.addActionListener(this);
        Mytext.file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

        Mytext.file_print.addActionListener(this);
        Mytext.file_print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

        Mytext.file_exit.addActionListener(this);
    }

    static void isChanged(){
        Mytext.myTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character ch=e.getKeyChar();
                if(ch!=null&&!Mytext.myTextArea.getText().toString().equals("")){
                    flag=2;
                }
            }
        });
    }



    public void New(){
        if(flag==0 || flag==1){        //刚启动记事本为0，刚新建文档为1
            return;
        }else if(flag==2 && this.currentPath==null){        //修改后
            //1、（刚启动记事本为0，刚新建文档为1）条件下修改后
            int result=JOptionPane.showConfirmDialog(this, "Are you sure to save changes to untitled?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.saveAs();        //另存为
            }else if(result==JOptionPane.NO_OPTION){
                Mytext.myTextArea.setText("");
                this.setTitle("Untitled");
                flag=1;
            }
            return;
        }else if(flag==2 && this.currentPath!=null ){
            //2、（保存的文件为3）条件下修改后
            int result=JOptionPane.showConfirmDialog(this, "是否将更改保存到"+this.currentPath+"?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                this.save();        //直接保存，有路径
            }else if(result==JOptionPane.NO_OPTION){
                Mytext.myTextArea.setText("");
                this.setTitle("Untitled");
                flag=1;
            }
        }else if(flag==3){        //saved file
            Mytext.myTextArea.setText("");
            flag=1;
            this.setTitle("Untitled");
        }

    }

    public void saveAs(){

        JFileChooser jFileChooser=new JFileChooser();
        int res=jFileChooser.showSaveDialog(this);
        if(res==JFileChooser.APPROVE_OPTION){
            //取得选择的文件[文件名是自己输入的]
            File file=jFileChooser.getSelectedFile();
            FileWriter fw=null;
            //保存
            try {
                fw=new FileWriter(file);
                fw.write(Mytext.myTextArea.getText());
                currentFileName = file.getName();
                currentPath=file.getAbsolutePath();

                fw.flush();
                this.flag=3;
                this.setTitle(currentPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }finally{
                try {
                    if(fw!=null) fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    public void save() {
        if (this.currentPath == null) {
            this.saveAs();
            if (this.currentPath == null) {
                return;
            }
        }
        FileWriter fw = null;
        //save
        try {
            fw = new FileWriter(new File(currentPath));
            fw.write(Mytext.myTextArea.getText());
            fw.flush();
            flag = 3;
            this.setTitle(this.currentPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (fw != null) fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    public void open() {
        if (flag == 2 && this.currentPath == null) {
            int res = JOptionPane.showConfirmDialog(this, "Are you sure to save", "notebook", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                this.saveAs();
            } else if (flag == 2 && this.currentPath != null) {
                int result = JOptionPane.showConfirmDialog(this, "If you want to save changes to" + this.currentPath + "?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    this.save();

                }
            }

        }
        JFileChooser choose = new JFileChooser();
        //choose file
        int result = choose.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            //get the chosen file
            File file = choose.getSelectedFile();
            //打开已存在的文件，提前将文件名存起来
            currentFileName = file.getName();
            //存在文件全路径
            currentPath = file.getAbsolutePath();
            flag = 3;
            this.setTitle(this.currentPath);
            BufferedReader br = null;
            try {
                //建立文件流[字符流]
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                br = new BufferedReader(isr);//动态绑定
                //读取内容
                StringBuffer sb = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append(SystemParam.LINE_SEPARATOR);
                }
                //显示在文本框[多框]
                Mytext.myTextArea.setText(sb.toString());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (br != null) br.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }
    }
    public void exit() {
        if (flag == 2 && currentPath == null) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure to save changes to untitled?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                saveAs();
            } else if (result == JOptionPane.NO_OPTION) {
                this.dispose();
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        } else if (flag == 2 && currentPath != null) {

            int result = JOptionPane.showConfirmDialog(this, "If you want to save changes to" + currentPath + "?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                save();
            } else if (result == JOptionPane.NO_OPTION) {
                this.dispose();
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure to close?", "System prompt", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                this.dispose();
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
    }
    public void print(){
        try{
            p= getToolkit().getPrintJob(this,"ok",null);
            g=p.getGraphics();
            Mytext.myTextArea.printAll(g);
            p.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Mytext.file_open){
            open();
        }
        if(e.getSource()==Mytext.file_new){
            New();
        }
        if(e.getSource()==Mytext.file_save){
            save();
        }
        if(e.getSource()==Mytext.file_saveAs){
            saveAs();
        }
        if(e.getSource()==Mytext.file_exit){
            exit();
        }
        if(e.getSource()==Mytext.file_print){
            print();
        }

    }
}