/**
 * Implementation of the stack ADT using a fixed-length array. An exception is
 * thrown if a push operation is attempted when the size of the stack is equal
 * to the length of the array. This class includes the main methods of the
 * built-in class java.util.Stack.
 * 
 */

public class ArrayStack<E> implements Stack<E> {
	/**
	 * Length of the array used to implement the stack.
	 */
	protected int capacity; // The actual capacity of the stack array

	/**
	 * Default array capacity.
	 */

	public static final int CAPACITY = 1000; // default array capacity
	/**
	 * Array used to implement the stack.
	 */
	protected E S[]; // Generic array used to implement the stack
	/**
	 * Index of the top element of the stack in the array.
	 */
	protected int top = -1; // index for the top of the stack

	/**
	 * Initializes the stack to use an array of default length.
	 */
	public ArrayStack() {
		this(CAPACITY); // default capacity
	}

	/**
	 * Initializes the stack to use an array of given length.
	 * 
	 * @param cap
	 *            length of the array.
	 */
	public ArrayStack(int cap) {
		capacity = cap;
		S = (E[]) new Object[capacity]; // compiler may give warning, but this
										// is ok
	}

	// end#fragment ArrayStack
	/**
	 * Returns the number of elements in the stack. This method runs in O(1)
	 * time.
	 * 
	 * @return number of elements in the stack.
	 */
	public int size() {
		return (top +1); // implement the size
	}

	/**
	 * Testes whether the stack is empty. This method runs in O(1) time.
	 * 
	 * @return true if the stack is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return (top < 0); // implement isEmpty
	}

	/**
	 * Inserts an element at the top of the stack. This method runs in O(1)
	 * time.
	 * 
	 * @return element inserted.
	 * @param element
	 *            element to be inserted.
	 * @exception FullStackException
	 *                if the array storing the elements is full.
	 */
	public void push(E element) throws FullStackException {
		// implement the push
		if (size() == capacity)
			throw new FullStackException("Stack is full.");
		S[++top] = element;
	}

	/**
	 * Inspects the element at the top of the stack. This method runs in O(1)
	 * time.
	 * 
	 * @return top element in the stack.
	 * @exception EmptyStackException
	 *                if the stack is empty.
	 */
	public E top() throws EmptyStackException {
		// implement the top
		if (isEmpty())
			throw new EmptyStackException("Stack is empty.");
		return S[top];
	}

	/**
	 * Removes the top element from the stack. This method runs in O(1) time.
	 * 
	 * @return element removed.
	 * @exception EmptyStackException
	 *                if the stack is empty.
	 */
	// begin#fragment ArrayStack
	public E pop() throws EmptyStackException {
		// implement the pop
		E element;
		if (isEmpty())
			throw new EmptyStackException("Stack is empty.");
		element = S[top];
		S[top--] = null;
		return element;
	}

}

