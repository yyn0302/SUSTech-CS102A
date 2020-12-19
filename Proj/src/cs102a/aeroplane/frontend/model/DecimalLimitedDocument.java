package cs102a.aeroplane.frontend.model;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * JTextField num1 = new JTextField(<LENGTH>);
 * num1.setDocument(new DecimalLimitedDocument());
 */
public class DecimalLimitedDocument extends PlainDocument {
// 正则表达式，只接收数字及小数点(只允许输入一次小数点)

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        StringBuilder tempInput = new StringBuilder(super.getText(0, super.getLength()));
        tempInput.insert(offs, str);
        Pattern decimalPattern = Pattern.compile("^-?\\d*(\\.)?\\d*$");
        Matcher matcher = decimalPattern.matcher(tempInput.toString());
        if (matcher.find()) super.insertString(offs, str, a);
    }
}