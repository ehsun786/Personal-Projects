public class BestFitMemManager extends MemManager {

	public BestFitMemManager(int s) {
		super(s);
	}

	int findSpace(int s) {
		int x = 0;
		int largest= _memory.length;
		int index = 0;
		int to_return = 0;
		int best = 0;
		for (int i = 0; i < _memory.length; i++) {
			int freeSpace = countFreeSpacesAt(i);
			if (freeSpace >= s) {
				if(freeSpace <= best) {
					largest = freeSpace;
					best = freeSpace;
				}
				index = i;
				i+=freeSpace;
			}
		}
		return index;
	}

}