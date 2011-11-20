/*
 * Read the input file for a digital logic circuit.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.StringBuilder;

public class ReadInputFile {

	private String fileName;
	public ArrayList<Component> components = new ArrayList<Component>();
	public ArrayList<Wire> inputSignals = new ArrayList<Wire>();
	
	public ReadInputFile(String fileName)
	{
		this.fileName = fileName;
	}
	
	public void parseFile()
	{
		//flag used to decide where to send token
		int flag = 0;
		
		try
		{		
			File file = new File(fileName);
			Scanner inputFile = new Scanner(file);
			String line;
			System.out.println("Executing "+ fileName + "\n");
			
			//while the input file has another available line to read
			while(inputFile.hasNextLine())
			{
				
				line = inputFile.nextLine();
				StringTokenizer st = new StringTokenizer(line);
				StringBuilder buffer = new StringBuilder();
				
				//parse each token inside the line
				while(st.hasMoreTokens())
				{
					flag = useToken(st.nextToken(), flag);
					if(flag == 10 || flag == 11 || flag == 12 || flag == 13)
					{
						while(st.hasMoreTokens())
						{
							buffer.append(" " + st.nextToken());
						}
						
						
						
					}
				}
				
				if(flag == 10)
				{
					ModifyComponent modifications = new ModifyComponent(components, inputSignals);
					modifications.connect(buffer);
					flag = 0;

					
				}
				else if (flag == 11){
					ModifyComponent modifications = new ModifyComponent(components, inputSignals);
					modifications.set(buffer);
					flag = 0;

				}
				else if (flag == 12)
				{
					ModifyComponent modifications = new ModifyComponent(components, inputSignals);
					modifications.displayOuts(buffer);
					flag = 0;
				}
				else if (flag == 13)
				{
					ModifyComponent modifications = new ModifyComponent(components, inputSignals);
					modifications.displayIns(buffer);
					flag = 0;
				}
				
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	
		

	}
	
	private int useToken(String token, int flag)
	{			
		
		if(flag > 0 && flag <= 9)
		{
			//create a new object
			switch(flag){
			case(1):
				//create AndGate
				Component andGate = new AndGate(token);
			    components.add(andGate);
			    Wire wire1 = new Wire(andGate);
			    int last = components.size() - 1;
				components.get(last).setOutput(0, wire1);
			    
				break;
			case(2):
				//create OrGate
				Component orGate = new OrGate(token);
		    	components.add(orGate);
			    Wire wire2 = new Wire(orGate);
			    int last2 = components.size() - 1;
				components.get(last2).setOutput(0, wire2);
				break;
			case(3):
				//create XorGate
				Component xorGate = new XorGate(token);
	    		components.add(xorGate);
			    Wire wire3 = new Wire(xorGate);
			    int last3 = components.size() - 1;
				components.get(last3).setOutput(0, wire3);
				break;
			case(4):
				//create NandGate
				Component nandGate = new NandGate(token);
		    	components.add(nandGate);
			    Wire wire4 = new Wire(nandGate);
			    int last4 = components.size() - 1;
				components.get(last4).setOutput(0, wire4);
				break;
			case(5):
				//create Inverter
				Component inverter = new Inverter(token);
				components.add(inverter);
				Wire wire5 = new Wire(inverter);
				int last5 = components.size() - 1;
				components.get(last5).setOutput(0, wire5);
				break;
			case(6):
				//create halfAdder
				Component halfAdder = new HalfAdder(token);
				components.add(halfAdder);
				Wire wire6 = new Wire(halfAdder);
				Wire wire7 = new Wire(halfAdder);
				int last6 = components.size() - 1;
				components.get(last6).setOutput(1, wire6);
				components.get(last6).setOutput(5, wire7);

				break;
			case(7):
				Component fullAdder = new FullAdder(token);
				components.add(fullAdder);
				Wire wire8 = new Wire(fullAdder);
				Wire wire9 = new Wire(fullAdder);
				int last7 = components.size() - 1;
				components.get(last7).setOutput(1, wire8);
				components.get(last7).setOutput(5, wire9);
				break;
			case(8):
				Component fullCarryAdder = new FullCarryAdder(token);
				components.add(fullCarryAdder);
				Wire wire10 = new Wire(fullCarryAdder);
				Wire wire11 = new Wire(fullCarryAdder);
				Wire wire12 = new Wire(fullCarryAdder);
				Wire wire13 = new Wire(fullCarryAdder);
				Wire wire14 = new Wire(fullCarryAdder);
				int last8 = components.size() - 1;
				components.get(last8).setOutput(1, wire10);
				components.get(last8).setOutput(2, wire11);
				components.get(last8).setOutput(3, wire12);
				components.get(last8).setOutput(4, wire13);
				components.get(last8).setOutput(5, wire14);
				
				break;
			case(9):
				//create input signal
				Wire wire = new Wire(token);
				inputSignals.add(wire);
				break;
						
			}
			
			flag = 0;
		}
		else if(token.compareTo("AndGate") == 0)
		{
			if(flag == 0)
			{
				flag = 1;

			}
		}
		else if(token.compareTo("OrGate") == 0)
		{
			if(flag == 0)
			{
				flag = 2;				
			}
		}
		else if(token.compareTo("XorGate") == 0)
		{
			if(flag == 0)
			{
				flag = 3;		
			}
		}
		else if(token.compareTo("NandGate") == 0)
		{
			if(flag == 0)
			{
				flag = 4;		
			}
		}
		else if(token.compareTo("Inverter") == 0)
		{
			if(flag == 0)
			{
				flag = 5;			
			}
		}
		else if(token.compareTo("HalfAdder") == 0)
		{
			if(flag == 0)
			{
				flag = 6;	
			}
		}
		else if(token.compareTo("FullAdder") == 0)
		{
			if(flag == 0)
			{
				flag = 7;
			}
		}
		else if(token.compareTo("FullCarryAdder") == 0)
		{
			if(flag == 0)
			{
				flag = 8;
			}
		}
		else if(token.compareTo("InSignal") == 0)
		{
			if(flag == 0)
			{
				flag = 9;	
			}
		}
		else if(token.compareTo("Connect") == 0)
		{
			flag=10;
		}
		else if(token.compareTo("Set") == 0)
		{
			flag=11;
		}
		else if(token.compareTo("DisplayOuts") == 0)
		{
			flag = 12;
		}
		else if(token.compareTo("DisplayIns") == 0)
		{
			flag = 13;
		}

		return flag;
		
	}
}
