package tomasulo;

import registers.Register;

public class Master {
private static ROB ROB;
private static RSSet RSSet;
private static Register PC;
private static InsQueue instructionBuffer;


public static ROB getROB() {
	return ROB;
}

public static void setROB(ROB rOB) {
	ROB = rOB;
}

public static RSSet getRSSet() {
	return RSSet;
}

public static void setRSSet(RSSet rSSet) {
	RSSet = rSSet;
}

public static Register getPC() {
	return PC;
}

public static void setPC(Register pC) {
	PC = pC;
}

public static InsQueue getInstructionBuffer() {
	return instructionBuffer;
}

public static void setInstructionBuffer(InsQueue instructionBuffer) {
	Master.instructionBuffer = instructionBuffer;
}



}
