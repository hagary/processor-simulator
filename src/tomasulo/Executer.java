package tomasulo;

import instructions.Instruction;
import instructions.state;

public class Executer {

	
	public static boolean canExecute(Instruction i){

		if(i.getState()==state.ISSUED&&i.getRS().getQj()==null && i.getRS().getQk()==null)
			return true;
		else return false;
	}
	
	public static void execute(Instruction i){
		int x=i.getExCycles()-1;
		i.setExCycles(x);
		if(i.getExCycles()==0)
			i.setState(state.EXECUTED);
	}
}
