class Link
{
	public int iData;
	public double dData;
	public Link next;

	public Link(int id, double dd)
	{
		iData = id;
		dData = dd;
	}

	public void displayLink()
	{
		System.out.print("{"+iData+", "+dData+"} ");
	}
}

class LinkList
{
	private Link first;
	public LinkList()
	{
		first = null;
	}

	public void insertFirst(int id, double dd)//從表頭插入
	{
		Link newLink = new Link(id, dd);//宣告一個新鍊結點
		newLink.next = first;//將原本first得值放入新鏈結點的的next屬性
		first = newLink;//將新鏈結點放入LinkList的first中
	}

	public Link find(int key)
	{
		Link current = first;
		while(current.iData !=key)
		{
			if(current.next == null)
			{
				return null;
			}else{
				current = current.next;
			}
		}
		return current;
	}

	public Link delete(int key)
	{
		Link current = first;
		Link previous = first;
		while(current.iData != key)
		{
			if(current.next == null)
			{
				return null;
			}else{
				previous = current;
				current = current.next;
			}
		}
		if(current == first)
		{
			first = first.next;
		}else{
			previous.next = current.next;
		}
		return current;
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

		System.out.println(" ");
	}
}

class LinkList2Demo
{

	public static void main(String[] args)
	{
		LinkList theList = new LinkList();
		theList.insertFirst(22, 2.99);
		theList.insertFirst(44, 4.99);
		theList.insertFirst(66, 6.99);
		theList.insertFirst(88, 8.99);

		theList.displayList();

		Link f = theList.find(44);
		if(f!= null)
		{
			System.out.println("Found link with key " + f.iData);
		}else{
			System.out.println("Can't find link");
		}

		Link d = theList.delete(66);
		if(d != null)
		{
			System.out.println("Deleted link with key "+ d.iData);
		}else{
			System.out.println("Can't delete link");
		}

		theList.displayList();

	}

}

/*
鏈表是一種廣泛使用的資料結構，他可以被用來當作資料庫，也可以被用來取代陣列作為輔助工具的基礎，
像是堆疊跟隊列。他改善了許多陣列所會發生的缺點。像是在無序陣列中，搜尋的速度比較慢、
在有序陣列中插入的速度比較慢，並且他們在刪除數據時速度都慢。而且陣列的大小在創建後是無法改變的。

一個鍊表是由許多鍊結點所組成，一個鏈結點就是一個物件。鍊表中每一筆數據的連接方式是他們的關係，
而不是他們的位置。在陣列中，如果我們有指標位置，
我們可以很輕易地透過指標位置直接取得該指標位置的數據。但是在鏈表中，如果我們要取得特定數據，
我們必須從表頭開始，透過它包含下一個鏈結點的屬性走向下一個鏈結點，
再透過他連結下一個鏈結點的屬性走向下一個鏈結點，經過他們之中所有的鏈結點到達我們的目標位置。

在範例中，LinkList只有一個屬性就是first，它的作用就是讓我們填入一個鏈結點(Link物件)。
First是這個LinkList(鍊表)的唯一屬性，鏈表中表頭以外的鏈結點都包含在Link中，而不是LinkList裡。

public void insertFirst(int id, double dd)//從表頭插入
{
	Link newLink = new Link(id, dd);//宣告一個新鍊結點
	newLink.next = first;//將原本first得值放入新鏈結點的的next屬性
	first = newLink;//將新鏈結點放入LinkList的first中
}

當我們插入一個新的鏈結點時，我們會將原本的first所指向的Link物件放置新Link的next屬性中，
然後再將鍊表的first屬性指向新的鏈結點(newLink)。

public Link find(int key)
{
	Link current = first;//將原本表頭的鏈結點放入變數current中
	while(current.iData !=key)//如果鏈結點的值不等於我們的目標值就執行迴圈
	{
		if(current.next == null)//當鏈結點的next為null時表示沒有下一個鏈結點了
		{
			return null;//回傳null 表示沒找到
		}else{
			current = current.next;//將下一個鏈結點再放入變數current中執行下一圈迴圈
		}
	}
	return current;//回傳最後的的鏈結點
}

當執行搜尋時我們必須先將屬性first的鏈結點放入變數current中，我們步行直接用first執行迴圈做搜尋，
那會影響first所指向的鏈結點。當變數current所指向的鏈結點得值不等於我們的目標值時我們就執行迴圈。
如果current的next為null時表示這已經是最後一個鏈結點，我們在練表中找不到目標值，所以回傳null。
如果還有下一個鏈結點，那就將下一個鏈結點放入current中執行下一圈迴圈，直到找到值為止。

public Link deleteFirst()
{
	Link temp = first;//將原本的first放入temp變數
	first = first.next;//將原本的first.next放入first變數
	return temp;//回傳雃最原始的first
}

刪除時我們會將原本練表中first的鏈結點放入變數temp中，
接著用原本first中的下一個鏈結點取代原本first得值。
最後回傳我們放入變數temp。

在java中，一個鍊表的表頭並非真的包含他後面所有的物件。
在一個鏈結點指向下一個鏈結點的屬性單單只是引用另一個鏈結點的物件



除非需要經常性的使用指標位址搜尋數據，不然鍊表都是一個好的選擇。
一個鏈結點鐘會有一個屬性指向另一個鏈結點，這個屬性就是下一個鏈結點的指引。




*/




































