package HW3;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

// You may not import any additional classes or packages.

import java.util.ArrayList;

public class SinglyLinkedList {

	private int numElements;
	public SingleNode head;
	
	public SinglyLinkedList() {
		numElements = 0;
		head = null;
	}
	
	/**
	 * The add method creates a SingleNode given data and inserts 
     * it at the beginning of the SinglyLinkedList.
     *
     * Do not change this method!
	 */
	public void add(String newData) {
		SingleNode node = new SingleNode(newData);
		node.next = head;
		head = node;
		numElements++;
	}
	
	/**
     * Do not change this method!
	 */
	public int size() {
		return numElements;
	}
	
	public String concatenate() {
		String allElements = "";
		SingleNode temp = head;
		for(int i=0;i<numElements;i++)
		{
			allElements += temp.data;
			temp = temp.next;
		}
		return allElements;
	}
	
	public void insertAfter(String insertData, String findData) {
		SingleNode tempHead = head;
		for(int i=0; i<numElements;i++)
		{
			if(tempHead.data.equals(findData))
			{
				SingleNode tempNode = tempHead.next;
				SingleNode insertNode = new SingleNode(insertData);
				tempHead.next = insertNode;
				insertNode.next = tempNode;
				numElements++;
			}
			tempHead = tempHead.next;
		}
	}
	
	public void buildList(ArrayList<String> arrayList) {
		
		for(int i=arrayList.size()-1; i>=0;i--)
			add(arrayList.get(i));
	
	}
	
	public boolean equals(SinglyLinkedList otherList) {
		// returns false if the two lists are unequal sizes
		if(this.size()!=otherList.size())
			return false;
	
		SingleNode tempCurrentNode = head;
		SingleNode tempOtherNode = otherList.head;
		
		while(tempCurrentNode != null && tempOtherNode != null)
		{
			// returns false if the two nodes aren't equal
			if (!(tempCurrentNode.data.equals(tempOtherNode.data)))
				return false;
			tempCurrentNode = tempCurrentNode.next;
			tempOtherNode = tempOtherNode.next;
		}
		
		return true; 
	}
	
	public void bringToFront(int index) {
		SingleNode tempHead = head;
		boolean indexFound = false;
		
		for(int i=0; i<numElements;i++)
		{
			if(i == index-1)
			{
				indexFound = true;
				
				try{
					// Adds indexed node to front
					add(tempHead.next.data);
					numElements--;
					
					// Finds node after indexed node
					SingleNode postNode = tempHead.next.next;
					
					// Points the node before indexed node to the one after
					tempHead.next = postNode;
				}
				catch (Exception E)
				{
					tempHead.next = null;
				}
			}
			else if (!indexFound)
				tempHead = tempHead.next;
		}
	}
	
	public void removeAll(int length) {
		if(head==null)
			return;
			
		SingleNode tempHead = head;
		
		while(tempHead.data.length()==length)
		{
			tempHead = tempHead.next;
			head = tempHead;
			numElements--;
		}
		
		while(tempHead.next != null) {
			if (tempHead.next.data.length() == length) {
				numElements--;

				if(tempHead.next.next != null)
				{
					// Finds node after node to be removed
					SingleNode nextNode = tempHead.next.next;

					// Points the node before the node to be removed
					// to the one after
					tempHead.next = nextNode;
				}
				else
					tempHead.next = null;
			}
			else
				tempHead = tempHead.next;
		}
	}
	
	public void reverse() {
		int elementsToRemove = 0;
		for(int i=0;i<numElements;i++)
		{
			bringToFront(i);
			elementsToRemove++;
		}
		numElements -= elementsToRemove;
		
	}
	
	/**
	 * Pretty print SinglyLinkedLists.
     *
     * Do not modify this method!
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		if(head == null) {
			sb.append("[HEAD] -> null -> [TAIL]");
		} else {
			SingleNode nodeRef = head;
			sb.append("[HEAD] -> ");
			while(nodeRef != null) {
				sb.append(nodeRef.data);
				sb.append(" -> ");
				nodeRef = nodeRef.next;
			}
			sb.append("[TAIL]");
		}
		
		return sb.toString();
	}
}
