public class Luhn {
	public static final int MASTERCARD = 0, VISA = 1;
	public static final int AMEX = 2, DISCOVER = 3, DINERS = 4;

	// Check that cards start with proper digits for
	// selected card type and are also the right length.
	public int validate(String number, int type) {
		switch (type) {

		case MASTERCARD:
			if (number.length() != 16 || Integer.parseInt(number.substring(0, 2)) < 51
					|| Integer.parseInt(number.substring(0, 2)) > 55) {

				return 0;
			}
			break;

		case VISA:
			if ((number.length() != 13 && number.length() != 16) || Integer.parseInt(number.substring(0, 1)) != 4) {
				return 0;
			}
			break;

		case AMEX:
			if (number.length() != 15 || (Integer.parseInt(number.substring(0, 2)) != 34
					&& Integer.parseInt(number.substring(0, 2)) != 37)) {
				return 0;
			}
			break;

		case DISCOVER:
			if (number.length() != 16 || Integer.parseInt(number.substring(0, 5)) != 6011) {
				return 0;
			}
			break;

		case DINERS:
			if (number.length() != 14 || ((Integer.parseInt(number.substring(0, 2)) != 36
					&& Integer.parseInt(number.substring(0, 2)) != 38) && Integer.parseInt(number.substring(0, 3)) < 300
					|| Integer.parseInt(number.substring(0, 3)) > 305)) {
				return 0;
			}
			break;
		}
		return luhnValidate(number) ? 1 : 0;
	}

	// The Luhn algorithm is basically a CRC type
	// system for checking the validity of an entry.
	// All major credit cards use numbers that will
	// pass the Luhn check. Also, all of them are based
	// on MOD 10.
	private boolean luhnValidate(String numberString) {
		char[] charArray = numberString.toCharArray();
		int[] number = new int[charArray.length];
		int total = 0;

		for (int i = 0; i < charArray.length; i++) {
			number[i] = Character.getNumericValue(charArray[i]);
		}

		for (int i = number.length - 2; i > -1; i -= 2) {
			number[i] *= 2;

			if (number[i] > 9)
				number[i] -= 9;
		}

		for (int i = 0; i < number.length; i++)
			total += number[i];

		if (total % 10 != 0)
			return false;

		return true;
	}
}