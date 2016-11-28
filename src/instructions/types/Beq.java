package instructions.types;

import instructions.Instruction;
import simulator.Simulator;
import tomasulo.InstructionParameters;
import tomasulo.Op;

public class Beq extends Instruction{
	
	public Beq (){
		this.setOP(Op.BEQ);
	}
	public short execute(InstructionParameters params){
		if(params == null){
			short regA = Simulator.getRegisterFile().readReg(this.getRegA());
			short regB = Simulator.getRegisterFile().readReg(this.getRegB());
			short PC = Simulator.getPC().getData();
			if(regA == regB){
				short imm = Simulator.getRegisterFile().readReg(this.getImm());
				short address = (short) (PC + imm + 1);
				return address;
			}
			else{
				return (short) (PC + 1);
			}
		}
		else{
			if(params.getRegA() == params.getRegB()){
				this.setEquality(true);
				return (short)(params.getPc() + 1 + params.getImm());	
			}
			params.setEquality(false);
			return (short)(params.getPc() + 1);
		}
	}
		
}
