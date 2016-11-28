package instructions.types;

import tomasulo.InstructionParameters;
import tomasulo.Op;
import simulator.Simulator;
import instructions.Instruction;

public class Ret extends Instruction {
	public Ret (){
		this.setOP(Op.RET);
	}
	public short execute(InstructionParameters params){
		if(params == null){
			short regA = Simulator.getRegisterFile().readReg(this.getRegA());
			return regA;
		}
        return -1;
    }

}
