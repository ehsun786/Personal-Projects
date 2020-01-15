
public class CreditCardTesting {

	public static void main(String[] args) {

		/* Test cases below check for MASTERCARD */

		// Will return 0 because first OR condition will be met
		validate("53963", 0);
		// Will return 0 because second OR condition will be met
		validate("48963", 0);
		// Will return 0 because third OR condition will be met
		validate("98963", 0);
		// Will achieve break point for MASTERCARD
		validate("5396333333333333", 0);

		/* Test cases below check for VISA */

		// Will return 0 because fist OR condition will be met
		validate("53963", 1);
		// Will return 0 because second OR condition will be met
		validate("5396333333333333", 1);
		// Will achieve break point for VISA
		validate("4396333333333333", 1);

		/* Test cases below check for AMEX */

		// Will return 0 because first OR condition will be met
		validate("51253", 2);
		// Will return 0 because second AND condition will be met
		validate("471234567891011", 2);
		// Will achieve break point for AMEX
		validate("371234567891011", 2);

		/* Test cases below check for DISCOVER */

		// Will return 0 because first OR condition will be met
		validate("51335", 3);
		// Will return 0 because second AND condition will be met
		validate("5551335123456789", 3);
		// Will achieve break point for DISCOVER
		validate("6011987654321123", 3);

		/* Test cases below check for DISCOVER */

		// Will return 0 because first OR condition will be met
		validate("548878", 4);
		// Will return 0 because second AND condition
		// will be met (all conditions will be traversed)
		validate("30612345678965", 4);
		// Will achieve break point for DINERS
		validate("30123456789654", 4);

		// N.B. Break points will cause the program to
		// run the luhnValidate(number) method
	}

	public static void validate(String number, int type) {

	}
}
