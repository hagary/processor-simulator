package tomasulo;

import instructions.Instruction;

public class InsQueue {
	private Instruction [] instructionBuffer;
	
	public InsQueue(int size){
		this.setInstructionBuffer(new  Instruction [size]);
	}

	public Instruction [] getInstructionBuffer() {
		return instructionBuffer;
	}

	public void setInstructionBuffer(Instruction [] instructionBuffer) {
		this.instructionBuffer = instructionBuffer;
	}
	
	public void insert(Instruction i){
		for(int j=0;j<this.getInstructionBuffer().length;j++){
			if(this.getInstructionBuffer()[j]==null)
				this.getInstructionBuffer()[j]=i;
		}
	}
}