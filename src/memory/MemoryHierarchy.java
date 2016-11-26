package memory;
import java.awt.Window.Type;
import java.util.*;
public class MemoryHierarchy {
	private LinkedList<Cache> cacheLevels ;
	private int lineSize;
	static private Memory mainMem;
	
	public MemoryHierarchy(int lineSize){
		this.cacheLevels= new LinkedList<Cache>();
		this.lineSize = lineSize;
	}

	public int getLineSize() {
		return lineSize;
	}


	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public Word readWord(int wordAddress){
		return cacheLevels.getFirst().readWord(wordAddress);
	}

	public void writeWord(int wordAddress, Word wordToWrite){
		cacheLevels.getFirst().writeWord(wordAddress, wordToWrite);
		return ;
	}

	public LinkedList<Cache> getCacheLevels() {
		return cacheLevels;
	}

	public void setCacheLevels(LinkedList<Cache> cacheLevels) {
		this.cacheLevels = cacheLevels;
	}
	public void addCacheLevel(Cache c){
		Cache lastAdded = cacheLevels.getLast();
		if(lastAdded!=null){
			lastAdded.setNextLevel(c);
			c.setPrevLevel(lastAdded);
		}
		cacheLevels.add(c);
	}
	public int lineAddress(int wordAddress){
		return wordAddress/lineSize;
	}


	public static Memory getMainMem() {
		return mainMem;
	}


	public static void setMainMem(Memory mainMem) {
		MemoryHierarchy.mainMem = mainMem;
	}

}
