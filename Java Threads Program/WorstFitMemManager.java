public class WorstFitMemManager extends MemManager {

	public WorstFitMemManager(int s) {
		super(s);
	}

	@Override
	protected int findSpace(int s) {
		int x = 0;
		int largest = 0;
		int index = 0;
		for(int i=0;i < _memory.length; i++) {
			if(_memory[i]=='.') {
				x++;
			} else {
				x=0;
			}
			if(x > largest) {
				largest = x;
				index = i-x+1;
			}
		} 
		return index;
	}

}
