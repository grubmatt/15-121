package HW6;
/**
 * 
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

import java.util.*;

public class ArrayStack<E> implements MyStack<E> {

	private E[] dataArray;
	private int top;

	/**
	 * Creates an empty stack.
	 * 
	 */
	public ArrayStack() {
		dataArray = (E[])new Object[5];
		top =-1;
	}

	/**
	 * Determines if the stack is empty or not.
	 * 
	 * @return true if the stack is empty or false otherwise
	 * 
	 */
	public boolean isEmpty() {
		return (top==-1);
	}
	
	/**
	 * Returns but does not remove the top element of the stack if the stack is
	 * not empty.
	 * 
	 * @return The top element of the stack
	 * @throws NoSuchElementException
	 *           if the stack is empty
	 *           
	 */
	public E peek() {
		if(this.isEmpty())
			throw new NoSuchElementException();
			
		return dataArray[top];
	}
	

	/**
	 * Pushes the given element on this stack
	 * 
	 * @param element
	 *          The element of type E to push on the stack.
	 *          
	 */
	public void push(E element) {
		top++;
		
		if(top==dataArray.length-1)
			createNewArray();
		
		dataArray[top] = element;
	}

	private void createNewArray() {
		E[] tempArray = (E[])new Object[dataArray.length*2];
		for(int i=0;i<dataArray.length;i++)
			tempArray[i] = dataArray[i];
		dataArray = tempArray;
		
	}

	/**
	 * Returns and removes the top element of the stack if the stack is not empty.
	 * 
	 * @return The top element of the stack
	 * @throws NoSuchElementException
	 *           if the stack is empty
	 *           
	 */
	public E pop() {
		if(this.isEmpty())
			throw new NoSuchElementException();
		
		E tempObject = dataArray[top];
		dataArray[top] = null;
		top--;
		return tempObject;
	}

	/**
	 * Returns a String representation of the stack in the following format top [
	 * 3 5 ] bottom
	 * 
	 */
	public String toString() {
		String theStack = "top [ ";
		for(int i=dataArray.length-1; i>=0;i--)
		{
			if(dataArray[i]!=null)
				theStack += (dataArray[i] + " ");
		}
		theStack += "] bottom";
		return theStack;
	}

}
