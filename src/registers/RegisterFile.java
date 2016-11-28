package registers;

import simulator.SimulatorException;

public class RegisterFile {
	private Register [] registers;
	
	public RegisterFile(){
		registers = new Register[8];
		
		for(int i = 0; i < registers.length; i++){
			registers[i] = new Register();
		}
	}

	public Register[] getRegisters() {
		return registers;
	}

	public void setRegisters(Register[] registers) {
		this.registers = registers;
	}
	
	public short readReg(int regNum){
		return registers[regNum].getData();
	}
	
	public void writeReg(int regNum, short data) throws SimulatorException{
		if(regNum != 0)
			registers[regNum].setData(data);
		else
			throw new SimulatorException("Can't write on R0");
	}
}
