/**
 * Runtime exception thrown when one tries to perform operation top or pop on an
 * empty stack. //end#fragment EmptyStackException
 */

@SuppressWarnings("serial")
public class EmptyStackException extends RuntimeException {
	
	// create a constructor for the EmptyStackException
	public EmptyStackException (String err) {
		
		super(err);// call the super and send in the error message
	}
}	
