package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.rules.TestRule;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int getSize() {
		// TODO
		return getSizeHelper(root);
	}
	private int getSizeHelper(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + getSizeHelper(node.getLeft()) + getSizeHelper(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		if (t == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			return false;
		}
		return containsHelper(root, t);
	}

	private boolean containsHelper(BSTNode<T> node, T t) {
		if (node == null) {
			return false;
		}
		if (node.getData().equals(t)) {
			return true;
		}
		if (t.compareTo(node.getData()) < 0) {
			return containsHelper(node.getLeft(), t);
		}
		else {
			return containsHelper(node.getRight(), t);
		}
		
	}

	public boolean removeElement(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		return false;
	}

	// need to do 
	//Attempts to remove one copy of an element from the tree, returning true if and only if such a copy was found and removed.
	/*public boolean removeElement(T t) {
		// TODO
		BSTNode<T> targetNode = null;
		if (t == null) {
			throw new NullPointerException();
		}
		boolean hasRemovedElement = false;
		
		if (((Comparable)t).compareTo(root.getData()) == 0) {
			root = getReplacement(root);
			hasRemovedElement = true;
			return hasRemovedElement;
		}
		else {
			targetNode = removeElementHelper(root, t);
			if (targetNode == null) {
				hasRemovedElement = false;
			}
		}
		return hasRemovedElement;
	}

	private BSTNode<T> removeElementHelper(BSTNode<T> node, T t) {
		BSTNode<T> result = null;
		BSTNode<T> child = null;
		BSTNode<T> replacement = null;
		if (node != null) {
			if (((Comparable)t).compareTo(node.getData()) < 0) { // found target
				child = node.getLeft();
				if (child != null && ((Comparable)t).compareTo(child.getData())==0) {
					result = child;
					replacement = getReplacement(child);
					if (replacement == null) {
						node.setLeft(null);
					}
					else {
						node.setLeft(replacement);
					}
				}
				else {
					result = removeElementHelper(child, t);
				}
			}
			else if (((Comparable)t).compareTo(node.getData()) >0) {
				child = node.getRight();
				if (child != null && ((Comparable)t).compareTo(child.getData())==0) {
					result = child;
					replacement = getReplacement(child);
					if (replacement == null) {
						node.setRight(null);
					}
					else {
						node.setRight(replacement);
					}
				}
			}
		}
		return result;
	}

	// determines which case its dealing with
	private BSTNode<T> getReplacement (BSTNode<T> node) {
		BSTNode<T> result = null;
		// case 1: node is a leaf
		if (node.getLeft() == null && node.getRight() == null) {
			result = null;
		}
		// case 2: node has a right child only
		else if (node.getLeft() == null && node.getRight() != null) {
			result = node.getRight();
		}
		// case 2: node has a left child only
		else if (node.getLeft() != null && node.getRight() == null) {
			result = node.getLeft();
		}
		// case 3: node has both a right and left child
		else {
			result = findInorderPredecessor(node.getLeft(), node);
			result.setLeft(node.getLeft());
			result.setRight(node.getRight());
		}
		return result;
	}

	private BSTNode<T> findInorderPredecessor(BSTNode<T> node, BSTNode<T> parent) {
		BSTNode<T> child = node.getRight();
		// node is the in order predecessor
		if (child == null) {
			// test if the parent is the node to be deleted
			if (parent.getLeft() == node) {
				return node;
			}
			// disconnect the node
			else {
				parent.setRight(null);
				return node;
			}
		}
		// continue searching
		else {
			return findInorderPredecessor(child, node);
		}
	}*/

	public T getHighestValueFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValueFromSubtree(node.getRight());
		}
	}

	public T getLowestValueFromSubtree(BSTNode<T> node) {
		// TODO
		if (node.getLeft() == null) {
			return node.getData();
		} 
		else {
			return getLowestValueFromSubtree(node.getLeft());
		}
	}

	private BSTNode<T> removeRightmostFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmostFromSubtree(node.getRight()));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	public BSTNode<T> removeLeftmostFromSubtree(BSTNode<T> node) {
		// TODO
		if (node.getLeft() == null) {
			return node.getRight();
		}
		else {
			node.setLeft(removeLeftmostFromSubtree(node.getLeft()));
			if (node.getLeft() != null){
				node.getLeft().setParent(node);
			}
			return node;
		}
	}

	public T getElement(T t) {
		// TODO
		if (t == null) {
			throw new NullPointerException();
		}
		return getElementHelper(t, root);
	}

	private T getElementHelper(T t, BSTNode<T> node) {
		if (node == null) {
			return null;
		}
		int result = t.compareTo(node.getData());
		if (result == 0) {
			return node.getData();
		}
		else if (result < 0) {
			return getElementHelper(t, node.getLeft());
		}
		else {
			return getElementHelper(t, node.getRight());
		}
	}

	public void addElement(T t) {
		// TODO
		if (t == null) {
			throw new NullPointerException();
		}
		root = addElementHelper(root, new BSTNode<T>(t, null, null));
	}

	private BSTNode<T> addElementHelper(BSTNode<T> rootNode, BSTNode<T> newNode) {
		if (rootNode == null) {
			return newNode;
		}
		int result = newNode.getData().compareTo(rootNode.getData());
		if (result <= 0) {
			rootNode.setLeft(addElementHelper(rootNode.getLeft(), newNode));
		} else {
			rootNode.setRight(addElementHelper(rootNode.getRight(), newNode));
		}
		return rootNode;
	}

	@Override
	// use getLowestValueFromSubtree()
	public T getMin() {
		// TODO
		if (root == null) {
			return null;
		}
		return getLowestValueFromSubtree(root);
	}

	@Override
	// use getHighestValueFromSubtree() method
	public T getMax() {
		// TODO
		if (root == null) {
			return null;
		}
		return getHighestValueFromSubtree(root);
	}

	@Override
	public int height() {
		// TODO
		if (root == null) {
			return -1;
		}
		BSTNode<T> newNode = root;
		return heightHelper(newNode);
	}

	private int heightHelper(BSTNode<T> node) {
		if(node == null) {
			return -1;
		}
		int heightLeft = heightHelper(node.getLeft());
   		int heightRight = heightHelper(node.getRight());
    	if (heightLeft > heightRight) {
			return heightLeft +1;
		}
		else {
			return heightRight+1;
		}
	}

	//Pre-order traversal: visit the current node, visit the left subtree, then visit the right subtree.
	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();
	}

	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}

	// left --> root --> right
	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	// need to do
	//Post-order traversal: visit the left subtree, visit the right subtree, then visit the current node.
	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}

	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		if (other == null) {
			throw new NullPointerException();
		}
		return equalsHelper(root, other.getRoot());
	}

	private boolean equalsHelper(BSTNode<T> nodeOne, BSTNode<T> nodeTwo) {
		if (nodeOne == null && nodeTwo == null) {
			return true;
		}
		else if (nodeOne == null || nodeTwo == null) {
			return false;
		}
		else {
			if (!nodeOne.getData().equals(nodeTwo.getData())) {
				return false;
			}
			return equalsHelper(nodeOne.getLeft(), nodeTwo.getLeft()) && equalsHelper(nodeOne.getRight(), nodeTwo.getRight());
		}
	}

	// need to do
	//Returns true if and only if this tree and the other tree store the same values, regardless of structure
	//Uses equals() to check
	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		if (other == null) {
			throw new NullPointerException();
		}
		boolean sameValue = false;
		Iterator<T> iter1 = this.inorderIterator();
		Iterator<T> iter2 = other.inorderIterator();
		while (iter1.hasNext() && iter2.hasNext()) 
			if (!iter1.next().equals(iter2.next())) {
				return sameValue;
			}
		sameValue = true;

		return sameValue;
	}
	
	// need to do
	//Returns the number of elements in the tree that has a value larger than min and smaller than max.
	@Override
	public int countRange(T min, T max) {
    	// TODO
		return countRangeHelper(root, min, max);
  	}
	
	private int countRangeHelper(BSTNode<T> node, T min, T max) {
		if(node == null){
			return 0;
		}
		
		int sum = 0;
		 //If root is within range add it to sum:
		
		if (node.getData().compareTo(max) < 0) {
			sum += countRangeHelper(node.getRight(), min, max);
		}
		if (node.getData().compareTo(min) > 0) {
			sum += countRangeHelper(node.getLeft(), min, max);
		}
		if (node.getData().compareTo(max) < 0 && node.getData().compareTo(min) > 0){
			return sum+=1;
		} 
		else {
			return sum;
		}
	}


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.addElement(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.removeElement(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.addElement(r);
		}
		System.out.println(tree.getSize());
		System.out.println(tree.height());
		System.out.println(tree.countRange("a", "g"));
		System.out.println(tree.countRange("c", "f"));
	}
}