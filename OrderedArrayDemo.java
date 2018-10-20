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

	public int binaryFind(long searchKey)
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
		int j = binaryFind(value);
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
		if(arr.binaryFind(searchKey) != arr.size())
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
在陣列搜尋的方法中，最基本的就是線性查找法了。線性查找法的方式就是在陣列中從第一比數據開始比對，直到找到目標為止。
線性查找法無論是在有序還是無序的陣列中都可以使用。

但是他的缺點就是在某些狀況下會非常沒有效率，尤其是在陣列相當長的時候。如果目標值在陣列前端，那搜尋就會較快。
但是如果目標在陣列後端，那搜尋就會較慢。
如果我們在陣列中有N個數據，那我們搜尋每一筆數據的平均長度就是N/2。

而二分查找法變改善了線性查找法在大組數搜尋後半部數據的效率。二分查找法的執行方式就是將有序陣列對拆為兩個部分，
然後只取目標值所在的那一部分再繼續對拆為兩半，直到範圍縮小至一個數字找到目標為止。

假設我們在玩1到100的猜數字遊戲，答案是60。以線性搜尋的方式，從1開始往後猜，我們需要猜60次。但如果是用二分搜尋法的方式，
不斷地找出範圍的中間值來猜，第一次猜50(範圍縮小至50-100)，第二次猜75(範圍縮小至50-75)，第三次猜60(範圍縮小至50-62)，
第四次猜56(範圍縮小至56-62)，第五次猜59(範圍縮小至59-62)，第六次猜60。這時我們已經找到了答案。只用了六步。
在二分搜尋法中，7步可以找到0-100內任何一個值。而0-1000最多只需要10步。

線性搜尋法再大O表示法中使用了O(N)的時間級數，而二分搜尋法則用了O(Log N)的時間級數。
在大O表示法的關係中，我們也可以看得出二分搜尋法更為優秀。
線性搜尋法使用的時間級數是O(N)，他的執行時間會隨著N等比增加，
如果現在的N為1000，那麼他單次查找的平均時間將會是 k * 2000/2。
如果N增加為2000，那麼單次搜尋的明君時間將會變成 k * 4000/2。

二分搜尋法的所使用的時間級數是O(log N)
如果現在的N為1024，那麼他單次查找的平均時間將會是 k * log2(1024) = k * 10
如果現在的N為2000，那麼他單次查找的平均時間將會是 k * log2(2000) = k * 11

由這兩個例子我們可以看到在陣列長度N由2000倍數成長至4000時，線性搜索的平均搜尋時間同時也倍數成長，
而二分搜尋法的平均時間只成長了百分之十。

public int binaryFind(long searchKey)
{
	int lowerBound = 0;//下標設為陣列範圍中的第一個位置
	int upperBound = nElems -1;//上標設為陣列範圍中的最後一個位置
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

在二分搜尋法中我們需要，三個變數來幫我們完成作業，分別是上標(upperBound)、下標(lowerBound)以及curIn(當前位置指標)。
二分搜尋法執行方法就是將lowerBound加上upperBound除以2計算出中位數，再以中位數與目標值進行比較決定接下來二分範圍。
如果最後的結果是剩下最後一位並且陣列中當前位置指標得值與目標值一樣，就表示搜尋成功。但是如果，陣列中沒有目標值，
那麼下標將會超過上標，搜尋也會結束。

我們在範例中所輸入的原始數組為
6 9 1 4 2 8 3 5 7 0

而我們搜尋的目標是在陣列位置5的數值8

而每一次對分的執行結果為下
while迴圈第一圈會將範圍縮小至 8 3 5 7 0
while迴圈第二圈會將範圍縮小至 8 3
while迴圈第二圈會將範圍縮小至 8 也就是我們所要搜尋的目標。


二分搜尋跟線性搜尋在小數量的搜尋上次數區別並不明顯。用線性搜尋搜尋十筆紀錄，
平均每一筆所需要的比較次數是5(N/2)，二分搜尋最多則只需要四次比較，看似差異不大，
但是在數量越大的搜尋中，差別會越來越明顯。當搜尋數量到達1000000時，線性搜尋平均每筆的比較次數會是500000，而二分搜尋則是20。

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
在計算機上log 通常是以10底數，而以十為底數的計算結果再乘以3.322就可以得到已2為底數的結果。

log10(100) = 2。2*3.322 = 6.644 =>7
*/






















