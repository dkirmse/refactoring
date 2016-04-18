package com.blogspot.my2centsonagile.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * This HTML Log Formatter is a simple replacement for the standard Log4J HTMLLayout formatter and
 * replaces the default timestamp (milliseconds, relative to the start of the log) with a more readable
 * timestamp (an example of the default format is 2008-11-21-18:35:21.472-0800).
 * */

public class LoggerHTMLLayout extends HTMLLayout
{
    private final String TS_FORMAT = "yyyy-MM-dd-HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(TS_FORMAT);

    private String logFile = "";
    private boolean backButton;

    public LoggerHTMLLayout()
    {
        super();
    }

    public void setLogFile(String logFile)
    {
        this.logFile = logFile;
    }

    public String getLogFileName()
    {
        return this.logFile;
    }

    @Override
    public String format(LoggingEvent event)
    {
        StringBuffer sbuf = new StringBuffer(BUF_SIZE);

        if (sbuf.capacity() > MAX_CAPACITY) {
            sbuf = new StringBuffer(BUF_SIZE);
        } else {
            sbuf.setLength(0);
        }

        sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);
        sbuf.append("<td title=\"Time\" width=\"250\">");
        sbuf.append(sdf.format(new Date(event.timeStamp)));
        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("<td title=\"Level\">");
        if (event.getLevel().equals(Level.ERROR)) {
            sbuf.append("<font color=\"red\"><strong>");
            sbuf.append(event.getLevel());
            sbuf.append("</strong></font>");
        }
        else if (event.getLevel().isGreaterOrEqual(Level.WARN)) {
            sbuf.append("<font color=\"orange\"><strong>");
            sbuf.append(event.getLevel());
            sbuf.append("</strong></font>");
        }
        else if (event.getLevel().isGreaterOrEqual(Level.INFO)) {
            sbuf.append("<font color=\"green\"><strong>");
            sbuf.append(event.getLevel());
            sbuf.append("</strong></font>");
        } else {
            sbuf.append(event.getLevel());
        }
        sbuf.append("</td>" + Layout.LINE_SEP);

        MessageInfo mI = (MessageInfo) event.getMessage();

        sbuf.append("<td title=\"Area\">");
        sbuf.append(mI.area);
        sbuf.append("</td>" + Layout.LINE_SEP);

        sbuf.append("<td title=\"Component\">");
        sbuf.append(mI.component);
        sbuf.append("</td>" + Layout.LINE_SEP);

        sbuf.append("<td title=\"Message\" class='message'>");
        if ( mI.link.equals(""))
        {
            sbuf.append(mI.message);
        }
        else
        {
            sbuf.append("<a href='");
            sbuf.append(mI.link);
            sbuf.append("' style='float: left;'>");
            sbuf.append(mI.message);
            sbuf.append("</a>");
        }

        if (event.getLevel().equals(Level.ERROR) && mI.area.equals("TEST"))
        {
            if (!mI.form.equals(""))
            {
                sbuf.append("<span style='text-align: right; margin: 0; float: right;'>");
                sbuf.append("<button onclick='javascript:document.getElementById(&quot;BugzillaForm${id}&quot;).submit();'>Create Bug</button>");
                sbuf.append("<form id='BugzillaForm${id++}' method='post' action='https://some.bugzilla.server/bugzilla/enter_bug.cgi?product=${this.product}' target='_blank' style='display: inline;'>");
                if (mI.form.summary != null)
                {
                    sbuf.append("<input type='hidden' name='short_desc' value='${this.summary}' />");
                }
                if (mI.form.symptom != null)
                {
                    sbuf.append("<input type='hidden' name='cf_symptom' value='${this.symptom}' />");
                }
                if (mI.form.description != null)
                {
                    sbuf.append("<input type='hidden' name='comment' value='${this.description}' />");
                }
                if (mI.form.priority != null)
                {
                    sbuf.append("<input type='hidden' name='priority' value='${this.priority}' />");
                }
                if (mI.form.component != null)
                {
                    sbuf.append("<input type='hidden' name='component' value='${this.component}' />");
                }
                sbuf.append("</span>");
            }
        }

        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("</tr>" + Layout.LINE_SEP);
        String record = sbuf.toString();
        return record;
    }

    @Override
    public String getHeader()
    {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"  + Layout.LINE_SEP);
        sbuf.append("<html>" + Layout.LINE_SEP);
        sbuf.append("<head>" + Layout.LINE_SEP);
        sbuf.append("<title>HTML Log</title>" + Layout.LINE_SEP);
        sbuf.append("<link id='productstyle' rel='stylesheet' href='../css/stylesheet.css' type='text/css'>");
        sbuf.append("</head>" + Layout.LINE_SEP);
        sbuf.append("<body>" + Layout.LINE_SEP);

        sbuf.append("<div class=pageheader>" + Layout.LINE_SEP);
        sbuf.append("<table class=pageheader>" + Layout.LINE_SEP);
        sbuf.append("<tr class=pageheader_top>" + Layout.LINE_SEP);
        sbuf.append("<td class=pageheader_top>" + Layout.LINE_SEP);
        sbuf.append("HTML Log" + Layout.LINE_SEP);
        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("<td class=pageheader_top>" + Layout.LINE_SEP);
        sbuf.append("</td>" + Layout.LINE_SEP);

        sbuf.append("</tr>" + Layout.LINE_SEP);
        sbuf.append("<tr class=pageheader_bottom>" + Layout.LINE_SEP);
        sbuf.append("<td class=pageheader_bottom><i>powered by MyLogger Services</i>" + Layout.LINE_SEP);
        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("<td class=pageheader_bottom>" + Layout.LINE_SEP);
        sbuf.append("</td>" + Layout.LINE_SEP);
        sbuf.append("</tr>" + Layout.LINE_SEP);
        sbuf.append("</table>" + Layout.LINE_SEP);
        sbuf.append("</div>" + Layout.LINE_SEP);

        sbuf.append("<div>" + Layout.LINE_SEP);
        sbuf.append("<table>" + Layout.LINE_SEP);
        sbuf.append("<thead>" + Layout.LINE_SEP);
        sbuf.append("<tr>" + Layout.LINE_SEP);
        sbuf.append("<th width=10%>Time</th>" + Layout.LINE_SEP);
        sbuf.append("<th width=10%>Level</th>" + Layout.LINE_SEP);
        sbuf.append("<th width=10%>Area</th>" + Layout.LINE_SEP);
        sbuf.append("<th width=10%>Component</th>" + Layout.LINE_SEP);
        sbuf.append("<th width=60%>Message</th>" + Layout.LINE_SEP);
        sbuf.append("</tr>" + Layout.LINE_SEP);
        sbuf.append("</thead>" + Layout.LINE_SEP);
        sbuf.append("<tbody>" + Layout.LINE_SEP);
        sbuf.append("<!--#config errmsg=\" -->"
                        + Layout.LINE_SEP);
        return sbuf.toString();
    }
}
