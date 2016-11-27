package tomasulo;

import simulator.Simulator;
import instructions.Instruction;

public class Committer {
	
	public boolean canCommit(Instruction i){
		if(i.equals(ROB.getROBEntries().peek()) && ROB.getROBEntries().peek().getReady()){
			return true;
		}
		else 
			return false;
	}
	
	public void commit(Instruction i){
		//update the register file or the memory
		if(i.Op == Op.STORE){
			Simulator.getDataMem().putInMemory(ROB.getROBEntries().peek().getDestination(), ROB.getROBEntries().peek().getValue());
		}
		//else if branch, JMP or RET update the PC
		
		else {
			Simulator.getRegsiterFile().getRegisters() [ROB.getROBEntries().peek().getDestination()] = ROB.getROBEntries().peek().getValue();
		}
	}

}
