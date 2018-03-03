package fr.yann.view;
 
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import fr.yann.model.Edge;
import fr.yann.model.Graph;
 
public class Frame extends JFrame{
 
  /** Pour éviter un warning venant du JFrame */
  private static final long serialVersionUID = -8123406571694511514L;
  private Graph myGraph;
  private JSplitPane splitPane = new JSplitPane();
  
  public Frame(Graph myGraph){
    super("Révise 1 UV de merde (une autre lel)");
	this.myGraph = myGraph;
    setSize(1500,920);
    mxGraph graph = new mxGraph();
    Object parent = graph.getDefaultParent();
    Menu m = new Menu(myGraph, this);
    
    add(m);
    
    graph.getModel().beginUpdate();
    try {
    	ArrayList<Object> objectList = new ArrayList<Object>();
    	
    	
    	
    	for(int i=0; i<myGraph.getNodeList().size(); i++)
    	{
    		objectList.add(graph.insertVertex(parent, null, myGraph.getNodeList().get(i).getName(), coord[i][0], coord[i][1],  80, 30,"fontColor=black;strokeColor=black;fillColor=#4fabfc"));
    	}
    	
    	for(int i=0; i<myGraph.getEdgeList().size(); i++)
    	{
    		graph.insertEdge(parent, null, myGraph.getEdgeList().get(i).getType(), objectList.get(myGraph.getEdgeList().get(i).getStart().getNumber()-1),  objectList.get(myGraph.getEdgeList().get(i).getArrival().getNumber()-1), "fontColor=black;strokeColor=black;");
    	}
    	
    	mxParallelEdgeLayout layout = new mxParallelEdgeLayout(graph);
        layout.execute(graph.getDefaultParent());
        
      //Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
      //Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
      //graph.insertEdge(parent, null, "Edge", v1, v2);
    } finally {
      graph.getModel().endUpdate();
    }
 
    mxGraphComponent graphComponent = new mxGraphComponent(graph);
    //getContentPane().add(graphComponent);
    
    
    splitPane.setSize(1500, 920);
    splitPane.setDividerSize(0);
    splitPane.setDividerLocation(150);
    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    splitPane.setLeftComponent(m);
    splitPane.setRightComponent(graphComponent);

    this.add(splitPane);
    setExtendedState(JFrame.MAXIMIZED_BOTH); 
    setVisible(true); 
  }
  
  public void updateGraph(ArrayList<Integer> path, Graph newGraph, ArrayList<Boolean> reachables)
  {
	mxGraph graph = new mxGraph();
	Object parent = graph.getDefaultParent();
	
	graph.getModel().beginUpdate();
	try {
		ArrayList<Object> objectList = new ArrayList<Object>();
		
		for(int i=0; i<myGraph.getNodeList().size(); i++)
		{
			if (findInPath(path, myGraph.getNodeList().get(i).getNumber())) //if in the path
			{
				objectList.add(graph.insertVertex(parent, null, myGraph.getNodeList().get(i).getName(), coord[i][0], coord[i][1], 80, 30,"fontColor=black;strokeColor=black;fillColor=#fc4fab"));
			}
			else
			{
				if(reachables.get(i) == true) //the node is reachable but not in the path
				{
					objectList.add(graph.insertVertex(parent, null, myGraph.getNodeList().get(i).getName(), coord[i][0], coord[i][1], 80, 30,"fontColor=black;strokeColor=black;fillColor=#fcb04f"));
				}
				else
				{
					objectList.add(graph.insertVertex(parent, null, myGraph.getNodeList().get(i).getName(), coord[i][0], coord[i][1], 80, 30,"fontColor=black;strokeColor=black;fillColor=#4fabfc"));
				}
			}
		}
		
		for(int i=0; i<newGraph.getEdgeList().size(); i++)
		{
			if (isEdgeInPath(path, newGraph, newGraph.getEdgeList().get(i)))
			{
				graph.insertEdge(parent, null, newGraph.getEdgeList().get(i).getType(), objectList.get(newGraph.getEdgeList().get(i).getStart().getNumber()-1),  objectList.get(newGraph.getEdgeList().get(i).getArrival().getNumber()-1),"fontColor=black;strokeColor=d6167c;");
			}
			else
			{
				graph.insertEdge(parent, null, newGraph.getEdgeList().get(i).getType(), objectList.get(newGraph.getEdgeList().get(i).getStart().getNumber()-1),  objectList.get(newGraph.getEdgeList().get(i).getArrival().getNumber()-1),"fontColor=black;strokeColor=black;");
			}
		}
		
		mxParallelEdgeLayout layout = new mxParallelEdgeLayout(graph);
	    layout.execute(graph.getDefaultParent());

	} finally {
	  graph.getModel().endUpdate();
	}
 
    mxGraphComponent graphComponent = new mxGraphComponent(graph);
    
    splitPane.setDividerLocation(150);
    splitPane.setRightComponent(graphComponent);
    this.add(splitPane);
  }
  
  public void updateGraph(Graph newGraph, ArrayList<Boolean> reachables)
  {
	mxGraph graph = new mxGraph();
	Object parent = graph.getDefaultParent();
	
	graph.getModel().beginUpdate();
	try {
		ArrayList<Object> objectList = new ArrayList<Object>();
		
		for(int i=0; i<myGraph.getNodeList().size(); i++)
		{
			if(reachables.get(i) == true) //the node is reachable but not in the path
			{
				objectList.add(graph.insertVertex(parent, null, myGraph.getNodeList().get(i).getName(), coord[i][0], coord[i][1], 80, 30,"fontColor=black;strokeColor=black;fillColor=#fcb04f"));
			}
			else
			{
				objectList.add(graph.insertVertex(parent, null, myGraph.getNodeList().get(i).getName(), coord[i][0], coord[i][1], 80, 30,"fontColor=black;strokeColor=black;fillColor=#4fabfc"));
			}
		}
		
		for(int i=0; i<newGraph.getEdgeList().size(); i++)
		{
			graph.insertEdge(parent, null, newGraph.getEdgeList().get(i).getType(), objectList.get(newGraph.getEdgeList().get(i).getStart().getNumber()-1),  objectList.get(newGraph.getEdgeList().get(i).getArrival().getNumber()-1),"fontColor=black;strokeColor=black;");
		}
		
		mxParallelEdgeLayout layout = new mxParallelEdgeLayout(graph);
	    layout.execute(graph.getDefaultParent());

	} finally {
	  graph.getModel().endUpdate();
	}
 
    mxGraphComponent graphComponent = new mxGraphComponent(graph);
    
    splitPane.setDividerLocation(150);
    splitPane.setRightComponent(graphComponent);
    this.add(splitPane);
  }
  
  private boolean findInPath(ArrayList<Integer> path, int toFind)
  {
	  for (int i =0; i<path.size(); i++)
	  {
		  if(path.get(i) == toFind) {
			  return true; //found
		  }
	  }
	  return false; //not found
  }
  
  private boolean isEdgeInPath(ArrayList<Integer> path,Graph newGraph, Edge edge)
  {
	  float mini = -1;
	  for (int i=0; i<path.size()-1; i++)
	  {
		  if((edge.getStart().getNumber()==path.get(i)) && (edge.getArrival().getNumber()==path.get(i+1)))
		  {
			  mini = edge.getTime();
			  for(Edge e : newGraph.getEdgeList())
			  {
				  //if an edge starts with a Node in the path, actualize the minimum time
				  if((e.getStart().getNumber()==path.get(i)) && (e.getArrival().getNumber()==path.get(i+1)))
				  {
					  if(mini>e.getTime())
					  {
						  mini=e.getTime();
					  }
				  }  
			  }
		  }
	  }
	  
	  if(mini==edge.getTime())
	  {
		  return true; 
	  }
	  return false;
  }
  
  double[][] coord = {
          {350, 850},//1
          {450, 780},//2
          {650, 740},//3
          {760, 650},//4
          {940,650},//5
          {820,550},//6
          {530, 470},//7
          {600, 580},//8
          {1000, 380},//9
          {600, 400},//10
          {820, 350},//11
          {970,480},//12
          {1000,70},//13
          {1000,150},//14
          {500, 350},//15
          {900,220},//16
          {500,530},//17
          {450,700},//18
          {400,600},//19
          {300,670},//20
          {320,520},//21
          {600,270},//22
          {600,200},//23
          {500,150},//24
          {820,40},//25
          {820,130},//26
          {150,150},//27
          {150,480},//28
          {100,380},//29
          {250,400},//30
          {350,300},//31
          {650,30},//32
          {400,50},//33
          {240,70},//34
          {100,250},//35
          {350,150},//36
          {500,100}
  };
  
  
}