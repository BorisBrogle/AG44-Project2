package fr.yann.model;

import java.util.ArrayList;

public class Node
{
	private int number;
	private String name;
	private int altitude;
	private ArrayList<Node> succList;
	
	public Node()
	{
		this.number = 0;
		this.name = "";
		this.altitude = 0;
		this.succList = new ArrayList<Node>();
	}
	
	public Node(int number, String name, int altitude)
	{
		this.number = number;
		this.name = name;
		this.altitude = altitude;
		this.succList = new ArrayList<Node>();
	}
	
	public int getNumber()
	{
		return this.number;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getAltitude()
	{
		return this.altitude;
	}
	
	public ArrayList<Node> getSuccessors()
	{
		return this.succList;
	}
	
	public boolean equals(Node n)
	{
		
		return (this.number == n.number && this.name.equals(n.name));
	}
	
	public boolean hasSuccessors()
	{
		return !(succList.isEmpty());
	}
	
	protected void updateSuccList(ArrayList<Edge> edgeList)
	{
		for(int i=0; i<edgeList.size(); i++)
		{
			if(edgeList.get(i).getStart().equals(this))
			{
				this.succList.add(edgeList.get(i).getArrival());
			}
		}
	}
	
	
}
