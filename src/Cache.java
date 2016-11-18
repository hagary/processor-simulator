import java.lang.*;
public class Cache {
	private WriteHitPolicy writeHitPolicy;  //write hit policy enum
	private WriteMissPolicy writeMissPolicy;  //write miss policy enum
	private int s; //bytes
	private int numSets;  //lines/m
	private int l; //bytes
	private int lines; //#blocks = s/l
	private int m; //associativity
	private int hitCycles;
	private Set[] sets; //size=sets
	
	public Word readWord(int wordAddress){
		return null;   
	}
	
	public Word readLine(int lineAddress){
		return null;   
	}
	
	public void writeWord(int wordAddress, boolean[] wordData){
		return ;   
	}
	
	public void writeLine(int wordAddress, boolean[] lineData){
		return ;   
	}
	
	public CacheEntry findInCache(int lineAddress){
		return null;
	}
	
	public void putInCache(int address, boolean[] line){
		// Dividing address to Tag, Index, Offset
		int offset = address%l;
		address /= l;
		int index = address%l;
		int tag = address/l;
		
		// Initializing the new cache entry to be inserted
		CacheEntry newCacheEntry = new CacheEntry();
		newCacheEntry.setDirty(false);
		newCacheEntry.setTag(tag);
		newCacheEntry.setLineData(lineToWords(line));
		
		Set targetSet = sets[index];
		CacheEntry[] setEntries = targetSet.getEntries();
		
		for (int i=0;i<targetSet.getM();i++)
		{
			if (setEntries[i]==null) // Found Empty slot, No Replacement needed
			{
				setEntries[i] = newCacheEntry;
				// put in upper levels can't configure how to do it
				return;
			}
		}
		// Replace 
		int replacment = (int) Math.Random() * targetSet.getM(); 
		if (setEntries[replacment].isDirty() && writeHitPolicy==WriteHitPolicy.WRITEBACK)
		{
			// Write to lower cache level
		}
		setEntries[replacment] = newCacheEntry;
		// put in upper levels can't configure how to do it
		
		return;
	}
	// Helper method to convert stream of bits to array of wordsss
	public Word[] lineToWords(boolean[] line)
	{
		Word[] res = new Word[line.length/16];
		for (int i=0;i<res.length;i++)
		{
			Word w = new Word();
			boolean[] wContent = new boolean[16];
			for (int j=0;j<16;i++)
				wContent[j] = line[i*16+j];
			res[i] = w;
		}
		return res;
	}
	public WriteHitPolicy getWriteHitPolicy() {
		return writeHitPolicy;
	}

	public void setWriteHitPolicy(WriteHitPolicy writeHitPolicy) {
		this.writeHitPolicy = writeHitPolicy;
	}
	
	public WriteMissPolicy getWriteMissPolicy() {
		return writeMissPolicy;
	}

	public void setWriteMissPolicy(WriteMissPolicy writeMissPolicy) {
		this.writeMissPolicy = writeMissPolicy;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getNumSets() {
		return numSets;
	}

	public void setNumSets(int numSets) {
		this.numSets = numSets;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getLines() {
		return lines;
	}

	public void setLines(int lines) {
		this.lines = lines;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getHitCycles() {
		return hitCycles;
	}

	public void setHitCycles(int hitCycles) {
		this.hitCycles = hitCycles;
	}

	public Set[] getSets() {
		return sets;
	}

	public void setSets(Set[] sets) {
		this.sets = sets;
	}


}
