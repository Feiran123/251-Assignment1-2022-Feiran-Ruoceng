import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class manage extends JFrame implements ActionListener{
    UndoManager undoManager=new UndoManager();
    public manage(){

        Mytext.manage_select.addActionListener(this);
        Mytext.manage_select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        Mytext.manage_cut.addActionListener(this);
        Mytext.manage_cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
        Mytext.manage_copy.addActionListener(this);
        Mytext.manage_copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        Mytext.manage_paste.addActionListener(this);
        Mytext.manage_paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        Mytext.manage_undo.addActionListener(this);
        Mytext.manage_undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        Mytext.manage_redo.addActionListener(this);
        Mytext.manage_redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        Mytext.manage_time.addActionListener(this);
        Mytext.manage_time.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        Mytext.myTextArea.getDocument().addUndoableEditListener(undoManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==Mytext.manage_select){
            Mytext.myTextArea.selectAll();
        }
        if(e.getSource()==Mytext.manage_cut){
            Mytext.myTextArea.cut();
        }
        if(e.getSource()==Mytext.manage_copy){
            Mytext.myTextArea.copy();
        }
        if(e.getSource()==Mytext.manage_paste){
            Mytext.myTextArea.paste();
        }
        if(e.getSource()==Mytext.manage_undo){
            if(undoManager.canUndo()) undoManager.undo();

        }
        if(e.getSource()==Mytext.manage_redo){
            if(undoManager.canRedo()) undoManager.redo();
        }
        if(e.getSource()==Mytext.manage_time){
            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm yyyy/MM/dd");
            Mytext.myTextArea.append(dateFormat.format(calendar.getTime()));
        }
    }
}
