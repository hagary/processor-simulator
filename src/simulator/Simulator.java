package simulator;

import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import memory.Cache;
import memory.Line;
import memory.Memory;
import memory.MemoryHierarchy;
import memory.Word;
import memory.WriteHitPolicy;
import memory.WriteMissPolicy;
import registers.RegisterFile;
import tomasulo.ROB;

public class Simulator {
	private static MemoryHierarchy dataMem;
	private static MemoryHierarchy instructionsMem;
	private static ROB ROB;
	private static RegisterFile registerFile;
	private static short startAddress;

	public static void main (String[]args){
		Scanner sc=new Scanner(System.in);
		System.out.println("-----MEMORY INPUT------");
		memInput(sc);
		System.out.println("-----TOMASULO INPUT------");
		tomasuloInput(sc);
		programInput();
		dataInput();
	}
	public static void run(){
		do{
			
		}while(true);
	}
	public static void dataInput(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("program/data-input.txt"));
			String address = "";
			String data = "";
			//Insert the data in the main memory
			while((address = br.readLine()) != null) {
				data = br.readLine();
				Word w = new Word(data);
				short wordAddress = Short.parseShort(address);
				MemoryHierarchy.getMainMem().putInMemory(wordAddress, w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void programInput(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("program/assembly-code.txt"));
			short startAddress = Short.parseShort(br.readLine());
			Simulator.startAddress = startAddress;
			String codeLine = "";
			int i = 0;
			//Insert the program code in the main memory
			while((codeLine = br.readLine()) != null) {
				Word w = new Word(codeLine);
				MemoryHierarchy.getMainMem().putInMemory(startAddress + i, w);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void tomasuloInput(Scanner sc){
		/* --------REQUIRED INPUT -----------*/
		int pipelineWidth;
		int insQueueSize;
		int ROBSize;
		//Reservation Stations Counts
		int beqCount;	int beqCycles;
		int jmpCount;	int jmpCycles;
		int jalrCount;	int jalrCycles;
		int retCount;	int retCycles;
		int loadCount;	int loadCycles;
		int storeCount;	int storeCycles;
		int addCount;	int addCycles;
		int addiCount;	int addiCycles;
		int subCount;	int subCycles;
		int mulCount;	int mulCycles;
		int nandCount;	int nandCycles;
		/*--------------END REQ INPUT ------------------*/

		/*----------------------SCANNER--------------------*/
		System.out.println("Enter the pipeline width:");
		pipelineWidth = sc.nextInt();
		System.out.println("Enter the instruction queue size:");
		insQueueSize = sc.nextInt();
		System.out.println("Enter the number of reservations stations for BEQ:");
		beqCount = sc.nextInt();
		System.out.println("Enter the number of cycles for beq:");
		beqCycles = sc.nextInt();
		System.out.println("Enter the number of reservations stations for JMP:");
		jmpCount = sc.nextInt();
		System.out.println("Enter the number of cycles for jmp:");
		jmpCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for JALR:");
		jalrCount = sc.nextInt();
		System.out.println("Enter the number of cycles for jalr:");
		jalrCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for RET:");
		retCount = sc.nextInt();
		System.out.println("Enter the number of cycles for ret:");
		retCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for LOAD:");
		loadCount = sc.nextInt();
		System.out.println("Enter the number of cycles for load:");
		loadCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for STORE:");
		storeCount = sc.nextInt();
		System.out.println("Enter the number of cycles for store:");
		storeCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for ADD:");
		addCount = sc.nextInt();
		System.out.println("Enter the number of cycles for add:");
		addCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for ADDI:");
		addiCount = sc.nextInt();
		System.out.println("Enter the number of cycles for addi:");
		addiCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for SUB:");
		subCount = sc.nextInt();
		System.out.println("Enter the number of cycles for sub:");
		subCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for MUL:");
		mulCount = sc.nextInt();
		System.out.println("Enter the number of cycles for mul:");
		mulCycles = sc.nextInt();
		System.out.println("Enter the number of reservation stations for NAND:");
		nandCount = sc.nextInt();
		System.out.println("Enter the number of cycles for nand:");
		nandCycles = sc.nextInt();
		/*-------------------END SCANNER-------------------*/
		/*-------------------DO SOMETHING------------------*/
		//TODO configure pipeline width in Issuer
		//TODO configure insQueueSize in InsQueue class
		//TODO configure ROBSize in ROB
		/*TODO for each op type do as follows
		RSSet.createRS(Op.Add, addCount);
		Add.setCycles(addCycles); */
		/*-------------------END DO SOMETHING------------------*/
	}
	public static void memInput(Scanner sc){
		/* --------REQUIRED INPUT -----------*/
		int memCycles; //main memory hit cycles
		int numCaches;
		short lineSize;
		short s;
		short m;
		WriteHitPolicy wH;
		WriteMissPolicy wM;
		String writeHit;
		String writeMiss;
		int hitCycles;
		/*--------------END------------------*/

		System.out.println("Please enter the number of cycles needed to access the main memory");
		memCycles=sc.nextInt();

		System.out.println("Please enter the number of caches you need");
		numCaches=sc.nextInt();

		System.out.println("Enter the line length of the caches in words.");
		lineSize=sc.nextShort();
		Line.setLineSize(lineSize);

		//MAIN MEMORY
		MemoryHierarchy.setMainMem(new Memory(lineSize,memCycles));
		//DATA MEMORY
		dataMem = new MemoryHierarchy(lineSize);
		//INSTRUCTIONS MEMORY
		instructionsMem = new MemoryHierarchy(lineSize);


		for(int i=0;i<numCaches;i++){
			int j=i+1;
			/* ----------- Input : Cache Size -------------*/
			System.out.println("Enter the size of cache in words." + j);
			s=sc.nextShort();
			//validate input
			while(s<lineSize || s%lineSize!=0){
				System.out.println("Enter the size of cache again!(multiple of line size)" + j);
				s=sc.nextShort();
			}

			/* ----------- Input : Associativity -------------*/
			System.out.println("Enter the associativity of cache " + j);
			m=sc.nextShort();
			//validate input
			while(s%m!=0){
				System.out.println("Enter associativity of cache again! (divides cache size)" + j);
				m=sc.nextShort();
			}

			/* ----------- Input : Write Policies -------------*/
			System.out.println("Enter the write hit policy of cache Writeback/Writethrough" + j);
			writeHit=sc.next();
			writeHit.replaceAll("\\s+", "");
			wH= WriteHitPolicy.valueOf(writeHit.toUpperCase());
			System.out.println("Enter the write miss policy of cache Writeallocate/Writearound" + j);
			writeMiss=sc.next();
			writeMiss.replaceAll("\\s+", "");
			wM=WriteMissPolicy.valueOf(writeMiss.toUpperCase());

			/* ----------- Input : Hit Cycles -------------*/
			System.out.println("Enter the number of hit cycles of cache" + j);
			hitCycles=sc.nextInt();

			/* ----------- Initialize Cache Levels -------------*/
			Cache cData = new Cache(s, lineSize, m, wH, wM, hitCycles);
			Cache cInstructions = new Cache(s, lineSize, m, wH, wM, hitCycles);

			dataMem.addCacheLevel(cData);
			instructionsMem.addCacheLevel(cInstructions);

		}

	}
	public static MemoryHierarchy getDataMem() {
		return dataMem;
	}
	public static void setDataMem(MemoryHierarchy dataMem) {
		Simulator.dataMem = dataMem;
	}
	public static MemoryHierarchy getInstructionsMem() {
		return instructionsMem;
	}
	public static void setInstructionsMem(MemoryHierarchy instructionsMem) {
		Simulator.instructionsMem = instructionsMem;
	}
}
