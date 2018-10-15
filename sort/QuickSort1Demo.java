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
		for(int j=0; j<nElems; j++)
		{
			System.out.print(theArray[j]+ " ");
		}
		System.out.println("");
	}

	public void quickSort()
	{
		recQuickSort(0, nElems-1);
	}

	public void recQuickSort(int left, int right)
	{
		if(right-left <=0)//剩一位返回
		{
			return;
		}else{
			long pivot = theArray[right];//樞紐值設定為最右邊的數字

			int partition = partitionIt(left, right, pivot);//劃分取樞紐值位置
			recQuickSort(left, partition-1);
			recQuickSort(partition+1,right);
		}
	}

	public int partitionIt(int left, int right, long pivot)
	{
		int leftPtr = left-1;//近迴圈在前遞增，一開始會是left的位置
		int rightPtr = right;//近迴圈在前遞減，樞紐職為最右邊的數，右指針近迴圈會在他左邊
		while(true)
		{
			while(theArray[++leftPtr]< pivot);//從左邊開始找比樞紐值大的的數

			while(rightPtr > 0 && theArray[--rightPtr]> pivot);//從樞紐值左邊一位開始找比樞紐值小的數

			if(leftPtr >= rightPtr)//當兩個指針重疊或交叉時停止
			{
				break;
			}else{
				swap(leftPtr, rightPtr);
			}
		}
		swap(leftPtr,right);
		return leftPtr;//此時樞紐值已就位
	}

	public void swap(int dex1, int dex2)
	{
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}
}

class QuickSort1Demo
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
快速排序法是屬於比較複雜的排序法，但是他的效率卻比簡單排序法快得多。快速排序法實現的方式主要是遞迴加上劃分，
在快速排序的範例中，我們將會看到她不斷地使用遞迴和劃分，直到進入函式的組數為一組無法再劃分為止。

public void quickSort()
{
	recQuickSort(0, nElems-1);
}

當進行快速排序時，我們需要呼叫快數排序的函式並且先給他一組完整的參數，就是陣列的第一個位置到最後一個位置。

public void recQuickSort(int left, int right)
{
	if(right-left <=0)//剩一位返回
	{
		return;
	}else{
		long pivot = theArray[right];//樞紐值設定為最右邊的數字

		int partition = partitionIt(left, right, pivot);//劃分取樞紐值位置
		recQuickSort(left, partition-1);
		recQuickSort(partition+1,right);
	}
}

當進入函式時，我們會先判斷所輸入的數量，如果只有一位便無法再劃分，所以返回。但是如果數量大於一位，那就進行劃分排序。
進行劃分的關鍵點在於樞紐值(pivot)，在這個範例中我們已陣列範圍最右端的數字作為樞紐值。取得樞紐值後就進行劃分排序。
當劃分完成後，我們會以樞紐值得位置為中間點，分別在以樞紐值前的範圍和樞紐值後的範圍各自進行更小範圍的劃分。

public int partitionIt(int left, int right, long pivot)
{
	int leftPtr = left-1;//近迴圈在前遞增，一開始會是left的位置
	int rightPtr = right;//近迴圈在前遞減，樞紐職為最右邊的數，右指針近迴圈會在他左邊
	while(true)
	{
		while(theArray[++leftPtr]< pivot);//從0開始找比樞紐值大的的數

		while(rightPtr > 0 && theArray[--rightPtr]> pivot);//從樞紐值左邊一位開始找比樞紐值小的數

		if(leftPtr >= rightPtr)//當兩個指針重疊或交叉時停止
		{
			break;
		}else{
			swap(leftPtr, rightPtr);
		}
	}
	swap(leftPtr,right);
	return leftPtr;//此時樞紐值已就位
}

函式中參數left代表的是這組陣列範圍內的起始位置，參數right代表的是這組陣列範圍的位置，結束位置的直也是我們的樞紐值(pivot)。
leftPtr為左指針，他會在迴圈中遞增向右移動，我們將他預設為left-1，並且在while迴圈中在前遞增，所以他在迴圈中第一圈的值會剛好等於left。
rightPtr為右指針，他會在迴圈中遞減向左移動，他的起始值等於right(樞紐值得位置)，在while迴圈中的第一圈，在前遞減，所以剛好跳過了樞紐值。
pivot在住個迴圈中會不斷地與每一個數比較，然後比樞紐值大的數會被交換到陣列的右方，比樞紐值小的數會被交換到陣列左方(與左指針leftPtr交換)。
leftPtr在遞增的過程中，所經過的位置是所有筆樞紐值小的位置，而rightPtr所經過的位置是所有比樞紐職大的位置，
所以當leftPtr和rightPtr重疊或交叉(leftPtr在右，rightPtr在左)表示這組比較已經完畢，而leftPtr就是樞紐值最後要被交換到的位置，
因為leftPtr的左方就是所有比樞紐值小的數字，並且在這個時候，leftPtr位置所在的值是比樞紐職大，所以可以進行交換。
當歸類好所有的數後，樞紐值也被交換到他的最終位置，就是所有筆樞紐值小的樹的後面以及所有筆樞紐職大的樹的前面。在這個時候，
樞紐值得位置在整個快速排序的過程中就部會在變動了，因為他已經到了他在整組陣列中適當的位置，他已經被完成排序。


public void recQuickSort(int left, int right)
{
	if(right-left <=0)//剩一位返回
	{
		return;
	}else{
		long pivot = theArray[right];//樞紐值設定為最右邊的數字

		int partition = partitionIt(left, right, pivot);//劃分取樞紐值位置
		recQuickSort(left, partition-1);
		recQuickSort(partition+1,right);
	}
}
範例執行結果如下
以下為我們一開始輸入的值，當進入第一層recQuickSort時，樞紐值為10。
13 2 8 9 12 3 4 14 5 7 0 6 11 1 10
接著他們將被劃分排序為1 2 8 9 6 3 4 0 5 7 10 14 12 11 13
在這個時候原本的樞紐職10已經到達他在所有數中排序的位置，然後以10為分割點，將所有數一分為二再進行recQuickSort。
此時共有兩組數進行recQuickSort，分別是 1 2 8 9 6 3 4 0 5 7 和 14 12 11 13。
1 2 8 9 6 3 4 0 5 7的樞紐值為7劃分後會變成1 2 5 0 6 3 4 7 9 8，此時，數字7也到達了他最後的排序位置。
1 2 5 0 6 3 4 7 9 8 這組數字也會再被分割為1 2 5 0 6 3 4 和9 8。
當一組數被劃分到剩下兩位，就是遞迴停止得時刻了，這兩位數都會被放到他們在排序中所需要的位置。
快速排序法的地滑化糞就是不斷地以劃分的方式把所有數字往他們應該去的方向移動，當地回越多層時，這些數字都會越來越接近他們的位置，
直到最後一層剩兩個數字時，他們就會被放入他們最後的位置。



*/

















