package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;

import test.Account.Type;

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
	private int count;
	
	public int getCount(){
		return count;
	}
	
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
	
	public static HashMap<String,Account> createMap(URL url) throws IOException{
		URLConnection myURLConnection = url.openConnection();
		try {
			myURLConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		String jsonText = readAll(in);
		JSONObject json = new JSONObject(jsonText);
		return createMap(json);
	}
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	public static HashMap<String,Account> createMap(JSONObject json){
		JSONArray ja = json.getJSONArray("results");
		HashMap<String,Account> a = new HashMap<>();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject temp = ja.getJSONObject(i);
			String id = temp.getString("_id");
			int bal = temp.getInt("balance");
			String c_id = temp.getString("customer_id");
			String nickname = temp.getString("nickname");
			int rewards = temp.getInt("rewards");
			String type = temp.getString("type");
			Type t = null;
			switch (type) {
			case "savings":
				t = Type.SAVINGS;
				break;
			case "checking":
				t = Type.CHECKING;
				break;
			case "credit_card":
				t = Type.CREDIT_CARD;
				break;
			}
			a.put(id,new Account(id, bal, c_id, nickname, rewards, t));
		}
		return a;
	}
	
}
