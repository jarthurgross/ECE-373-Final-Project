
public class Inverter implements Component{
	
	public Wire in1, out;
	public String name;
	
	public Inverter(String name)
	{
		this.name = name;
	}
	
	public void logic() {
		if(in1.getValue() == 1)
		{
			out.setSignal(0);
		}
		else{
			out.setSignal(1);
		}
		
	}


	public String getName() {
		return name;
	}

	
	public Wire getInput(int number) {
		return in1;
	}


	public Wire getOutput(int number) {
		return out;
	}

	
	public void setInput(int number, Wire newWire) {
		in1 = newWire;	
	}


	public void setOutput(int number, Wire newWire) {
		out = newWire;
	}
	

}
