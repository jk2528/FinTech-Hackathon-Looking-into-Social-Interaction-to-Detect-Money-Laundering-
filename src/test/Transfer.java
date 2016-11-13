package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.Account.Type;

public class Transfer {
	private String transferId;
	private double amount;
	private String description;
	private String payeeId;
	private String payerId;
	private Account payeeAccount;
	private Account payerAccount;
	public boolean executed; //true if executed, false if cancelled
	private int year; //year the transfer occured
	private int month; //month the transfer occured
	private int day; //day the transfer occured
	public Transfer(String t, double a, String d, String payeeId, String payerId,
			Account payee, Account payer, boolean e,int y, int m, int day){
		transferId = t;
		amount = a;
		description = d;
		this.payeeId = payeeId;
		this.payerId = payerId;
		payeeAccount = payee;
		payerAccount = payer;
		executed = e;
		year = y;
		month = m;
		this.day = day;
	}
	
	//Get Methods
	public String getTransferId() {return transferId;}
	public double getAmount() {return amount;}
	public String getDescription() {return description;}
	public String getPayeeId() {return payeeId;}
	public String getPayerId() {return payerId;}
	public boolean isExecuted() {return executed;}
	public int getYear() {return year;}
	public int getMonth() {return month;}
	public int getDay() {return day;}
	public Account getPayee() {return payeeAccount;}
	public Account getPayer() {return payerAccount;}
	
	
	
	public static ArrayList<Transfer> createList(URL url, HashMap<String,Account> accountMap) throws IOException{
		URLConnection myURLConnection = url.openConnection();
		try {
			myURLConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		String jsonText = readAll(in);
		JSONObject json = new JSONObject(jsonText);
		return createList(json, accountMap);
	}
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public static ArrayList<Transfer> createList(JSONObject json, HashMap<String,Account> accountMap){
		JSONArray ja = json.getJSONArray("results");
		ArrayList<Transfer> a = new ArrayList<>();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject temp = ja.getJSONObject(i);
			String id = temp.getString("_id");
			double am = 0.01;
			try{
				am = temp.getDouble("amount");
			}catch(JSONException e){
			}
			String dis = "";
			try{
				dis = temp.getString("description");
		
			}catch (JSONException e){
				
			}
			String payee = temp.getString("payee_id");
			String payer = temp.getString("payer_id");
			Account payees = accountMap.get(payee);
			Account payers = accountMap.get(payer);
			boolean ex = ("executed"== temp.getString("status"));
			int y = -1;
			int m = -1;
			int day = -1;
			try{
				String date = temp.getString("transaction_date");
				y = Integer.parseInt(date.substring(0, 3));
				m = Integer.parseInt(date.substring(5,6));
				day = Integer.parseInt(date.substring(8,9));
			}catch (Exception e){
				
			}
			a.add(new Transfer(id,am,dis,payee,payer,payees,payers,ex,y,m,day));
		}
		return a;
	}
}
