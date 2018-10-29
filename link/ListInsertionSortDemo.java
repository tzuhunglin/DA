class Link
{
	public long dData;
	public Link next;

	public Link(long dd)
	{
		dData = dd;
	}
}


class SortedList
{
	private Link first;

	public SortedList()
	{
		first = null;
	}

	public SortedList(Link[] linkArr)
	{
		first = null;
		for(int j=0; j<linkArr.length; j++)
		{
			insert(linkArr[j]);
		}
	}

	public void insert(Link k)
	{
		Link previous = null;
		Link current = first;

		while(current != null && k.dData > current.dData)//current!=null表示鏈表不為空，並且key值比結點值大。
		{
			previous = current;//將previous指向現在current所指向的位置
			current = current.next;//再將current指向current.next的位置。此時，原鍊錶被分為兩段
		}

		if(previous==null)
		{
			first = k;
		}
		else
		{
			previous.next = k;//將previous段的next接上NewLink
		}

		k.next = current;//將newLink.next接上current段
	}

	public Link remove()
	{
		Link temp = first;
		first = first.next;
		return temp;
	}
}

class ListInsertionSortDemo
{
	public static void main(String[] args)
	{
		int size = 10;

		Link[] linkArray = new Link[size];

		for(int j=0; j<size; j++)
		{
			int n = (int)(java.lang.Math.random()*99);
			Link newLink = new Link(n);
			linkArray[j] = newLink;
		}

		System.out.print("Unsorted array: ");
		for(int j=0; j<size; j++)
		{
			System.out.print(linkArray[j].dData + " ");
		}
		System.out.println(" ");

		SortedList theSortedList = new SortedList(linkArray);

		for(int j=0; j<size; j++)
		{
			linkArray[j] = theSortedList.remove();
		}

		System.out.print("Sorted Array:  ");
		for(int j=0; j<size; j++)
		{
			System.out.print(linkArray[j].dData + " ");
		}
		System.out.println(" ");
	}
}



/*
有序鍊錶在插入新結點時會依照節點中某項特定的值進行排序。
有序鍊錶在插入新數據時是比有序陣列有優勢的，因為他不需要移動的其他元素，另外，
鍊表不像陣列有長度的限制。有序鍊錶經常使用於優先及隊列以及堆。在這個範例中，
我們將使用有序鍊表作為工具協助排序陣列中的數據。


在這個範例中我們將會生成一組無序陣列然後將無序陣列交給有序練表的類別生成一組有序練表，
再有序鍊錶生成後我們會用刪除節點的方式將節點一個一個返回陣列中取代陣列中原有的數據。
int size = 10;

Link[] linkArray = new Link[size];

for(int j=0; j<size; j++)
{
	int n = (int)(java.lang.Math.random()*99);
	Link newLink = new Link(n);
	linkArray[j] = newLink;
}

System.out.print("Unsorted array: ");

一開始我們需要先生成一組無序Link陣列。

SortedList theSortedList = new SortedList(linkArray);

for(int j=0; j<size; j++)
{
	linkArray[j] = theSortedList.remove();
}

System.out.print("Sorted Array:  ");

無序陣列生成後我們將它用來呼叫有序鏈結類別SortedList，呼叫完成後物件theSortedList已經成為一組有序鏈結，
鏈結中各節點的值都對應陣列中節點的值。然後我們會用刪除的方式依序將鏈表中各節點取代陣列中原有的節點讓陣列變為有序陣列。

public void insert(Link k)
{
	Link previous = null;
	Link current = first;

	while(current != null && k.dData > current.dData)//current!=null表示鏈表不為空，並且key值比結點值大。
	{
		previous = current;//將previous指向現在current所指向的位置
		current = current.next;//再將current指向current.next的位置。此時，原鍊錶被分為兩段
	}

	if(previous==null)
	{
		first = k;
	}
	else
	{
		previous.next = k;//將previous段的next接上NewLink
	}

	k.next = current;//將newLink.next接上current段
}

insert為我們鍊表中插入新結點並排序的函式，在函式中我們會需要兩個變數previous跟current來協助我們進行下面的while迴圈。
previous預設為null，current預設為first，如果鍊錶為空，first的值也會為空。
當while迴圈進行時，我們會先判斷current != null && k.dData > current.dData(練表不為空，並且新數據大於current的數據)。
我們要做的事升冪排列，所以我們比須一直找到比新數據大的值才停下。當還沒找到比新數據大的值時，
我們需要將變數previous指向現在current所指向的位置，再將current指向current.next的位置。此時，我們可以將鍊錶看作為兩段
一段為previous段，此段的最後一個節點就是previous。一段為current段，此段的第一個節點為current。
當我們找到了新結點的位置時，我們會將previous段和current段分別皆在新結點的前後形成有序鍊錶。




有序鍊表再插入或著刪除特定項數據需要O(N)的時間，因為他需要搜尋平均一半的鍊錶節點數量。
但是在找到最小項或著刪除最小項只需要O(N)的時間。如果我們經常的需要最小的項目，
並且不需要經常地插入新數據，有序鍊錶會是一個好的選擇，例如堆和優先級隊列。

*/










































