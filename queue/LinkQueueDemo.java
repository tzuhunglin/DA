class Link
{
	public long dData;
	public Link next;

	public Link(long d)
	{
		dData = d;
	}

	public void displayLink()
	{
		System.out.print(dData + " ");
	}
}

class FirstLastList
{
	private Link first;
	private Link last;

	public FirstLastList()
	{
		first = null;
		last = null;
	}

	public boolean isEmpty()
	{
		return (first == null);
	}

	public void insertLast(long dd)
	{
		Link newLink = new Link(dd);
		if( isEmpty() )
		{
			first = newLink;
		}
		else
		{
			last.next = newLink;
		}
		last = newLink;
	}

	public long deleteFirst()
	{
		long temp = first.dData;
		if(first.next == null)
		{
			last = null;
		}
		first = first.next;
		return temp;
	}

	public void displayList()
	{
		Link current = first;
		while(current != null)
		{
			current.displayLink();
			current = current.next;
		}
		System.out.println(" ");
	}
}

class LinkQueue
{
	private FirstLastList theList;

	public LinkQueue()
	{
		theList = new FirstLastList();

	}

	public boolean isEmpty()
	{
		return theList.isEmpty();
	}

	public void insert(long j)
	{
		theList.insertLast(j);

	}

	public long remove()
	{
		return theList.deleteFirst();
	}

	public void displayQueue()
	{
		System.out.print("Queue (front-->rear): ");
		theList.displayList();
	}
}

class LinkQueueDemo
{
	public static void main(String[] args)
	{

		LinkQueue theQueue = new LinkQueue();
		theQueue.insert(20);
		theQueue.insert(40);

		theQueue.displayQueue();

		theQueue.insert(60);
		theQueue.insert(80);

		theQueue.displayQueue();

		theQueue.remove();
		theQueue.remove();

		theQueue.displayQueue();

	}
}




/*
這是一個以鍊表實現隊列的例子，也是一個抽象數據類型的例子，就是只注意他做了什麼，
而不考慮他是怎麼做的。在這個隊列中我們可以注意到的是它就是一個隊列，擁有先進先出的特性。
而在這這對列中他是使用雙端鍊錶來實現，當然，他也可以使用陣咧來實現。
但是這對我們來說並不重要。

陣列和鏈表都可以用來實現對列，但是當我們不清楚數據的容量時，練表會是更好的選擇。

*/




























