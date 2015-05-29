package HW6;
/**
 * 
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 * 
 */

public class StackQueueSolver {

	// Runtime O(n)
	public static int lastCustomer(int numPersons, int numToBack) {
		ArrayQueue customers = new ArrayQueue();
		for(int i=1;i<=numPersons;i++)
			customers.enqueue(i);
		
		int lastCustomer = 0;
		
		while(!customers.isEmpty())
		{
			for(int i=0;i<numToBack;i++)
				customers.enqueue(customers.dequeue());
			
			lastCustomer = (Integer) customers.dequeue();
		}
		return lastCustomer;
	}

	// Runtime O(n)
	public static boolean areEqual(MyStack<String> stack1, MyStack<String> stack2) {
		ArrayStack tempStack = new ArrayStack();
		while(!stack1.isEmpty())
		{
			if(stack1.peek()==stack2.peek())
			{
				stack2.pop();
				tempStack.push(stack1.pop());
			}
			else
			{
				return false;
			}
		}
		
		while(!tempStack.isEmpty())
		{
			stack1.push((String) tempStack.peek());
			stack2.push((String) tempStack.pop());
		}
		return true;
		
			
	}

	// Runtime O(n)
	public static MyStack<Integer> duplicateStack(MyStack<Integer> original) {
		ArrayStack copyStack = new ArrayStack();
		ArrayQueue copyQueue = new ArrayQueue();
		while(!original.isEmpty())
		{
			copyStack.push(original.pop());
		}
		while(!copyStack.isEmpty())
		{
			copyQueue.enqueue(copyStack.peek());
			original.push((Integer) copyStack.pop());
		}
		while(!copyQueue.isEmpty())
		{
			copyStack.push(copyQueue.dequeue());
		}
		
		return copyStack; // remove this line when you are done
	}

	public static void main(String[] args) {
		
		System.out.print(lastCustomer(2,2));
	
	}

}