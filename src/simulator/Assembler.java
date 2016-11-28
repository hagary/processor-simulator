package simulator;

import instructions.types.*;
import instructions.Instruction;
import simulator.Simulator;



public class Assembler {
	
	public static Instruction assemblyToInstruction(String assemblyInstruction){

		String[] insOps = assemblyInstruction.split(" ");
		Instruction instruction = new Instruction();
		if(insOps[0].equals("LW")){
			instruction = new Load();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setImm(Integer.parseInt(insOps[3]));
		}
		else if(insOps[0].equals("SW")){
			instruction = new Store();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setImm(Integer.parseInt(insOps[3]));
		}
		else if(insOps[0].equals("JMP")){
			instruction = new Jmp();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setImm(Integer.parseInt(insOps[2]));
		}
		else if(insOps[0].equals("BEQ")){
			instruction = new Beq();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setImm(Integer.parseInt(insOps[3]));
			instruction.setPc(Simulator.getPC().getData());
		}
		else if(insOps[0].equals("JALR")){
			instruction = new Jalr();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
		}
		else if(insOps[0].equals("RET")){
			instruction = new Ret();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
		}
		else if(insOps[0].equals("ADD")){
			instruction = new Add();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setRegC(Integer.parseInt(insOps[3].substring(1)));
		}
		else if(insOps[0].equals("SUB")){
			instruction = new Sub();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setRegC(Integer.parseInt(insOps[3].substring(1)));
		}
		else if(insOps[0].equals("ADDI")){
			instruction = new Addi();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setImm(Integer.parseInt(insOps[3]));
		}
		else if(insOps[0].equals("NAND")){
			instruction = new Nand();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setRegC(Integer.parseInt(insOps[3].substring(1)));
		}
		else if(insOps[0].equals("MUL")){
			instruction = new Mul();
			instruction.setRegA(Integer.parseInt(insOps[1].substring(1)));
			instruction.setRegB(Integer.parseInt(insOps[2].substring(1)));
			instruction.setRegC(Integer.parseInt(insOps[3].substring(1)));
		}
		return instruction;
				
	}
	
	public static void main(String[] args){
		String load = "LW r0 r1 20";
		String store = "SW r2 r3 10";
		String jump = "JMP r4 15";
		String beq = "BEQ r5 r6 71";
		String jalr = "JALR r7 r0";
		String ret = "RET r1";
		String add = "ADD r2 r3 r4";
		String sub = "SUB r5 r6 r7";
		String addi = "ADDI r0 r1 34";
		String nand = "NAND r2 r3 r4";
		String mul = "MUL r5 r6 r7";
		
		System.out.println(assemblyToInstruction(load).print());
		System.out.println(assemblyToInstruction(store).print());
		System.out.println(assemblyToInstruction(jump).print());
		System.out.println(assemblyToInstruction(beq).print());
		System.out.println(assemblyToInstruction(jalr).print());
		System.out.println(assemblyToInstruction(ret).print());
		System.out.println(assemblyToInstruction(add).print());
		System.out.println(assemblyToInstruction(sub).print());
		System.out.println(assemblyToInstruction(addi).print());
		System.out.println(assemblyToInstruction(nand).print());
		System.out.println(assemblyToInstruction(mul).print());
	}

}
