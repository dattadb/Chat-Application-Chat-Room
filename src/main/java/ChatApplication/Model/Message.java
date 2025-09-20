package ChatApplication.Model;

public class Message {

	private String sender;
	private String content;
	private String type;
	private String replyTo;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public Message(String sender, String content, String type, String replyTo) {
		super();
		this.sender = sender;
		this.content = content;
		this.type = type;
		this.replyTo = replyTo;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

}