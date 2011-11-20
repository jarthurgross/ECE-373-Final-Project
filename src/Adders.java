
public class Adders {

	public Wire in1, in2, in3, in4, in5, in6, in7, in8, carryin;
	public Wire sum1, sum2, sum3, sum4, carryout;
	
	public String name;
	
	public String getName()
	{
		return name;
	}
	

	public Wire getInput(int number)
	{

		if(number == 1)
			return in1;
		else if(number==2) 
			return in2;
		else if(number==3) 
			return in3;
		else if(number==4) 
			return in4;
		else if(number==5) 
			return in5;
		else if(number==6) 
			return in6;
		else if(number==7) 
			return in7;
		else if(number==8) 
			return in8;
		else 
			return carryin;
	}
	
	public void setInput(int number, Wire newWire)
	{

		if (number == 1)
			in1 = newWire;
		else if (number == 2)
			in2 = newWire;
		else if (number == 3)
			in3 = newWire;
		else if (number == 4)
			in4 = newWire;
		else if (number == 5)
			in5 = newWire;
		else if (number == 6)
			in6 = newWire;
		else if (number == 7)
			in7 = newWire;
		else if (number == 8)
			in8 = newWire;
		else 
			carryin = newWire;
	}
	

	public Wire getOutput(int number){

		if (number == 1)
			return sum1;
		else if (number == 2)
			return sum2;
		else if (number == 3)
			return sum3;
		else if (number == 4)
			return sum4;
		else
			return  carryout;
	}
	
	public void setOutput(int number, Wire newWire){

		if (number == 1)
			sum1 = newWire;
		else if (number == 2)
			sum2 = newWire;
		else if (number == 3)
			sum3 = newWire;
		else if (number == 4)
			sum4 = newWire;
		else
			carryout = newWire;
	}
}