import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	static List<String> charityAllocation(List<Float> profits) {

		float donationToA = 0;
		float donationToB = 0;
		float donationToC = 0;

		List<String> charities = new ArrayList<String>();

		if (profits.size() == 0) {
			return Collections.emptyList();
		} else if (profits.size() == 1) {
			charities.add(0, "A");
			return charities;
		} else if (profits.size() == 2) {
			charities.add(0, "A");
			charities.add(1, "B");
			return charities;
		} else if (profits.size() == 3) {
			charities.add(0, "A");
			charities.add(1, "B");
			charities.add(2, "C");
			return charities;
		} else {
			donationToA = profits.get(0);
			donationToB = profits.get(1);
			donationToC = profits.get(2);
			charities.add(0, "A");
			charities.add(1, "B");
			charities.add(2, "C");

			for (int i = 3; i < profits.size(); i++) {

				if (donationToA <= donationToB) {
					if (donationToA <= donationToC) {
						donationToA += profits.get(i);
						charities.add(i, "A");
					} else {
						donationToC += profits.get(i);
						charities.add(i, "C");
					}
				} else {
					if (donationToB <= donationToC) {
						donationToB += profits.get(i);
						charities.add(i, "B");
					} else {
						donationToC += profits.get(i);
						charities.add(i, "C");
					}
				}
			}

		}

		return charities;
	}

	static long playlist(List<Integer> songs) {

		long count = 0;
		int sum=60;

		for (int i = 0; i < songs.size(); i++) {
			for (int j = i + 1; j < songs.size(); j++) {
				if(songs.get(i)+songs.get(j)==sum);
				count++;
			}
		}
		return count;
	}

}
