
public class FullCarryAdder extends Adders implements Component {

	public FullCarryAdder(String name){
		this.name = name;
	}
	
	public void logic() {
		
		FullAdder tempFull1 = new FullAdder("tempFull1");
		FullAdder tempFull2 = new FullAdder("tempFull2");
		FullAdder tempFull3 = new FullAdder("tempFull3");
		FullAdder tempFull4 = new FullAdder("tempFull4");
		Wire tempWire1 = new Wire(tempFull1 , tempFull2);
		Wire tempWire2 = new Wire(tempFull2 , tempFull3);
		Wire tempWire3 = new Wire(tempFull3 , tempFull4);
		

		tempFull1.setInput(1, in4);
		tempFull1.setInput(2, in8);
		tempFull1.setInput(9, carryin);
		tempFull1.setOutput(5, tempWire1);
		tempFull1.setOutput(1, sum1);
		tempFull2.setInput(1, in3);
		tempFull2.setInput(2, in7);
		tempFull2.setInput(9, tempWire1);
		tempFull2.setOutput(5, tempWire2);
		tempFull2.setOutput(1, sum2);
		tempFull3.setInput(1, in2);
		tempFull3.setInput(2, in6);
		tempFull3.setInput(9, tempWire2);
		tempFull3.setOutput(5, tempWire3);
		tempFull3.setOutput(1, sum3);
		tempFull4.setInput(1, in1);
		tempFull4.setInput(2, in5);
		tempFull4.setInput(9, tempWire3);
		tempFull4.setOutput(5, carryout);
		tempFull4.setOutput(1, sum4);
		
		
				
		
		tempFull1.logic();
		tempFull2.logic();
		tempFull3.logic();
		tempFull4.logic();
		
		
		
		
	}
}