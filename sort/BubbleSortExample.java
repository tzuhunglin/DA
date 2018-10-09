class ArrayBubble{
	private long[] arrBubble;
	private int numValues;
	protected int cntSwap;

	public ArrayBubble(int max)
	{
		arrBubble = new long[max];
		numValues = 0; 
	}

	public void insert(long value)
	{
		arrBubble[numValues] = value;
		numValues++;
	}

	public void bubbleSort()
	{
		for (int out = numValues-1; out>1; out--) //外迴圈的索引表示最大值最後會被移動到的位址，也表示這個位置的值以及這個位置之前的值為這一圈比教的對象。		
		{
			for (int in=0; in<out; in++)//內回圈會抓出索引位置的直以及索引位置後一位得值進行比較
			{
				if(arrBubble[in] > arrBubble[in+1])
				{
					swap(in, in+1);
				}	
			}
			show();
		}
		// show();
		System.out.println("It swapped "+cntSwap+" times");

	}

	public void show()
	{
		for (int i=0; i<numValues; i++) 
		{
			System.out.print(arrBubble[i]+" ");	
		}
		System.out.println("");
	}

	private void swap(int value1, int value2)
	{
		long temp = arrBubble[value1];
		arrBubble[value1] = arrBubble[value2];
		arrBubble[value2] = temp;
		cntSwap ++;
	}
}

class BubbleSortExample
{
	public static void main(String[] args)
	{
		ArrayBubble arrTest = new ArrayBubble(10);

		arrTest.insert(6);
		arrTest.insert(9);
		arrTest.insert(1);
		arrTest.insert(4);
		arrTest.insert(2);
		arrTest.insert(8);
		arrTest.insert(3);
		arrTest.insert(5);
		arrTest.insert(7);
		arrTest.insert(0);


		// arrTest.show();
		arrTest.bubbleSort();

	}
}

/*
冒泡排序法是簡單排序法的一種，他在概念上相對簡單，在學習排序法時相當適合入門。
在程式範例中我門先建立了一個冒泡排序法的類別ArrayBubble，我們再分別定義了arrBubble(用來儲存需要比較的值)、
numValues(目前arrBubble中value的數量)以及cntSwap(用來計算交換的次數)。
每當我們在arrBubble插入一筆值時numValues的數量就會+1，numValues也會在排序的函式中被使用。

bubbleSort就是我們用執行冒泡排序的函式，在函示中我們需要兩個迴圈來執行冒泡排序，分別為外迴圈和內迴圈。
冒泡排序的執行方式就是透過不斷的比較兩組相鄰的數字，如果兩組數字符合我們所設的條件就進行交換。
最後，這個最大或最小的數字就會往目標方向移動。
外迴圈的指標out就代表著移動的目標位置，而內回圈的指標in就代表著比較數組的起始位置。
在範例中，out被我們設定為arrBubble的長度-1，就是這個陣列中的最後一個位置，這表示著在外迴圈的第一圈，
我們將會把陣列中最大的數字移動到這個位置，接著out會在每一圈中遞減，這表示著目標位置會在每一圈中往前移動。
例如，在第二圈中，我們會把第二大的數字移動到最後一位前面一位的位置，依此類推。
我們設定in為內迴圈的指標，從0開始。每一次內迴圈的第一圈都回從指標0的位置開始跟指標1的位置比較，
如果他們比較的結果符合我們的條件，arrBubble[in] > arrBubble[in+1](前項比後項大)，就執行函式swap進行交換，把比較大的數字向右移，把比較小的數字向左移。
然後由指標1的位置跟指標2的位置比較一直比較到外迴圈out所代表的位置。

我們在範例中所輸入的原始數組為
6 9 4 1 2 8 3 5 7 0

而每一次外迴圈的執行結果為下
6 1 4 2 8 3 5 7 0 9
1 4 2 6 3 5 7 0 8 9
1 2 4 3 5 6 0 7 8 9
1 2 3 4 5 0 6 7 8 9
1 2 3 4 0 5 6 7 8 9
1 2 3 0 4 5 6 7 8 9
1 2 0 3 4 5 6 7 8 9
1 0 2 3 4 5 6 7 8 9
It swapped 25 times

我們可以看到在外迴圈第一圈時，數字9(陣列中的最大數)從第二位被移動到了最後一個位置。
而第二圈中(此時數字9的位置已經不在進行比較)，數字8從第五位的位置被移動到了倒數第二的位置。
在接下來的每一圈外迴圈，相對應的數字都會透過交換被移動到個別相對應的位置。

*/











