/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.formValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Aks
 */
public class AlphabeticalDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset,
            String input, AttributeSet attr)
            throws BadLocationException {
            Pattern pattern = Pattern.compile("^\\p{Alpha}+$"); // use \\p{L} for Unicode support
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches() || input.equals("")) {
                super.insertString(fb, offset, input, attr);
            }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
            String input, AttributeSet attrs)
            throws BadLocationException {
        Pattern pattern = Pattern.compile("^\\p{Alpha}+$"); // use \\p{L} for Unicode support
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches() || input.equals("")) {
                super.insertString(fb, offset, input, attrs);
            }
    }

}
