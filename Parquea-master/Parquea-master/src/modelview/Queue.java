package modelview;

public class Queue<T> { //Queue class implemented using a Circular array 

	T[] queue;//Queue values constructor
	int size;
	int head;
	int tail;
	int length;

	public Queue(int size) {//Queue initializer
		if (size > 0) {
			this.size = size;

			queue = (T[]) new Object[size]; // creating array of generic type
		} else
			System.out.println("Error: Invalid Size");//Must be bigger than 0
	}

	public void enQueue(T data) {
		if (!isFull()) {
			queue[tail] = data;
			tail = (tail + 1) % size;
			length++;
		} else
			System.out.println("enQueue(" + data + ")-Error: Queue is Full");
	}

	public T deQueue() {

		T data = queue[head];

		if (!isEmpty()) {
			head = (head + 1) % size;
			length--;
		} else {
			System.out.println("deQueue-Error: Queue is Empty");
		}
		return data;

	}

	public T peek() {
		if (!isEmpty()) {

			T data = queue[head];
			return data;
		} else
			return null;
	}

	public int getLength() {//Queue current length
		return length;
	}

	public boolean isEmpty() {
		return getLength() == 0;
	}

	public boolean isFull() {
		return getLength() == size;
	}

	public void print() {
		for (int i = 0; i < length; i++) {
			System.out.print(queue[(head + i) % size] + " ");
		}
		System.out.println();
	}
}
