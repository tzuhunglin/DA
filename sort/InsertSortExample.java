class ArrayInsert
{
	private long[] arrInsert;
	private int numValues;

	public ArrayInsert(int max)
	{
		arrInsert = new long[max];
		numValues = 0;
	}

	public void insert(long value)
	{
		arrInsert[numValues] = value;
		numValues++;
	}



	public void show()
	{
		for (int j = 0; j<numValues; j++) 
		{
			System.out.print(arrInsert[j]+ " ");	
		}
		System.out.println("");
	}

	public void insertionSort()
	{

		for ( int out = 1; out<numValues; out++) //從第一位遞增往後跑
		{
			long temp = arrInsert[out];//存入暫存變數	
			int in = out;
			while(in > 0 && arrInsert[in-1] >= temp)//所有的樹都跟temp比較,temp大就往後移,直到沒有將temp放入那一位	
			{
				arrInsert[in] = arrInsert[in-1];
				--in;
			}
			arrInsert[in] = temp;
			show();
		}
	}
}

class InsertSortExample
{
	public static void main(String[] args)
	{
		ArrayInsert arrTest = new ArrayInsert(10);

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

		// arrTest.display();
		arrTest.insertionSort();
		// arrTest.display();
	}
}

/*
插入排序法是簡單排序法中最快的一種，比冒泡排序法還有選擇排序法更快，
但是跟他們兩種比起來，他也是最困難的一種。不過他也不是非常困難，只要把它想成最小範圍的局部排序，兩位，
然後慢慢增大範圍至三位、四位去做局部排序就會覺得容易許多。

在程式範例中我門先建立了一個插入排序法的類別ArrayInsert，我們再分別定義了arrSelect(用來儲存需要比較的值)、
numValues(目前arrInsert中value的數量)。
每當我們在arrInsert插入一筆值時numValues的數量就會+1，numValues也會在排序的函式中被使用。

insertionSort就是我們用執行插入排序的函式，在函式中我們需要兩個迴圈來執行插入排序，分別為外迴圈和內迴圈。

插入排序的執行方式就是透過外迴圈指標out位置的值不斷的往前比較，如果前面的數字比out位置的值大，
那我們就把它往後移一位，為out指標位置的值騰出空間，如果out指標位置的值比前面所有的數小，
那麼他前面的數就會全部會往後移一位，然後out指標位置的值就會被插入第一位的位置。但是如果out指標位置的值在中途就遇到了比它小的值，
那麼他就會停止往前然後插入在先前已經被它往後移的那些數所騰出的空位。

外迴圈的指標out代表這一圈外迴圈要開始往前比較的值的位置，他的索引位置會被放入變數temp中用來和前面的值比較，
out會在每一圈後遞增，擴大往前比較的範圍。
而內回圈的指標in就代表著需要用來和temp位置的值比較的位置。
在範例中，out在一開始被我們設定為1，然後將他的值放入變數temp中來和前面一位比較，
如果他的前面一位數比他大，那他前面的那一位數就會被往後移，然後temp位置所代表的直就會被用來插入那個空間。
如果現在out的值是6，那他將會被放入temp中然後和前面6個位置比較。
如果out位置的值是最小的，那他將會續把第六位、第五位....都往後移一位然後插入第一位中。
但是如果有五個數字比他小，那他將會在移動第六位數至第七個位置(就是他自己的位置)後停下，然後插入第六位。

插入排序法會透過這種方式在每一圈外迴圈中進行一定範圍的局部排序，例如，現在out的位置是3，那在這一圈結束時，
位置0,1,2,3會被完成排序。然後依序擴大局部排序的範圍至所有數。





我們在範例中所輸入的原始數組為
6 9 1 4 2 8 3 5 7 0

而每一次外迴圈的執行結果為下
6 9 1 4 2 8 3 5 7 0
1 6 9 4 2 8 3 5 7 0
1 4 6 9 2 8 3 5 7 0
1 2 4 6 9 8 3 5 7 0
1 2 4 6 8 9 3 5 7 0
1 2 3 4 6 8 9 5 7 0
1 2 3 4 5 6 8 9 7 0
1 2 3 4 5 6 7 8 9 0
0 1 2 3 4 5 6 7 8 9

我們可以看到在外迴圈第一圈完成時，外迴圈out的值為1，往前比較後，第1位和第0位已經被完成排序。
然後在第二圈，外迴圈out的值為2，擴大範圍至三個數字，往前比較後，第2位、第1位和第0位已經被完成排序。
第二圈時，外迴圈out的值為3，擴大範圍至四個數字，往前比較後，第3位、第2位、第1位和第0位已經被完成排序。

*/














