
public class Component_2in_1out{

	public Wire in1, in2, out;
	public String name;
	
	public String getName()
	{
		return name;
	}
	
	public Wire getInput(int number)
	{
		if(number == 1)
		{
			return in1;
		}
		else {
			return in2;
		}
	}
	
	public void setInput(int number, Wire newWire)
	{
		if(number == 1)
		{
			in1 = newWire;
		}
		else if (number == 2){
			in2 = newWire;
		}
	}
	
	public void setOutput(int number, Wire newWire)
	{
		out = newWire;
	}
	
	
	public Wire getOutput(int number){
		return out;
	}
	


	

}
