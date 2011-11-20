
public interface Component {
	
	
	public void logic();
	
	public String getName();
	
	public Wire getInput(int number);
	
	public Wire getOutput(int number);
	
	public void setInput(int number, Wire newWire);
	
	public void setOutput(int number, Wire newWire);

}
