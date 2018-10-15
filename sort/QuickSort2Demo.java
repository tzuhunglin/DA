class ArrayIns
{
	private long[] theArray;
	private int nElems;

	public ArrayIns(int max)
	{
		theArray = new long[max];
		nElems = 0;
	}

	public void insert(long value)
	{
		theArray[nElems] = value;
		nElems++;
	}

	public void display()
	{
		System.out.print("A=");
		for(int j=0; j<nElems;j++)
		{
			System.out.print(theArray[j]+" ");
		}
		System.out.println("");
	}

	public void quickSort()
	{
		recQuickSort(0,nElems-1);
	}

	public void recQuickSort(int left, int right)
	{
		int size = right-left+1;
		if(size <=3)
		{
			manualSort(left,right);//手動劃分，處理3個以下的數據
		}else{
			long median = medianOf3(left,right);//取得midian時，三個數字的最小樹會在最左邊，最爸數會在最右邊，中間數會在最大數的左邊作為樞紐值。
			int partition = partitionIt(left,right, median);
			recQuickSort(left, partition -1);
			recQuickSort(partition+1,right);
		}
	}

	public long medianOf3(int left, int right)
	{

		int center = (left+right)/2;//找出中間值的位置
		if(theArray[left] > theArray[center])//如果最左邊的值大於中間值便進行交換
		{
			swap(left,center);
		}

		if(theArray[left] > theArray[right]){//如果最左邊的值大於最右邊的值便進行交換，此時，三個數中最小的數字會被放置在最左邊的位置。
			swap(left,right);
		}

		if(theArray[center]> theArray[right])//如果中間值大於最右邊的值便進行交換，此時，中間值和最大的樹都會被各自放到中間和最後一位。
		{
			swap(center,right);
		}

		swap(center,right-1);//將中間值放至right-1的位置，變回傳作為樞紐值。
		return theArray[right-1];
	}
	public int partitionIt(int left, int right, long pivot)
	{
		int leftPtr = left;//在執行medianOf3函式後，最左邊的值已經是最小值，在這裡將左指針社為起始位置left，在while迴圈第一圈中，
						   //leftPtr將會在前遞增，由left+1的位置開始。
		int rightPtr = right-1;//在執行medianOf3函式後，最右邊的right的位置為最大值，right-1為樞紐值，
							   //在這裡一開始我們把又指針的位置設為樞紐值的位置，以便迴圈中在前遞減時由樞紐值左邊的數開始。

		while(true)
		{
			while(theArray[++leftPtr]<pivot);//從左邊開始找比樞紐值大的的數
			while(theArray[--rightPtr]>pivot);//從樞紐值左邊一位開始找比樞紐值小的數
			if(leftPtr >= rightPtr)//當兩個指針重疊或交叉時停止
			{
				break;
			}else{
				swap(leftPtr,rightPtr);
			}
		}
		swap(leftPtr,right-1);
		return leftPtr;//此時樞紐值已就位，並回傳樞紐值的位置。
	}



	public void swap(int dex1, int dex2)
	{
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}

	

	public void manualSort(int left, int right)
	{
		int size = right-left+1;//繼換算陣列範圍數量
		if(size <=1)//如果只有一位，就回傳。
		{
			return;
		}

		if(size ==2){//兩位時，進行兩位比較，有駒要的話就交換位置。
			if(theArray[left]>theArray[right])
			{
				swap(left,right);
			}
			return;
		}else{
			if(theArray[left] > theArray[right -1])//第一位先跟第二位比較，有需要就交換位置
			{
				swap(left,right-1);
			}
			if(theArray[left]>theArray[right])//第一位在跟第二位比較，有需要就交換位置，交換完成後第一位的位置已經是最小的數。
			{
				swap(left,right);
			}
			if(theArray[right-1] > theArray[right])//當第一位已經是最小的數時，在比較後面兩位數。
			{
				swap(right-1, right);
			}
		}
	}
}

class QuickSort2Demo
{
	public static void main(String[] args)
	{
		int maxSize = 16;
		ArrayIns arr;
		arr = new ArrayIns(maxSize);
		arr.insert(13);
		arr.insert(2);
		arr.insert(8);
		arr.insert(9);
		arr.insert(12);
		arr.insert(3);
		arr.insert(4);
		arr.insert(14);
		arr.insert(5);
		arr.insert(7);
		arr.insert(0);
		arr.insert(6);
		arr.insert(11);
		arr.insert(1);
		arr.insert(10);



		arr.display();
		arr.quickSort();
		arr.display();

	}
}


/*
快速排序法中一個很重要的部分就是在範圍內選出一個樞紐值並對樞紐值以外的數進行劃分，在QuickSort1Demo中，
我們選取樞紐值得方式是選取範圍內最右邊的值做為樞紐值。這種選取樞紐值的方式是最方便的，因為不需要執行太多的運算，
然而在某些狀況下，這種方式會使劃分的效率下降。例如，範圍內最右邊的數為範圍中最大的數或最小的數。在這種狀況下，
這一次的劃分就等於指定位了樞紐值，而對於其他數，並沒有有效的進行劃分以及讓他們往目標方向移動。

因此，在QuickSort2Demo中，我們將以另外一種方式來選取樞紐值，三數據取中。
三數據曲中的方式就是以陣列範圍中的最左邊一位、中間位以及最右邊位，三項進行比較，然後取書介於中間的直作為樞紐。
這種做法雖然無法保證一定可以取道範圍中介於最中間的值，但是可以避免取到最大或最小的值作為樞紐值。除此之外，
當陣列範圍小於3時，我們將不在進行劃分，而是以手動排列(manualSort)的方式進行排列。

public void quickSort()
{
	recQuickSort(0,nElems-1);
}

當進行快速排序時，我們需要呼叫快數排序的函式並且先給他一組完整的參數，就是陣列的第一個位置到最後一個位置。

public void recQuickSort(int left, int right)
{
	int size = right-left+1;
	if(size <=3)
	{
		manualSort(left,right);//手動劃分，處理3個以下的數據
	}else{
		long median = medianOf3(left,right);//取得midian時，三個數字的最小樹會在最左邊，最爸數會在最右邊，中間數會在最大數的左邊作為樞紐值。
		int partition = partitionIt(left,right, median);
		recQuickSort(left, partition -1);
		recQuickSort(partition+1,right);
	}
}

當進入函式時，我們會先判斷所輸入的數量。如果數量大於三，我們就會進行三數據取中以及劃分。

public long medianOf3(int left, int right)
{
	int center = (left+right)/2;//找出中間值的位置
	if(theArray[left] > theArray[center])//如果最左邊的值大於中間值便進行交換
	{
		swap(left,center);
	}

	if(theArray[left] > theArray[right]){//如果最左邊的值大於最右邊的值便進行交換，此時，三個數中最小的數字會被放置在最左邊的位置。
		swap(left,right);
	}

	if(theArray[center]> theArray[right])//如果中間值大於最右邊的值便進行交換，此時，中間值和最大的樹都會被各自放到中間和最後一位。
	{
		swap(center,right);
	}

	swap(center,right-1);//將中間值放至right-1的位置，變回傳作為樞紐值。
	return theArray[right-1];
}

在執行三數據取中時，我們需要一組範圍，left為最左邊的位置以及right為最右邊的位置，接著以這兩個數來計算中間的位置。
計算完成後，就開始比較這三個位置的數然後進行排列。排列完成後我們會將三個數中最小的數放在最左邊的位置、
介於中間的數放在中間的位置以及最大的數放在最右邊的位置。在這個時候，最左邊以及最右邊的數已經完成歸類，
他們已經在介於中間的值的左邊以及右邊(只是完成歸類，他們還沒被排序到最後的位置。)。
接著我們會將接於中間的值放置到right-1位以供接下來的partitionIt函式作為樞紐值使用。


public int partitionIt(int left, int right, long pivot)
{
	int leftPtr = left;//在執行medianOf3函式後，最左邊的值已經是最小值，在這裡將左指針社為起始位置left，在while迴圈第一圈中，
					   //leftPtr將會在前遞增，由left+1的位置開始。
	int rightPtr = right-1;//在執行medianOf3函式後，最右邊的right的位置為最大值，right-1為樞紐值，
						   //在這裡一開始我們把又指針的位置設為樞紐值的位置，以便迴圈中在前遞減時由樞紐值左邊的數開始。

	while(true)
	{
		while(theArray[++leftPtr]<pivot);//從左邊開始找比樞紐值大的的數
		while(theArray[--rightPtr]>pivot);//從樞紐值左邊一位開始找比樞紐值小的數
		if(leftPtr >= rightPtr)//當兩個指針重疊或交叉時停止
		{
			break;
		}else{
			swap(leftPtr,rightPtr);
		}
	}
	swap(leftPtr,right-1);
	return leftPtr;//此時樞紐值已就位，並回傳樞紐值的位置。
}

這裡的函式partitionIt的執行方式與QuickSort1Demo中的函式partitionIt相當類似，差別在於這裡的函式partitionIt需要配合已執行的medianOf3。
所以在這裡樞紐值得位置是right-1，並且因為最左邊的數和在最右邊的數已經在medianOf3中完成歸類，所以這裡的劃分將不會對他們進行比較以及交換。
在迴圈開始時，我們的左指針會從left+1的位置開始遞增，右指針會從樞紐值左邊一位開始遞減，並且找尋適當的數字進行交換。
當迴圈結束時，我們會將範圍中比樞紐值小的數都放在陣列範圍中的左端，以及所有比樞紐職大的數都放在他們(比樞紐值小的數)後面。
此時樞紐值將與比樞紐值大的數的最左邊那一位交換位置，那也是樞紐值完成排序的位置。而與樞紐值交換的那一個數字呢？
因為他本身就比樞紐值大，所以對於他來說被交換到樞紐值右方是他適當的位置，他將在接下來更小的劃分中被排入適當的位置。
函式的最後將會回傳樞紐值得位置，讓下一層的recQuickSort找到他們參數的範圍。


public void manualSort(int left, int right)
{
	int size = right-left+1;//繼換算陣列範圍數量
	if(size <=1)//如果只有一位，就回傳。
	{
		return;
	}

	if(size ==2){//兩位時，進行兩位比較，有駒要的話就交換位置。
		if(theArray[left]>theArray[right])
		{
			swap(left,right);
		}
		return;
	}else{
		if(theArray[left] > theArray[right -1])//第一位先跟第二位比較，有需要就交換位置
		{
			swap(left,right-1);
		}
		if(theArray[left]>theArray[right])//第一位在跟第二位比較，有需要就交換位置，交換完成後第一位的位置已經是最小的數。
		{
			swap(left,right);
		}
		if(theArray[right-1] > theArray[right])//當第一位已經是最小的數時，在比較後面兩位數。
		{
			swap(right-1, right);
		}
	}
}
如果陣列範圍只有一位，無需排列就直接回傳。如果陣列範圍為兩位或三位，recQuickSort將進行手動排序manualSort。
在這個快速排序的範例中只有manualSort會真正的執行最後的排序動作，
在manualSort中的排序和partitionIt中最後pivot交換到的位置一樣，都是這些數最後的位置。
partitionIt雖然可以將樞紐值定位(排至他最後需要去的位置)，但是對於樞紐值以外的數他們只能將其移動至特定的方向而已。當然，在這過程中，
有些數可能剛好的被排在了適當的位置。

範例執行結果如下
以下為我們一開始輸入的值，當進入第一層recQuickSort時，size為15。
13 2 8 9 12 3 4 14 5 7 0 6 11 1 10
接著執行這一層的midianOf3，
(center值為7,我們抽出13 4 10 做三數據取一，將4和13定位後將10交換到right-1的位置)
結果為
4 2 8 9 12 3 1 14 5 7 0 6 11 10 13
接者對者組數進行劃分，樞紐值為10。
4 2  8 9 6 3 1 0 5 7 10 12 11 14 13
在這時候，樞紐值10已經找到她在全範圍排序中的最後位置。所以接下來的前半部recQuickSort和後半部recQuickSort都會避開它。
接下來的兩組recQuickSort分別是範圍0~9和範圍13~14。
第一組範圍0~9的recQuickSort因為數量夠大，所以還會再進行三數據取一和劃分，如果他們在劃分完之後的組數還是超過三位，
那這樣的動作會一直持續下去。
而範圍13~14這組recQuickSort將會進入manualSort函式進行最後的排列。
透過不段的由三組數取樞紐值和劃分，所有的數都會一直被移動到靠近最終位置的範圍，當劃分到剩下三個連續位置的數字時，
這三個連續位置的數字的最終排序位置也必定在這三個位置上，所以這時候我們可以用manualSort來比較他們的值並交換他們的位置。

















*/




































