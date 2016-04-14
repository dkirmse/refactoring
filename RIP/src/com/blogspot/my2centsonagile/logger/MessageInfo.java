package com.blogspot.my2centsonagile.logger

import com.sap.hanadi.logsyndication.message.ILogMessage
import com.sap.hanadi.logsyndication.message.ILogMessageAction
import com.sap.hanadi.logsyndication.message.ILogMessageLink
import com.sap.hanadi.logsyndication.utils.ILogMessageRenderer
import com.sap.hanadi.logsyndication.utils.ILogMessageRendererBuilder

class MessageInfo implements ILogMessage, ILogMessageLink
{
    String context = ''
    String subcontext = ''
    String message = ''
    String link = ''
    ILogMessageAction bzForm

    public String toString()
    {
        return "context=${context}, subcontext=${subcontext}, message=${message}, link=${link}, bzForm=${bzForm}"
    }

    @Override
    public String messageText()
    {
        return message ? message : ''
    }
    @Override
    public ILogMessageRenderer renderer (ILogMessageRendererBuilder rendererBuilder)
    {
        return rendererBuilder.reporting(this).linkItTo(this).provide(bzForm).build()
    }

    @Override
    public String linkTarget ()
    {
        return link
    }
}
