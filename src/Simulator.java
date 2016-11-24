import java.awt.Window.Type;
import java.util.Scanner;


public class Simulator {
	static MemoryHierarchy dataMem;
	static MemoryHierarchy instructionsMem;
	public static void main (String[]args){
		Scanner sc=new Scanner(System.in);
		int s;
		int m;
		WriteHitPolicy wH;
		WriteMissPolicy wM;
		String writeHit;
		String writeMiss;
		Type value;
		Type value2;	
		int hitCycles;
		System.out.println("Please Enter the number of caches you need");
		int x=sc.nextInt();
		System.out.println("Enter the line length of the caches in words.");
		int l=sc.nextInt();
		memory=new MemoryHierarchy();
		memory.setLineSize(l);
		MemoryHierarchy.setMainMem(new Memory(l));
		for(int i=0;i<x;i++){
			int j=i+1;
			System.out.println("Enter the size of cache in words." + j);
			s=sc.nextInt();
			while(s<l || s%l!=0){
				System.out.println("Enter the size of cache again!(multiple of line size)" + j);
				s=sc.nextInt();
			}
			System.out.println("Enter the associativity of cache " + j);
			m=sc.nextInt();
			while(s%m!=0){
				System.out.println("Enter associativity of cache again! (divides cache size)" + j);
				m=sc.nextInt();
			}
			System.out.println("Enter the write hit policy of cache Writeback/Writethrough" + j);
			writeHit=sc.next();
			writeHit.replaceAll("\\s+", "");
			wH= WriteHitPolicy.valueOf(writeHit.toUpperCase());
			System.out.println("Enter the write miss policy of cache Writeallocate/Writearound" + j);
			writeMiss=sc.next();
			writeMiss.replaceAll("\\s+", "");
			wM=WriteMissPolicy.valueOf(writeMiss.toUpperCase());
			System.out.println("Enter the number of hit cycles of cache" + j);
			hitCycles=sc.nextInt();
			Cache c=new Cache(s,l,m,wH,wM);
			memory.addCacheLevel(c);
			
		}
		
	
	}
}
