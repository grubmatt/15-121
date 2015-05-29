package HW6;
/**
 * 
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

import java.util.*;

public class TwoStackQueue<E> implements MyQueue<E> {

	private MyStack<E> out;
	private MyStack<E> in;

	public TwoStackQueue() {
		out = new ArrayStack<E>();
		in = new ArrayStack<E>();
	}

	/**
	 * Returns true if this queue no elements.
	 * 
	 * @return true if this queue is empty, false otherwise.
	 * 
	 */
	public boolean isEmpty() {
		return (out.isEmpty() && in.isEmpty());
	}

	/**
	 * Adds the specified element to the end of the queue.
	 * 
	 * @param The
	 *          element to add on to the end of the queue.
	 *          
	 */
	public void enqueue(E element) {
		in.push(element);
	}

	/**
	 * Removes and returns the element at the front of this queue.
	 * 
	 * @return The element removed from the front of this queue.
	 * @throws NoSuchElementException
	 *           if the queue is empty.
	 *           
	 */
	public E dequeue() {
		if(this.isEmpty())
			throw new NoSuchElementException();
		
		if(out.isEmpty())
			transferData();
		
		return out.pop();
	}

	private void transferData() {
		while(!in.isEmpty())
			out.push(in.pop());
	}

	/**
	 * Returns, but does not remove, the element at the front of this queue.
	 * 
	 * @return The element at the front of this queue.
	 * @throws NoSuchElementException
	 *           if the queue is empty.
	 *           
	 */
	public E peek() {
		if(this.isEmpty())
			throw new NoSuchElementException();
		
		if(out.isEmpty())
			transferData();

		return out.peek();
	}

	/**
	 * Returns a String representation of this queue. If the queue will dequeue
	 * values 5 7 8 in that order, and the out stack contains one value, the
	 * string will have following format.
	 * 
	 * front [ 5 | 7 8 ] back
	 * 
	 */
	public String toString() {
		String theStack = "front [ ";

		while (!out.isEmpty()) 
			theStack += (out.pop() + " ");

		theStack += "| ";

		//transfersData to out stack so it prints in the correct order
		transferData();
		while(!out.isEmpty())
			theStack += (out.pop()+" ");
			
		theStack += "] back";
		return theStack;
	}

}
