package fr.yann.model;

import java.util.ArrayList;

public class Graph
{
	private ArrayList<Node> nodeList;
	private ArrayList<Edge> edgeList;
	
	public Graph()
	{
		this.nodeList = new ArrayList<Node>();
		this.edgeList = new ArrayList<Edge>();
	}
	
	public Graph(Graph toCopy, ArrayList<String> edgeType)
	{
		this.nodeList = new ArrayList<Node>();
		this.edgeList = new ArrayList<Edge>();
		for(int i=0; i<toCopy.getNodeList().size(); i++) {		
			nodeList.add(toCopy.getNode(i));
		}
		
		for(Edge edge : toCopy.edgeList)
		{
			for(String type : edgeType)
			{
				if(edge.getType().equals(type))
				{
					edgeList.add(edge);
				}
			}
		}
	}

	public void addNode(Node node)
	{
		this.nodeList.add(node);
	}
	
	public void addEdge(Edge edge)
	{
		this.edgeList.add(edge);
	}
	
	public Node getNode(int i)
	{
		return this.nodeList.get(i);
	}
	
	public Edge getEdge(int i) {
		return this.edgeList.get(i);
	}
	
	public Node getNodeByNumber(int number)
	{
		Node toReturn = new Node();
		for(int i=0; i<this.nodeList.size(); i++)
		{
			if(this.nodeList.get(i).getNumber()==number)
			{
				toReturn = this.nodeList.get(i);
			}
		}
		return toReturn;
	}
	
	public void updateNodeList()
	{
		for(Node n : this.nodeList)
		{
			n.updateSuccList(this.edgeList);
		}
	}
	
	public ArrayList<Node> getNodeList()
	{
		return this.nodeList;
	}
	
	public ArrayList<Edge> getEdgeList()
	{
		return this.edgeList;
	}

	public Node getNodeFromString(String text) {
		for(Node n : nodeList)
		{
			if(text.equals(n.getName()))
			{
				return n;
			}
		}
		return null;
	}
}