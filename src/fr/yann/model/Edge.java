package fr.yann.model;


public class Edge
{
	
	private int number;
	private String name;
	private String type;
	private Node start;
	private Node arrival;
	private float distance;
	private float time;
	
	public Edge()
	{
		this.number = 0;
		this.name = "";
		this.type = "";
		this.start = null;
		this.arrival = null;
		this.distance = 0;
		this.time = 0;
	}
	
	public Edge(int number, String name, String type, Node start, Node arrival)
	{
		this.number = number;
		this.name = name;
		this.type = type;
		this.start = start;
		this.arrival = arrival;
		
		updateDistanceTime();
	}
	
	private void updateDistanceTime()
	{
		this.distance = Math.abs(start.getAltitude() - arrival.getAltitude());
		switch (type)
		{
		  case "V":
			  this.time = 5*distance/100;
		    	break;
		  case "B":
			  this.time = 4*distance/100;
			    break;
		  case "R":
			  this.time = 3*distance/100;
			    break;
		  case "N":
			  this.time = 2*distance/100;
			    break;
		  case "KL":
			  this.time = (float)((1.0/6.0)*distance/100); //casting because 1.0/6.0 is a double
			    break;
		  case "SURF":
			  this.time = 10*distance/100;
			    break;
		  case "TPH":
			  this.time = 4+2*distance/100;
			    break;
		  case "TC":
			  this.time = 2+3*distance/100;
			    break;
		  case "TSD":
			  this.time = 1+3*distance/100;
			    break;
		  case "TS":
			  this.time = 1+4*distance/100;
			    break;
		  case "TK":
			  this.time = 1+4*distance/100;
			    break;
		  case "BUS":
			  if(name.equals("navette1600-1800") || name.equals("navette1800-1600"))
			  {
				  this.time = 40;
			  }
			  else if(name.equals("navette1600-2000") || name.equals("navette2000-1600"))
			  {
				  this.time = 30;
			  }
			    break;
		}
	}
	
	public int getNumber()
	{
		return this.number;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public float getTime()
	{
		return this.time;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public Node getStart()
	{
		return this.start;
	}
	
	public Node getArrival()
	{
		return this.arrival;
	}
	
}