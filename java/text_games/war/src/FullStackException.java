/**
 * Runtime exception thrown when the capacity of the array used by an ArrayStack
 * has been exceeded.
 * 
 * @see ArrayStack
 */
@SuppressWarnings("serial")
public class FullStackException extends RuntimeException {
	
	// create a constructor for the FullStackException
	public FullStackException (String err) {
	
		super(err);// call the super and send in a string
	}	
}
