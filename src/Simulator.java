
import java.awt.Window.Type;
import java.util.Scanner;

import memory.Cache;
import memory.Memory;
import memory.MemoryHierarchy;
import memory.WriteHitPolicy;
import memory.WriteMissPolicy;


public class Simulator {
	static MemoryHierarchy dataMem;
	static MemoryHierarchy instructionsMem;
	public static void main (String[]args){
		/* --------REQUIRED INPUT -----------*/
		int memCycles; //main memory hit cycles
		int numCaches;
		int lineSize;
		int s;
		int m;
		WriteHitPolicy wH;
		WriteMissPolicy wM;
		String writeHit;
		String writeMiss;
		int hitCycles;
		/*--------------END------------------*/
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Please enter the number of cycles needed to access the main memory");
		memCycles=sc.nextInt();
		
		System.out.println("Please enter the number of caches you need");
		numCaches=sc.nextInt();
		
		System.out.println("Enter the line length of the caches in words.");
		lineSize=sc.nextInt();
		
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
			s=sc.nextInt();
			//validate input
			while(s<lineSize || s%lineSize!=0){
				System.out.println("Enter the size of cache again!(multiple of line size)" + j);
				s=sc.nextInt();
			}
			
			/* ----------- Input : Associativity -------------*/
			System.out.println("Enter the associativity of cache " + j);
			m=sc.nextInt();
			//validate input
			while(s%m!=0){
				System.out.println("Enter associativity of cache again! (divides cache size)" + j);
				m=sc.nextInt();
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
}
