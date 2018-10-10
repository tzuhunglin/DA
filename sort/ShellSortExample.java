class ArrayShell
{
	private long[] arrShell;
	private int numValues;

	public ArrayShell(int max)
	{
		arrShell = new long[max];
		numValues = 0;
	}

	public void insert(long value)
	{
		arrShell[numValues] = value;
		numValues++;
	}

	public void show(){
		System.out.print("Array =");
		for(int j=0; j<numValues; j++)
		{
			System.out.print(arrShell[j]+" ");
		}
		System.out.println(" ");
	}

	public void shellSort()
	{
		int in, out;
		long temp;

		int h=1;//間隔;
		while(h<= numValues/3)
		{
			h= h*3 +1;
		}

		while(h>0)//當間隔大於0
		{
			for(out=h; out<numValues; out++)
			{
				temp = arrShell[out];
				in = out;

				while(in > h-1 && arrShell[in-h]>=temp)//in > h-1用來做結束判斷，
				{
					arrShell[in] = arrShell[in-h];
					in -=h;
				}
				arrShell[in] = temp;
			}
			h = (h-1)/3;
			show();
		}
	}
}

class ShellSortExample
{
	public static void main(String[] args)
	{
		ArrayShell arrTest = new ArrayShell(20);

		// for(int j=0; j<maxSize;j++)
		// {
		// 	long n = (int)(java.lang.Math.random()*99);
		// 	arr.insert(n);
		// }
		arrTest.insert(2);
		arrTest.insert(8);
		arrTest.insert(16);
		arrTest.insert(9);
		arrTest.insert(11);
		arrTest.insert(14);
		arrTest.insert(3);
		arrTest.insert(15);
		arrTest.insert(4);
		arrTest.insert(12);
		arrTest.insert(19);
		arrTest.insert(1);
		arrTest.insert(18);
		arrTest.insert(13);
		arrTest.insert(5);
		arrTest.insert(7);
		arrTest.insert(10);
		arrTest.insert(17);
		arrTest.insert(0);
		arrTest.insert(6);

		arrTest.show();
		arrTest.shellSort();
		// arrTest.show();
	}
}
/*
希爾排序可以說是插入排序的加強版，原本的插入排序只往前一格作比較，
這讓排在陣列右端的小數目需要不斷一格一格的往前比較然後往後移動那些比她大的數字，
這會增加排序的時間。例如現在有十個數字做排序，恰巧最小數在最後一位，
那麼在最後一圈時他就需要跟前面九個數字做比較並且移動那九個數字，這是很沒有效率的做法。
而希爾排序基於插入排序，針對的這個缺點做了改良。

希爾排序與插入排序的差異點就在於兩組值間隔數，有了這個間格數，
希爾排序可以讓排在陣列後端的小數目大幅度且快速的移動到陣列前方，
在這個範例中我們所使用的間隔數計算法是 h = h*3+1。這不是唯一的計算法，
有興趣的話可以在網路上找到其他間格數的計算方式。

shellSort是我們執行希爾排序的函式，一開始最重要的步驟就是先計算我們要使用的間隔數。
int h=1;
while(h<= numValues/3)
{
	h= h*3 +1;
}
while迴圈的條件是當我們的陣列長度除以3大於我們的間隔數時，我們就執行h= h*3 +1，
然後再做一次判斷，直到條件不符合才停止。h的初始值為1，
重複執行h= h*3 +1我們會得到序列1,4,13,121.....
序列中的數字代表著兩組進行比較的數字的間隔，如果我們得到的間隔數是13，
那麼我們一開始將會比較陣列中的第13位和第0位然後第14位和第1位......
在某些狀況下，部分中間的數字是不會在間隔中被比較到的。
在比較完13的間隔後，我們會在下面比較迴圈中做縮小間隔的動作h = (h-1)/3，依以上間隔數13的例子，
間隔數將會被縮小為4，這時候會由陣列中的第4位和第0位進行比較然後第5位和第1位進行比較。
當比較的位置到了第8位它將會先和第4位做比較然後和第0位做比較，第9位時它將會先和第5位做比較然後和第1位做比較。
如果到了第16位，間隔是4，那他將會和第12位、第8位、第4位、第0位做比較，
如果遇到比她小的值那就把它們往後移動騰出位置，如果遇到比她大的值或是前方已經沒有值可以比較時那就停下來插入空格。
當間隔數被縮小到1時，那就等於執行基本的插入排序法。只是這時候小的數目都已經被移動到了陣列前端，
大的數目都被移動到了陣列的後端，
所以不會發生一般插入排序中所發生的小數目需要比較以及移動陣列中大部分的值才可以到達他所需要到的位置。

for(out=h; out<numValues; out++)
{
	temp = arrShell[out];
	in = out;

	while(in > h-1 && arrShell[in-h]>=temp)
	{
		arrShell[in] = arrShell[in-h];
		in -=h;
	}
	arrShell[in] = temp;
}
h = (h-1)/3;

執行希爾排序一共需要三層迴圈，最外圈是判斷間隔值，最小的間隔式1，所以必須大於0。
中層迴圈的指標是比較的起始點，會由間隔數開始，然後在每一圈遞增。
在每一圈中我們會把起始點的值放入temp變數中用來比較，然後將起始點的位置放入內迴圈的指標in中。
內回圈是判斷需要用來比較的位置(in-h)、進行比較以及比較後是否需要將前面的數往後h個位置移動。
當條件成立移動位置後，我們便會執行in -=h，這表示in指標會再往前h個位置，如果位置不存在(in小於0)便會停止迴圈，
然後將temp變數得值插入適當的位置。當中層回圈結束後便再次縮小間隔。

範例執行結果如下
以下為我們一開始輸入的值
Array =2 8 16 9 11 14 3 15 4 12 19 1 18 13 5 7 10 17 0 6
當外迴圈第一圈，間隔為13結束後
Array =2 5 7 9 11 0 3 15 4 12 19 1 18 13 8 16 10 17 14 6
在這一圈中比較得的組數分別是
位置13 & 位置0
位置14 & 位置1
位置15 & 位置2
位置16 & 位置3
位置17 & 位置4
位置18 & 位置5
位置19 & 位置6
檢查上述的組數可以發現這些經過比較的組數都已經過排列。
在這邊我們可以發現位置7~12的數字並沒有進行比較，不過沒有關係，因為他們將會在下一圈中進行比較。

當外迴圈第二圈，間隔為4結束後
Array =2 0 3 1 4 5 7 6 10 12 8 9 11 13 14 15 18 17 19 16
在這一圈中比較得的組數分別是
位置4 & 位置0
位置5 & 位置1
位置6 & 位置2
位置7 & 位置3
位置8 & 位置4 & 位置0
位置9 & 位置5 & 位置1
位置10 & 位置6 & 位置2
位置11 & 位置7 & 位置3
位置12 & 位置8 & 位置4 & 位置0
位置13 & 位置9 & 位置5 & 位置1
位置14 & 位置10 & 位置6 & 位置2
位置15 & 位置11 & 位置7 & 位置3
位置16 & 位置12 & 位置8 & 位置4 位置0
位置17 & 位置13 & 位置9 & 位置5 位置1
位置18 & 位置14 & 位置10 & 位置6 位置2
位置19 & 位置15 & 位置11 & 位置7 位置3



當外迴圈第三圈，間隔為1結束後。這時候經過間隔1的排序(基本的插入排序)他已經變成完全有序了。
Array =0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19

*/

































