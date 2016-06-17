/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic.formValidation;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Aks
 */
public class NumericDocumentFilter extends DocumentFilter {
                @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset,
                    String string, AttributeSet attr)
                    throws BadLocationException {
                try {
                    if (string.equals(".")
                            && !fb.getDocument()
                                    .getText(0, fb.getDocument().getLength())
                                    .contains(".")) {
                        super.insertString(fb, offset, string, attr);
                        return;
                    }
                    Double.parseDouble(string);
                    super.insertString(fb, offset, string, attr);
                } catch (Exception e) {
                   
                }

            }

           
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                    String text, AttributeSet attrs)
                    throws BadLocationException {
                try {
                    if (text.equals(".")
                            && !fb.getDocument()
                                    .getText(0, fb.getDocument().getLength())
                                    .contains(".")) {
                        super.insertString(fb, offset, text, attrs);
                        return;
                    }
                    Double.parseDouble(text);
                    super.replace(fb, offset, length, text, attrs);
                } catch (Exception e) {
                   
                }
            }
        

}
