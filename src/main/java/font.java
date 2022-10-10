import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class font extends JDialog {
    private static final long serialVersionUID = -6437493905392469749L;
    private Font font;
    private Font[] fonts;
    private JButton ok;
    private JButton cancel;
    private JLabel previewLabel;
    private JList<String> familyList;
    private JTextField familyTextField;
    private JList<String> styleList;
    private JTextField styleTextField;
    private JList<Integer> sizeList;
    private JTextField sizeTextField;

    public font() {
        this(new Font("Arial", Font.PLAIN, 15));
    }

    public font(Font font) {
        this("Font dialog", font);
    }

    public font(String title, Font font) {
        super.setTitle(title);
        this.font = font;
        this.init();
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return this.font;
    }

    private void init() {
        this.fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        this.setLayout(new FlowLayout());
        this.setMinimumSize(new Dimension(400 + 90, 400));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setLocationByPlatform(true);
        this.add(this.getFamilyPanel());
        this.add(this.getStylePanel());
        this.add(this.getSizePanel());
        this.add(this.getPreviewPanel());
        this.add(this.getButtonPanel());
        this.eventHandling();
    }

    private JPanel getFamilyPanel() {
        JPanel familyPanel = new JPanel();
        familyPanel.setPreferredSize(new Dimension(200 + 15, 200 + 15));
        familyPanel.setBorder(new TitledBorder("Font:"));
        familyPanel.setLayout(new FlowLayout());
        familyPanel.add(this.getFamilyTextBox());
        familyPanel.add(this.getFamilyScrolledList());
        return familyPanel;
    }

    private JTextField getFamilyTextBox() {
        familyTextField = new JTextField();
        familyTextField.setPreferredSize(new Dimension(220, 20));
        familyTextField.setText(this.getFont().getFamily());
        familyTextField.setEditable(false);
        return familyTextField;
    }

    private JScrollPane getFamilyScrolledList() {
        familyList = new JList<>(this.getFamily());
        JScrollPane fontPane = new JScrollPane();
        fontPane.setViewportView(familyList);
        fontPane.setPreferredSize(new Dimension(200, 155));
        familyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        familyList.setSelectedValue(this.getFont().getFamily(), true);

        return fontPane;
    }

    private JPanel getStylePanel() {
        JPanel stylePanel = new JPanel();
        stylePanel.setPreferredSize(new Dimension(150 + 15, 200 + 15));
        stylePanel.setBorder(new TitledBorder("Style:"));
        stylePanel.setLayout(new FlowLayout());
        stylePanel.add(this.getStyleTextBox());
        stylePanel.add(this.getStyleScrolledList());
        return stylePanel;
    }

    private JTextField getStyleTextBox() {
        styleTextField = new JTextField();
        styleTextField.setPreferredSize(new Dimension(155, 25));
        String style = this.calculateStyle(this.getFont(), this.getFont().getFamily());
        if (style.equals("")) {
            style = "Plain";
        }
        styleTextField.setText(style);
        styleTextField.setEditable(false);
        return styleTextField;
    }

    private JScrollPane getStyleScrolledList() {
        styleList = new JList<>(this.getFontStyles(this.getFont().getFamily()));
        JScrollPane stylePane = new JScrollPane();
        stylePane.setViewportView(styleList);
        stylePane.setPreferredSize(new Dimension(150, 155));
        styleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        String style = this.calculateStyle(this.getFont(), this.getFont().getFamily());
        if (style.equals("")) {
            style = "Plain";
        }
        styleList.setSelectedValue(style, true);

        return stylePane;
    }

    private JPanel getSizePanel() {
        JPanel sizePanel = new JPanel();
        sizePanel.setPreferredSize(new Dimension(50 + 10, 200 + 10));
        sizePanel.setBorder(new TitledBorder("Size:"));
        sizePanel.setLayout(new FlowLayout());
        sizePanel.add(this.getSizeTextBox());
        sizePanel.add(this.getSizeScrolledList());
        return sizePanel;
    }

    private JTextField getSizeTextBox() {
        sizeTextField = new JTextField();
        sizeTextField.setPreferredSize(new Dimension(50, 20));
        int size = this.getFont().getSize();
        sizeTextField.setText(Integer.toString(size));
        return sizeTextField;
    }

    private JScrollPane getSizeScrolledList() {
        sizeList = new JList<>(this.getFontSize());
        JScrollPane sizePane = new JScrollPane();
        sizePane.setViewportView(sizeList);
        sizePane.setPreferredSize(new Dimension(50, 152));
        sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sizeList.setSelectedValue(this.getFont().getSize(), true);
        return sizePane;
    }

    private JPanel getPreviewPanel() {
        JPanel previewPanel = new JPanel();
        previewPanel.setBorder(new TitledBorder("Preview"));
        previewPanel.setPreferredSize(new Dimension(355, 90));
        previewPanel.setLayout(new FlowLayout());
        previewPanel.add(this.getPreviewLabel());
        return previewPanel;
    }

    private JLabel getPreviewLabel() {
        previewLabel = new JLabel("HELLO hello 123");
        previewLabel.setPreferredSize(new Dimension(550, 80));
        previewLabel.setHorizontalAlignment(JLabel.CENTER);
        previewLabel.setFont(this.getFont());
        return previewLabel;
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(80, 60));
        buttonPanel.setLayout(new FlowLayout());
        ((FlowLayout) buttonPanel.getLayout()).setVgap(5);
        buttonPanel.add(this.getOKButton());
        buttonPanel.add(this.getCancelButton());
        return buttonPanel;
    }

    private JButton getOKButton() {
        ok = new JButton("OK");
        ok.setPreferredSize(new Dimension(80, 25));

        return ok;
    }

    private JButton getCancelButton() {
        cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(80, 25));

        return cancel;
    }

    private String[] getFontStyles(String fontFamily) {
        List<String> fontStyles = new ArrayList<>();
        for (Font f : fonts) {
            if (f.getFamily().equals(fontFamily)) {
                if (f.getFontName().length() == fontFamily.length()) {
                    fontStyles.add("Plain");
                    continue;
                }
                fontStyles.add(this.calculateStyle(f, fontFamily));
            }
        }
        String[] str = new String[fontStyles.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = fontStyles.get(i);
        }
        return str;
    }

    private String[] getFamily() {
        Set<String> stylesSet = new TreeSet<>();
        for (Font value : this.fonts) {
            stylesSet.add(value.getFamily());
        }
        Object[] obj = stylesSet.toArray();
        String[] str = new String[obj.length];
        for (int i = 0; i < str.length; i++) {
            str[i] = obj[i].toString();
        }
        return str;
    }

    private String calculateStyle(Font font, String fontFamily) {
        StringBuilder style = new StringBuilder(font.getFontName().replace(fontFamily, ""));
        if (style.toString().startsWith(" ")) {
            style.replace(0, 1, "");
        }
        return style.toString();
    }

    private Integer[] getFontSize() {
        Integer[] fs = new Integer[25];
        int n = 6;
        for (int i = 0; i < fs.length; i++) {
            n = n + 2;
            fs[i] = n;
        }
        return fs;
    }

    private void updateFont() {
        String family = this.familyTextField.getText();
        String style = this.styleTextField.getText();
        int size = Integer.parseInt(this.sizeTextField.getText());
        if (!style.equals("Plain") && !style.contains(".")) {
            family = family + " " + style;
        } else if (!style.equals("Plain") && style.contains(".")) {
            family = family + "." + style;
        }
        this.font = new Font(family, Font.PLAIN,  size);
        this.previewLabel.setFont(this.font);
    }

    private void eventHandling() {
        this.familyList.addListSelectionListener(e -> {
            String selectedFamily = familyList.getSelectedValue();
            familyTextField.setText(selectedFamily);
            styleList.setListData(getFontStyles(selectedFamily));
            styleList.setSelectedIndex(0);
        });
        this.styleList.addListSelectionListener(e -> {
            String selectedStyle = styleList.getSelectedValue();
            styleTextField.setText(selectedStyle);
            updateFont();
        });
        this.sizeList.addListSelectionListener(e -> {
            Integer selectedSize = Integer.valueOf(sizeList.getSelectedValue().toString());
            sizeTextField.setText(selectedSize.toString());
            updateFont();
        });
        this.sizeTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                if (evt.getKeyChar() < '0' || evt.getKeyChar() > '9') {
                    evt.consume();
                }
            }

            public void keyReleased(KeyEvent evt) {
                String value = sizeTextField.getText();
                evt.consume();
                if (value.equals("") || value.equals("0")) {
                    sizeTextField.setText("8");
                }
                sizeList.setSelectedValue(Integer.valueOf(value), true);
                updateFont();
            }
        });
        this.ok.addActionListener(e -> {
            setFont(previewLabel.getFont());
            Font selectedFont = this.getFont();
            System.out.println(selectedFont.toString());
            Mytext.myTextArea.setFont(selectedFont);
            dispose();
        });
        this.cancel.addActionListener(e -> dispose());
    }
}
