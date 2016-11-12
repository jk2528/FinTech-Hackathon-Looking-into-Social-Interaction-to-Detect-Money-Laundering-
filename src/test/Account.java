package test;

public class Account {
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
