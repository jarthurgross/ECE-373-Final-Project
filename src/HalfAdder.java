
public class HalfAdder extends Adders implements Component {
	
	public HalfAdder(String name){
		this.name = name;
	}
	
	
	public void logic() {

		XorGate tempXor = new XorGate("tempXor1");
		AndGate tempAnd = new AndGate("tempAnd1");

		tempXor.setInput(1, in1);
		tempXor.setInput(2, in2);
		tempXor.setOutput(1, sum1);
		tempAnd.setInput(1, in1);
		tempAnd.setInput(2, in2);
		tempAnd.setOutput(1, carryout);

		tempXor.logic();
		tempAnd.logic();
		
	}





}

