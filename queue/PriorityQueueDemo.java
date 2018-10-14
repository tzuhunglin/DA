import java.io.*;
class PriorityQueue
{
	private int maxSize;
	private long[] queArray;
	private int nItems;

	public PriorityQueue(int s)
	{
		maxSize = s;
		queArray = new long[maxSize];
		nItems = 0;
	}

	public void insert(long item)
	{
		int j;
		if(nItems==0)
		{
			queArray[nItems++] = item;
		}else{
			for(j=nItems-1; j>=0;j--)
			{
				if(item > queArray[j])
				{
					queArray[j+1] = queArray[j];
				}else{
					break;
				}
			}
			queArray[j+1] = item;
			nItems ++;
		}
	}

	public long remove()
	{
		return queArray[--nItems];
	}

	public long peek()
	{
		return queArray[nItems-1];
	}

	public boolean isEmpty()
	{
		return (nItems==0);
	}

	public boolean isFull()
	{
		return (nItems == maxSize);
	}

}

class PriorityQueueDemo
{
	public static void main(String[] args) throws IOException
	{
		PriorityQueue thePQ = new PriorityQueue(5);
		thePQ.insert(30);
		thePQ.insert(50);
		thePQ.insert(10);
		thePQ.insert(40);
		thePQ.insert(20);

		while(!thePQ.isEmpty())
		{
			long item = thePQ.remove();
			System.out.print(item + " ");
		}
		System.out.print(" ");


	}
}

/*
優先及隊列和一班隊列的差別就在於插入新數據時優先及隊列會以某項值為基準，然後將新插入的數據放置到排序後的位置。
假如現在已有數據1 3 5 7，如果我們插入新數據4，此時陣列將會變為 1 3 4 5 7，而不是 1 3 5 7 4。 

public void insert(long item)
{
	int j;
	if(nItems==0)
	{
		queArray[nItems++] = item;
	}else{
		for(j=nItems-1; j>=0;j--)
		{
			if(item > queArray[j])
			{
				queArray[j+1] = queArray[j];
			}else{
				break;
			}
		}
		queArray[j+1] = item;
		nItems ++;
	}
}

這是我們優先級隊列在插入新數據時所進行的函式，只要陣列中數據的數量不為0有需要排列就會進行排列。
範例中所使用的是插入排序法，同時在排序完成後陣列中數據的數量nItems會進行增加。

public long remove()
{
	return queArray[--nItems];
}

public long peek()
{
	return queArray[nItems-1];
}

因為隊列在插入時已經經過排序，所以隊列不會在陣列中循環，最小的值會從位置	0的地方開始排序。
移除數據和檢查數據我們都設定在隊列尾端的位置，可以進行查找最大的值和移除最大的值。

範例一開始我們所輸入的值為 30 50 10 40 20。經過排序後為 50 40 30 20 10。
而刪除的順序將會從尾端50開始，接著依序為 40 30 20 10。
/*























