package com.vee.datastructures;

public class LinkedList<M extends Object> {
	private Node<M> header;
	private int size = -1;
	
	public int getSize() {
		return size;
	}

	public LinkedList() {
		header = new Node<M>();
		size = 0;
	}
	
	public LinkedListIterator<M> iterator() {
		return new LinkedListIterator<M>(header);
	}
	
	public boolean isEmpty() {
		if (header == null)
			return true;
		if (header.getLink() == null)
			return true;
		return false;
	}
	
	public void insertAfter(M data, M after) {
		if(isEmpty()) {
			insertFirst(data);
			return;
		}
		Node<M> prevNode = find(after);
		if(prevNode == null) 
			return;
		Node<M> newNode = new Node<M>();
		newNode.setData(data);
		newNode.setLink(prevNode.getLink());
		prevNode.setLink(newNode);
		size++;
	}
	
	public void insertFirst(M data) {
		Node<M> newNode = new Node<M>();
		newNode.setData(data);
	    newNode.setLink(header.getLink());
		header.setLink(newNode);
		size = 1;
	}
	
	public void insert(M data) {
		insertEnd(data);
	}
	
	public void insertEnd(M data) {
		if(isEmpty()) {
			insertFirst(data);
			return;
		}
		Node<M> newNode = new Node<M>();
		newNode.setData(data);
		Node<M> last = findLast();
		last.setLink(newNode);
		size++;
	}
	
	public void remove(M data) {
		if(isEmpty()) 
			return;
		Node<M> prevNode = findPrev(data);
		if(prevNode == null) 
			return;
		Node<M> currentNode = prevNode.getLink();
		prevNode.setLink(currentNode.getLink());
		size--;
	}
	
	public Node<M> find(M data) {
		Node<M> it = header.getLink();
		while(it != null) {
			if(it.getData() == data)
				return it;
			it = it.getLink();
		}
		System.err.println("Cant find " + data);
		return null;
		
	}
	
	public Node<M> findPrev(M data) {
		Node<M> it = header.getLink();
		Node<M> prev = header;
		while(it != null) {
			if(it.getData() == data)
				return prev;
			prev = it;
			it = it.getLink();
		}
		System.err.println("Cant find " + data);
		return null;
		
	}
	
	public Node<M> findLast() {
		Node<M> it = header.getLink();
		while(it.getLink() != null) {
			it = it.getLink();
		}
		return it;
		
	}
	
	public void display() {
		Node<M> n = new Node<M>();
		n = header.getLink();
		while(n.getLink() != null) {
			System.out.println(n.getData());
			n = n.getLink();
		}
		System.out.println(n.getData());
	}
	
	/* 
	 * 
	 * Stack based operations 
	 * 
	 * 
	*/ 
	
	/** Insert new <b>node</b> at end 
	 * @param newNode
	 */
	public void pushNode(Node<M> newNode) {
		if(isEmpty()) {
			newNode.setLink(header.getLink());
			header.setLink(newNode);
			size = 1;
			return;
		}
		Node<M> last = findLast();
		last.setLink(newNode);
		size++;
	}
	
	/* 
	 * 
	 * END Stack based operations 
	 * 
	 * 
	*/ 
	
	public int[] toIntArray() {
		int[] array = new int[size];
		int counter = 0;
		Node<M> n = new Node<M>();
		n = header.getLink();
		while(n.getLink() != null) {
			array[counter++] = (Integer) n.getData();
			n = n.getLink();
		}
		array[counter] = (Integer) n.getData();
		return array;
	}
	
	public static void main(String args[]) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.insertEnd(10);
		ll.insertEnd(11);
		ll.insertEnd(12);
		ll.insertEnd(13);
		ll.insertFirst(9);
		ll.insertEnd(16);
		ll.insertAfter(15, 13);
		ll.remove(13);
		
		ll.display();
	}
}
