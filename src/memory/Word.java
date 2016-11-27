package memory;
import java.io.Serializable;


public class Word implements Serializable{
	private String data;
	
	public Word(){
		this.data = null;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
