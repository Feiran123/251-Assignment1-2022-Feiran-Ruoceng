import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class syntax extends JFrame implements ActionListener {
    public syntax(){
        Mytext.language1.addActionListener(this);
        Mytext.language2.addActionListener(this);
        Mytext.language3.addActionListener(this);
        Mytext.language4.addActionListener(this);
        Mytext.language5.addActionListener(this);
        Mytext.language6.addActionListener(this);
        Mytext.language7.addActionListener(this);
        Mytext.language8.addActionListener(this);
        Mytext.language9.addActionListener(this);
        Mytext.language10.addActionListener(this);
    }
    public static void refresh(){
        Mytext.language1.setState(false);
        Mytext.language2.setState(false);
        Mytext.language3.setState(false);
        Mytext.language4.setState(false);
        Mytext.language5.setState(false);
        Mytext.language6.setState(false);
        Mytext.language7.setState(false);
        Mytext.language8.setState(false);
        Mytext.language9.setState(false);
        Mytext.language10.setState(false);
    }
    public static void change(String suffix){
        if(Objects.equals(suffix,"txt")){
            refresh();
            Mytext.language1.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        }
        else if(Objects.equals(suffix,"c")){
            refresh();
            Mytext.language2.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
        }
        else if(Objects.equals(suffix,"cs")){
            refresh();
            Mytext.language3.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSHARP);
        }
        else if(Objects.equals(suffix,"cpp")){
            refresh();
            Mytext.language4.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        }
        else if(Objects.equals(suffix,"css")){
            refresh();
            Mytext.language5.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CSS);
        }
        else if(Objects.equals(suffix,"html")){
            refresh();
            Mytext.language6.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        }
        else if(Objects.equals(suffix,"java")){
            refresh();
            Mytext.language7.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        }
        else if(Objects.equals(suffix,"js")){
            refresh();
            Mytext.language8.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        }
        else if(Objects.equals(suffix,"md")){
            refresh();
            Mytext.language9.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MARKDOWN);
        }
        else if(Objects.equals(suffix,"py")){
            refresh();
            Mytext.language10.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        }else{
            refresh();
            Mytext.language1.setState(true);
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        }
    }
    public void changeSyntax(JCheckBoxMenuItem item,String syntax){
        boolean temp = item.getState();
        refresh();
        item.setState(temp);
        if(temp){
            Mytext.myTextArea.setSyntaxEditingStyle(syntax);
        }
        else {
            Mytext.myTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
            Mytext.language1.setState(true);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Mytext.language1){
            changeSyntax(Mytext.language1,SyntaxConstants.SYNTAX_STYLE_NONE);
        }
        else if(e.getSource() == Mytext.language2){
            changeSyntax(Mytext.language2,SyntaxConstants.SYNTAX_STYLE_C);
        }
        else if(e.getSource() == Mytext.language3){
            changeSyntax(Mytext.language3,SyntaxConstants.SYNTAX_STYLE_CSHARP);
        }
        else if(e.getSource() == Mytext.language4){
            changeSyntax(Mytext.language4,SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        }
        else if(e.getSource() == Mytext.language5){
            changeSyntax(Mytext.language5,SyntaxConstants.SYNTAX_STYLE_CSS);
        }
        else if(e.getSource() == Mytext.language6){
            changeSyntax(Mytext.language6,SyntaxConstants.SYNTAX_STYLE_HTML);
        }
        else if(e.getSource() == Mytext.language7){
            changeSyntax(Mytext.language7,SyntaxConstants.SYNTAX_STYLE_JAVA);
        }
        else if(e.getSource() == Mytext.language8){
            changeSyntax(Mytext.language8,SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        }
        else if(e.getSource() == Mytext.language9){
            changeSyntax(Mytext.language9,SyntaxConstants.SYNTAX_STYLE_MARKDOWN);
        }
        else if(e.getSource() == Mytext.language10){
            changeSyntax(Mytext.language10,SyntaxConstants.SYNTAX_STYLE_PYTHON);
        }
    }
}
