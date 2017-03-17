public abstract class MemManager {
	
	protected static volatile boolean _changed;
	protected static volatile int _largestSpace;
	protected static char[] _memory;
	
	public MemManager(int s) {
		this._changed = false;
		this._largestSpace = s;
		_memory = new char[s];
		for(int i = 0; i<_memory.length;i++){
			_memory[i] = '.';
		}
	}
	
	public synchronized void allocate(Process p) {
		while(_largestSpace < p.getSize()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Allocate the Memory---
		//-------
		int index = findSpace(p.getSize());
		for(int i = index; i < index+p.getSize(); i++) {
			_memory[i] = p.getID();
		}
		int x = 0;
		int largest = 0;
		for (int i = 0; i < _memory.length; i++) {
			if (_memory[i] == '.') {
				x++;
			} else {
				x = 0;
			}
			if (x > largest) {
				largest = x;
			}
		}
		_largestSpace = largest;
		_changed = true;
		notifyAll();
	}
	
	protected synchronized int countFreeSpacesAt(int pos) {
		int x=0;
		for(int i=pos; i<_memory.length;i++) {
			if(_memory[i]=='.') {
				x++;
			} else {
				return x;
			}
		}
		return x;
	}
	
	abstract int findSpace(int s);
	
	public synchronized void free(Process p) {
		for (int i = 0; i< _memory.length; i++) {
			if(_memory[i]==p.getID()) {
					_memory[i]='.';
			}
		}
		int x = 0;
		int largest = 0;
		for (int i = 0; i < _memory.length; i++) {
			if (_memory[i] == '.') {
				x++;
			} else {
				x = 0;
			}
			if (x > largest) {
				largest = x;
			}
		}
		_largestSpace = largest;
		_changed = true;
		notifyAll();
	}
	
	public synchronized static boolean isChanged() {
		return _changed;
	}
	
	public synchronized String toString() {
		int x = 0;
		int y= -20;
		String to_return = "";
		for(int i=0; i < _memory.length/20 /*largestSpace*/; i++) {
			y+=20;
			if(i==0) {
				to_return+=y+" |";
			} else {
				to_return+=y+"|";
			}
			for(int j=0; j < 20; j++){
				to_return+=_memory[x];
				x++;
			}
			to_return+="|\n";
		}
		to_return+="ls:" + " " + _largestSpace;
		_changed=false;
		return to_return;
	}
	
}
