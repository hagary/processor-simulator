
public class Line {
	private Word[] lineData; //size of l
	
	public Line(int l){
		lineData = new Word[l]; //l is in words
	}
	public Word getWord(int wordAddress){
		int wordIndex  = wordAddress % lineData.length;
		return lineData[wordIndex];
	}
	
	//TODO deep copy
	public void modifyLine(int wordAddress, Word word){
		int wordIndex = wordAddress % lineData.length;
		lineData[wordIndex] = word;
	}
	
	public int getLineSize(){
		return this.lineData.length;
	}
}
