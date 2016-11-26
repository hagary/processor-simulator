package memory;


import org.apache.commons.lang3.SerializationUtils;


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

	public Cache(int s,int l,int m,WriteHitPolicy writeHitPolicy,WriteMissPolicy writeMissPolicy, int hitCycles){
		this.s=s;
		this.l=l;
		this.m=m;
		this.writeHitPolicy=writeHitPolicy;
		this.writeMissPolicy=writeMissPolicy;
		this.hitCycles = hitCycles;
	}
	public Word readWord(int wordAddress){
		int lineAddress = wordAddress / l;
		Line line = readLine(lineAddress);
		Word w = line.getWord(wordAddress);
		return w;
	}
	public Line readLine(int lineAddress){
		CacheEntry ce = this.findInCache(lineAddress); //TODO returns hit cycles
		if(ce == null) //miss
		{
			Line targetLine;
			//Is this the last level?
			if(this.getNextLevel() == null)
			{
				//read it from main memory
				//TODO increment access cycles with that returned from readInMemory
				targetLine = MemoryHierarchy.getMainMem().readInMemory(lineAddress);
			}
			else
			{
				//TODO increment access cycles with that returned from readLine
				targetLine = this.getNextLevel().readLine(lineAddress);
			}
			//create a deep copy
			targetLine = SerializationUtils.clone(targetLine);
			//insert it in correct position in current cache
			this.putInCache(lineAddress, targetLine);
			//TODO increment access cycles with that returned from readLine
			return this.readLine(lineAddress); //it should now become a hit
		}
		else //hit
		{
			//return a deep copy
			//TODO return total cycles calculated ^^
			return SerializationUtils.clone(ce.getLine());
		}
	}

	public void writeWord(int wordAddress, Word wordToWrite){
		int lineAddress = wordAddress/l;
		CacheEntry cacheEntry = this.findInCache(lineAddress);
		if( cacheEntry == null )
			this.writeMissHandler(wordAddress, wordToWrite);
		else
			this.writeHitHandler(wordAddress, wordToWrite,cacheEntry);
		return ;
	}
	private void writeMissHandler(int wordAddress, Word wordToWrite){
		if(writeMissPolicy == WriteMissPolicy.WRITEAROUND){
			this.nextLevel.writeWord(wordAddress, wordToWrite);
			return;
		}
		if(writeMissPolicy == WriteMissPolicy.WRITEALLOCATE){
			//get equivalent line address
			int lineAddress = wordAddress / l;
			//fetch it from next level
			Line line = this.nextLevel.readLine(lineAddress);
			//insert it in current level
			this.putInCache(lineAddress, line);
			//treat it as write hit
			this.writeWord(wordAddress, wordToWrite);
		}
	}
	private void writeHitHandler(int wordAddress, Word wordToWrite, CacheEntry targetCE){
		// 1. modify line data in current cache level
		targetCE.getLine().modifyLine(wordAddress, wordToWrite);
		// 2. check write policy
		if(writeHitPolicy == WriteHitPolicy.WRITEBACK){
			//set dirty bit
			targetCE.setDirty(true);
			return;
		}
		if(writeHitPolicy == WriteHitPolicy.WRITETHROUGH){
			this.nextLevel.writeWord(wordAddress, wordToWrite);
			return;
		}

	}

	public CacheEntry findInCache(int lineAddress){

		//divide address into tag & index dependent on m
		int setIndex = lineAddress % numSets;
		int tag = lineAddress / numSets;

		Set targetSet = sets[setIndex];
		CacheEntry[] setEntries = targetSet.getEntries();

		for( int i = 0; i < setEntries.length; i++)
		{
			CacheEntry c = setEntries[i];
			int cacheTag = c.getTag();
			if(cacheTag == tag){ //hit
				return c;
			}
		}
		return null; //miss
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
				return;
			}
		}
		// Replace 
		int replacmentIndex = 0; 	
		CacheEntry repEntry = setEntries[replacmentIndex];
		//Get the line address of the cache entry to be removed
		int remAddress = repEntry.getTag()*numSets + index;
		removeFromCache(remAddress);
		setEntries[replacmentIndex] = newCacheEntry;
		return;
	}
	public void removeFromCache(int lineAddress){
		// Remove from upper cache levels
		if (this.getPrevLevel()!=null)
			this.getPrevLevel().removeFromCache(lineAddress);
		
		int index = lineAddress%numSets;
		int tag = lineAddress/numSets;
		Set targetSet = sets[index];
		CacheEntry[] setEntries = targetSet.getEntries();
		for (int i=0;i<targetSet.getM();i++)
		{
			CacheEntry current = setEntries[i];
			if (current.getTag()==tag)
			{
				Line newLine = SerializationUtils.clone(current.getLine());
				//If the policy is write back then we have to propagate to lower levels
				if (current.isDirty() && this.getWriteHitPolicy()==WriteHitPolicy.WRITEBACK)
				{
					if (this.getNextLevel()==null)
						MemoryHierarchy.getMainMem().putInMemory(lineAddress, newLine);
					else 		
						this.getNextLevel().writeLine(lineAddress, current.getLine());
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
