import java.io.*;

class Node
{
	private int iData;
	public Node(int key)
	{
		iData = key;
	}

	public int getKey()
	{
		return iData;
	}

	public void setKey(int id)
	{
		iData = id;
	}
}

class Heap
{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public Heap(int mx)
	{
		maxSize = mx;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}

	public boolean isEmpty()
	{
		return currentSize==0;
	}

	public boolean insert(int key)
	{
		if(currentSize==maxSize)//當堆陣列已滿
		{
			return false;//回傳false
		}

		Node newNode = new Node(key);//宣告新結點
		heapArray[currentSize] = newNode;//將新結點放入堆陣列最後一位
		trickleUp(currentSize++);//將節點移動到適當的位置
		return true;
	}

	public void trickleUp(int index)
	{
		int parent = (index-1)/2;//找到父節點的位置
		Node bottom = heapArray[index];//將bottom設為新結點
		while(index>0 && heapArray[parent].getKey()< bottom.getKey())//當指標大0並且父結點的值小於子結點
		{
			heapArray[index] = heapArray[parent];//將父節點的值放入子結點的位置
			index = parent;//將index的值設為父結點的指標
			parent = (parent-1)/2;//找尋下一個父節點
		}
		heapArray[index] = bottom;//將新結點放入最後的合適位置
	}
	public Node remove()
	{
		Node root = heapArray[0];//將堆陣列中根結點位置的值放入變數root裡面
		heapArray[0] = heapArray[--currentSize];//將堆陣列中最後一個節點放入根節點的位置
		trickleDown(0);//調整堆中結點的位置
		return root;//回傳刪除的節點
	}

	public void trickleDown(int index)
	{
		int largerChild;
		Node top = heapArray[index];//將根結點位置的直放入變數top
		while(index<currentSize/2)//當指標未到達葉節點階層時執行
		{
			int leftChild = 2*index+1;
			int rightChild = leftChild+1;

			if(rightChild < currentSize && heapArray[leftChild].getKey()<heapArray[rightChild].getKey())
			//當右子節點存在並且右子節點大於左子結點
			{
				largerChild = rightChild;
			}
			else
			//當rightChild == currentSize 表示右子節點不存在。
			{
				largerChild = leftChild;//將largerChild設為左子結點
			}

			if(top.getKey()>= heapArray[largerChild].getKey())
			{
				break;
			}

			heapArray[index] = heapArray[largerChild];//將比較大的子結點放入父傑點的位置
			index = largerChild;//將index設為largerChild的值
		}
		heapArray[index] = top;//將top中的節點放入適當的位置
	}

	public boolean change(int index, int newValue)
	{
		if(index<0 || index>currentSize)
		{
			return false;
		}

		int oldValue = heapArray[index].getKey();
		heapArray[index].setKey(newValue);

		if(oldValue<newValue)
		{
			trickleUp(index);
		}
		else
		{
			trickleDown(index);
		}
		return true;
	}


	public void displayHeap()
	{
		System.out.print("heapArray: ");
		for(int m=0; m<currentSize;m++)
		{
			if(heapArray[m]!=null)
			{
				System.out.print(heapArray[m].getKey()+ " ");
			}
			else
			{
				System.out.print("-- ");
			}
		}
		System.out.println();

		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "------------------------";
		System.out.println(dots+dots);

		while(currentSize>0)
		{
			if(column == 0)
			{
				for(int k=0; k<nBlanks; k++)
				{
					System.out.print(" ");
				}
			}

			System.out.print(heapArray[j].getKey());
			if(++j == currentSize)
			{
				break;
			}

			if(++column == itemsPerRow)
			{
				nBlanks /=2;
				itemsPerRow *=2;
				column = 0;
				System.out.println();
			}
			else
			{
				for(int k=0; k<nBlanks*2-2; k++)
				{
					System.out.print(' ');
				}
			}
		}
		System.out.println("\n"+dots+dots);
	}
}

class HeapDemo
{
	public static void main(String[] args) throws IOException
	{
		int value, value2;
		Heap theHeap = new Heap(31);
		boolean success;

		theHeap.insert(70);
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);

		while(true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, remove, change:");
			int choice = getChar();

			switch(choice)
			{
				case 's':
					theHeap.displayHeap();
					break;
				case 'i':
					System.out.print("Enter value to insert:");
					value = getInt();
					success = theHeap.insert(value);
					if(!success)
					{
						System.out.println("Can't insert; heap full");
					}
					break;
				case 'r':
					if(!theHeap.isEmpty())
					{
						theHeap.remove();
					}
					else
					{
						System.out.println("Can't remove; heap empty");
					}
					break;
				case 'c':
					System.out.print("Enter current index of item:");
					value = getInt();
					System.out.print("Enter new key:");
					value2 = getInt();
					success = theHeap.change(value, value2);
					if(!success)
					{
						System.out.println("Invalid index");
					}
					break;
				default:
					System.out.println("Invalid entry\n");
			}
		}
	}

	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
}

/*
堆是一種實現優先及隊列的一種結構，他在概念上是一種特殊的二叉樹，首先他在概念上是完全二叉樹，完全二叉樹就是樹中除了最後一層以外，
所有的節點都是滿的，而且在最後一層裡，空節點只會在右邊的部分連續出現，也就是所有的連續節點之中不會出現空節點。第二，
堆中的每一個節點都會大於(或等於)她子節點的關鍵字。最後，堆經常都使用陣列來實現。

堆是一種弱序的資料結構(和二叉搜索樹相比)。在堆中要有序地遍歷所有節點是有困難的，因為在堆的規則中，
他只要求每一條從根結點到葉節點的路徑都是降冪排列。一個指定節點的左右或是上下節點，因為不在同一條路徑上，
他的值可能會比指定節點或大或或小，這些路徑除了擁有同樣的節點之外，他們都是互相獨立的。

public boolean insert(int key)
{
	if(currentSize==maxSize)//當堆陣列已滿
	{
		return false;//回傳false
	}

	Node newNode = new Node(key);//宣告新結點
	heapArray[currentSize] = newNode;//將新結點放入堆陣列中空格的第一位
	trickleUp(currentSize++);//將節點移動到適當的位置
	return true;
}

堆在插入新結點時一律先將新結點放入堆陣列中的空格的第一個位置，再將新結點放置堆陣列後再進行trickleUp(向上篩選)排入適當的位置。

public void trickleUp(int index)
{
	int parent = (index-1)/2;//找到父節點的位置
	Node bottom = heapArray[index];//將bottom設為新結點
	while(index>0 && heapArray[parent].getKey()< bottom.getKey())//當指標大0並且父結點的值小於子結點
	{
		heapArray[index] = heapArray[parent];//將父節點的值放入子結點的位置
		index = parent;//將index的值設為父結點的指標
		parent = (parent-1)/2;//找尋下一個父節點
	}
	heapArray[index] = bottom;//將新結點放入最後的合適位置
}

進行trickelUp(向上篩選)時需要先計算新結點父結點的位置，然後與父節點的值進行比較，如果新結點的值更大，
那麼就將父節點的值放入新結點的位置，然後再以父節點的位置為基準找尋下一個父節點進行比較，
比較會一直持續到新結點找到比他大的父結點或著新結點到達跟節點的位置在將新結點排入適當位置。


public Node remove()
{
	Node root = heapArray[0];//將堆陣列中根結點位置的值放入變數root裡面
	heapArray[0] = heapArray[--currentSize];//將堆陣列中最後一個節點放入根節點的位置
	trickleDown(0);//調整堆中結點的位置
	return root;//回傳刪除的節點
}

範例中的堆在刪除時一率刪除最大數據的節點，也就是堆陣列中的第一個數據(根節點的數據)，再刪除後會以堆陣列中第一個空格前一個位置的數據遞補，
之後進行trickleDown(向下塞選)在找出值最大的節點遞補根節點的位置並且把被原本放入跟節點遞補的節點再次放入適當的位置。

public void trickleDown(int index)
{
	int largerChild;
	Node top = heapArray[index];//將根結點位置的直放入變數top
	while(index<currentSize/2)//當指標未到達葉節點階層時執行
	{
		int leftChild = 2*index+1;
		int rightChild = leftChild+1;

		if(rightChild < currentSize && heapArray[leftChild].getKey()<heapArray[rightChild].getKey())
		//當右子節點存在並且右子節點大於左子結點
		{
			largerChild = rightChild;
		}
		else
		//當rightChild == currentSize 表示右子節點不存在。
		{
			largerChild = leftChild;//將largerChild設為左子結點
		}

		if(top.getKey()>= heapArray[largerChild].getKey())
		{
			break;
		}

		heapArray[index] = heapArray[largerChild];//將比較大的子結點放入父傑點的位置
		index = largerChild;//將index設為largerChild的值
	}
	heapArray[index] = top;//將top中的節點放入適當的位置
}

堆在執行trickleDown時一率由根結點開始，此時根節點的數據是從葉節點遞補空缺的數據所以需要從今將這個位置的節點放入適當的位置。
trickleDown在執行到每一個節點時都會進行兩個比較，第一是找出數值比較大的子結點，第二是用當前節點的值去跟比較大的子結點的執比較。
在第一輪的比較時，跟接點的數值將會找出根節點的兩個子結點比較大的那一個然後跟他比較(
在正常的狀況下當根節點被刪除然後由葉節點填補空缺後，堆中值最大的節點將會出現在跟節點的兩個子節點中)，
比較換位後堆中最大的值會被移動到根節點的位置，之後原本處在跟節點的執會被移動到換位的結點位置然後再與她子結點中值比較大的節點相比，
依此類推一直到葉節點的位置。

由於堆本身弱序的特性，有些操作再堆中是非常困難甚至不可能的。除了無法遍歷之外，也無法有效率的找到特定數據，
因為在查找的過程中沒有足夠的資訊可以確定目標數據的所在位置(在堆中左子節點的數據可能會比右子節點的數據大)，
這個特性也使得堆無法跟二叉樹ㄧ樣在O(logN)的時間內刪除一個數據。

在堆的操作中trickleUp(向上篩選)和trickleDown(向下篩選)是最費時的操作，他們都透過迴圈循環，
沿著一條路徑走到頂部或底部並且向上或向下移動節點。在trickleUp和trickleDown的過程中複製的次數和堆的高度有關，
如果一個堆有五層，那麼過程需要執行四次複製。trickleUp在迴圈執行時只需要一次比較，就是比較心結點的值和當前節點的值。
而trickleDown則需要兩次比較，一次比較最大的子結點，一次比較這個最大的子結點和最後結點。
*/













































