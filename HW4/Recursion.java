package HW4;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

// You may not import any additional classes or packages.
import java.util.ArrayList;

public class Recursion {

    public static int count(Node head, String findData) {
        if(head==null)
        	return 0;
        else if(head.data.equals(findData))
        	return 1 + count(head.next, findData);
        else
        	return 0 + count(head.next, findData);
    }

    public static boolean isReverse(String string1, String string2) {
    	if(string1.length()!=string2.length())
    		return false;
    	else if(string1.length()==0 && string1.equals(string2))
        	return true;
        else if(string1.charAt(0)==string2.charAt(string2.length()-1))
        	return isReverse(string1.substring(1),string2.substring(0, string2.length()-1));
        else
        	return false;
    }

    public static void insertAfter(Node head, String insertData,
                                   String findData) {
    	if(head==null)
    		return;
    	else if(head.data.equals(findData))
    	{
    		Node tempNode = head.next;
    		head.next = new Node(insertData);
    		head.next.next = tempNode;
    	}
    	else
    		insertAfter(head.next, insertData, findData);
    }

    public static boolean itAddsUp(ArrayList<Integer> list, Integer target) {
    	if(list.size()==0)
    	{
    		if(target==0)
    			return true;
    		else
    			return false;
    	}
    	else if(list.size()==1)
    	{	
    		if(list.get(0)==target)
    			return true;
    		else
    			return false;
    	}
        else
        {
        	Integer secondToLast = list.get(list.size()-2);
        	secondToLast += list.get(list.size()-1);
        	list.remove(list.size()-2);
        	list.remove(list.size()-1);
        	list.add(secondToLast);
        	return itAddsUp(list, target);
        }
    }

    public static String removeDuplicates(String string) {
        if(string.length()==1)
        	return string;
        else if(string.charAt(0)==string.charAt(1))
        	return removeDuplicates(string.substring(1));
        else
        	return string.charAt(0) + removeDuplicates(string.substring(1));
    }

    public static String stringNumbers(Integer n) {
        if(n==0)
        	return "";
        else if(n==1)
        	return n.toString();
        else
        {
        	if(n%2==0)
        		return(n.toString() +"-"+stringNumbers(n-1));
        	else
        		return(stringNumbers(n-1)+"-"+n.toString());
        }
    }

    public static Node removeAll(Node head, Integer length) {
    	if(head==null)
    		return null;
    	else if(head.data.length()==length)
    	{
    		return removeAll(head.next,length);
    	}
    	else
    	{
    		head.next = removeAll(head.next,length);
    		return head;
    	}
    }

    public static ArrayList<Integer> mirrorList(ArrayList<Integer> list) {
    	if(list.size()==0)
    	{
    		return list;
    	}
    	else if(list.size()==1)
		{
			list.add(list.get(0));
			return list;
		}
		else
		{
			Integer tempInt = list.get(0);
			list.remove(0);
			return mirrorHelper(list, tempInt);
		}	
    }
    
    public static ArrayList<Integer> mirrorHelper(ArrayList<Integer> list, Integer firstElement )
    {
    	ArrayList<Integer> tempList = new ArrayList<Integer>();
    	tempList = mirrorList(list);
    	tempList.add(firstElement);
    	tempList.add(0, firstElement);
    	return tempList;
    }

    public static int sumDigits(int n) {
        if(n<0)
        	throw new IllegalArgumentException();
        else if(n<10)
        	return n;
        else
        	return n%10 + sumDigits(n/10);
    }

    public static int countBinary(int n) {
    	if(n==0)
    		return 0;
    	else
    		return Recursion.generateBinary("0", 0, n)+Recursion.generateBinary("1", 1, n);
    }
    
    public static int generateBinary(String curBinary, int lastBit, int sizeBinary)
    {
    	// Generates all binaries that don't have 2 consecutive 0's
    	if(sizeBinary==curBinary.length())
    		return 1;
    	else if(lastBit==0)
    		return generateBinary(curBinary+"1", 1, sizeBinary);
    	else
    		return generateBinary(curBinary+"1", 1, sizeBinary) + generateBinary(curBinary+"0",0,sizeBinary);
    }

    /**
    * Use the main method to write your tests. Delete this method when you're
    * done.
    */
    public static void main(String args[]) {
    }
}
