package com.my.project.epam.milena.util;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;

public class InfoTimeTag extends TagSupport {

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


    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
