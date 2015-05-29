package HW7;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

// You may not import any additional classes and packages.
import java.util.*;

/// isSubset does not work!!!!

public class BinaryTrees {
    public static int countLeavesAtLevel(TreeNode<Integer> t, int level){
    	if(t==null) return 0;
    	else if(level==0 && t.left==null && t.right==null) return 1;
    	else if(level==0 && t.data==null) return 0;
    	else if(t.left==null && t.right==null) return 0;
    	else if(t.left==null && t.right!=null) return countLeavesAtLevel(t.right, level-1);
    	else if(t.left!=null && t.right==null) return countLeavesAtLevel(t.left, level-1);
    	else return (countLeavesAtLevel(t.left, level-1) + countLeavesAtLevel(t.right, level-1));
    }

    public static boolean isPerfect(TreeNode<Integer> t){
    	if(t==null) return true;
    	else if(t.left==null && t.right==null) return true;
    	else if(t.left==null && t.right!=null) return false;
    	else if(t.left!=null && t.right==null) return false;
    	else return (isPerfect(t.left)&&isPerfect(t.right));
    }
    
    public static boolean isFull(TreeNode<Integer> t){
    	if(t==null) return true;
    	else if(t.left==null && t.right==null) return true;
    	else if(t.left==null && t.right!=null) return false;
    	else if(t.left!=null && t.right==null) return false;
    	else return (isFull(t.left)&&isFull(t.right));
    }

    public static boolean isSubset(TreeNode<String> t1, TreeNode<String> t2){
    	if(t1==null && t2==null) return true;
    	else if(t2==null) return false;
    	else if(t1==null) return true;
    	
    	//breaks t2 down into a list of all elements
    	ArrayList<String> t2ToTree = treeToList(t2);
    	boolean t2isSubset;
    	
    	for(int i=0;i<t2ToTree.size();i++) // returns if each value is in t1
    	{	
    		t2isSubset = isSubsetHelper(t1,t2ToTree.get(i));
    		if(t2isSubset==false) return false;
    	}
    	
    	return true;
    }

	private static Boolean isSubsetHelper(TreeNode<String> t, String node) {
		if(t.data==node) return true;
		else if(t.left==null && t.right==null) return false;
		else if(t.left==null) return isSubsetHelper(t.right,node);
		else if(t.right==null) return isSubsetHelper(t.left,node);
		else return (isSubsetHelper(t.left,node) || isSubsetHelper(t.right,node));
	}

	public static ArrayList<String> treeToList(TreeNode<String> t) {
		ArrayList<String> treeList = new ArrayList<String>();
		
		if(t.left == null && t.right == null) ;
		else if(t.left==null) treeList.addAll(treeToList(t.right));
		else if(t.right==null) treeList.addAll(treeToList(t.left));
		else
		{
			treeList.addAll(treeToList(t.left));
			treeList.addAll(treeToList(t.right));
		}
		treeList.add(t.data);
		return treeList;		
	}

	public static boolean areEqual(TreeNode<String> t1, TreeNode<String> t2){
       if(t1 == null && t2 == null) return true;
       else if(t1 == null || t2 == null) return false;
       else if(t1.data == t2.data && (t1.left==null && t1.right == null 
    		   && t2.right==null && t2.left==null)) return true;
       else if(t1.data!=t2.data) return false;
       else if(t1.left==null && t2.left!=null) return false;
       else if(t1.right!=null && t2.right==null) return false;
       else if(t1.left==null) return areEqual(t1.right,t2.right);
       else if(t1.right==null) return areEqual(t1.left, t2.left);
       else return(areEqual(t1.left,t2.left) && areEqual(t1.right,t2.right));
    }

    public static TreeNode<Integer> mirror(TreeNode<Integer> t){
    	if(t==null) return t;
    	else if(t.left==null) 
    	{
    		t.right = null;
    		return t;
    	}

    	t.right = copyTree(t.left);
    	return t;
    }
    
    public static TreeNode<Integer> copyTree(TreeNode<Integer> t) {
    	if(t.left==null && t.right==null) return t;
    	else if(t.left!=null && t.right==null) 
    	{
    		t.left = copyTree(t.left);
    		return t;
    	}
    	else if(t.right!=null && t.left==null)
    	{
    		t.right = copyTree(t.right);
    		return t;
    	}
    	else 
    	{
    		t.right = copyTree(t.right);
    		t.left = copyTree(t.left);
    		return t;
    	}		
	}

	public static boolean isBinarySearchTree(TreeNode<Integer> t){
        if(t.left!=null && t.left.data>t.data) return false;
        else if(t.right!=null && t.right.data<t.data) return false;
        else if(t.right==null && t.left==null) return true;
        else if(t.right==null) return isBinarySearchTree(t.left);
        else if(t.left==null) return isBinarySearchTree(t.right);		
        else return (isBinarySearchTree(t.left) && isBinarySearchTree(t.right));
    }
}
