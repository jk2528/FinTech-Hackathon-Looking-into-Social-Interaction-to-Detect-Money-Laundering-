package test;

	// TODO: Fields here 
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
	
	public String getAccountId(){
		return accountId;
	}
	public int getBalance(){
		return balance;
	}
	public String getCustomerId(){
		return customerId;
	}
	public String getNickname(){
		return nickname;
	}
	public int getRewards(){
		return rewards;
	}
	public Type getType(){
		return type;
	}
	
}
