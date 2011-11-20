import java.util.ArrayList;


public class Wire {

	private Component inputComponent;
	private int signalValue; 
	private boolean inputSignal;
	private String name; //if used as input
	public ArrayList<Component> outputComponent = new ArrayList<Component>();
	
	public Wire(Component outputComponent, Component inputComponent)
	{
		this.outputComponent.add(outputComponent);
		this.inputComponent = inputComponent;
		inputSignal = false;
	}
	
	public Wire(String name) //used for input signal
	{
		this.signalValue = 0; //input signals created get default value of 0
		inputSignal = true;
		this.name = name;
	}
	
	public Wire(Component outputComponent)
	{
		this.outputComponent.add(outputComponent);
	}
	
	public void changeOutputComponent(Component outputComponent)
	{
		this.outputComponent.set(0, outputComponent);
	}
	
	public void clearInputSignalConnections()
	{
		outputComponent.clear();
	}
	
	public void setInSignalConnections(Component outputComponent)//used for input signals
	{
		this.outputComponent.add(outputComponent);
	}
	
	public void changeInputComponent(Component inputComponent)
	{
		this.inputComponent = inputComponent;
	}
	
	
	public void setSignal(int signalValue)
	{
		this.signalValue = signalValue;
	}
	
	public int getValue()
	{
		if(inputSignal == true)
		{
			return signalValue;
		}
		else{
		
			for(Component values : outputComponent)
			{

				values.logic();
			}	
			return signalValue;
		}
	}
	
	public String getName()
	{
		return name;
	}

}
