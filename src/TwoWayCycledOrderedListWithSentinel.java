
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

	private class Element{
		public Element(E e) {
			//TODO
			this.object = e;
		}
		public Element(E e, Element next, Element prev) {
			//TODO
			this.object = e;
			this.next = next;
			this.prev = prev;
		}
		// add element e after this
		public void addAfter(Element elem) {
			//TODO
			this.getNext().setPrev(elem);
			elem.setNext(this.getNext());
			this.setNext(elem);
			elem.setPrev(this);
			size++;
		}
		// assert it is NOT a sentinel
		public void remove() {
			//TODO
			if(this != sentinel) {
				this.getPrev().setNext(this.getNext());
				this.getNext().setPrev(this.getPrev());
			}
		}
		E object;
		Element next=null;
		Element prev=null;


		public E getValue() {return object;}

		public void setObject(E object) {this.object = object;}

		public Element getNext() {return next;}

		public void setNext(Element next) {this.next = next;}

		public Element getPrev() {return prev;}

		public void setPrev(Element prev) {this.prev = prev;}

	}


	Element sentinel;
	int size;

	private class InnerIterator implements Iterator<E>{
		//TODO
		Element elem = sentinel;
		public InnerIterator() {
			//TODO
		}
		@Override
		public boolean hasNext() {
			//TODO
			return elem.getNext()!=sentinel;
		}

		@Override
		public E next() {
			//TODO
			elem = elem.getNext();
			return elem.getValue();
		}
	}

	private class InnerListIterator implements ListIterator<E>{
		//TODO
		boolean wasNext=false;
		boolean wasPrevious=false;
		Element elem=sentinel;
		public InnerListIterator() {
			//TODO
		}
		@Override
		public boolean hasNext() {
			//TODO
			return elem.getNext()!=sentinel;
		}

		@Override
		public E next() {
			//TODO
			wasNext=true;
			wasPrevious=false;
			elem=elem.getNext();
			return elem.getValue();
		}
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean hasPrevious() {
			//TODO
			return elem!=sentinel;
		}
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public E previous() {
			//TODO
			wasNext=false;
			wasPrevious=true;
			E retValue=elem.getValue();
			elem=elem.getPrev();
			return retValue;
		}
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
	public TwoWayCycledOrderedListWithSentinel() {
		//TODO
		sentinel = new Element(null);
		sentinel.setNext(sentinel);
		sentinel.setPrev(sentinel);
	}

	//@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		//TODO
		Element newElem = new Element(e);
		if(e instanceof Link) {
			Link actLink;
			Element elem = sentinel;
			while(elem.getNext()!=sentinel) {
				actLink = (Link) elem.getNext().getValue();
				if(((Link) e).compareTo(actLink) < 0)
				{
					elem.addAfter(newElem);
					return true;
				}
				elem = elem.getNext();
			}
		}
		sentinel.getPrev().addAfter(newElem);
		return true;
	}

	private Element getElement(int index) {
		//TODO
		if(index<0 || index > size)
			throw new NoSuchElementException();
		Element elem=sentinel.getNext();
		int counter=0;
		while(elem!=sentinel && counter<index){
			counter++;
			elem=elem.getNext();}
		if(elem==sentinel)
			throw new NoSuchElementException();
		return elem;
	}

	public Element getElement(E obj) {
		//TODO
		Element elem=sentinel.getNext();
		while(elem!=sentinel && !obj.equals(elem.getValue())){
			elem=elem.getNext();}
		if(elem==sentinel)
			return null;
		return elem;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clear() {
		//TODO
		sentinel.setNext(sentinel);
		sentinel.setPrev(sentinel);
	}

	@Override
	public boolean contains(E element) {
		//TODO
		return indexOf(element)!=-1;
	}

	@Override
	public E get(int index) {
		//TODO
		Element elem=getElement(index);
		return elem.getValue();
	}


	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		//TODO
		Element elem = sentinel.getNext();
		int counter=0;
		while(elem!=sentinel && !elem.getValue().equals(element)){
			counter++;
			elem = elem.getNext();}
		if(elem==sentinel)
			return -1;
		return counter;    }

	@Override
	public boolean isEmpty() {
		//TODO
		return sentinel.getNext()==sentinel;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
	}

	@Override
	public E remove(int index) {
		//TODO
		Element elem = getElement(index);
		elem.remove();
		return elem.getValue();
	}

	@Override
	public E remove(E e) {
		//TODO
		Element elem = getElement(e);
		if(elem==null)
			return null;
		E temp = elem.getValue();
		elem.remove();
		return temp;

	}

	@Override
	public int size() {
		//TODO
		Element elem = sentinel.getNext();
		int counter = 0;
		while(elem!=sentinel){
			counter++;
			elem=elem.getNext();
		}
		return counter;
	}

	//@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
		//TODO
		if(this != other)
		{
			Element actElem = this.sentinel;
			Element newElem = other.sentinel.getNext();
			Element tmpElem;
			Link actLink;
			Link newLink;
			while(newElem!=other.sentinel)
			{
				actLink = (Link) actElem.getNext().getValue();
				newLink = (Link) newElem.getValue();
				tmpElem = newElem.getNext();
				if(actElem.getNext()==this.sentinel)
				{
					newElem.getNext().setPrev(newElem.getPrev());
					newElem.getPrev().setNext(newElem.getNext());
					actElem.addAfter(newElem);
					newElem = tmpElem;
				}
				else if(actLink.compareTo(newLink) > 0)
				{
					newElem.getNext().setPrev(newElem.getPrev());
					newElem.getPrev().setNext(newElem.getNext());
					actElem.addAfter(newElem);
					newElem = tmpElem;
				}
				actElem = actElem.getNext();
			}
		}
	}
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {
		//TODO
		Element elem = sentinel.getNext();
		while(elem != sentinel)
		{
			if(elem.getValue().equals(e))
				elem.remove();
			elem = elem.getNext();
		}
	}

}