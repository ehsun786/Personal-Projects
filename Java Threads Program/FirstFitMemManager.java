public class FirstFitMemManager extends MemManager {
	
	public FirstFitMemManager(int s) {
		super(s);
	}
	
	protected int findSpace(int s) {
		int x = 0;
		int to_return = 0;
		for(int i=0;i < _memory.length; i++) {
			if(_memory[i]=='.') {
				x++;
			} else {
				x=0;
			}
			if(x==s) {
				to_return = i-x+1; 
			}
		} 
		return to_return;
	}
	
}
