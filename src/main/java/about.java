import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class about extends JFrame implements ActionListener{
    private JButton button;
    private JLabel label;
    private JPanel panel;
    private BoxLayout boxLayout;

    public about(){
        Mytext.help_about.addActionListener(this);
    }
    public void message(){
        panel=new JPanel();
        boxLayout=new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        button=new JButton("OK");
        button.setSize(50,20);
        button.setAlignmentX(BOTTOM_ALIGNMENT);

        String s1="The information of my team members:";
        String s2="member1:";
        String s3="name: Feiran Li; Id: 21012669";
        String s4="member2:";
        String s5="name: Ruoceng Gao; Id: 21012663";
        String s="<html><body>"+s1+"<br>"+s2+"<br>"+s3+"<br>"+s4+"<br>"+s5+"<html></body>";
        label=new JLabel(s);
        label.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(button);

        this.add(panel);
        this.setSize(400,300);
        this.setTitle("About");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        button.addActionListener(e->{
            this.dispose();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Mytext.help_about){
            message();
        }
    }
}
