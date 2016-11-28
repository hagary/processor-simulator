package tomasulo;

import java.util.Queue;

import simulator.Simulator;
import instructions.Instruction;
import instructions.state;
import instructions.types.*;
public class Writer {

	public boolean canWrite(Instruction i)
	{
		if (i.getState()==state.EXECUTED)
			return true;
		return false;
	}
	public void write(Instruction i){
		InstructionParameters params = new InstructionParameters();
		RegisterFile rF = Simulator.getRegisterFile();
		if (i instanceof Add)
		{		
			params.setRegB(rF.readReg(i.getRegB()));
			params.setRegC(rF.readReg(i.getRegC()));
		}
		else if (i instanceof Addi)
		{
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Beq)
		{
			params.setRegA(rF.readReg(i.getRegA()));
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Jalr)
		{
			params.setRegA((short) i.getRegA());
			params.setRegB(rF.readReg(i.getRegB()));
		}
		else if (i instanceof Jmp)
		{
			params.setRegA(rF.readReg(i.getRegA()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Load)
		{
			params.setRegA((short) i.getRegA());
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		else if (i instanceof Mul)
		{
			params.setRegB(rF.readReg(i.getRegB()));
			params.setRegC(rF.readReg(i.getRegC()));
		}
		else if (i instanceof Nand)
		{
			params.setRegB(rF.readReg(i.getRegB()));
			params.setRegC(rF.readReg(i.getRegC()));
		}
		else if (i instanceof Ret)
		{
			params.setRegA(rF.readReg(i.getRegA()));
		}
		else if (i instanceof Store)
		{
			params.setRegA(rF.readReg(i.getRegA()));
			params.setRegB(rF.readReg(i.getRegB()));
			params.setImm((short) i.getImm());
		}
		short res = i.execute(params);
		ROB rob = Simulator.getROB();
		Queue<ROBEntry> robQ = rob.getROBTable();
		ROBEntry myEntry;
		for (int j=0;j<robQ.size();j++)
		{
			if (robQ.peek().getInstruction()==i)
				myEntry = robQ.peek();
			robQ.add(robQ.remove());
		}
		myEntry.setReady(true);
		myEntry.setValue(res);
	}
}
