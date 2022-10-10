import com.esotericsoftware.yamlbeans.YamlReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;

public class format extends JFrame implements ActionListener {
    private static final long serialVersionUID = -6437493905392469749L;
    boolean flag=false;
    int cl=1;
    JColorChooser jcc1=null;
    Color color=Color.BLACK;

    public format(){
        Mytext.autoLine.addActionListener(this);
        Mytext.autoLine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        Mytext.FontSet.addActionListener(this);
        Mytext.FontSet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        Mytext.Back.addActionListener(this);

    }
    public void fontSet(){
        font Font=new font(Mytext.myTextArea.getFont());
        Font.setVisible(true);
    }
    public void Background(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Mytext.autoLine){
            cl++;
            flag=cl%2==0;
            Mytext.myTextArea.setLineWrap(flag);
        }
        if(e.getSource()==Mytext.FontSet){
            fontSet();
        }
        if(e.getSource()==Mytext.Back){
            jcc1 = new JColorChooser();
            JOptionPane.showMessageDialog(this, jcc1,"选择背景颜色颜色",-1);
            color = jcc1.getColor();
            Mytext.myTextArea.setBackground(color);

        }
    }
    public void run(){
        try{
            YamlReader reader=new YamlReader(new FileReader(""))
        }
    }
}
