package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;

public class Jmp extends Instruction{
	
	public short execute(InstructionParameters IP){
		short regA = IP.getRegA();
		short PC = IP.getPc();
		short imm = IP.getImm();
		short address  = (short)(regA + PC + imm + 1);
		return address;
	}	
}
