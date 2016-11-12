package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import test.Account.Type;

public class Transfer {
	private String transferId;
	private int amount;
	private String description;
	private String payeeId;
	private String payerId;
	public boolean executed; //true if executed, false if cancelled
	private int year; //year the transfer occured
	private int month; //month the transfer occured
	private int day; //day the transfer occured
	public Transfer(String t, int a, String d, String payee,String payer, boolean e,
			int y, int m, int day){
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
	
	public static ArrayList<Transfer> createList(URL url) throws IOException{
		URLConnection myURLConnection = url.openConnection();
		try {
			myURLConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		String jsonText = readAll(in);
		JSONObject json = new JSONObject(jsonText);
		return createList(json);
	}
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public static ArrayList<Transfer> createList(JSONObject json){
		JSONArray ja = json.getJSONArray("results");
		ArrayList<Transfer> a = new ArrayList<>();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject temp = ja.getJSONObject(i);
			String id = temp.getString("_id");
			int am = temp.getInt("amount");
			String dis = temp.getString("description");
			String payee = temp.getString("payee_id");
			String payer = temp.getString("payer_id");
			boolean ex = ("executed"== temp.getString("status"));
			String date = temp.getString("transaction_date");
			int y = Integer.parseInt(date.substring(0, 3));
			int m = Integer.parseInt(date.substring(5,6));
			int day = Integer.parseInt(date.substring(8,9));
			a.add(new Transfer(id,am,dis,payee,payer,ex,y,m,day));
		}
		return a;
	}
	
	
}
