
public class ArrayQueue<E> implements Queue<E> {

	public static final int CAPACITY = 1000;
	protected E[ ] Q;
	protected int front;
	protected int rear;
	protected int capacity;
	
	public ArrayQueue(int cap) {
		Q = (E[]) new Object[cap];
		front = 0;
		rear = 0;
		capacity = cap;
	}
	
	public ArrayQueue() {
		Q = (E[]) new Object[CAPACITY];
		front = 0;
		rear = 0;
		capacity = CAPACITY;
	}
	
	public int size() {
		return ((capacity - front + rear)%capacity);
	}

	public boolean isEmpty() {
		return (front == rear);
	}

	public E front() {
		if(isEmpty())
			throw new EmptyQueueException("Queue is empty.");
		return Q[front];
	}

	public E dequeue() throws EmptyQueueException {
		E temp=null;
		if (isEmpty())
			throw new EmptyQueueException("Queue is empty.");
		temp = Q[front];
		Q[front] = null;
		front = ((front+1)%capacity);
		
		return temp;
	}
	
	public void enqueue(E element) {
		if(size() == Q.length-1)
			throw new FullQueueException("Queue is full.");
		Q[rear] = element;
		rear = ((rear+1)%capacity);
	}
}
