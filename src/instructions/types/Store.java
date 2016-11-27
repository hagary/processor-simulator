package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;

public class Store extends Instruction {
	
	public short execute(InstructionParameters IP){
		short regB = IP.getRegB();
		short imm = IP.getImm();
		short address = (short) (regB + imm);
		return address;
	}

}
