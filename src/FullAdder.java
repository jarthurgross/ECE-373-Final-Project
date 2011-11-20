
public class FullAdder extends Adders implements Component {

	public FullAdder(String name){
		this.name = name;
	}
	
	public void logic() {
		
		XorGate tempXor1 = new XorGate("tempXor1");
		XorGate tempXor2 = new XorGate("tempXor2");
		AndGate tempAnd1 = new AndGate("tempAnd1");
		AndGate tempAnd2 = new AndGate("tempAnd2");
		OrGate tempOr = new OrGate("tempOr");
		Wire tempWire1 = new Wire(tempXor1, tempXor2);
		Wire tempWire2 = new Wire(tempAnd1, tempOr);
		Wire tempWire3 = new Wire(tempAnd2, tempOr);
		//set input wires input and output


		
		tempXor1.setInput(1, in1);
		tempXor1.setInput(2, in2);
		tempXor1.setOutput(1,tempWire1);
		tempAnd1.setInput(1, in2);
		tempAnd1.setInput(2, in1);
		tempAnd1.setOutput(1,tempWire2);
		tempAnd2.setInput(1, carryin);
		tempAnd2.setInput(2, tempWire1);
		tempAnd2.setOutput(1,tempWire3);
		tempXor2.setInput(1, tempWire1);
		tempXor2.setInput(2, carryin);
		tempXor2.setOutput(1, sum1);
		tempOr.setInput(1, tempWire2);
		tempOr.setInput(2, tempWire3);
		tempOr.setOutput(1, carryout);
		

		tempXor1.logic();
		tempAnd1.logic();
		tempAnd2.logic();
		tempXor2.logic();
		tempOr.logic();
		
	}
}