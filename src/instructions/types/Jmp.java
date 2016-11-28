package instructions.types;

import instructions.Instruction;
import tomasulo.InstructionParameters;
import tomasulo.Op;
import simulator.Simulator; 

public class Jmp extends Instruction{
	public Jmp (){
		this.setOP(Op.JMP);
	}
	public short execute(InstructionParameters IP){
		if(IP == null){
			short regA = Simulator.getRegisterFile().readReg(this.getRegA());
			short PC = Simulator.getPC().getData();
			int imm = this.getImm();
			short address  = (short)(regA + PC + imm + 1);
			return address;	
		}
		return -1;
	}	
}
