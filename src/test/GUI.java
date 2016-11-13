package test;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import test.Graph.Node;

public class GUI extends JFrame{
	Graph graph;
	int r = 3;
	int d = 6;
	public GUI(){
		setMinimumSize(new Dimension(2000,1000));
		pack();
        validate();
		repaint();
		setVisible(true);
	}
	public GUI(Graph graph){
		repaint();
	}
	public void paint(Graphics g){
		for(String key : graph.Accountmap.keySet()){
			Account a = graph.Accountmap.get(key);
			
		}
	}
	public void drawNode(Node node, Graphics g){
		g.drawOval(node.y-r/2, node.y-r/2, r,r);
	}
	public void drawLine(Node node1, Node node2, Graphics g){
		g.drawLine(node1.x,node1.y,node2.x,node2.y);
	}
	
	public static void main (String[] args){
		
		GUI gui= new GUI();
	}
	
	
}
