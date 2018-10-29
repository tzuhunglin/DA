class Link
{
	public long dData;
	public Link next;
	public Link previous;

	public Link(long d)
	{
		dData = d;
	}

	public void displayLink()
	{
		System.out.print(dData + " ");
	}
}

class DoublyLinkedList
{
	private Link first;
	private Link last;

	public DoublyLinkedList()
	{
		first = null;
		last = null;
	}

	public boolean isEmpty()
	{
		return (first ==null);
	}

	public void insertFirst(long dd)
	{
		Link newLink = new Link(dd);
		if(isEmpty())//當鍊表為空時
		{
			last = newLink;//新結點為唯一結點，所以將last也指向新結點
		}
		else//當鍊表不為空時
		{
			first.previous = newLink;//將first所指向物件的previous屬性指向新結點
		}

		newLink.next = first;//將新結點所指向的物件的next屬性指向原本first所指向的物件
		first = newLink;//將first指向新結點
	}

	public void insertLast(long dd)
	{
		Link newLink = new Link(dd);
		if(isEmpty())//當鍊表為空時
		{
			first = newLink;//新結點為唯一結點，所以將first也指向新結點
		}
		else//當鍊表不為空時
		{
			last.next = newLink;//將last所指向物件的next屬性指向新結點
			newLink.previous = last;//將新結點的previous屬性指向原本last所指向的物件
		}

		last = newLink;//將last指向新結點
	}

	public Link deleteFirst()
	{
		Link temp = first;//將temp指向原本first所指向的物件

		if(first.next == null)//如果first所指向物件的next屬性為null(沒有結點)
		{
			last = null;//將last設為null
		}
		else//如果first所指向物件的next屬性不為null(有結點)
		{
			first.next.previous = null;//將first所指向的物件的next屬性所指向的的物件的previous屬性設為null
		}

		first = first.next;//將first指向原本first所指向物件的next屬性所指向的物件
		return temp;//回傳指向原本first所指向的物件
	}

	public Link deleteLast()
	{
		Link temp = last;//將temp指向原本last所指向的物件
		if(first.next == null)//如果first所指向物件的next屬性為null(沒有結點)
		{
			first = null;//將first設為null
		}
		else//如果first所指向物件的next屬性不為null(有結點)
		{
			last.previous.next = null;//將last所指向的物件的previous屬性所指向的的物件的next屬性設為null
		}
		last = last.previous;//將last指向原本last所指向物件的previousㄒ屬性所指向的物件
		return temp;//回傳指向原本last所指向的物件
	}

	public boolean insertAfter(long key, long dd)
	{
		Link current = first;//將current指向first所指向的物件
		while(current.dData != key)	//當還沒找到目標節點時迴圈繼續執行
		{
			current = current.next;//將current指向current.next所指向的物件
			if(current == null)//一直找到最後一個節點的next
			{
				return false;//返回錯誤
			}
		}

		Link newLink = new Link(dd);//建立新節點

		if(current == last)//如果current所指向的物件為last所指向的物件
		{
			newLink.next = null;//新節點將被last所指向，他的next設為null
			last = newLink;//將last指向新結點物件
		}
		else//如果current不為last所指向的物件
		{
			newLink.next = current.next;//新結點的next屬性指向current的next屬性所指向的物件

			current.next.previous = newLink;//將current所指向物件的next屬性所指向物件的previous屬性指向新結點
		}

		newLink.previous = current;//將新結點的previous屬性指向current所指向的物件
		current.next = newLink;//將current的next屬性指向心結點
		return true;
	}

	public Link deleteKey(long key)
	{
		Link current = first;//將current指向first所指向的物件
		while(current.dData != key)//當還沒找到目標節點時迴圈繼續執行
		{
			current = current.next;//將current指向current.next所指向的物件
			if(current == null)//一直找到最後一個節點的next
			{
				return null;//返回錯誤
			}
		}

		if(current==first)//如果current所指向的物件為first所指向的物件
		{
			first = current.next;//將first指向current所指向物件的next屬性所指向的物件
		}
		else//如果current所指向的物件不為first所指向的物件
		{
			current.previous.next = current.next;//將current所指向物件的previous屬性所指向的物件的next屬性指向current所指向物件的next屬性所指向的物件
		}

		if(current==last)//如果current所指向的物件為last所指向的物件
		{
			last = current.previous;//將last指向current所指向物件的previous屬性所指向的物件
		}
		else//如果current所指向的物件不為last所指向的物件
		{
			current.next.previous = current.previous;//將current所指向物件的next屬性所指向物件的previous屬性指向將current所指向物件的previous屬性所指向的物件
		}

		return current;//返回current節點
	}
	public void displayForward()
	{
		System.out.print("List (first-->last): ");
		Link current = first;
		while(current !=null)
		{
			current.displayLink();
			current = current.next;
		}

		System.out.println("");
	}

	public void displayBackward()
	{
		System.out.print("List (last-->first): ");
		Link current = last;
		while(current != null)
		{
			current.displayLink();
			current = current.previous;
		}
		System.out.println("");
	}
}
class DoublyLinkedDemo
{
	public static void main(String[] args)
	{
		DoublyLinkedList theList = new DoublyLinkedList();

		theList.insertFirst(22);
		theList.insertFirst(44);
		theList.insertFirst(66);

		theList.insertLast(11);
		theList.insertLast(33);
		theList.insertLast(55);

		theList.displayForward();
		theList.displayBackward();

		theList.deleteFirst();
		theList.deleteLast();
		theList.deleteKey(11);

		theList.displayForward();


		theList.insertAfter(22, 77);
		theList.insertAfter(33, 88);

		theList.displayForward();



	}

}




/*
無論是傳統的鍊表還是雙端鍊表他們都可以讓指標很輕易的從一個節點到達下一個節點，
但是如果需要讓指標從一個節點一動到上一個節點就必須讓指標再從first的位置從新遍歷，
一步一步的移動到原本目標結點的上一層。
這種從first開始查找方式效率非常低也讓某些鍊錶的應用上出現問題。

雙端鍊表提供了向上遍歷的功能，就是讓每個節點增加了一個屬性previous，
讓一個節點可以向上做連結，讓㽪個鍊表可以由last的位置向上遍歷至first的位置。

雙端鍊錶的缺點是在插入或著是刪除一個節點時需要處理四個引用(傳統鍊錶為兩個)，兩個連接前一個節點，
兩個連接後一個節點。當然，在處理更多引用的狀況下，鍊結點所佔的空間也更大了。

public DoublyLinkedList()
{
	first = null;
	last = null;
}

雙項鍊表和雙端鍊表一樣，我們會有兩個屬性first和last。一個指向表頭，一個指向表尾。

public void insertFirst(long dd)
{
	Link newLink = new Link(dd);
	if(isEmpty())//當鍊表為空時
	{
		last = newLink;//新結點為唯一結點，所以將last也指向新結點
	}
	else//當鍊表不為空時
	{
		first.previous = newLink;//將first所指向物件的previous屬性指向新結點
	}

	newLink.next = first;//將新結點所指向的物件的next屬性指向原本first所指向的物件
	first = newLink;//將first指向新結點
}

insertFirst為由表頭插入新結點的函式，一開始我們先宣告一個新結點。
接著判斷鍊表是否為空，如果鍊表為空，那表示我們需要將last也指向這個唯一的節點。
如果鍊表不為空，我們就將原本first所指向物件的previous屬性指向新結點，
讓新結點排在first所指向物件的上一層。
接著再將新結點的next屬性指向first所指向的物件。
最後我們要將first指向新節點。

public void insertLast(long dd)
{
	Link newLink = new Link(dd);
	if(isEmpty())//當鍊表為空時
	{
		first = newLink;//新結點為唯一結點，所以將first也指向新結點
	}
	else//當鍊表不為空時
	{
		last.next = newLink;//將last所指向物件的next屬性指向新結點
		newLink.previous = last;//將新結點的previous屬性指向原本last所指向的物件
	}

	last = newLink;//將last指向新結點
}

insertLast為由表尾插入新結點的函式，一開始我們先宣告一個新結點。
接著判斷鍊表是否為空，如果鍊表為空，那表示我們需要將first也指向這個唯一的節點。
如果鍊表不為空，我們就將原本last所指向物件的next屬性指向新結點，
讓新結點排在last所指向物件的下一層。
接著再將新結點的previous屬性指向last所指向的物件。
最後我們要將last指向新節點。

public Link deleteFirst()
{
	Link temp = first;//將temp指向原本first所指向的物件

	if(first.next == null)//如果first所指向物件的next屬性為null(沒有結點)
	{
		last = null;//將last設為null
	}
	else//如果first所指向物件的next屬性不為null(有結點)
	{
		first.next.previous = null;//將first所指向的物件的next屬性所指向的的物件的previous屬性設為null
	}

	first = first.next;//將first指向原本first所指向物件的next屬性所指向的物件
	return temp;//回傳指向原本first所指向的物件
}

deleteFirst為刪除表頭節點的函式，首先我們先將temp指向原本first所指向的物件。
接著判斷first的下一層是否為空，如果下一層為空則表示目前只有一個節點。
刪除後鍊錶就通通為空，所以將last設為null。
如果first的下一層還有節點，就將下一層的previous設為null(因為first要被刪除)。
最後用原本first下一層的物件取代原本的first，再回傳被刪除的節點。

public Link deleteLast()
{
	Link temp = last;//將temp指向原本last所指向的物件
	if(first.next == null)//如果first所指向物件的next屬性為null(沒有結點)
	{
		first = null;//將first設為null
	}
	else//如果first所指向物件的next屬性不為null(有結點)
	{
		last.previous.next = null;//將last所指向的物件的previous屬性所指向的的物件的next屬性設為null
	}
	last = last.previous;//將last指向原本last所指向物件的previousㄒ屬性所指向的物件
	return temp;//回傳指向原本last所指向的物件
}

deleteLast為刪除表尾節點的函式，首先我們先將temp指向原本last所指向的物件。
接著判斷first的下一層是否為空，如果下一層為空則表示目前只有一個節點。
刪除後鍊錶就通通為空，所以將last設為null。
如果first的下一層還有節點，
就將last下一層節點的previous屬性設為null(因為last要被刪除)。
最後用原本last上一層的物件取代原本的last，再回傳被刪除的節點。

public boolean insertAfter(long key, long dd)
{
	Link current = first;//將current指向first所指向的物件
	while(current.dData != key)	//當還沒找到目標節點時迴圈繼續執行
	{
		current = current.next;//將current指向current.next所指向的物件
		if(current == null)//一直找到最後一個節點的next
		{
			return false;//返回錯誤
		}
	}

	Link newLink = new Link(dd);//建立新節點

	if(current == last)//如果current所指向的物件為last所指向的物件
	{
		newLink.next = null;//新節點將被last所指向，他的next設為null
		last = newLink;//將last指向新結點物件
	}
	else//如果current不為last所指向的物件
	{
		newLink.next = current.next;//新結點的next屬性指向current的next屬性所指向的物件

		current.next.previous = newLink;//將current所指向物件的next屬性所指向物件的previous屬性指向新結點
	}

	newLink.previous = current;//將新結點的previous屬性指向current所指向的物件
	current.next = newLink;//將current的next屬性指向心結點
	return true;
}

insertAfter的作用在於在特定結點後插入新的節點。首先，我們會先找到新結點所要被插入的位置，
找到後我們可以將這個功能視為將鍊錶拆為兩段，分別為previous段跟current段。
previous所指向的結點就是鏈表前段的最後一個節點。
current所指向的就是鍊錶後段的第一個節點。
最後的步驟就是將前後兩段分別與新結點連上。




public Link deleteKey(long key)
{
	Link current = first;//將current指向first所指向的物件
	while(current.dData != key)//當還沒找到目標節點時迴圈繼續執行
	{
		current = current.next;//將current指向current.next所指向的物件
		if(current == null)//一直找到最後一個節點的next
		{
			return null;//返回錯誤
		}
	}

	if(current==first)//如果current所指向的物件為first所指向的物件
	{
		first = current.next;//將first指向current所指向物件的next屬性所指向的物件
	}
	else//如果current所指向的物件不為first所指向的物件
	{
		current.previous.next = current.next;//將current所指向物件的previous屬性所指向的物件的next屬性指向current所指向物件的next屬性所指向的物件
	}

	if(current==last)//如果current所指向的物件為last所指向的物件
	{
		last = current.previous;//將last指向current所指向物件的previous屬性所指向的物件
	}
	else//如果current所指向的物件不為last所指向的物件
	{
		current.next.previous = current.previous;//將current所指向物件的next屬性所指向物件的previous屬性指向將current所指向物件的previous屬性所指向的物件
	}

	return current;//返回current節點
}

deleteKey函式的功能是在練表中刪除特定的節點，首先，我們會先找到我們所要刪除的節點。
找到之後，如果節點為first或last所指向的節點，我們會需要將這兩個變數從新指向到新結點的位置。
如果要刪除的節點不為first或last所指向的節點，那麼我們就需要調整目標節點上一層和下一層的節點。
將他們兩個節點的previous和next屬性互相做連結。

*/











































