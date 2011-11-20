import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ModifyComponent {
	
	public ArrayList<Component> components = new ArrayList<Component>();
	public ArrayList<Wire> inputSignals = new ArrayList<Wire>();
	
	public ModifyComponent(ArrayList<Component> components, ArrayList<Wire> inputSignals)
	{
		this.components = components;
		this.inputSignals = inputSignals;
	}

	public void set(StringBuilder buffer)
	{
		String line = buffer.toString();
		StringTokenizer st = new StringTokenizer(line);
		int state = 0;
		int index = -1;
		
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			if(token.compareTo("InSignal") == 0)
			{
				state = 1;
			}
			else if(state == 1){
				//search for token name in inputSignals
				int x=0;
				while( x < inputSignals.size())
				{
					if(inputSignals.get(x).getName().compareTo(token) == 0)
					{
						index = x;
					}
					x++;
				}
				state = 2;
			}
			else if(state == 2)
			{
				int signalValue = Integer.parseInt(token);
				inputSignals.get(index).setSignal(signalValue);
			}
			

		}
		
	}
	
	public void connect(StringBuilder buffer)
	{				
		
		String line = buffer.toString();
		StringTokenizer st = new StringTokenizer(line);
		int state = 0;
		int index1 = -1;
		int index2 = -1;
		int flagInput = 0;
		String componentType= new String();
		int outputNumber=0;
		
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			
			if(token.compareTo("InSignal") == 0)
			{
				state = 11;
				flagInput = 1;

			}
			else if(state == 0)
			{
				state = 12;
			}
			else if(state == 11)
			{
				int x=0;
				while( x < inputSignals.size())
				{
					if(inputSignals.get(x).getName().compareTo(token) == 0)
					{
						index1 = x;
					}
					x++;
				}
				state = 3;	
			}
			else if(state == 12)
			{
				int x=0;
				while( x < components.size())
				{
					if(components.get(x).getName().compareTo(token) == 0)
					{
						index1 = x;
					}
					x++;
				}
				state = 2;	
			}
			else if(state == 2)
			{
				outputNumber=0;
				
				if(componentType.compareTo("HalfAdder") == 0 || componentType.compareTo("FullAdder") == 0)
				{
					if (token.compareTo("outSum") == 0) {
						outputNumber = 1;
					} else if (token.compareTo("outCarry") == 0) {
						outputNumber = 5;
					} 
				}
				else if(componentType.compareTo("FullCarryAdder") == 0 )
				{
					if (token.compareTo("out1") == 0) {
						outputNumber = 1;
					} 
					else if (token.compareTo("out2") == 0) {
						outputNumber = 2;
					} 
					else if (token.compareTo("out3") == 0) {
						outputNumber = 3;
					} 
					else if (token.compareTo("out4") == 0) {
						outputNumber = 4;
					} 
					else if (token.compareTo("outCarry") == 0) {
						outputNumber = 5;
					} 
				}

				state = 3;
			}
			else if(state == 3)
			{

				componentType = token;
				state = 4;
			}
			else if(state == 4)
			{

				int x=0;
				while( x < components.size())
				{
					if(components.get(x).getName().compareTo(token) == 0)
					{
						index2 = x;
					}
					x++;
				}
				state = 5;
			}
			else if(state == 5)
			{	
				state = 6;
			}
			else if(state == 6)
			{
			
				
				int inputNumber=0;
				if(componentType.compareTo("AndGate") == 0 || componentType.compareTo("OrGate") == 0
						|| componentType.compareTo("XorGate") == 0 || componentType.compareTo("NandGate") == 0)
				{
					inputNumber = Integer.parseInt(token);
				}
				else{
					
					if(componentType.compareTo("Inverter") == 0)
					{
						inputNumber = 1;
					}
					else if(componentType.compareTo("HalfAdder") == 0 || componentType.compareTo("FullAdder") == 0)
					{
					
						if (token.compareTo("in1") == 0) {
							inputNumber = 1;
						} else if (token.compareTo("in2") == 0) {
							inputNumber = 2;
						} else if (token.compareTo("inCarry") == 0) {
							inputNumber = 9;
						}
					}
					else{ //this is a carry ripple adder
						
						if (token.compareTo("11") == 0) {
							inputNumber = 1;
						
						}
						else if (token.compareTo("12") == 0) {
							inputNumber = 2;
						}
						else if (token.compareTo("13") == 0) {
							inputNumber = 3;
						}
						else if (token.compareTo("14") == 0) {
							inputNumber = 4;
						}
						else if (token.compareTo("21") == 0) {
							inputNumber = 5;
						}
						else if (token.compareTo("22") == 0) {
							inputNumber = 6;
						}
						else if (token.compareTo("23") == 0) {
							inputNumber = 7;
						}
						else if (token.compareTo("24") == 0) {
							inputNumber = 8;
						}
						else if (token.compareTo("inCarry") == 0) {
							inputNumber = 9;
						}
					}
				}

				//connect components together using wires
				if(flagInput == 1)//input wire connecting to components inputs
				{
				
					
					inputSignals.get(index1).setInSignalConnections(components.get(index2));
					components.get(index2).setInput(inputNumber, inputSignals.get(index1));

					
				}
				else if (flagInput == 0) //output of component to input of another component
				{
					
					Wire wire1 = new Wire(components.get(index1), components.get(index2));
					components.get(index1).setOutput(outputNumber, wire1);
					components.get(index2).setInput(inputNumber, wire1);

				}
				
				state = 3;
			}
		}
		
	}
	
	public void displayIns(StringBuilder buffer) //0 = 2 by 1, 1 = inverter, 
	{
		String line = buffer.toString();
		StringTokenizer st = new StringTokenizer(line);
		int state = 0;
		int index = -1;
		String componentType = new String();
		
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			if(state == 0)
			{
				componentType = token;
				state = 1;
			}
			else if(state == 1)
			{
				//search for token name in components
				int x=0;
				while( x < components.size())
				{
					if(components.get(x).getName().compareTo(token) == 0)
					{
						index = x;
					}
					x++;
				}
				
				System.out.print(componentType +" " + components.get(index).getName());
				if(componentType.compareTo("AndGate") == 0 || componentType.compareTo("OrGate") == 0
						|| componentType.compareTo("XorGate") == 0 || componentType.compareTo("NandGate") == 0)
				{
					System.out.print(" in1: " + components.get(index).getInput(1).getValue());
					System.out.println(" in2: " + components.get(index).getInput(2).getValue());
				}

				else if(componentType.compareTo("Inverter") == 0)
				{
					System.out.println(" in1: " + components.get(index).getInput(1).getValue());
				}
				else if(componentType.compareTo("HalfAdder") == 0)
				{
					System.out.print(" in1: " + components.get(index).getInput(1).getValue());
					System.out.println(" in2: " + components.get(index).getInput(2).getValue());
				}
				else if(componentType.compareTo("FullAdder") == 0)
				{
					System.out.print(" in1: " + components.get(index).getInput(1).getValue());
					System.out.print(" in2: " + components.get(index).getInput(2).getValue());
					System.out.println(" inCarry: " + components.get(index).getInput(9).getValue());
				}
				else if(componentType.compareTo("FullCarryAdder") == 0)
				{
					
				}
				
				state = 0;
			}
			
		}
		
	}
	
	public void displayOuts(StringBuilder buffer)
	{
		String line = buffer.toString();
		StringTokenizer st = new StringTokenizer(line);
		int state = 0;
		int index = -1;
		String componentType = new String();
		
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			if(state == 0)
			{
				componentType = token;
				state = 1;
			}
			else if(state == 1)
			{
				
				//search for token name in components
				int x=0;
				while( x < components.size())
				{
					if(components.get(x).getName().compareTo(token) == 0)
					{
						index = x;
					}
					x++;
				}
				
				System.out.print(componentType +" " + components.get(index).getName());
			
				if(componentType.compareTo("AndGate") == 0 || componentType.compareTo("OrGate") == 0
						|| componentType.compareTo("XorGate") == 0 || componentType.compareTo("NandGate") == 0)
				{
					
					System.out.println(" out: " + components.get(index).getOutput(1).getValue());
					
				}

				else if(componentType.compareTo("Inverter") == 0)
				{
					System.out.println(" out: " + components.get(index).getOutput(1).getValue());
				}
				else if(componentType.compareTo("HalfAdder") == 0)
				{
					
					System.out.print(" outSum: " + components.get(index).getOutput(1).getValue());
					System.out.println(" outCarry: " + components.get(index).getOutput(5).getValue());
				}
				else if(componentType.compareTo("FullAdder") == 0)
				{
					System.out.print(" outSum: " + components.get(index).getOutput(1).getValue());
					System.out.println(" outCarry: " + components.get(index).getOutput(5).getValue());
				}
				else if(componentType.compareTo("FullCarryAdder") == 0)
				{
					System.out.print(" outs: " + components.get(index).getOutput(4).getValue() 
							+components.get(index).getOutput(3).getValue() +components.get(index).getOutput(2).getValue()
							+components.get(index).getOutput(1).getValue());
					System.out.println(" carry-out: " + components.get(index).getOutput(5).getValue());
				}
				
				state = 0;
				
			}
			
		}
		
	}
}
