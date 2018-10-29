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
		System.out.println(dData + " ");
	}
}

class SortedList
{
	private Link first;

	public SortedList()
	{
		first = null;
	}

	public boolean isEmpty()
	{
		return (first==null);
	}

	public void insert(long key)
	{
		Link newLink = new Link(key);
		Link previous = null;
		Link current = first;

		while(current != null && key > current.dData)//current!=null表示鏈表不為空，並且key值比結點值大。
		{
			previous = current;//將previous指向現在current所指向的位置
			current = current.next;//再將current指向current.next的位置。此時，原鍊錶被分為兩段
		}

		if(previous==null)
		{
			first = newLink;
		}
		else
		{
			previous.next = newLink;//將previous段的next接上NewLink
		}
		newLink.next = current;//將newLink.next接上current段
	}

	public Link remove()
	{
		Link temp = first;
		first = first.next;
		return temp;
	}

	public void displayList()
	{
		System.out.print("List (first-->last): ");
		Link current = first;
		while(current != null)
		{
			current.displayLink();
			current = current.next;
		}
		System.out.println("");

	}
}

class SortedListDemo
{
	public static void main(String[] args)
	{
		SortedList theSortedList = new SortedList();
		theSortedList.insert(20);
		theSortedList.insert(40);
		theSortedList.displayList();
		theSortedList.insert(10);
		theSortedList.insert(30);
		theSortedList.insert(50);

		theSortedList.displayList();
		theSortedList.displayList();

	}
}


/*
有序鍊錶的儲存方式為鍊表，但在插入新結點時會依照節點中某項特定的直進行排序。
有序鍊錶再插入新數據時是比有序陣列有優勢的，因為他不需要移動的其他元素，另外，
鍊表不像陣列有長度的限制。有序鍊錶經常使用於優先及隊列以及堆。

有序鍊表再插入或著刪除特定項數據需要O(N)的時間，因為他需要搜尋平均一半的鍊錶節點數量。
但是在找到最小項或著刪除最小項只需要O(N)的時間。如果我們經常的需要最小的項目，
並且不需要經常地插入新數據，有序鍊錶會是一個好的選擇，例如堆汗優先級隊列。


*/


























