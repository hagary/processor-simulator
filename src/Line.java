
public class Line {
	private Word[] lineData; //size of l
	public Line(int l){
		lineData = new Word[l]; //l is in words
	}
	public Word getWord(int wordAdress){
		int wordIndex  = wordAdress % lineData.length;
		return lineData[wordIndex];
	}
}
