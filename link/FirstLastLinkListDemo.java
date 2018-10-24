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
		System.out.println(dData + " ");
	}
}

class FirstLastLinkList
{
	private Link first;//鍊表表頭
	private Link last;//鍊表表尾

	public FirstLastLinkList()
	{
		first = null;//預設為null
		last = null;//預設為null
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public void insertFirst(long dd)
	{
		Link newLink = new Link(dd);

		if(isEmpty())//如果鍊錶為空，將表頭及表尾都指向新數據
		{
			last = newLink;
		}
		newLink.next = first;//將first的值放入新結點的next
		first = newLink;//將first指向新結點
	}

	public void insertLast(long dd)
	{
		Link newLink = new Link(dd);

		if(isEmpty())//如果鍊錶為空，將表頭及表尾都指向新數據
		{
			first = newLink;
		}
		else
		{
			last.next = newLink;//將新結點放入表尾的next
		}

		last = newLink;//將last指向新結點
	}

	public long deleteFirst()
	{
		long temp = first.dData;//將表頭數據放入變數temp
		if(first.next == null)//如果first的next為null，表示鍊錶只有一筆數據，刪除後first跟last都為空
		{
			last = null;
		}

		first = first.next;//將first的next的值放入first
		return temp;
	}

	public void displayList()
	{
		System.out.println("List (first-->last): ");
		Link current = first;
		while(current != null)
		{
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}

class FirstLastLinkListDemo
{
	public static void main(String[] args)
	{
		FirstLastLinkList theList = new FirstLastLinkList();

		theList.insertFirst(22);
		theList.insertFirst(44);
		theList.insertFirst(66);

		theList.insertLast(11);
		theList.insertLast(33);
		theList.insertLast(55);

		theList.displayList();

		theList.deleteFirst();

		theList.deleteFirst();
		theList.displayList();


	}
}

/*
雙端鍊表比普通鍊表多增加了一個特性，就是對於表尾的操作。
我們現在可以用簡單快速的方式在婊尾多增加一個節點。
*/


































