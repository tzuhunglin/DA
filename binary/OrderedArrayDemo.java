class OrdArray
{
	private long[] a;
	private int nElems;

	public OrdArray(int max)
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
		int lowerBound = 0;//下標設為陣列中第一位的位置
		int upperBound = nElems -1;//上標設為陣列中最後一位的位置
		int curIn;//當前指標位置

		while(true)
		{
			curIn = (lowerBound + upperBound) /2;//總數量為基數時，cutIn的位置會在中位數。偶數時會在前半部分的最後一位
			if(a[curIn]==searchKey)//找到目標值，返回
			{
				return curIn;
			}
			else if(lowerBound> upperBound)//當下標超過上標表示找不到
			{
				return nElems;
			}
			else
			{
				if(a[curIn]<searchKey)//當目標值比當前位置得值大時
				{
					lowerBound = curIn+1;//從目前位置+1繼續找
				}else{				  //當目標值比當前位置得值小時
					upperBound = curIn -1;//從目前位置-1繼續找
				}
			}
		}
	}

	public void insert(long value)
	{
		int j;
		for(j=0; j<nElems; j++)//找到比j大的值後停止
		{
			if(a[j] > value)
			{
				break;
			}
		}

		for(int k = nElems; k>j; k--)//將所有筆j小的值向後移動
		{
			a[k] = a[k-1];
		}
		a[j] = value;//將j插入空格
		nElems++;
	}

	public boolean delete(long value)
	{
		int j = find(value);
		if(j==nElems)
		{
			return false;
		}else{
			for(int k=j; k<nElems; k++)
			{
				a[k] = a[k+1];
			}
			nElems--;
			return true;
		}
	}

	public void display()
	{
		for(int j=0; j<nElems;j++)
		{
			System.out.print(a[j]+" ");
		}
		System.out.println(" ");
	}
}

class OrderedArrayDemo
{
	public static void main(String[] args)
	{
		int maxSize = 100;
		OrdArray arr;
		arr = new OrdArray(maxSize);

		arr.insert(77);
		arr.insert(99);
		arr.insert(44);
		arr.insert(55);
		arr.insert(22);
		arr.insert(88);
		arr.insert(11);
		arr.insert(00);
		arr.insert(66);
		arr.insert(33);

		int searchKey =55;
		if(arr.find(searchKey) != arr.size())
		{
			System.out.println("Found " + searchKey);
		}else{
			System.out.println("Can't find " +searchKey);
		}

		arr.display();

		arr.delete(00);
		arr.delete(55);
		arr.delete(99);
		arr.display();



	}
}


/*
二分搜尋跟線性搜尋在小數量的搜尋上次數區別並不明顯。用線性搜尋搜尋十筆紀錄，
平均每一筆所需要的比較次數是5(N/2)，二分搜尋最多則只需要四次比較，看似差異不大，
但是在數量越大的搜尋中，差別會越來越明顯。當搜尋數量到達1000000時，
線性搜尋平均每筆的比較次數會是500000，而二分搜尋則是20。

二分搜尋的步數還有範圍2的乘冪有關。
當步數為 0 時，我們可以找到的範圍是     1 ，也可以看做 2^0。
當步數為 1 時，我們可以找到的範圍是     2 ，也可以看做 2^1。
當步數為 2 時，我們可以找到的範圍是     4 ，也可以看做 2^2。
當步數為 3 時，我們可以找到的範圍是     8 ，也可以看做 2^3。
當步數為 4 時，我們可以找到的範圍是   16 ，也可以看做 2^4。
當步數為 5 時，我們可以找到的範圍是   32 ，也可以看做 2^5。
當步數為 6 時，我們可以找到的範圍是   64 ，也可以看做 2^6。
當步數為 7 時，我們可以找到的範圍是 128 ，也可以看做 2^7。

當我們將步數設為s，將範圍設為r，我們就可以用步數( s )求出範圍( r )。

2^s = r

當步數為 3 時，我們的搜尋範圍可以達到 8。
當步數為 6 時，我們的搜尋範圍可以達到 64。

如果我們需要計算在範圍內需要多少步數的話就會需要用到2的指數的反函數。
指數的反函數稱為對數，對數就是要達到一個值，底數重複相承的次數。
由範圍求出步數公式是 s = log2( r )，二分搜尋法以 2為底數，2^?=r， ? = s。
例如，2 = log2( 4 )，5 = log2( 32 )。
在計算機上log 通常是以10底數，而以食為底數的計算結果再乘以3.322就可以得到已2為底數的結果。

log10(100) = 2。2*3.322 = 6.644 =>7

二分搜尋法式適合用在頻繁搜尋的狀況。例如，搜尋，插入資料至特定位置以及刪除特定資料。

*/


