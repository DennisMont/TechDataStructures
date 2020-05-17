package com.datastructures;

public class LinkedList {
	class Node {
		int value;
		Node next = null;
		Node(int value) {
			this.value = value;
		}
	}
	
	public Node head = null;
	public Node tail = null;
	
	public void addToFront(int value) {
		Node newNode = new Node(value);
		newNode.next = head;
		head = newNode;
		if(newNode.next == null) {
			tail = newNode;
		}
	}
	
	public void addToBack(int value) {
		if(tail == null) {
			this.addToFront(value);
		}
		else
		{
			Node newNode = new Node(value);
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	public void addAtIndex(int value, int index) {
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			this.addToFront(value);
		} else {
			Node newNode = new Node(value);
			Node current = head;
			for (int i = 0; i < index -1; i++) {
				if (current == null) {
					throw new IndexOutOfBoundsException();
				}
				current = current.next;
			}
			if (current.next == null) {
				tail.next = newNode;
				tail = newNode;
			} else {
				newNode.next = current.next;
				current.next = newNode;
			}
		}
	}
	
	public boolean contains(int value) {
		Node current = head;
		while(current != null) {
			if(current.value == value) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	public int getByIndex(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			Node current = head;
			for(int i = 0; i < index; i++) {
				if(current == null || current.next == null) {
					throw new IndexOutOfBoundsException();
				}
				current = current.next;
			}
			return current.value;
		}
	}
	
	public void removeFromFront() {
		if (head != null) {
			head = head.next;
		}
		if (head == null) {
			tail = null;
		}
	}
	
	public void removeFromBack() {
		if (head == null) {	//Empty list
			return;
		} else if (head == tail) {	//Single element list
			head = null;
			tail = null;
		} else {
			Node current = head;
			while (current != tail) {
				current = current.next;
			}
			tail = current;
			current.next = null;
		}
	}
	
	public void removeAtIndex(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			this.removeFromFront();
		} else {
			Node current = head;
			for (int i = 0; i > index -1; i++) {
				if(current == null || current.next == null) {
					throw new IndexOutOfBoundsException();
				}
				current = current.next;
			}
			if (current.next == null) {
				throw new IndexOutOfBoundsException();
			}
			current.next = current.next.next;
		}
	}
}
