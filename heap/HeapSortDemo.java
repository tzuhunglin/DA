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

	public Node remove()
	{
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
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

	public void displayHeap()
	{
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = ".............";
		System.out.println(dots+dots);

		while(currentSize>0)
		{
			if(column==0)
			{
				for(int k=0; k<nBlanks; k++)
				{
					System.out.print(' ');
				}
			}

			System.out.print(heapArray[j].getKey());

			if(++j == currentSize)
			{
				break;
			}

			if(++column == itemsPerRow)
			{
				nBlanks /= 2;
				itemsPerRow *=2;
				column =0;
				System.out.println();
			}
			else
			{
				for(int k=0; k<nBlanks*2-2;k++)
				{
					System.out.print(' ');
				}
			}

			System.out.println('\n'+dots+dots);
		}
	}

	public void displayArray()
	{
		for(int j=0; j<maxSize; j++)
		{
			System.out.print(heapArray[j].getKey()+" ");
		}
		System.out.println(" ");
	}

	public void insertAt(int index, Node newNode)
	{
		heapArray[index] = newNode;
	}

	public void incrementSize()
	{
		currentSize++;
	}
}


class HeapSortDemo
{
	public static void main(String[] args) throws IOException
	{
		int size, j;
		System.out.print("Enter number of items:");
		size = getInt();
		Heap theHeap = new Heap(size);

		for(j=0; j<size; j++)
		{
			int random = (int)(java.lang.Math.random()*100);
			Node newNode = new Node(random);
			theHeap.insertAt(j, newNode);
			theHeap.incrementSize();
		}

		System.out.print("Random: ");
		theHeap.displayArray();

		for(j = size/2-1; j>=0; j--)
		{
			theHeap.trickleDown(j);
		}

		System.out.print("Heap: ");
		theHeap.displayArray();
		theHeap.displayHeap();

		for(j=size-1; j>=0; j--)
		{
			Node biggestNode = theHeap.remove();
			theHeap.insertAt(j, biggestNode);
		}

		System.out.print("Sorted: ");
		theHeap.displayArray();
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
堆排序是基於堆的一種排序法，他很間單，又很有效率。堆排序的方法就是使用最簡單的插入法將數據插入堆陣列中(所有數據在陣列中都是隨機排列)，
之後再將這些數據執行trickleDown讓他們符合堆的結構排序，此時這些數據還不是完全有序，但是每一個父節點的數據都會比子結點的數據大。
在完成基本的堆結構排序後再以remove和insertAt的方式將節點放回堆陣列中完成排序。

因為他insert和remove方法操作的時間複雜度都是O(logN)，而且每個方法都要執行N次，所以整個排序的操作需要O(N*logN)的時間。
這跟快速排序差不多，但是快速排序比他更有效率，因為trickleDown裡面的while循環中的側做比較多。

for(j=0; j<size; j++)
{
	int random = (int)(java.lang.Math.random()*100);
	Node newNode = new Node(random);
	theHeap.insertAt(j, newNode);
	theHeap.incrementSize();
}

在範例中的一開始我們先隨意插入數據進入堆陣列中，並且在插入的過程中持續增加堆陣列的中數據的數量。

for(j = size/2-1; j>=0; j--)
{
	theHeap.trickleDown(j);
}

再插入完所有數據後，我們會先執行一次trickleDown，讓這個陣列中的數據符合堆陣列的規則，就是每一個父節點的數據都會比子結點的數據大。

for(j=size-1; j>=0; j--)
{
	Node biggestNode = theHeap.remove();
	theHeap.insertAt(j, biggestNode);
}

最後再用刪除節點的方式取得最大節點並放回堆中。

public Node remove()
{
	Node root = heapArray[0];
	heapArray[0] = heapArray[--currentSize];
	trickleDown(0);
	return root;
}
在remove的操作中，我們會將根節點放入變數root返回，並且用最後一個未處理數據取代跟節點的數據執行tricleDown。
未處理的數據就是重新插回陣列的數據，他為由最大的數據排在陣列的最後端依序一直被重新插回陣列中，
這些數據再重新放回陣列中就已經完成排序不會在移動，取代根節點數據的位置會在每一次的刪除中依序往前，
所以在堆排序完成時我們可以得到一組升冪排序的陣列。

堆排序的時間複雜度為O(N*logN)。雖然他比快速排序慢一點，但是他比快速排序更穩定。在某些狀況下，快速排序運行的時間複雜度可能會降到O(N^2)，
然而堆排序的時間可以穩定的維持在O(N*logN)。
*/














































