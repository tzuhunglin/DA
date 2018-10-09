class ArraySelect
{
	private long[] arrSelect;
	private int numValues;
	protected int cntSwap;


	public ArraySelect(int max)
	{
		arrSelect = new long[max];
		numValues = 0;

	}

	public void insert(long value)
	{
		arrSelect[numValues] =value;
		numValues++;
	}

	public void show()
	{
		for (int j = 0; j<numValues; j++) 
		{
			System.out.print(arrSelect[j]+" ");	
		}
		System.out.println(" ");
	}

	public void selectionSort()
	{

		for (int out = 0; out<numValues; out++) 
		{
			int min = out;
			for (int in = out+1; in<numValues; in++) {
				if(arrSelect[in]<arrSelect[min])
				{
					min = in;
				}
			}
			swap(out,min);
			show();

		}
		// show();
		System.out.println("It swapped "+cntSwap+" times");

	}

	private void swap(int value1, int value2)
	{
		long temp = arrSelect[value1];
		arrSelect[value1] = arrSelect[value2];
		arrSelect[value2] = temp;
		cntSwap ++;

	}
}



class SelectSortExample
{
	public static void main(String[] args)
	{
		ArraySelect arrTest = new ArraySelect(10);

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
		arrTest.selectionSort();
	}
}
/*
選擇排序法是簡單排序法的一種，他在概念上相對簡單，在學習排序法時相當適合入門。
在程式範例中我門先建立了一個選擇排序法的類別ArraySelect，我們再分別定義了arrSelect(用來儲存需要比較的值)、
numValues(目前arrSelect中value的數量)以及cntSwap(用來計算交換的次數)。
每當我們在arrSelect插入一筆值時numValues的數量就會+1，numValues也會在排序的函式中被使用。

selectionSort就是我們用執行選擇排序的函式，在函式中我們需要兩個迴圈來執行選擇排序，分別為外迴圈和內迴圈。

選擇排序的執行方式就是透過在每一圈中依序檢視範圍內所有的值，從第一位開始將他的索引位置放入變數min中，
再和後面的數字做比較。如果後面的數字更小，就用他的指標位置取代原本min中的索引位置。在這一圈比較完範圍內所有的值執行完畢後，
我們就知道了最小值的索引位置，就是變數min中所存的數字，再用這個位置的值和目標位置的值進行交換。
在外迴圈第一圈結束時，我們會將最小的數字交換到第一位，第二圈結束時，我們會將第二小的數字交換到第二位。



外迴圈的指標out就代表當內回圈結束時變數min需要做交換的目標位置，
而內回圈的指標in就代表著需要比較的數組的起始位置。
在範例中，out被我們設定為0，就是這個陣列中的第一位的位置，這表示著在外迴圈的第一圈，
我們將會把陣列中最小的數字移動到這個位置，接著out會在每一圈中遞增，
這表示著目標位置會在每一圈中往後移動。
例如，在第二圈中，我們會把第二小的數字移動到第二位，依此類推。
我們設定in為內迴圈的指標，從out+1開始，因為out已經被我們放入了min中，
他可以說是我們在這一圈中比較的起始點，所以in指標從out+1開始來和他做比較。
每一次內迴圈的第一圈都回從out指標的位置開始跟指標out+1的位置比較，接者是out+1的位置的值和out+2的位置的值做比較。
在每一次的比較後，我們都會將比較小的值的位置存入min中。當內回圈結束時，min中會儲存著這些比較數字的最小值的位置。
然後我們就將他和out指標代表的目標位置的值進行交換。如果一開始指標out的位置的值就是這些比較數目中的最小值，
那麼out指標就會一直贏得比較並且待在min變數中，最後還是會進行交換，但是out指標所代表的數字和變數中min一樣，所以結果不變。


我們在範例中所輸入的原始數組為
6 9 1 4 2 8 3 5 7 0

而每一次外迴圈的執行結果為下
0 9 1 4 2 8 3 5 7 6
0 1 9 4 2 8 3 5 7 6
0 1 2 4 9 8 3 5 7 6
0 1 2 3 9 8 4 5 7 6
0 1 2 3 4 8 9 5 7 6
0 1 2 3 4 5 9 8 7 6
0 1 2 3 4 5 6 8 7 9
0 1 2 3 4 5 6 7 8 9
0 1 2 3 4 5 6 7 8 9
0 1 2 3 4 5 6 7 8 9
It swapped 10 times

我們可以看到在外迴圈第一圈時，數字0(陣列中的最小數)從第十位被移動到了第一個位置。
而第二圈中(此時數字0的位置已經不在進行比較)，數字1從第三位的位置被移動到了倒數第二的位置。
在接下來的每一圈外迴圈，相對應的數字都會透過交換被移動到個別相對應的位置。
跟冒泡排序法不同，冒泡排序法只要兩個數字比較後符合條件就會進行交換，而選擇排序法執行交換的數量就是外迴圈的數量(陣列的長度)。

*/



















