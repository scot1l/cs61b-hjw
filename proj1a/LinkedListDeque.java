
public class LinkedListDeque<T> {
	private static class DequeNode<T>{
		public T item;
		public DequeNode<T> prev;
		public DequeNode<T> next;

		DequeNode(T item){
			this.item = item;
			prev = null;
			next = null;
		}
	}



	private DequeNode<T> sentinel;
	private int size;

	public LinkedListDeque(){
		sentinel = new DequeNode<T>(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	public LinkedListDeque(LinkedListDeque other){
		sentinel = new DequeNode<T>(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
		DequeNode<T> temp = other.sentinel;
		while (temp.next!=other.sentinel){
			temp = temp.next;
			this.addLast(temp.item);
		}
	}

	private T getRecursive(int index,DequeNode<T> p) {
		if(index >size-1) return null;
		if(index ==0) {
			return p.next.item;
		}
		return getRecursive(index-1,p.next);
	}

	public T getRecursive(int index){
		return getRecursive(index,sentinel);
	}

	public void addFirst(T item){
		size++;
		DequeNode<T> newNode = new DequeNode<>(item);
		newNode.prev = sentinel;
		newNode.next = sentinel.next;
		newNode.next.prev = newNode;
		sentinel.next = newNode;
	}

	public void addLast(T item){
		size++;
		DequeNode<T> newNode = new DequeNode<>(item);
		newNode.next = sentinel;
		newNode.prev = sentinel.prev;
		sentinel.prev.next = newNode;
		sentinel.prev = newNode;
	}

	public boolean isEmpty(){
		return size==0;
	}

	public int size(){
		return size;
	}

	public void printDeque(){
		DequeNode<T> temp = sentinel;
		while (temp.next!=sentinel){
			temp = temp.next;
			System.out.print(temp.item + " ");
		}
		System.out.println();
	}

	public T removeFirst(){
		if(isEmpty()) return null;
		size--;
		T temp = sentinel.next.item;
		sentinel.next.next.prev = sentinel;
		sentinel.next = sentinel.next.next;
		return temp;
	}
	public T removeLast(){
		if(isEmpty()) return null;
		size--;
		T temp = sentinel.prev.item;
		sentinel.prev.prev.next = sentinel;
		sentinel.prev = sentinel.prev.prev;
		return temp;
	}
	public T get(int index){
		DequeNode<T> temp = sentinel.next;
		while (index>0){
			temp = temp.next;
			index--;
		}
		return temp.item;
	}

}
