package fr.yann.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

import fr.yann.model.Edge;
import fr.yann.model.Graph;
import fr.yann.model.Node;
import fr.yann.view.Frame;


public class Main
{
	public static void main(String[] args)
	{
		Graph myGraph = new Graph();
		
	    try{
	        Scanner reader = new Scanner(new FileInputStream("ressources/dataski.txt"));
	        
	        int numberOfNodes = Integer.parseInt(reader.next());

	        
	        for (int i = 0; i<numberOfNodes; i++)
	        {
	        	reader.nextLine();
		        Node newNode = new Node(Integer.parseInt(reader.next()), reader.next(),  Integer.parseInt(reader.next()));
		        myGraph.addNode(newNode);
	        }
	        
	        reader.nextLine();
	        int numberOfEdges = Integer.parseInt(reader.next());
	        
	        for (int i = 0; i<numberOfEdges; i++)
	        {
	        	reader.nextLine();
		        Edge newEdge = new Edge(Integer.parseInt(reader.next()), reader.next(), reader.next(), myGraph.getNodeByNumber(Integer.parseInt(reader.next())), myGraph.getNodeByNumber(Integer.parseInt(reader.next())));
		        myGraph.addEdge(newEdge);
	        }

            reader.close();
            
            myGraph.updateNodeList();
            
            Frame frame = new Frame(myGraph);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 800);
            frame.setVisible(true);
            
            //Call to the functions to find the shortest path, etc
            
	    }
	    catch(FileNotFoundException e)
	    {
	    	System.out.println("Error: no file found");
	    }
	}
}
	