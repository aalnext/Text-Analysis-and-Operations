
public class LinkedList<T> {

	private Node<T> head, current, tail;

	public int sizeLL;
	
	public LinkedList() {
		head = current = tail = null;
		
	}
	
	
	public void insert(T val) { 
	
		if(tail==null)
			 current= head = tail = new Node<T>(val);
		else {
			
			tail.next = new Node<T>(val);
			tail = tail.next;
			current= tail;
			
		}
		
	}
	
		
	public boolean empty()  {		
		return head == null;	
	}
	
	public boolean last() {
		return current==null;
	}
	
	public void findFirst() {
		
		current = head;
	}
	
	public Node<T> findHead()  { //needded?
		
		return head;
	}
	
	public void findNext() {
		current = current.next;
	}
	

	
	public T retrieve(){
		return current.data;
	}
	
	public T retrieveFirst(){
		return head.data;
	}
	
	
	public void update (T val) { //remove later
		current.data = val;
	}


	
	
	
}
