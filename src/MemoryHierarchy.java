import java.awt.Window.Type;
import java.util.*;
public class MemoryHierarchy {
	private LinkedList<Cache> cacheLevels ;
	 
	public MemoryHierarchy(LinkedList<Cache> cacheLevels){
		this.cacheLevels=cacheLevels;
	}
	
	
	public Word readWord(int wordAddress){
		LinkedList<Cache> tmp_cacheLevels = cacheLevels;
		Cache firstCache = tmp_cacheLevels.getFirst();
		Cache pointer = tmp_cacheLevels.removeFirst();
		if(!pointer.equals(null)){
			for(int i = 0; i < cacheLevels.size(); i++){
				Word x = pointer.readWord(wordAddress);
				if(!x.equals(null)){
					firstCache.putInCache(wordAddress, x.getBits());
					return x;
				}
			}
		}
		//In case we let class Memory
		//Search in the memory
		return null;   //should call cacheLevels.getFirst().readWord()
	}
	
	public void writeWord(int wordAddress, boolean[] wordData){
		return ;   //should call cacheLevels.getFirst().writeWord()
	}
	
	public LinkedList<Cache> getCacheLevels() {
		return cacheLevels;
	}

	public void setCacheLevels(LinkedList<Cache> cacheLevels) {
		this.cacheLevels = cacheLevels;
	}
	
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
		LinkedList<Cache> cacheLevels=new LinkedList<Cache>();
		
		System.out.println("Please Enter the number of caches you need");
		int x=sc.nextInt();
		System.out.println("Enter the line length of the caches ");
		int l=sc.nextInt();
		for(int i=0;i<x;i++){
			int j=i+1;
			System.out.println("Enter the size of cache " + j);
			s=sc.nextInt();
			while(s<l || s%l!=0){
				System.out.println("Enter another size of cache " + j);
				s=sc.nextInt();
			}
			System.out.println("Enter the associativity of cache " + j);
			m=sc.nextInt();
			while(s%m!=0){
				System.out.println("Enter another associativity of cache " + j);
				m=sc.nextInt();
			}
			System.out.println("Enter the Writehit policy of cache Writeback/Writethrough" + j);
			writeHit=sc.next();
			writeHit.replaceAll("\\s+", "");
			wH= WriteHitPolicy.valueOf(writeHit.toUpperCase());
			System.out.println("Enter the Writemiss policy of cache Writeallocate/Writearound" + j);
			writeMiss=sc.next();
			writeMiss.replaceAll("\\s+", "");
			wM=WriteMissPolicy.valueOf(writeMiss.toUpperCase());
			Cache c=new Cache(s,l,m,wH,wM);
			cacheLevels.add(c);
			
			
		}
		MemoryHierarchy Memory=new MemoryHierarchy(cacheLevels);
	
	}
	

}
