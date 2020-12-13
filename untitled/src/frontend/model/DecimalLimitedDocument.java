package frontend.model;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * JTextField num1 = new JTextField(10);
 * num1.setDocument(new DecimalOnlyDocument());
 */
public class DecimalLimitedDocument extends PlainDocument {
// 正则表达式，只接收数字键及小数点(只允许输入一次小数点)

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        StringBuilder tmp = new StringBuilder(super.getText(0, super.getLength()));
        tmp.insert(offs, str);
        Pattern p = Pattern.compile("^-?\\d*(\\.)?\\d*$");
        Matcher m = p.matcher(tmp.toString());
        if (m.find()) super.insertString(offs, str, a);
    }
}