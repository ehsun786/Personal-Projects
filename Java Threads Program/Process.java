public class Process implements Runnable {
	
	MemManager m;
	char id;
	int size;
	int runtime;
	int address = -1;
	
	public Process(MemManager m, char i, int s, int r) {
		this.m = m;
		this.id = i;
		this.size = s;
		this.runtime = r;
	}
	
	public int getAddress() {
		return address;
	}
	
	public char getID() {
		return id;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setAddress(int a) {
		this.address = a;
	}
	
	public String toString() {
		if(address == -1) {
			return id+":"+"U"+"+"+size;
		} else {
			return id+":"+address+"+"+size;
		}
	}
	
	@Override
	public void run() {
		System.out.println(this + " waiting to run");
		m.allocate(this);
		System.out.println(this + " running");
		try {
			Thread.sleep(100*runtime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.free(this);
		System.out.println(this + " has finished");
		//notifyAll();
	}
}
