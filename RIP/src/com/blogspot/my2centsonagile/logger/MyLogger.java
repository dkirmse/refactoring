package com.blogspot.my2centsonagile.logger;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

public class MyLogger
{
    private String htmlLogFileName;
    private String logPath;
    private Logger log;

    public MyLogger (String logPath) throws Exception
    {
        this.logPath = logPath;
        initialiseHTMLLogging();
    }

    private void initialiseHTMLLogging () throws Exception
    {
        htmlLogFileName = this.logPath + "\\log.html";
        FileAppender fileAppender = new FileAppender( new LoggerHTMLLayout(), htmlLogFileName, false );
        this.log = Logger.getLogger(htmlLogFileName);
        this.log.addAppender( fileAppender );
    }

    public void log ()
    {
        MessageInfo logMessage = new MessageInfo();
        logMessage.area = "global";
        logMessage.component = "test";
        logMessage.message = "This is a test message";

        log.info(logMessage);
    }
}
