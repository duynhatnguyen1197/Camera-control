package sample;

import java.util.Date;

/*
 * Message received from server.
 *
 * @Author Jay Sridhar
 */
public class ServerMessage
{
    private String from;
    private String message;
    private String content;
    private Date time = new Date();

    public ServerMessage() {}
    public static String RXTX;
    public ServerMessage(String from,String message,String content)
    {
	this.from = from;
	this.message = message;
	this.content = content;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Date getTime()
    {
        return time;
    }

    public String toString()
    {
	return String
	    .format("{from: %1$-10s | content: %2$-10s | time: %4$-15d | mesg: %3$s}",
		    getFrom(), getContent(),
		    getMessage(), getTime().getTime());
    
    }
}
