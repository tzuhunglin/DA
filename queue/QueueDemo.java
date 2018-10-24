class Queue
{
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;
	private int nItems;

	public Queue(int s)
	{
		maxSize = s;
		queArray = new long[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	public void insert(long j)
	{
		if(rear == maxSize -1)//當後指標在陣列最尾端時，
		{
			rear = -1;//須將他調製-1的位置，讓他在後面進入0的位置。
		}
		queArray[++rear] = j;
		nItems++;
	}

	public long remove()
	{
		long temp = queArray[front++];
		if(front == maxSize)//表示front指標已超出範圍
		{
			front = 0;
		}
		nItems --;
		return temp;
	}

	public long peekFront()
	{
		return queArray[front];
	}

	public boolean isEmpty()
	{
		return (nItems == 0);
	}

	public boolean isFull()
	{
		return (nItems==maxSize);
	}

	public int size()
	{
		return nItems;
	}
}

class QueueDemo
{
	public static void main(String[] args)
	{
		Queue theQueue = new Queue(5);

		theQueue.insert(10);
		theQueue.insert(20);
		theQueue.insert(30);
		theQueue.insert(40);

		theQueue.remove();
		theQueue.remove();
		theQueue.remove();

		theQueue.insert(50);
		theQueue.insert(60);
		theQueue.insert(70);
		theQueue.insert(80);


		while(!theQueue.isEmpty())
		{
			long n = theQueue.remove();
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.println("");

	}
}


/*
和堆疊一樣，隊列大部分的時候被用來作為工具。不過它的特性和堆疊相反，他是先進先出和後進後出。
這種特性就像排隊買票一樣，越早排隊的人將會越早買到票，然後離開隊伍。而越晚進入隊伍的人，
需要等到前面所有的人都離開時才可以買票。

在隊列的結構中，我們一次只被允許查找一筆數據，就是在陣列中最早被插入的那一筆數據。
再刪除它之後，我們可以查找的數據變成他後面的那一筆。
所以如果我們想要找到特定的數據，例如數字100，我們必須將它之前的數據都刪除才行。

Queue類別是我們所見建構隊列的地方。在這裡，我們可以看到許多不同的屬性。
private int maxSize;可以說是這個類別內容的最大容量，也是類別內主要陣列的長度。
private long[] queArray;類別內最主要的陣列，用來儲存隊列中所有的值。
private int front;前指標，用來指向隊列中最早插入得值，會隨著所指向的值被刪除後而往後移動。
private int rear;後指標，用來指向一筆新值在插入對咧時所需要插入的位置，
private int nItems;陣列中數據的數量。

public void insert(long j)
{
	if(rear == maxSize -1)//當後指標在陣列最尾端時，
	{
		rear = -1;//須將他調製-1的位置，讓他在後面進入0的位置。
	}
	queArray[++rear] = j;
	nItems++;
}

隊列中插入新數據的函式，執行方式相當簡單，就是將新的數據j插入當前後指標位置之後的位置。
然後在總數量nItems進行增加。
唯一較特別的地方是當後指標在陣列最尾端時，我們會將他的值改為-1，讓他再賦予陣列新數據時由0開始。
隊列的數據在陣列中是不斷循環的而非永遠從位置0開始，這種方式雖然複雜了一點，
但是卻省去了移動數據所消耗的時間和資源。	

public long remove()
{
	long temp = queArray[front++];
	if(front == maxSize)//表示front指標已超出範圍
	{
		front = 0;
	}
	nItems --;
	return temp;
}

在執行刪除函式時，我們會將所要刪除的值放入temp變數中，再做回傳。總數量nItems執行-1。
因為我們的隊列會在陣列中不斷的循環，所以當前指標增加後超出了陣列的位置範圍，我必須將他的值改為0，
讓前指標移回陣列最前方的位置從新開始。

public long peekFront()
{
	return queArray[front];
}

在隊列中，我們可以查找的唯一數據就是前指標所指向的值。

public boolean isEmpty()
{
	return (nItems == 0);
}

運以檢測隊列中的數據數量是否為0。

public boolean isFull()
{
	return (nItems==maxSize);
}
運以檢測隊列中的數據數量是否已滿。

public int size()
{
	return nItems;
}

運以檢測隊列中的數據數量。


在範例中，我們先依序插入了10 20 30 40。
此時隊列中的數據為 10 20 30 40，前指標的位置在10的位置。
所以當我們執行三個刪除時，程式由10開始刪除，接著20以及30。
當隊列中只有40一筆數據時，我們在插入50 60 70 80。
我們在呼叫隊列時所設定的最大數量為5，
所以我們所新插入的四比數局並不會全部排在40的右方。
在陣列中40的右方只剩下一個位置，所以其餘的三個數將被放在40的左方。
60 70 80 40 50。當然，前指針的位置還是只在40，的位置。
如果我們執行查找或刪除，我們所看到的數據將會是40。 

隊列在數據新增跟刪除的時間複雜度都是常數O(1)，
也就是說無論堆疊中陣列的長度是多少，他們操作時間是一樣的。
*/























