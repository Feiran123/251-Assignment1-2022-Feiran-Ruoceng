import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class fonts extends JFrame implements ActionListener {
    private static final long serialVersionUID = -6437493905392469749L;
    boolean flag=false;
    int cl=1;
    private JTextField f;
    public fonts(){
        Mytext.autoLine.addActionListener(this);
        Mytext.autoLine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        Mytext.FontSet.addActionListener(this);
        Mytext.FontSet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));

    }
    public void fontSet(){
        JLabel l=new JLabel("ziti");
        GraphicsEnvironment gr=GraphicsEnvironment.getLocalGraphicsEnvironment();
        JList fontnames=new JList(gr.getAvailableFontFamilyNames());
        int selection=JOptionPane.showConfirmDialog(null,new JOptionPane(fontnames),
                "xuanzeziti",JOptionPane.OK_CANCEL_OPTION);
        Object selectedFont=fontnames.getSelectedValue();
        if (selection==JOptionPane.OK_OPTION&&selectedFont!=null){
            f.setFont(new Font(fontnames.getSelectedValue().toString(),Font.PLAIN,20));
        }
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
