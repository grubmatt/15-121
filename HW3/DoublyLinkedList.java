package HW3;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

import java.util.*;

public class DoublyLinkedList<E> {
	// Do not change these fields.
	public DoubleNode<E> currNode;
	public int numElements;

	public DoublyLinkedList() {
		currNode = null;
		numElements = 0;
	}

	public int size() {
		return numElements;
	}

	public E get() {
		if(currNode == null)
			throw new NoSuchElementException();
		else
			return currNode.data;
	}

	public void add(E item) {
		DoubleNode<E> newItem = new DoubleNode<E>(item);
		
		
		if(numElements==0)
		{
			currNode = newItem;
			currNode.prev = currNode;
			currNode.next = currNode;
		}
		else
		{
			DoubleNode<E> prevItem = currNode;
			DoubleNode<E> nextItem = currNode.next;
		
			currNode = newItem;
			
			//Alters previous item interaction
			currNode.prev = prevItem;
			prevItem.next = currNode;
		
			//Alters next item interaction
			currNode.next = nextItem;
			nextItem.prev = currNode;
		}
		
		numElements++;
	}

	public List<E> toList() {
		List<E> allElements = new LinkedList<E>();
		DoubleNode<E> tempNode = currNode.next;
		
		
		for(int i=0;i<numElements;i++)
		{
			allElements.add(tempNode.data);
			tempNode = tempNode.next;
		}
		
		return allElements;
		
	}

	public boolean contains(E item) {
		DoubleNode<E> tempNode = currNode;
		
		for(int i=0;i<numElements;i++)
		{
			if(tempNode.data.equals(item))
				return true;
			tempNode = tempNode.next;
		}
		
		return false;
			
	}

	public void scroll(Integer n) {
		if(numElements==0)
			throw new NoSuchElementException();
		
		if(n>0)
		{
			for(int i=0;i<n;i++)
				currNode = currNode.next;
		}
		else if(n<0)
		{
			for(int i=Math.abs(n);i>0;i--)
				currNode = currNode.prev;
		}
	}

	public E remove() {
		if(numElements==0)
			throw new NoSuchElementException();
		
		numElements--;
		
		if(currNode.prev == currNode)
		{
			E tempValue = currNode.data;

			currNode.prev = null;
			currNode.next = null;
			currNode = null;
			
			return tempValue;
		}
		
		//Get item before currNode
		//Get item after cuurNode
		//change prev item to afterItem
		// change nextitem to prevItem
		
		DoubleNode<E> prevNode = currNode.prev;
		DoubleNode<E> nextNode = currNode.next;
		E tempValue = currNode.data;

		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		
		currNode = prevNode;
		
		return tempValue;
	}
}
