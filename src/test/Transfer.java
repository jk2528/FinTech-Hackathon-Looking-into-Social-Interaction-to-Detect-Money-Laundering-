package test;

public class Transfer {
	private String transferId;
	private int amount;
	private String description;
	private String payeeId;
	private String payerId;
	public boolean executed; // true if executed, false if cancelled
	private int year; // year the transfer occured
	private int month; // month the transfer occured
	private int day; // day the transfer occured

	public Transfer(String t, int a, String d, String payee, String payer, boolean e, int y, int m, int day) {
		transferId = t;
		amount = a;
		description = d;
		payeeId = payee;
		payerId = payer;
		executed = e;
		year = y;
		month = m;
		this.day = day;
	}

}
