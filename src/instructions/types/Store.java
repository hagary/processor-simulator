package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Store extends Instruction {
	public Store (){
		this.setOP(Op.STORE);
	}
	public short execute(InstructionParameters IP){
		short regB = IP.getRegB();
		short imm = IP.getImm();
		short address = (short) (regB + imm);
		return address;
	}

}
