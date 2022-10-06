import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class file extends Mytext implements ActionListener {
    public file(){
        Mytext.file_save.addActionListener(this);
        Mytext.file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
    }

    public void saveFile(){
        JFileChooser jFileChooser=new JFileChooser();

        jFileChooser.setDialogTitle("Open");
        //only choose
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.showOpenDialog(null);
        jFileChooser.setVisible(true);

        String abs=jFileChooser.getSelectedFile().getAbsolutePath();
        FileWriter fw=null;
        BufferedWriter bw=null;

        File newfile =new File(abs);

        try{
            fw =new FileWriter(newfile);
            bw=new BufferedWriter(fw);

            //Label myTextArea = null;
            String[] s=myTextArea.getText().split("\n");
            for (String value:s){
                bw.write(value+"\n");
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                assert bw!=null;
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFile() throws IOException{
        FileReader fileReader =null;
        BufferedReader bufferedReader=null;
        JFileChooser jFileChooser=new JFileChooser();

        jFileChooser.setDialogTitle("Open");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.showOpenDialog(null);
        jFileChooser.setVisible(true);

        String abs=jFileChooser.getSelectedFile().getAbsolutePath();
        try{
            fileReader=new FileReader(abs);
            bufferedReader=new BufferedReader(fileReader);
            String now;
            String all="";

            while((now=bufferedReader.readLine())!=null){
                all+=now+"\n";
                return all;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                assert bufferedReader !=null;
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("create")){
            if(myTextArea.getText()==null||"".equals(myTextArea.getText())){
                return;

            }else{
                int num =JOptionPane.showConfirmDialog(null,"Are you sure to save?","text",JOptionPane.YES_NO_OPTION);
                if(num==0){
                    new file();
                    this.dispose();
                    new Mytext();

                }
                if(num==1){
                    this.dispose();
                    new Mytext();
                }

            }
        }
        if(e.getSource()==file_save){
            File file=new File("D://251//text");
            FileWriter fw=null;
            BufferedWriter bw=null;
            try{
                fw=new FileWriter(file);
                bw=new BufferedWriter(fw);

                String[] s = myTextArea.getText().split("\n");
                for (String value : s) {
                    bw.write(value + "\n");
                    bw.flush();
                }
                System.out.println("Save successfully");
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally {
                try{
                    assert bw !=null;
                    bw.close();
                    fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }













}
