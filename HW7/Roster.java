package HW7;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

// You may not import any additional classes and packages.
import java.util.*;


/// DeleteStudent doesnt work!!!

public class Roster {
    public StudentNode root;
    public int numStudents;

    public Roster() {
        root = null;
        numStudents = 0;
    }

    public int size() {
        return numStudents;
    }

    public void addCourse(String name, String course) {
        if(root == null)
        {
        	root = new StudentNode(name);
        	root.courses.add(course);
        	numStudents++;
        	return;
        }      
        
        StudentNode rootCopy = root;     
        while(rootCopy!=null)
    	{
			if(rootCopy.name.equals(name)) 
			{
				rootCopy.courses.add(course);
				return;
			}
			else if(name.compareTo(rootCopy.name)>0 && rootCopy.right==null)
			{
				rootCopy.right = new StudentNode(name);
				rootCopy.right.courses.add(course);
			}
			else if(name.compareTo(rootCopy.name)>0) 
				rootCopy = rootCopy.right;
			else if(name.compareTo(rootCopy.name)<0 && rootCopy.left==null)
			{
				rootCopy.left = new StudentNode(name);
				rootCopy.left.courses.add(course);
			}
			else rootCopy = rootCopy.left;
    	}
    }
    
	public void dropCourseFromAll(String course) {
		if(root==null) return; //do nothing
		else dropCourseFromAllHelper(root,course);
    }

    private void dropCourseFromAllHelper(StudentNode rootCopy, String course) {
    	if(rootCopy.courses.contains(course)) rootCopy.courses.remove(course);
		
    	if(rootCopy.left==null && rootCopy.right==null) 
    		return; //do nothing 
		else if(rootCopy.left==null) 
			dropCourseFromAllHelper(rootCopy.right,course);
		else if(rootCopy.right==null) 
			dropCourseFromAllHelper(rootCopy.left,course);
		else 
		{
			dropCourseFromAllHelper(rootCopy.left,course);
			dropCourseFromAllHelper(rootCopy.right,course);
		}
		
	}

	public void deleteStudent(String aName) {
		if(root.left==null && root.right==null && root.name.equals(aName))
		{
			root = null;
			return;
		}
		else if(root.left!=null && root.right==null && root.name.equals(aName))
		{
			root = root.left;
			return;
		}
		else if(root.left==null && root.right!=null && root.name.equals(aName))
		{
			root = root.right;
			return;
		}
		else if(root.left!=null && root.right!=null && root.name.equals(aName))
		{
			removeStudentWithTwoLeaves(root.left, root.right);
			root=root.left;
			return;
		}
		else if(root==null) return;
		
		StudentNode aboveStudent = findStudent(root, aName);
		StudentNode student = null;

		if(aboveStudent==null)
			return;
		else if(aboveStudent.left!=null && aboveStudent.left.name.equals(aName))
		{
			student = aboveStudent.left;
			if(student.left==null && student.right==null) 
				aboveStudent.left = null;
			else if(student.left!=null && student.right==null) 
				aboveStudent.left = student.left;
			else if(student.left==null && student.right!=null) 
				aboveStudent.right = student.right;
			else 
			{
				removeStudentWithTwoLeaves(student.left, student.right);
				aboveStudent.left = student.left;
			}
		}
		else if(aboveStudent.right!=null && aboveStudent.right.name.equals(aName))
		{
			System.out.println("Here");
			student = aboveStudent.right;
			if(student.left==null && student.right==null) 
				aboveStudent.right = null;
			else if(student.left!=null && student.right==null) 
				aboveStudent.right = student.left;
			else if(student.left==null && student.right!=null) 
				aboveStudent.right = student.right;
			else 
			{
				removeStudentWithTwoLeaves(student.left, student.right);
				aboveStudent.right = student.left;
			}
		}
		else return; // because the student does not exist
	}

	private void removeStudentWithTwoLeaves(StudentNode leftNode, StudentNode rightNode) {
		if(leftNode.name.compareTo(rightNode.name)<0 && leftNode.right==null)
			leftNode.right = rightNode;
		else if(leftNode.name.compareTo(rightNode.name)<0) 
			removeStudentWithTwoLeaves(leftNode.right, rightNode);
		else if(leftNode.name.compareTo(rightNode.name)>0 && leftNode.left==null)
			leftNode.left = rightNode;
		else removeStudentWithTwoLeaves(leftNode.left, rightNode);
	}

	private StudentNode findStudent(StudentNode rootCopy, String name) {
    	StudentNode lastNode = null;
    	lastNode=rootCopy;
    	
    	if(name.equals(rootCopy.name)) return lastNode;
    	
    	while(rootCopy!=null)
    	{
    		lastNode=rootCopy;
			if(name.compareTo(rootCopy.name)>0) rootCopy = rootCopy.right;
			else rootCopy = rootCopy.left;
			if(rootCopy!=null && name.equals(rootCopy.name)) return lastNode;
    	}
    	return null;
	}

	public String toString() {
       if(root==null) return "There are no students registered.";
       
       return rosterToList(root);
    }

    private String rosterToList(StudentNode rootCopy) {
    	String rosterString = "";
		
		if(rootCopy.left == null && rootCopy.right == null) 
		{
			rosterString = nameToString(rootCopy);
			return rosterString;
		}
		else if(rootCopy.left==null) 
		{
			rosterString = nameToString(rootCopy);
			return rosterString + rosterToList(rootCopy.right);
		}
		else if(rootCopy.right==null)
		{
			rosterString = nameToString(rootCopy);
			return rosterToList(rootCopy.left) + rosterString;
		}
		else
		{
			rosterString = nameToString(rootCopy);
			return rosterToList(rootCopy.left) + rosterString + rosterToList(rootCopy.right);
		}
		
	}
    
    private String nameToString(StudentNode rootCopy) {
    	String nameString = rootCopy.name;
		
		if(rootCopy.courses.isEmpty())
			nameString += ": no courses taken";
		else
		{
			nameString += " has taken:";
			for(String course: rootCopy.courses)	
				nameString += " " + course + ",";
			nameString = nameString.substring(0, nameString.length()-1); //removes last colon
		}
		
		nameString += "\n";
		return nameString;
	}

	// Do not change anything below this line.

	public void display() {
        new TreeDisplay<String>().setRoot(copy(root));
    }

    private static TreeNode<String> copy(StudentNode node) {
        if (node == null) return null;
        return new TreeNode<String>(node.name + ":" + node.courses,
                                    copy(node.left),
                                    copy(node.right));
    }
}
