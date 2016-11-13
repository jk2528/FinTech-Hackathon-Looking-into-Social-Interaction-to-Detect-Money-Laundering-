package test;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	public HashMap <String, Account> Accountmap; 
	public HashMap <Account, Transfer[]> AccToTran;
	public ArrayList<Node> nodes;
	public Graph(HashMap <String, Account> Accmap, HashMap <Account, Transfer[]> AccTran){
		Accountmap = Accmap;
		AccToTran = AccTran;
		for(String key : Accountmap.keySet()){
			nodes.add(new Node(Accountmap.get(key),(int)Math.random()*2000,(int)Math.random()*2000));
		}
	}
	public class Node{
		Account account;
		int x;
		int y;
		public boolean called = false;
		public Node(Account acc,int x, int y){
			account = acc;
			this.x=x;
			this.y=y;
		}
		public int getX (){
			return x;
		}
		public int getY(){
			return y;
		}
	}
	public class Edge{
		Transfer transfer;
		Node payer;
		Node payee;
		public Edge(Transfer tran, Node init, Node end){
			transfer = tran;
			payer = init;
			payee = end;
		}
		public Node otherNode (Node given){
			if (payer==given){
				return payee;
			}
			if(payee==given){
				return payee;
			}
			System.out.println("Wrong Node!");
			return null;
		}
		
	}
}
