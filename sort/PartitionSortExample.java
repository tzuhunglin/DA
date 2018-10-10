class ArrayPartition
{
	private long[] arrPartition;
	private int numValues;

	public ArrayPartition(int max)
	{
		arrPartition = new long[max];
		numValues = 0;
	}

	public void insert(long value)
	{
		arrPartition[numValues] = value;
		numValues++;
	}

	public int size()
	{
		return numValues;
	}

	public void show()
	{
		System.out.print("Array = ");
		for(int j=0; j<numValues; j++)
		{
			System.out.print(arrPartition[j]+" ");
		}
		System.out.println("");
	}

	public int partition(int left, int right, long pivot)
	{
		int leftIndex = left -1;
		int rightIndex = right +1;
		while(true)
		{
			while(leftIndex<right&&arrPartition[++leftIndex]<pivot);
			while(rightIndex > left&&arrPartition[--rightIndex]>pivot);
			
			if(leftIndex >= rightIndex)
			{
				break;
			}else{
				swap(leftIndex, rightIndex);
			}
		}
		return leftIndex;
	}

	public void swap(int value1, int value2)
	{
		long temp;
		temp = arrPartition[value1];
		arrPartition[value1] = arrPartition[value2];
		arrPartition[value2] = temp;
		System.out.println("swap("+arrPartition[value2]+","+arrPartition[value1]+")");

	}
}

class PartitionSortExample
{
	public static void main(String[] args)
	{
		ArrayPartition arrTest = new ArrayPartition(20);

		// for(int j= 0; j<maxSize; j++)
		// {
		// 	long n = (int)(java.lang.Math.random()*199);
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
		long pivot = 9;

		System.out.println("Pivot is " + pivot);
		int size = arrTest.size();
		int parIndex = arrTest.partition(0, size-1, pivot);
		System.out.println("Partition is at index " + parIndex);
		arrTest.show();
	}
}

/*
劃分是在排序中所使用的一種分類方式，他不是一種排序法，因為它不會真正的把所有數字完成排序。
但是我們還是在排序法中看到他的身影，因為有些排序法會需要將先將陣列中的數字先粗略地移動到目標的方向。

劃分的關鍵在於一個樞紐值(pivot)，以這個樞紐值為基準，如果我們要做的事由小到大排列的話，
那麼劃分的工作就會將比樞紐值小的數字都放到陣列的左方，比樞紐值大的數字則被放到陣列的右方。
當然，在陣列左方，比樞紐值小的這一群數字和在陣列右方，比樞紐值大的這一群數字都不是有序排列，
因為劃分的工作僅僅是將這些數字有限的歸類以方便後面真正的排序。

在劃分中，樞紐值(pivot)是最重要的，因為我們需要以他來與所有數進行比較。在劃分中，
我們會用到兩層while迴圈，外層一個，內層兩個。外層迴圈不斷的執行，直到符合比較完畢的條件。
而兩個內迴圈的工作則是負責在陣列兩端的位置找出需要交換位置的數值。透過這樣的交換，
我們可以把比樞紐值小的數字都放在陣列的左邊部分，比樞紐值大的數字都放在陣列的右邊部分。

函式partition就是範例中我們執行劃分的部分。
變數left和變數right分別是陣列中第一位和最後一位的位置。
也是我們雙指針leftIndex(左指針)和rightIndex(右指針)在迴圈中的起始位置。
我們在定義leftIndex和rightIndex都分別讓他們-1和+1超過陣列範圍，
leftIndex為-1，rightIndex為20，這是為了配和while迴圈中的++leftIndex和--rightIndex。
雙指針會在迴圈中遞增和遞減往陣列中間方向移動。
當迴圈開始時，兩個內迴圈會分別在陣列的兩端找尋符合條件的值，左指針找尋比樞紐值大的值，
右指針找尋比樞紐值小的值，當兩個指針都找到時，這兩個數就會進行交換。透過不斷的找尋和交換，
所有比樞紐值小的值都會被放在陣列左方，所有比樞紐值大的值都會被方在陣列右方，完成劃分。

範例執行結果如下
以下為我們一開始輸入的值
Array = 2 8 16 9 11 14 3 15 4 12 19 1 18 13 5 7 10 17 0 6
樞紐值設定為9
Pivot is 9
左指針在陣列左邊部分發現數字16大於樞紐職9，以及右指針在陣列左邊部分發現數字6小於樞紐值9，
然後讓他們進行交換
swap(16,6)
左指針在陣列左邊部分發現數字9等於樞紐職9，以及右指針在陣列左邊部分發現數字0小於樞紐值9，
然後讓他們進行交換
swap(9,0)
左指針在陣列左邊部分發現數字11等於樞紐職9，以及右指針在陣列左邊部分發現數字7小於樞紐值9，
然後讓他們進行交換
swap(11,7)
左指針在陣列左邊部分發現數字14等於樞紐職9，以及右指針在陣列左邊部分發現數字5小於樞紐值9，
然後讓他們進行交換
swap(14,5)
左指針在陣列左邊部分發現數字15等於樞紐職9，以及右指針在陣列左邊部分發現數字1小於樞紐值9，
然後讓他們進行交換
swap(15,1)
最後回傳樞紐值劃分的位置，也就是在陣列位置9的左方為小於樞紐值得數字，右方圍大於以及等於樞紐值得數字。
Partition is at index 9
Array = 2 8 6 0 7 5 3 1 4 12 19 15 18 13 14 11 10 17 9 16
*/





























