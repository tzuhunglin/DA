class ordArray
{
	private long[] a;
	private int nElems;

	public ordArray(int max)
	{
		a = new long[max];
		nElems = 0;
	}

	public int size()
	{
		return nElems;
	}

	public int find(long searchKey)
	{
		return recFind(searchKey, 0, nElems-1);//帶入目標值，範圍最小值和範圍最大值
	}

	private int recFind(long searchKey, int lowerBound, int upperBound)
	{
		int curIn;//指標

		curIn = (lowerBound+ upperBound)/2;//找出中間的位置，將陣列一分為二
		if(a[curIn]==searchKey)//當陣列中curIn的位置為目標值時
		{
			return curIn;//返回curIn
		}
		else if(lowerBound > upperBound)//當下限大於上限時停止查找
		{
			return nElems;
		}
		else
		{
			if(a[curIn] < searchKey)//當curIn所在位置的值小於目標值時
			{
				return recFind(searchKey,curIn+1,upperBound);//把範圍縮小到陣列的右邊部分繼續遞迴搜尋
			}
			else//當curIn所在位置的值大於目標值時
			{
				return recFind(searchKey, lowerBound, curIn-1);//把範圍縮小到陣列的左邊邊部分繼續遞迴搜尋
			}
		}
	}
	public void insert(long value)
	{
		int j;
		for(j=0; j<nElems; j++)
		{
			if(a[j]>value)
			{
				break;
			}
			
		}
		for(int k=nElems; k>j; k--)
			{
				a[k] = a[k-1];
			}
			a[j] = value;
		nElems++;

	}

	public void display()
	{
		for(int j=0;j<nElems; j++)
		{
			System.out.print(a[j]+ " ");
		}

		System.out.println(" ");
	}
}

class BinarySearchDemo
{
	public static void main(String[] args)
	{
		int maxSize = 100;
		ordArray arr;
		arr = new ordArray(maxSize);

		arr.insert(3);
		arr.insert(7);
		arr.insert(8);
		arr.insert(1);
		arr.insert(9);
		arr.insert(0);
		arr.insert(2);
		arr.insert(5);
		arr.insert(6);
		arr.insert(4);


		arr.display();

		int searchKey = 9;

		if(arr.find(searchKey) != arr.size())
		{
			System.out.println("Found "+ searchKey);
		}
		else
		{
			System.out.println("Can't find "+ searchKey);
		}

	}
}

/*
二分搜尋法除了使用迴圈外，也可以使用遞迴來實現。實現的方法相同，
也是透過不段的縮小搜尋的範圍直到找到目標值。

private int recFind(long searchKey, int lowerBound, int upperBound)
{
	int curIn;//指標

	curIn = (lowerBound+ upperBound)/2;//找出中間的位置，將陣列一分為二
	if(a[curIn]==searchKey)//當陣列中curIn的位置為目標值時
	{
		return curIn;//返回curIn
	}
	else if(lowerBound > upperBound)//當下限大於上限時停止查找
	{
		return nElems;
	}
	else
	{
		if(a[curIn] < searchKey)//當curIn所在位置的值小於目標值時
		{
			return recFind(searchKey,curIn+1,upperBound);//把範圍縮小到陣列的右邊部分繼續遞迴搜尋
		}
		else//當curIn所在位置的值大於目標值時
		{
			return recFind(searchKey, lowerBound, curIn-1);//把範圍縮小到陣列的左邊邊部分繼續遞迴搜尋
		}
	}
}

在我們一開始呼叫最外層的recFind函式我們會帶入目標值和這組陣列的最小位置和最大位置，
接著在每一層的recFind中判斷後會一直的縮小陣列的範圍直到找到目標值。

在進入函式的一開始，我們會先找出陣列範圍的中間位置，因為是有序陣列，在中間位置的值，
就是所有數值大小位於中間的值。中間位置(curIn)的求法就是用lowerBound(搜索範圍最左邊的位置)加上
upperBound(陣列範圍最右邊的位置)除2。
接著用curIn所在位置的數字作為判斷，如果這個位置的值就是我們的目標值，
那就表示我們已經找到目標，可以把這個值得位置返回。如果下限(lowerBound)已經大於上限(upperBound)
那表示在陣列中沒有我們要找的目標值。如果目標值大於或小於curIn所在位置的值，
那表示我們還需要繼續把範圍縮小尋找尋找。

按照範例中所給的陣列數值和目標值7，我們在最外層的recFind函式所帶入的範圍是0跟9。
接者我們會用這兩個數字求出中間值，(0+9)/2所得到的結果是5。
接著我們會用目標值8和在陣列中地5個位置的值4做比較，8比四大，
所以我們會再縮小範圍進入第二層遞迴搜尋recFind(searchKey,curIn+1,upperBound)，
這個時候我們所帶入的範圍是原本的curIn+1=6和9，進入這一層後我們需要在求出這一層的中間位置(6+9)/2=8，
陣列中curIn的位置還不等於我們的目標值，所以繼續做比較。我們的目標值7比陣列中curIn位置的值8小，
所以再度執行recFind(searchKey, lowerBound, curIn-1)，這次我們所帶入的下限值是6上限值是curIn-1=7，
範圍已經被縮小到兩個值。
而這一層的中間位置curIn為(6+7)/2=7，就是我們的目標值，開始返回curIn到最外層。


遞迴的二分搜尋法和迴圈的二分搜尋法再大O表示法中同樣都是O(logN)的時間等級，
雖然遞迴的二分搜尋法的程式碼更簡潔清楚，但是他在相同的狀況下可能會慢一點。
*/

















































