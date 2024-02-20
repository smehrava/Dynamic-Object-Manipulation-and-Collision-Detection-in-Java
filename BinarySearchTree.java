/* This class implements an ordered dictionary using a binary search tree. Each node of the tree will store a Pel
object;
 * STUDENT ID = 251185394
 * AUTHOR = SARA MEHRAVAR
 */
public class BinarySearchTree implements BinarySearchTreeADT{
	
	private BNode root;
	
	
	public BinarySearchTree() {
		this.root = new BNode();
	}
	
	//this method  Returns the Pel object storing the given key, if the key is stored in the tree; returns null otherwise
	public Pel get(BNode r, Location key) {
		//if r is a leaf, it will return null
		if (r.isLeaf() == true) return null;
		else{
			//using compareTo method to see if location of r is equal to key
			if (r.getData().getLocus().compareTo(key) == 0) {
				//if so we return the data (value) that is stored in r
				return r.getData();
			} else {
				// if key is less than the location of r
				if (key.compareTo(r.getData().getLocus()) == -1) {
					// we return this:
					return get(r.leftChild(),key);
				} else {
					// otherwise we return this:
					return get(r.rightChild(),key);
				}
			}
		}
	}
	//using a helper getNode method and the difference is, this one returns r when r is a leaf and the original get method return null
	private BNode getNode(BNode r, Location key) {
		if (r.isLeaf() == true) {
			return r; 
		}
		else{
			//using compareTo method as we did above
			if (r.getData().getLocus().compareTo(key) == 0) {
				return r;
			} else {
				if (key.compareTo(r.getData().getLocus()) == -1) {
					return getNode(r.leftChild(),key);
				} else {
					return getNode(r.rightChild(),key);
				}
			}
		}
	}
	
	
	//this method inserts newData in the tree if no data item with the same key is already there;
	// if the key is already there, it will throw an exception
	public void put(BNode r,Pel newData) throws DuplicatedKeyException{
		//we make a BNode type variable as p
		BNode p = new BNode();
		//using helper get method
		p = getNode(r,newData.getLocus());
		//if p is internal, we throw exception
		if (isInternalNode(p) == true) 
			throw new DuplicatedKeyException("his key already exists");
		else {
			//set the new data as the data value for Node p.
			p.setContent(newData);
			
			//making two BNode variables that are used as left and right child
			BNode newNode1 = new BNode();
			BNode newNode2 = new BNode();
			//setting new nodes parents as p
			newNode1.setParent(p);
			newNode2.setParent(p);
			//setting new nodes as p's children (left and right)
			p.setLeftChild(newNode1);
			p.setRightChild(newNode2);
		}
		
	}
	
	//this method is a helper method because the original smallest method returns nodes data but this method just returns node
	private BNode smallestNode(BNode r) {
		if (r.isLeaf() == true) {
			return null;
		}
		else {
			BNode p = r;
			while (p.isLeaf() == false) {
				p = p.leftChild();
			}
			return p.parent();
		}
	}
	
	//this method is a helper method because the original largest method returns nodes data but this method just returns node
	private BNode largestNode(BNode r) {
		if (r.isLeaf() == true) return r.parent();
		else {
			return largestNode(r.rightChild());
		}
	}
	
	
	//this method removes the data item with the given key, if the key is stored in the tree; throws an InexistentKeyExceptio otherwise.
	public void remove(BNode r, Location key) throws InexistentKeyException { 
        BNode deleted = getNode(r,key);
        //if the node we wanted to delete is not in the tree
        if (deleted.isLeaf()){ 
            throw new InexistentKeyException("this node does not exist in the tree");
        }
        else{ 
        	//if one of the children is leaf:
        	// if left child is a leaf
            if(deleted.leftChild().isLeaf()){
                BNode otherChild = deleted.rightChild();
                BNode parent = deleted.parent();
                if (parent != null){
                	// comparing parent of the node we want to delete's location with the location of node we want to delete
                	//if parents location is less than deleted's location
                    if(parent.getData().getLocus().compareTo(deleted.getData().getLocus()) == -1){
                        parent.setRightChild(otherChild);
                        otherChild.setParent(parent);
                    }
                    //otherwise:
                    else{
                    	
                        parent.setLeftChild(otherChild);
                        otherChild.setParent(parent);
                    }
                }
                else{
                	//setting otherchild as the root of the tree
                    root = otherChild;
                    otherChild.setParent(null);
                }
            }
            // if right child is a leaf
            else if(deleted.rightChild().isLeaf()){
                BNode otherChild = deleted.leftChild();
                BNode parent = deleted.parent();
                if (parent != null){
                	// comparing parent of the node we want to delete's location with the location of node we want to delete
                	//if parents location is less than deleted's location
                    if(parent.getData().getLocus().compareTo(deleted.getData().getLocus()) == -1){
                        parent.setRightChild(otherChild);
                        otherChild.setParent(parent);
                    }
                    //otherwise:
                    else{
                        parent.setLeftChild(otherChild);
                        otherChild.setParent(parent);
                    }
                }
                //setting the other child as the root of the tree
                else{
                    root = otherChild;
                    otherChild.setParent(null);
                }
            }
            else{ //when key is in the tree and neither of the children are leaves:
                BNode smallest = new BNode();
                smallest = smallestNode(deleted.rightChild());
                deleted.setContent(smallest.getData());
                remove(smallest, smallest.getData().getLocus());
            }
        }

    }
	
	//private method for realizing if a node is internal or not:
	private boolean isInternalNode(BNode r) {
		if (r.leftChild() == null && r.rightChild() == null) {
			return false;
		}
		return true;
	}
	
	
	//this method : Returns the Pel object with the smallest key larger than the given one (note that the tree does not need to store a node with the given key)
	//Returns null if the given key has no successor
	public Pel successor(BNode r, Location key) {
		//if key is a leaf, return null
		if (r.isLeaf() == true) return null;
		//using helper method to figure out where the node is in the tree
		BNode p = getNode(r,key);
		//if p and its right child are both internal:
		if (isInternalNode(p) && isInternalNode(p.rightChild())) {
			//we return the smallest nodes data as the method returns data(pel)
			return smallestNode(p.rightChild()).getData();
		} else {
			//setting p as its parent;
			p = p.parent();
			// if the smallest key is larger than the given one:
			while (p != null && p.getData().getLocus().compareTo(key) == -1) {
				p = p.parent();
			}
			if (p == null) return null;
			else return p.getData();
		}
	}
	
	//this method  Returns the Pel object with the largest key smaller than the given one (note that the tree does not need to store a node with the given key).
	//Returns null if the given key has no predecessor.

	public Pel predecessor(BNode r, Location key) {
		if (r.isLeaf()) return null;
		BNode p = getNode(r,key); 
		// if p and its left child are both internal nodes:
		if (isInternalNode(p) && isInternalNode(p.leftChild())) {
			//finding the largest node
			return largestNode(p.leftChild()).getData();
		} else {
			p = p.parent();
			//largest key smaller than the given one
			while (p != null && p.getData().getLocus().compareTo(key) == 1) {
				p = p.parent();
			}
			if (p == null) return null;
			else return p.getData();
		}
	}
	
	//finding smallest node's data
	public Pel smallest(BNode r) {
		if (r.isLeaf() == true) return r.parent().getData();
		else {
			return smallest(r.leftChild());
		}
	}
	
	//finding largest node's data
	public Pel largest(BNode r) {
		if (r.isLeaf() == true) return r.parent().getData();
		else {
			return largest(r.rightChild());
		}
	}
	
	//returning the root
	public BNode getRoot() {
		return this.root;
	}
	
}