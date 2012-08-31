package uniregensburg.medieninformatik.mdm.com;

public class MDMMessage {
	
	public static final int ALL_CLIENTS = 0;
	public static final int ONE_CLIENT = 1;
	
	public static final int MESSAGE_TYPE_CONNECTED = 0;
	public static final int MESSAGE_TYPE_DISCONNECTED = 1;
	
	private String sender_id;
	private String sender_ip;
	private int recepients;
	private String receiver_id;
	private int message_type;
	private String message_content;
	
	public MDMMessage(String sender_id, String sender_ip, int recepients, String receiver_id,
			int message_type, String message_content) {
		super();
		this.sender_id = sender_id;
		this.sender_ip = sender_ip;
		this.recepients = recepients;
		this.receiver_id = receiver_id;
		this.message_type = message_type;
		this.message_content = message_content;
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
	
}
