
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;

public class search extends JFrame implements ActionListener {
    public search(){
        Mytext.Find.addActionListener(this);
        Mytext.Find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        Mytext.Replace.addActionListener(this);
        Mytext.Replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
    }
    public void find(){

        final JDialog findDialog=new JDialog(this,"find",false);
        Container con=findDialog.getContentPane();//返回此对话框的contentPane对象
        con.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel findContentLabel=new JLabel("find content: ");

        final JTextField findText=new JTextField(15);

        JButton findNextButton=new JButton("findNext");

        final JCheckBox matchCheckBox=new JCheckBox("case sensitive");

        ButtonGroup bGroup=new ButtonGroup();

        final JRadioButton upButton=new JRadioButton("up");
        final JRadioButton downButton=new JRadioButton("down");
        JRadioButton circleButton=new JRadioButton("circle");

        downButton.setSelected(true);
        bGroup.add(upButton);
        bGroup.add(downButton);
        bGroup.add(circleButton);

        JButton cancel=new JButton("cancel");

        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        JPanel directionPanel=new JPanel();

        directionPanel.setBorder(BorderFactory.createTitledBorder("direction"));
        directionPanel.add(upButton);
        directionPanel.add(downButton);
        directionPanel.add(circleButton);

        panel1.setLayout(new GridLayout(2,1));
        panel1.add(findNextButton);
        panel1.add(cancel);

        panel2.add(findContentLabel);
        panel2.add(findText);
        panel2.add(panel1);

        panel3.add(matchCheckBox);
        panel3.add(directionPanel);

        con.add(panel2);
        con.add(panel3);

        findDialog.setSize(410,180);
        findDialog.setResizable(false);//不可调整大小
        findDialog.setLocation(230,280);
        findDialog.setVisible(true);

        //取消按钮事件处理
        cancel.addActionListener(e12 -> findDialog.dispose());
        findNextButton.addActionListener(e13 -> {
            int k;
            final String str1,str2,str3,str4,strA,strB;
            str1=Mytext.myTextArea.getText();
            str2=findText.getText();
            str3=str1.toUpperCase();
            str4=str2.toUpperCase();
            if(matchCheckBox.isSelected()){
                strA=str1;
                strB=str2;
            }else{
                strA=str3;
                strB=str4;
            }
            if(upButton.isSelected()){ //向上开始
                if(Mytext.myTextArea.getSelectedText()==null)
                    k=strA.lastIndexOf(strB,Mytext.myTextArea.getCaretPosition()-1);
                else
                    k=strA.lastIndexOf(strB, Mytext.myTextArea.getCaretPosition()-findText.getText().length()-1);
                if(k>-1){
                    Mytext.myTextArea.setCaretPosition(k);
                    Mytext.myTextArea.select(k,k+strB.length());
                }else{
                    JOptionPane.showMessageDialog(null,"What you were looking for could not be found","find",JOptionPane.INFORMATION_MESSAGE);
                }
            }else if (circleButton.isSelected()) {// 选择循环

                if (Mytext.myTextArea.getSelectedText() == null)
                    k=strA.indexOf(strB,Mytext.myTextArea.getCaretPosition()+1);
                else{
                    k=strA.indexOf(strB, Mytext.myTextArea.getCaretPosition()-findText.getText().length()+1);
                }
                if(k==-1 &&(strA.contains(strB))) {
                    k = strA.indexOf(strB);
                }
                if(k>-1){
                    Mytext.myTextArea.setCaretPosition(k);
                    Mytext.myTextArea.select(k,k+strB.length());
                }else{
                    JOptionPane.showMessageDialog(null,"What you were looking for could not be found","find",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    public void replace(){
        JDialog replaceDialog=new JDialog(this,"Replace",false);
        Container con=replaceDialog.getContentPane();
        con.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel findContentLabel=new JLabel("find content");
        JLabel replaceLablel=new JLabel("replace with");

        JTextField findText=new JTextField(15);
        JTextField replaceText=new JTextField(15);

        JButton findNextButton=new JButton("findNext");
        JButton replaceButton=new JButton("replace");
        JButton replaceAllButton=new JButton("replaceAll");
        JButton cancel=new JButton("cancel");

        JCheckBox matchCheckBox=new JCheckBox("case sensitive");
        JCheckBox circleButton=new JCheckBox("circle");

        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        JPanel panel4=new JPanel();

        panel4.setLayout(new GridLayout(2,1));
        panel1.add(findContentLabel);
        panel1.add(findText);
        panel1.add(findNextButton);

        panel4.add(replaceButton);
        panel4.add(replaceAllButton);

        panel2.add(replaceLablel);
        panel2.add(replaceText);
        panel2.add(panel4);

        panel3.add(matchCheckBox);
        panel3.add(circleButton);
        panel3.add(cancel);

        con.add(panel1);
        con.add(panel2);
        con.add(panel3);

        replaceDialog.setSize(420,420);
        replaceDialog.setResizable(false);
        replaceDialog.setLocation(230,280);
        replaceDialog.setVisible(true);

        cancel.addActionListener(e -> replaceDialog.dispose());

        findNextButton.addActionListener(e -> {
            int k;
            String str1,str2,str3,str4,strA,strB;
            str1=Mytext.myTextArea.getText();
            str2=Mytext.myTextArea.getText();
            str3=str1.toUpperCase(Locale.ROOT);
            str4=str2.toUpperCase(Locale.ROOT);
            if(matchCheckBox.isSelected()){
                strA=str1;
                strB=str2;
            }else{
                strA=str3;
                strB=str4;
            }//change to capital
        if(circleButton.isSelected()){
            if(Mytext.myTextArea.getSelectedText()==null){
                k=strA.indexOf(strB,Mytext.myTextArea.getCaretPosition()+1);
            }else{
                k=strA.indexOf(strB,Mytext.myTextArea.getCaretPosition()-findText.getText().length()+1);
            }
            if(k==-1&&(strA.contains(strB))){
                k=strA.indexOf(strB);
            }
            if(k>-1){
                Mytext.myTextArea.setCaretPosition(k);
                Mytext.myTextArea.select(k,k+strB.length());
            }else{
                JOptionPane.showMessageDialog(null,"What you were looking for could not be found","find",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }else{//or circulate down
            if(Mytext.myTextArea.getSelectedText()==null){
                k=strA.indexOf(strB,Mytext.myTextArea.getCaretPosition()+1);
            } else {
                k = strA.indexOf(strB, Mytext.myTextArea.getCaretPosition() - findText.getText().length() + 1);
            }
            if(k>-1){
                Mytext.myTextArea.setCaretPosition(k);
                Mytext.myTextArea.select(k,k+strB.length());
            }else{
                JOptionPane.showMessageDialog(null,"What you were looking for could not be found","find",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        replaceButton.addActionListener(e -> {
            if(replaceText.getText().length()==0 && Mytext.myTextArea.getSelectedText() != null)
                Mytext.myTextArea.replaceSelection("");
            if(replaceText.getText().length()>0 && Mytext.myTextArea.getSelectedText()!=null)
                Mytext.myTextArea.replaceSelection(replaceText.getText());
        });

        replaceAllButton.addActionListener(e14 -> {
            if(replaceText.getText().length()>0) {
                String a = Mytext.myTextArea.getText().replace(findText.getText(), replaceText.getText());
                int txtAreaLength = Mytext.myTextArea.getText().length();
                Mytext.myTextArea.replaceRange(a, 0, txtAreaLength);
            }else{
                String a = Mytext.myTextArea.getText().replace(findText.getText(),"");
                int txtAreaLength = Mytext.myTextArea.getText().length();
                Mytext.myTextArea.replaceRange(a, 0, txtAreaLength);
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Mytext.Find){
            find();
        }
        if(e.getSource()==Mytext.Replace){
            replace();
        }
    }
}
