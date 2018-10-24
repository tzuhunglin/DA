class Link
{
	public long dData;
	public Link next;

	public Link(long dd)
	{
		dData = dd;
	}

	public void displayLink()
	{
		System.out.print(dData + " ");
	}
}

class LinkList
{
	private Link first;

	public LinkList()
	{
		first = null;
	}

	public boolean isEmpty()
	{
		return (first == null);
	}

	public void insertFirst(long dd)
	{
		Link newLink = new Link(dd);
		newLink.next = first;
		first = newLink;
	}

	public long deleteFirst()
	{
		Link temp = first;
		first = first.next;
		return temp.dData;
	}

	public void displayList()
	{
		Link current = first;
		while(current != null)
		{
			current.displayLink();
			current = current.next;
		}

		System.out.println("");
	}
}

class LinkStack
{
	private LinkList theList;

	public LinkStack()
	{
		theList = new LinkList();
	}

	public void push(long j)
	{
		theList.insertFirst(j);
	}

	public long pop()
	{
		return theList.deleteFirst();
	}

	public boolean isEmpty()
	{
		return (theList.isEmpty());
	}

	public void displayStack()
	{
		System.out.print("Stack (top-->bottom): ");
		theList.displayList();
	}
}

class LinkStackDemo
{
	public static void main(String[] args)
	{
		LinkStack theStack = new LinkStack();
		theStack.push(20);
		theStack.push(40);

		theStack.displayStack();

		theStack.push(60);
		theStack.push(80);

		theStack.displayStack();

		theStack.pop();
		theStack.pop();

		theStack.displayStack();
	}
}

/*
這是一個以鍊表實現堆疊的例子，也是一個抽象數據類型的例子，就是只注意他做了什麼，
而不考慮他是怎麼做的。
*/





























