package com.my.project.epam.milena.util;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Represents a custom tag which shows the time on page
 *
 * @author Milena Bocharova
 */

public class InfoTimeTag extends TagSupport {

    /**
     * The method is called during the request if it sees the starting element of the tag
     * @return SKIP_BODY that will call the next method {@link #doEndTag()}
     */
    @Override
    public int doStartTag() {
        var gc = new GregorianCalendar();
        String time = "<hr>Time : <b> " + gc.getTime() + "</b><hr/>";
        JspWriter out = pageContext.getOut();
        try {
            out.write(time);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return SKIP_BODY;
    }

    /**
     * The method is called once when all previous methods have been processed
     * @return EVAL_PAGE that allows further processing of the page
     */
    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
