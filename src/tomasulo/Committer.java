package tomasulo;

import memory.Line;
import memory.Word;
import registers.Register;
import simulator.Simulator;
import instructions.Instruction;
import instructions.state;
import instructions.types.Beq;
import instructions.types.Jalr;
import instructions.types.Jmp;
import instructions.types.Ret;
import instructions.types.Store;

public class Committer {
	
	public static boolean canCommit(Instruction i){
		if(i.equals(Simulator.getROB().peek().getInstruction()) && Simulator.getROB().peek().isReady()){
			return true;
		}
		else 
			return false;
	}
	
	public static void commit(Instruction i){
		if(i instanceof Store){
			String value = "" + Simulator.getROB().peek().getValue();
			Simulator.getDataMem().writeWord((short)Simulator.getROB().peek().getDest(), new Word(value));
		}
		
		else if (i instanceof Ret || i instanceof Beq || i instanceof Jalr || i instanceof Jmp){
			Register PCTmp = new Register();
			PCTmp.setData((short)Simulator.getROB().peek().getValue());
			Simulator.setPC((Register)(PCTmp));
		}
		
		else {
			Simulator.getRegisterFile().writeReg((short)Simulator.getROB().peek().getDest(), (short)Simulator.getROB().peek().getValue());
		}
		i.setState(state.COMMITED);
		Simulator.getROB().dequeue();
	}

}
