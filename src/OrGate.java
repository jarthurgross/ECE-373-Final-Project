
public class OrGate extends Component_2in_1out implements Component{

	public OrGate(String name){
		this.name = name;
	}

	public void logic() {
		if(in1.getValue() == 1 || in2.getValue() == 1)
		{
			out.setSignal(1);
		}
		else{
			out.setSignal(0);
		}
		
	}

}
