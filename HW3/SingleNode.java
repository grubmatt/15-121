package HW3;
/*
 * 
 * DO NOT EDIT/CNAHGE THIS FILE
 *
 */


public class SingleNode {
	public SingleNode next;
	public String data;
	
	public SingleNode(String data) {
		this.next = null;
		this.data = data;
	}
	
	public boolean equals(SingleNode otherNode) {
		return this.data.equals(otherNode.data);
	}
	
	public String toString() {
		return data;
	}
}
