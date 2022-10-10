import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class file extends JFrame implements ActionListener {
    Mytext text;
    public static int flag = 0;
    String currentPath = null;
    String currentFileName = null;
    PrintJob p = null;
    Graphics g = null;
    String content;

    FileDialog open = new FileDialog(this, "Open", FileDialog.LOAD);
    FileDialog save = new FileDialog(this, "Save", FileDialog.SAVE);
    FileDialog export2pdf = new FileDialog(this, "Export", FileDialog.SAVE);


    public file(Mytext t) {
        text = t;
        Mytext.file_save.addActionListener(this);
        Mytext.file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        Mytext.file_saveAs.addActionListener(this);
        //Mytext.file_saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        Mytext.file_open.addActionListener(this);
        Mytext.file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

        Mytext.file_new.addActionListener(this);
        Mytext.file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

        Mytext.file_print.addActionListener(this);
        Mytext.file_print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

        Mytext.file_toPDF.addActionListener(this);

        Mytext.file_exit.addActionListener(this);
    }

    static void isChanged() {
        Mytext.myTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Character ch = e.getKeyChar();
                if (ch != null && !Mytext.myTextArea.getText().toString().equals("")) {
                    flag = 2;
                }
            }
        });
    }


    public void New() {
        if (flag == 0 || flag == 1) {        // when it Start:0，get a new text:1
            return;
        } else if (flag == 2 && this.currentPath == null) {        //after change

            int result = JOptionPane.showConfirmDialog(this, "Are you sure to save changes to untitled?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                this.saveAs();        //save as
            } else if (result == JOptionPane.NO_OPTION) {
                Mytext.myTextArea.setText("");
                this.setTitle("Untitled");
                flag = 1;
            }
            return;
        } else if (flag == 2 && this.currentPath != null) {

            int result = JOptionPane.showConfirmDialog(this, "If save the change to" + this.currentPath + "?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                this.save();        //save strightly
            } else if (result == JOptionPane.NO_OPTION) {
                Mytext.myTextArea.setText("");
                this.setTitle("Untitled");
                flag = 1;
            }
        } else if (flag == 3) {        //saved file
            Mytext.myTextArea.setText("");
            flag = 1;
            this.setTitle("Untitled");
        }

    }

    public void saveAs() {

        JFileChooser jFileChooser = new JFileChooser();
        int res = jFileChooser.showSaveDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            //get the chosen file
            File file = jFileChooser.getSelectedFile();
            FileWriter fw = null;
            //保存
            try {
                fw = new FileWriter(file);
                fw.write(Mytext.myTextArea.getText());
                currentFileName = file.getName();
                currentPath = file.getAbsolutePath();

                fw.flush();
                this.flag = 3;
                this.setTitle(currentPath);
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

    private String readFile(File file) throws Exception {
        String suffix = getFileSuffix(file);
        syntax.change(suffix);
        StringBuilder result = new StringBuilder();
        if (suffix.equals("odt")) {
            result = oriContent(Mytext.myfile.getPath());
            return result.toString();
        }
        if (suffix.equals("rtf")) {
            return Rtf(file.getPath());
        }
        try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", JOptionPane.ERROR_MESSAGE);
        }
        return result.toString();
    }

    public void open() {
        JFileChooser dialog = new JFileChooser();
        dialog.setMultiSelectionEnabled(false);
        try {
            int result = dialog.showOpenDialog(this);
            if (result == JFileChooser.CANCEL_OPTION)
                return;
            if (result == JFileChooser.APPROVE_OPTION) {
                if (Mytext.forchanged)
                    save();
                Mytext.myfile = dialog.getSelectedFile();
                content = readFile(Mytext.myfile);
                Mytext.myTextArea.setText(content);
                if (Objects.equals(getFileSuffix(Mytext.myfile), "odt") || Objects.equals(getFileSuffix(Mytext.myfile), "rtf")) {
                    text.setTitle("Editor - " + Mytext.myfile.getName() + "(Read Only)");
                    Mytext.myTextArea.setEditable(false);
                    Mytext.forchanged = false;
                    return;
                }
                Mytext.forchanged = false;
                text.setTitle("Editor - " + Mytext.myfile.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exit() {
        if (flag == 2 && currentPath == null) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure to save changes to untitled?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                saveAs();
                System.exit(0);

            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
//                this.dispose();
//                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }

        } else if (flag == 2 && currentPath != null) {

            int result = JOptionPane.showConfirmDialog(this, "If you want to save changes to" + currentPath + "?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                save();
                System.exit(0);

            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
//                this.dispose();
//                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure to close?", "System prompt", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                System.exit(0);
                //this.dispose();
                //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    public void toPDF()throws FileNotFoundException, DocumentException {
        Document document=new Document(PageSize.A4,50,50,30,20);
        String text = Mytext.myTextArea.getText();
        export2pdf.setVisible(true);
        String dirPath = export2pdf.getDirectory();  //get the file root
        System.out.println(dirPath);
        String fileName = export2pdf.getFile() + ".pdf";
        System.out.println(fileName);
        PdfWriter.getInstance(document, new FileOutputStream(dirPath+'/'+fileName));

        document.open();
        document.add(new Paragraph(text));
        document.close();

    }
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Mytext.file_open) {
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
        if(e.getSource()==Mytext.file_toPDF){
            try {
                toPDF();
            } catch (FileNotFoundException | DocumentException ex) {
                ex.printStackTrace();
            }
        }

    }
    public static String getFileSuffix(File file) {
        if (file == null) {
            return null;
        }
        String suffix = null;
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return suffix;
    }
    public static String Rtf(String filePath) {
        String result = null;
        File file = new File(filePath);
        try {
            DefaultStyledDocument styledDoc = new DefaultStyledDocument();
            InputStream streamReader = new FileInputStream(file);
            new RTFEditorKit().read(streamReader, styledDoc, 0);
            result = new String(styledDoc.getText(0, styledDoc.getLength()).getBytes("ISO8859-1"),"GBK");
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
        return result;
    }

    static String str = "";
    private static void getText(org.w3c.dom.Node node) {
        if (node.getChildNodes().getLength() > 1) {
            NodeList childNodes = node.getChildNodes();
            for (int a = 0; a < childNodes.getLength(); a++) {
                getText(node.getChildNodes().item(a));
            }
        } else {
            if (node.getNodeValue() != null) {
                //str is used to concatenate the label content
                str = str + node.getNodeValue();
            }
            if (node.getFirstChild() != null) {
                str = str + node.getFirstChild().getNodeValue();
            }
        }
    }
    public StringBuilder oriContent(String srcFile) throws Exception {
        StringBuilder result = new StringBuilder();
        ZipFile zipFile = new ZipFile(srcFile);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        ZipEntry entry;
        org.w3c.dom.Document doc;
        while (entries.hasMoreElements()) {
            entry = entries.nextElement();
            //only operate the .xml file
            if (entry.getName().equals("content.xml")) {
                //create the file
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder docBuilder = domFactory.newDocumentBuilder();
                doc = docBuilder.parse(zipFile.getInputStream(entry));
                //access to the node
                NodeList list = doc.getElementsByTagName("text:p");

                for (int a = 0; a < list.getLength(); a++) {
                    Node node =list.item(a);
                    //retrieve the label content recursively
                    getText(node);
                    //System.out.println(str);
                    result.append(str).append("\n");
                    //empty the data, record the content of next label
                    str = "";
                }
            }
        }
        return result;
    }
}