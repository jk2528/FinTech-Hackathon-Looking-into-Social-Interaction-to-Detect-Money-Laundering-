package test;

public class Account {
	
	public Account(String sa, int b, String c, String n, int r, Type t){
		accountId = sa;
		balance = b;
		customerId = c;
		nickname = n;
		rewards = r;
		type = t;
	}
	
	private String accountId;
	private int balance;
	private String customerId;
	private String nickname;
	private int rewards;
	private Type type; 
	public enum Type {CREDIT_CARD,SAVINGS,CHECKING};
	
}
