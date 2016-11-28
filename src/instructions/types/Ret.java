package instructions.types;

import tomasulo.InstructionParameters;
import simulator.Simulator;
import instructions.Instruction;

public class Ret extends Instruction {
	
	public short execute(InstructionParameters params){
		if(params == null){
			short regA = Simulator.getRegisterFile().readReg(this.getRegA());
			return regA;
		}
        return -1;
    }

}
