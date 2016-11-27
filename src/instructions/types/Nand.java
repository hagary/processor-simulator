package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;

public class Nand extends Instruction{
	
	public short execute(InstructionParameters IP){
		short regB = IP.getRegB();
		short regC = IP.getRegC();
		return (short)~(regB & regC);
	}
}
