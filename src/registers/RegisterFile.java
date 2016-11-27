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
	
	
}
