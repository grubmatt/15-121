package HW6;
/**
 * 
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

import java.util.*;

public class ArrayQueue<E> implements MyQueue<E> {

	private E[] dataArray;
	private int front; //index of first item to remove
	private int back;  //index of last item to remove
	private int numOfElements; // for convenience

	public ArrayQueue() {
		dataArray = (E[])new Object[5];
		front = 0;
		back = 5;
	}

	/**
	 * Returns true if this queue no elements.
	 * 
	 * @return true if this queue is empty, false otherwise.
	 * 
	 */
	public boolean isEmpty() {
		return (numOfElements==0);
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
		return dataArray[front];
	}
	
	
	/**
	 * Adds the specified element to the back of this queue.
	 * 
	 * @param element
	 *          to add on to the back of this queue.
	 *          
	 */
	public void enqueue(E element) {
		back++;
		
		if(numOfElements==dataArray.length)
			createNewArray();
		
		if(back>=dataArray.length)
			back=0;
		
		dataArray[back] = element;
		numOfElements++;
	}
	
	

	private void createNewArray() {
		E[] tempArray = (E[])new Object[dataArray.length*2];
		if(back<front)
		{
			int i = 0;
			for(int j=front;j<dataArray.length;j++)
			{
				tempArray[i] = dataArray[j];
				i++;
			}
			for(int j=0;j<=back;j++)
			{
				tempArray[i] = dataArray[j];
				i++;
			}
		}
		else
		{
			for(int i=0;i<dataArray.length;i++)
				tempArray[i] = dataArray[i];
		}
		
		front = 0;
		back = numOfElements;
		dataArray = tempArray;
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
		if(numOfElements==0)
			throw new NoSuchElementException();
		
		E tempElement = dataArray[front];
		dataArray[front] = null;
		front++;
		numOfElements--;
		if(front>=dataArray.length)
			front=0;
		
		return tempElement;
	}


	/**
	 * Returns a String representation of this queue in the format described in
	 * the writeup
	 * 
	 */
	public String toString() {
		String theStack = "front: "+front+" back: "+back+"\nfront [ ";
		if(front>=back)
		{
			for(int i=front;i<dataArray.length;i++)
			{
				if(dataArray[i]!=null)
					theStack += (dataArray[i] + " ");
			}
			for(int i=0;i<back;i++)
			{
				if(dataArray[i]!=null)
					theStack += (dataArray[i] + " ");
			}
		}
		else
		{
			for(int i=front;i<=back;i++)
			{
				if(dataArray[i]!=null)
					theStack += (dataArray[i] + " ");
			}
		}
		theStack += "] back";
		return theStack;
	}

}
