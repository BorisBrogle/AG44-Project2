package fr.yann.model;

import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {
	
	//an ArrayList that will group a weight and a predecessor for each node
	private ArrayList<WeightPred> distanceResp;
	private Node start;
	
	public Dijkstra(Node Node)
	{
		distanceResp = new ArrayList<>();
		start = Node;
	}
	
	public void launchDijkstra(Graph graph)
	{
		//Initialising the array with +inf or 0 for the start
		for(Node node : graph.getNodeList())
		{
			WeightPred temp = new WeightPred();
			if (node.getNumber() == start.getNumber())
			{
				temp.setWeight(0);
				temp.setPred(start.getNumber());
			}
			else
			{
				temp.setWeight(Float.MAX_VALUE);
				temp.setPred(-1);
			}
			
			distanceResp.add(temp);
		}
		
		//used to know if we modified a weight during a loop of the algorithm
		boolean modif = true;
		
		while(modif == true)
		{
			modif = false;
			for(Edge e : graph.getEdgeList())
			{
				//if the weight of a node isn't +inf we can actualize the successor weight if necessary
				if(distanceResp.get((e.getStart().getNumber())-1).getWeight() != Float.MAX_VALUE)
				{
					//calculating the newWeight from the edge's start
					float newWeight = distanceResp.get(e.getStart().getNumber()-1).getWeight() + e.getTime();
					
					//comparing the newWeight to the actual weight, if the newWeight is smaller, we actualize the weight
					if(newWeight < distanceResp.get(e.getArrival().getNumber()-1).getWeight())
					{
						distanceResp.get(e.getArrival().getNumber()-1).setWeight(newWeight);
						//we actualize the predecessor from which the weight is the smallest
						distanceResp.get(e.getArrival().getNumber()-1).setPred(e.getStart().getNumber());
						//we set modif to true because we did something during the loop
						modif  = true;
					}
				}
			}
		}
	}
	
	public ArrayList<Integer> returnPath(Node arrival) //Read distanceResp to build the shortest path
	{
		ArrayList<Integer> path = new ArrayList<>();
		//we will build the path upside down and then reverse it
		
		//verifying if the starting node has any successors
		if(start.hasSuccessors())
		{			
			if(isNodeReachable(arrival))
			{
				if((start.getNumber() != arrival.getNumber()))
				{
					path.add(arrival.getNumber());
					//we get the predecessor of the arrival (from which the weight is the smallest)
					int pred = distanceResp.get(arrival.getNumber()-1).getPred();
					//while we haven't reached the start vertex, we get through the predecessors and add them to the path
					while(pred != start.getNumber())
					{
						path.add(pred);
						pred = distanceResp.get(pred-1).getPred();
					}
					//adding the starting vertex
					path.add(pred);
					Collections.reverse(path);
				}
				else
				{
					System.out.println("\nError : the arrival is the same Node as the departure !");
				}
			}
			else
			{
				System.out.println("\nError : the arrival is not reachable !");
			}
		}
		else
		{
			System.out.println("\nError : the starting Node has no successors !");
		}
		return path;
	}
	
	//Vérifie si le Dijkstra a pu atteindre le noeud d'arrivé
	public boolean isNodeReachable(Node node)
	{
		if(distanceResp.get(node.getNumber()-1).getWeight()!=Float.MAX_VALUE) //&&*/ wp.getWeight()!=Float.MAX_VALUE)
		{
			return true;
		}
		return false;
	}
	
	public ArrayList<Boolean> areReachable()
	{
		ArrayList<Boolean> reachables = new ArrayList<>();
		
		for (int i=0; i<distanceResp.size(); i++)
		{
			if (distanceResp.get(i).getWeight() != Float.MAX_VALUE)
			{
				reachables.add(true);
			}
			else
			{
				reachables.add(false);
			}
		}
		
		return reachables;
	}
	
	//Nested class used to build an ArrayList to group a weight and a predecessor for each node
	private class WeightPred {
		private float weight;
		private int pred;
		
		public WeightPred()
		{
			weight = 0;
			pred = 0;
		}
		
		public float getWeight()
		{
			return weight;
		}
		
		public int getPred()
		{
			return pred;
		}
		
		public void setWeight(float w)
		{
			weight = w;
		}
		
		public void setPred(int n)
		{
			pred = n;
		}
	}
}
