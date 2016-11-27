package registers;

public class Register {
	private short data;
	
	public Register(){
		data = 0;
	}

	public short getData() {
		return data;
	}

	public void setData(short data) {
		this.data = data;
	}

}
