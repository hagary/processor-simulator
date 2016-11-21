
public class Cache{
	private WriteHitPolicy writeHitPolicy;  //write hit policy enum
	private WriteMissPolicy writeMissPolicy;  //write miss policy enum
	private int s; //words
	private int numSets;  //lines/m
	private int l; // line size in words 
	private int lines; //#blocks = s/l number of lines
	private int m; //associativity
	private int hitCycles;
	private Set[] sets; //size=sets
	private Cache nextLevel;
	private Cache prevLevel;

	public Cache getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(Cache nextLevel) {
		this.nextLevel = nextLevel;
	}

	public Cache(int s,int l,int m,WriteHitPolicy writeHitPolicy,WriteMissPolicy writeMissPolicy){
		this.s=s;
		this.l=l;
		this.m=m;
		this.writeHitPolicy=writeHitPolicy;
		this.writeMissPolicy=writeMissPolicy;
	}

	public Line readLine(int lineAddress){
		CacheEntry ce = this.findInCache(lineAddress);
		if(ce == null) //miss
		{	
			Line targetLine;
			//Is this the last level?
			if(this.getNextLevel() == null)
			{
				//read it from main memory
				//TODO return a copy of line instead of reference
				targetLine = MemoryHierarchy.getMainMem().readInMemory(lineAddress);				
			}
			else
			{
				targetLine = this.getNextLevel().readLine(lineAddress);
			}
			//insert it in correct position in current cache
			this.putInCache(lineAddress, targetLine);
			return targetLine;
		}
		else //hit
		{
			return ce.getLine();
		}
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

	public void putInCache(int address, Line line){
		int index = address%numSets;
		int tag = address/numSets;
		
		// Initializing the new cache entry to be inserted
		CacheEntry newCacheEntry = new CacheEntry(false,tag,line);
		
		Set targetSet = sets[index];
		CacheEntry[] setEntries = targetSet.getEntries();
		
		for (int i=0;i<targetSet.getM();i++)
		{
			if (setEntries[i]==null) // Found Empty slot, No Replacement needed
			{
				setEntries[i] = newCacheEntry;
				this.getPrevLevel().putInCache(address, line);
				return;
			}
		}
		// Replace 
		int replacmentIndex = (int) Math.random() * targetSet.getM(); 	
		CacheEntry repEntry = setEntries[replacmentIndex];
		//Get the line address of the cache entry to be removed
		int remAdrress = repEntry.getTag()*numSets + index;
		removeFromCache(remAdrress);
		setEntries[replacmentIndex] = newCacheEntry;
		this.getPrevLevel().putInCache(address, line);
		return;
	}
	public void removeFromCache(int address){
		// Remove from upper cache levels
		this.getPrevLevel().removeFromCache(address);
		
		int index = address%numSets;
		int tag = address/numSets;
		Set targetSet = sets[index];
		CacheEntry[] setEntries = targetSet.getEntries();
		for (int i=0;i<targetSet.getM();i++)
		{
			CacheEntry current = setEntries[i];
			if (current.getTag()==tag)
			{
				//If the policy is write back then we have to propagate to lower levels
				if (current.isDirty() && this.getWriteHitPolicy()==WriteHitPolicy.WRITEBACK)
				{
					// TODO 
					this.getNextLevel().writeLine(address, null);
				}
				//nullify the cacheEntry to be removed
				setEntries[i] = null;
				break;
			}
		}
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

	public Cache getPrevLevel() {
		return prevLevel;
	}

	public void setPrevLevel(Cache prevLevel) {
		this.prevLevel = prevLevel;
	}

	public int lineAddress(int wordAddress){
		return wordAddress/l;
	}
}
