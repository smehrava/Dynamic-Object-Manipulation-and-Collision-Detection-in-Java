/* This class represents the nodes of the binary search tree
 * AUTHOR'S NAME = SARA MEHRAVAR
 * STUDENT ID = 251185394
 */
public class BNode {
	
	//instant variables
	private Pel value;
	private BNode left;
	private BNode right; 
	private BNode parent;
	
	//constructor
	public BNode (Pel value, BNode left, BNode right, BNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	//setting all variables to null
	public BNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.left = null;
		
	}
	//returning parent
	public BNode parent() {
		return parent;
	}
	//setting parent to newParent
	public void setParent(BNode newParent) {
		this.parent = newParent;
	}
	//setting leftChild to p
	public void setLeftChild(BNode p) {
		this.left = p;
		
	}
	//setting rightChild to p
	public void setRightChild(BNode p) {
		this.right = p;
	}
	//setting value to value
	public void setContent(Pel value) {
		this.value = value;
	}
	//figuring out if the node is leaf or not
	public boolean isLeaf() {
		if(left == null && right == null) {
			return true;
		}
		else {
			return false;
		}
	}
	//returning value
	public Pel getData() {
		return value;
	}
	//returning left child
	public BNode leftChild() {
		return left;
	}
	//returning right child
	public BNode rightChild() {
		return right;
	}
	
	

}
