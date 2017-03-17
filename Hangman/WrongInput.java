public class WrongInput extends Exception {


	public WrongInput() {
		super();
	}
	
	public WrongInput(String message) {
		super(message);
	}
	
	public WrongInput(String message, Throwable cause) {
		super(message, cause);
	}
	
	public WrongInput(Throwable cause) {
		super(cause);
	}
	
	public static void checkString(String x) throws WrongInput {
		if(!(x.substring(4).equals("file")) || (x.charAt(4)!='1') || (x.charAt(4)!='2') || (x.charAt(4)!='3') || (x.charAt(4)!='4')) {
			throw new WrongInput("You have entered the wrong input. Please try again.");
		}
	}

}
