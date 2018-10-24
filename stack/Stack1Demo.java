class StackX
{
	private int maxSize;//最大長度
	private long[] stackArray;//類別陣列
	private int top;

	public StackX(int s)
	{
		maxSize = s;
		stackArray = new long[maxSize];
		top = -1;
	}

	public void push(long j)
	{
		stackArray[++top] = j;
	}

	public long pop()
	{
		return stackArray[top--];
	}

	public long peek()
	{
		return stackArray[top];
	}

	public boolean isEmpty()
	{
		return (top == -1);
	}

	public boolean isFull()
	{
		return (top == maxSize-1);
	}
}

class Stack1Demo
{
	public static void main(String[] args)
	{
		StackX theStack = new StackX(10);
		theStack.push(2);
		theStack.push(4);
		theStack.push(6);
		theStack.push(8);

		while(!theStack.isEmpty())
		{
			long value = theStack.pop();
			System.out.print(value);
			System.out.print(" ");
		}
		System.out.println(" ");
	}
}

/*
堆疊是一種先進後出、後進先出的結構，就像你把一堆盤子一個一個的疊再一起，
最後放上去在最上面的那一個盤子你可以先拿起來，
但是你當初第一個放的盤子，就是盤子最底部那一個，
他必須等到所有的盤子都被拿走時她才可以被拿出來。
這就是我們所說的先進後出跟後進先出。所以在堆疊的結構中，
最先儲存的數據項會被存在最裡面，而最晚儲存的數據像會被儲存在最外面。

在堆疊的結構中，我們一次只可以訪問一個數據項，
就是我們最後插入的那一筆數據項，
如果我們想要訪問更先前插入的數據項，
我們必須先移除這一筆數據項。因此，堆疊在在程式中更多時候被擁來當作工具來運用，
輔助構思算法，而不是完整的儲存工具
他們的生命週期比較短，只有在程序操作時才被創造，
並且當任務完成時被消毀。

在一些比較複雜的資料結構算法中我們也可以看到堆疊的身影，
像是二叉樹使用堆疊來遍歷節點以及圖利用堆疊來輔助尋找圖的頂點。

private int maxSize;//最大長度
private long[] stackArray;//實現堆疊的陣列
private int top;//最上層的位置

public StackX(int s)
{
	maxSize = s;
	stackArray = new long[maxSize];
	top = -1;
}

堆疊最主要是透過陣列來實現，陣列stackArray是我們實現堆疊的陣列，
maxSize代表著類別中陣列的最大長度，也是我們堆疊的最大長度。
top代表著是堆疊中最上層的位置，也是陣列最後端的位置，他是唯一可以被訪問的位置，
當然他的數字在陣列有新增或刪除的動作時值會改變。

public void push(long j)
{
	stackArray[++top] = j;
}

在我們新增一筆值到堆疊時，類別屬性top就會增加，除了用來讓當作索引讓新的值放入陣列，
他也代表著堆疊中可以查找的位置。

public long pop()
{
	return stackArray[top--];
}

當我們在這堆疊中刪除一個值時，我們並沒有真正的執行刪除或是取代的動作。
我們唯一做的就是top--，和返回那一筆值(這對陣列中那一個位置得值沒有影響，
不過我們再也不可以訪問他了)。
但是這不會影響我們接下來的動作，因為在下次做新增的動作時，原本的直就會被覆蓋過去。

public long peek()
{
	return stackArray[top];
}

這是我們用來訪問目前最上層數據的函式，他永遠是返回陣列中top位置的值，
而top的值永遠隨著陣列長度變動，所以可訪問的直永遠是陣列最後面的值。

public boolean isEmpty()
{
	return (top == -1);
}

public boolean isFull()
{
	return (top == maxSize-1);
}

isEmpty還有isFull這兩個善分別被用來確定陣列是否為空和陣列是否滿了。

範例執行結果如下
以下為我們一開始輸入的值，當while迴圈開始時，訪問的位置會在8的位置。
2 4 6 8
所以第一圈時，返回的數字為8，返回後就將top的位置向前移6的位置。
所以第二圈時，返回的數字為6，返回後就將top的位置向前移4的位置。
所以第三圈時，返回的數字為4，返回後就將top的位置向前移2的位置。
所以第四圈時，返回的數字為2，返回後就將top的位置向前移(此時索引位置為-1)。

堆疊在數據新增跟刪除的時間複雜度都是常數O(1)，
也就是說無論堆疊中陣列的長度是多少，他們操作時間是一樣的。
*/

















