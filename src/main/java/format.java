import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class format extends JFrame implements ActionListener {
    private static final long serialVersionUID = -6437493905392469749L;
    boolean flag=false;
    int cl=1;

    public format(){
        Mytext.autoLine.addActionListener(this);
        Mytext.autoLine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        Mytext.FontSet.addActionListener(this);
        Mytext.FontSet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));

    }
    public void fontSet(){
        font Font=new font(Mytext.myTextArea.getFont());
        Font.setVisible(true);
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
    }
}
