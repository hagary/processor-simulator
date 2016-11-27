package registers;

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
	
	public void writeReg(int regNum, short data){
		registers[regNum].setData(data);
	}
}
