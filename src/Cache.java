
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
		return;   
	}
	
	public CacheEntry findInCache(int lineAddress){
		
		//divide address into tag & index dependent on m
		int setIndex = lineAddress % numSets;
		int tag = lineAddress / numSets;
		
		/*if(m == lines) //fully associative --> no index
		{
			tag = lineAddress;
		}
		else{
			tag = lineAddress / numSets;
		}*/
		
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
		return;
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
