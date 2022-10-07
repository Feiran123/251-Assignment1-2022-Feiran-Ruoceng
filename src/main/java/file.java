import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class file extends JFrame implements ActionListener{
    int flag=0;
    String currentPath=null;
    String currentFileName=null;

    FileDialog open=new FileDialog(this,"Open",FileDialog.LOAD);
    FileDialog save=new FileDialog(this,"Save",FileDialog.SAVE);

    public file(){
        Mytext.file_save.addActionListener(this);
        Mytext.file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
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
                //如果比较少，需要写
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
    private void save() {
        if (this.currentPath == null) {
            this.saveAs();
            if (this.currentPath == null) {
                return;
            }
        }
        FileWriter fw = null;
        //保存
        try {
            fw = new FileWriter(new File(currentPath));
            fw.write(Mytext.myTextArea.getText());
            //如果比较少，需要写
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




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Mytext.file_save){
            save();
        }
    }
}
