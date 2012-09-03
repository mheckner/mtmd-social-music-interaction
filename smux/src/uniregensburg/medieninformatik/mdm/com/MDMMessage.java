package uniregensburg.medieninformatik.mdm.com;

public class MDMMessage {
	
	public static final int ALL_CLIENTS = 0;
	public static final int ONE_CLIENT = 1;
	
	public static final int MESSAGE_TYPE_CONNECTED = 0;
	public static final int MESSAGE_TYPE_DISCONNECTED = 1;
	
	private String sender_id = "-";
	private String sender_ip = "-";
	private int recepients = -1;
	private String receiver_id = "-";
	private int message_type = -1;
	private String message_content = "-";

	public MDMMessage() {
	}

	public String getSender_id() {
		return sender_id;
	}

	public String getSender_ip() {
		return sender_ip;
	}
	
	public int getRecepients() {
		return recepients;
	}

	public String getReceiver_id() {
		return receiver_id;
	}

	public int getMessage_type() {
		return message_type;
	}

	public String getMessage_content() {
		return message_content;
	}
	
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public void setSender_ip(String sender_ip) {
		this.sender_ip = sender_ip;
	}

	public void setRecepients(int recepients) {
		this.recepients = recepients;
	}

	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}

	public void setMessage_type(int message_type) {
		this.message_type = message_type;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	//TODO JSON
	public String toString() {
		return sender_id+"###"+sender_ip+"###"+recepients+"###"+receiver_id+"###"+message_type+"###"+message_content;
	}
	
}
