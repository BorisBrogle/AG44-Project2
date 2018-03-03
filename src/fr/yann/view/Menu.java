package fr.yann.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.yann.model.Dijkstra;
import fr.yann.model.Graph;
import fr.yann.model.Node;

public class Menu extends JPanel  implements ActionListener{

	private static final long serialVersionUID = 1L;
	protected JButton	submit;
	private JLabel startString;
	private JLabel endString;
	private JTextField start;
	private JTextField end;
	private ArrayList<JCheckBox> edgeTypeList;
	private JCheckBox V;
	private JCheckBox B;
	private JCheckBox R;
	private JCheckBox N;
	private JCheckBox TK;
	private JCheckBox TS;
	private JCheckBox TSD;
	private JCheckBox TC;
	private JCheckBox TPH;
	private JCheckBox BUS;
	private JCheckBox KL;
	private JCheckBox SURF;
	private JLabel skiingLevel;
	private Graph myGraph;
	private Frame frame;
	private JLabel reachableString;
	private JLabel reachable;
	private JLabel legend;
	private JLabel legendDefault;
	private JLabel legendReachable;
	private JLabel legendPath;
	
	public Menu(Graph g, Frame f)
	{
		//Use layout (gridBagLayout)
		
	    setLayout(null);
		
		myGraph = g;
		frame = f;
		startString = new JLabel("Start:"); startString.setBounds(new Rectangle(new Point(5,5), startString.getPreferredSize()));
		endString = new JLabel("End:"); endString.setBounds(new Rectangle(new Point(80,5), endString.getPreferredSize()));
		start = new JTextField("\"Start\"", 5); start.setBounds(new Rectangle(new Point(5,25), start.getPreferredSize()));
		end = new JTextField("\"End\"", 5); end.setBounds(new Rectangle(new Point(80,25), end.getPreferredSize()));
		skiingLevel = new JLabel("Select your path:"); skiingLevel.setBounds(new Rectangle(new Point(5,50), skiingLevel.getPreferredSize()));
		edgeTypeList = new ArrayList<>();
		V = new JCheckBox("V"); edgeTypeList.add(V); V.setBounds(new Rectangle(new Point(25,70), V.getPreferredSize()));
		B = new JCheckBox("B"); edgeTypeList.add(B); B.setBounds(new Rectangle(new Point(90,70), B.getPreferredSize()));
		R = new JCheckBox("R"); edgeTypeList.add(R); R.setBounds(new Rectangle(new Point(25,90), R.getPreferredSize()));
		N = new JCheckBox("N"); edgeTypeList.add(N); N.setBounds(new Rectangle(new Point(90,90), N.getPreferredSize()));
		TK = new JCheckBox("TK"); edgeTypeList.add(TK); TK.setBounds(new Rectangle(new Point(25,110), TK.getPreferredSize()));
		TS = new JCheckBox("TS"); edgeTypeList.add(TS); TS.setBounds(new Rectangle(new Point(90,110), TS.getPreferredSize()));
		TSD = new JCheckBox("TSD"); edgeTypeList.add(TSD); TSD.setBounds(new Rectangle(new Point(25,130), TSD.getPreferredSize()));
		TC = new JCheckBox("TC"); edgeTypeList.add(TC); TC.setBounds(new Rectangle(new Point(90,130), TC.getPreferredSize()));
		TPH = new JCheckBox("TPH"); edgeTypeList.add(TPH); TPH.setBounds(new Rectangle(new Point(25,150), TPH.getPreferredSize()));
		BUS = new JCheckBox("BUS"); edgeTypeList.add(BUS); BUS.setBounds(new Rectangle(new Point(90,150), BUS.getPreferredSize()));
		KL = new JCheckBox("KL"); edgeTypeList.add(KL); KL.setBounds(new Rectangle(new Point(25,170), KL.getPreferredSize()));
		SURF = new JCheckBox("SURF"); edgeTypeList.add(SURF); SURF.setBounds(new Rectangle(new Point(90,170), SURF.getPreferredSize()));
		submit = new JButton("Submit"); submit.setBounds(new Rectangle(new Point(40,210), submit.getPreferredSize()));
		reachableString = new JLabel("Reachable Nodes:"); reachableString.setBounds(new Rectangle(new Point(5,250), reachableString.getPreferredSize()));
		reachable = new JLabel("<html>");
		legend = new JLabel("Legend:"); legend.setBounds(new Rectangle(new Point(5,620), legend.getPreferredSize()));
		legendDefault = new JLabel("Default:"); legendDefault.setBounds(new Rectangle(new Point(15,640), legendDefault.getPreferredSize()));
		legendReachable = new JLabel("Reachable:"); legendReachable.setBounds(new Rectangle(new Point(15,660), legendReachable.getPreferredSize()));
		legendPath = new JLabel("Path:"); legendPath.setBounds(new Rectangle(new Point(15,680), legendPath.getPreferredSize()));

		final JScrollPane scrolll = new JScrollPane(reachable);
		scrolll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrolll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrolll.setBounds(new Rectangle(new Point(5,270), new Dimension(140,350)));
		
		add(legend);
		add(legendDefault);
		add(legendReachable);
		add(legendPath);
		add(scrolll);
		add(startString);
		add(endString);
		add(start);
		add(end);
		add(skiingLevel);
		start.addActionListener(this);
		end.addActionListener(this);
		for(JCheckBox jcb : edgeTypeList)
		{
			jcb.addActionListener(this);
			jcb.setSelected(true);
			add(jcb);
		}
		add(submit);
		add(reachableString);
		//add(reachableScroll);
		
		submit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Node startNode = getNodeFromString(start.getText());
		Node endNode = getNodeFromString(end.getText());
		
		Graph newGraph;
		
		reachable.setText("<html>");
		
		ArrayList<String> edgeType = new ArrayList<String>();
		
		for(JCheckBox jcb : edgeTypeList)
		{
			if(jcb.isSelected())
			{
				edgeType.add(jcb.getText());
			}
		}
		
		newGraph = new Graph(myGraph,edgeType);

		if(startNode==null)
		{
			System.out.println("\nError : the starting node doesn't exist");
		}
		else
		{
			Dijkstra dij = new Dijkstra(startNode);
	        dij.launchDijkstra(newGraph);
	        ArrayList<Boolean> reachables = new ArrayList<>();
	        reachables = dij.areReachable();
	        for(int i=0; i<reachables.size();i++)
	        {
	        	if(reachables.get(i)==true)
	        	{
	        		reachable.setText(reachable.getText()+newGraph.getNode(i).getName()+"<br>");
	        	}
	        }
	        reachable.setText(reachable.getText()+"</html>");
			reachable.setBounds(5,250,(int)reachable.getPreferredSize().getWidth(),(int)reachable.getPreferredSize().getHeight());
	        frame.updateGraph(newGraph, reachables);
	        
	        if(endNode==null)
	        {
	        	System.out.println("\nError : the ending node doesn't exist");
	        }
	        else
	        {
	        	ArrayList<Integer> path = dij.returnPath(endNode);
		        printPath(path);
		        
		        frame.updateGraph(path, newGraph, reachables);
	        }
		}
	}

	private void printPath(ArrayList<Integer> path) {
		
		ArrayList<String> stringPath = new ArrayList<>();
		
		for(int i=0; i<path.size();i++)
		{
			for(int j=0; j<myGraph.getNodeList().size(); j++)
			{
				if(path.get(i)==myGraph.getNodeList().get(j).getNumber())
				{
					stringPath.add(myGraph.getNodeList().get(j).getName());
				}
			}
		}
		
		if (path.size()>0)
		{
			System.out.println("\nPath : ");
		}
        for(int i=0; i<path.size(); i++)
        {
        	if (i==path.size()-1) {
        		System.out.print(stringPath.get(i));
        	}
        	else
        	{
        		System.out.print(stringPath.get(i)+" => ");
        	}
        }
	}

	private Node getNodeFromString(String text) {
		return myGraph.getNodeFromString(text);
	}
	
	@Override
	  public void paintComponent(Graphics g)
	  {
		  super.paintComponents(g);
		  g.setColor(Color.decode("#4fabfc"));
		  g.fillRect(65,642,20,15);
		  g.setColor(Color.decode("#fcb04f"));
		  g.fillRect(85,662,20,15);
		  g.setColor(Color.decode("#fc4fab"));
		  g.fillRect(50,682,20,15);
	  }

}
