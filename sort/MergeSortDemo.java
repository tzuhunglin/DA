class DArray
{
	private long[] theArray;
	private int nElems;

	public DArray(int max)
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
		for(int j=0; j<nElems; j++)
		{
			System.out.print(theArray[j]+" ");
		}
		System.out.println("");
	}

	public void mergeSort()
	{
		long[] workSpace = new long[nElems];
		recMergeSort(workSpace, 0, nElems-1);
	}

	private void recMergeSort(long[] workSpace, int lowerBound, int upperBound)
	{
		if(lowerBound == upperBound)
		{
			return;
		}else{
			int mid = (lowerBound+upperBound)/2;//取整數
			
			recMergeSort(workSpace, lowerBound, mid);
			recMergeSort(workSpace, mid+1, upperBound);
			merge(workSpace, lowerBound, mid+1, upperBound);
		}
	}

	private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound)
	{
		int j = 0;
		int lowerBound = lowPtr;//lowerBound 底限，lowPtr低指針
		int mid = highPtr-1;//高指針往後走 mid是低指針上限
		int n = upperBound-lowerBound+1;//組數

		while(lowPtr <= mid && highPtr <= upperBound)//lowPtr由lowerBound 走至mid，highPtr由自己的位置走向highBound
		{
			if(theArray[lowPtr] < theArray[highPtr])//如果lowPtr比較小
			{
				workSpace[j++] = theArray[lowPtr++];//lowPtr位置的值放入新陣列
			}else{//如果highPtr位置的值比較小
				workSpace[j++] = theArray[highPtr++];//highPtr位置的值放入陣列
			}
		}//其中一方走完全部組數lowPtr超過mid或著highPtr超過upperBound

		while(lowPtr <= mid)//如果lowPtr還沒超過mid就把剩下位置的組數放入新陣列
		{
			workSpace[j++] = theArray[lowPtr++];
		}

		while(highPtr <= upperBound)//如果highPtr還沒超過upperBound就把剩下位置的組數放入新陣列
		{
			workSpace[j++] = theArray[highPtr++];
		}

		for(j=0; j<n; j++)
		{
			theArray[lowerBound+j] = workSpace[j];//將新陣列中的值照原本範圍位置塞回原本陣列
		}

		// for(int q=lowerBound; q<upperBound; q++)
		// {
		// 	System.out.print(workSpace[q]+" ");
		// }
		// System.out.println("");
		// display();
	}
}

class MergeSortDemo
{
	public static void main(String[] args)
	{
		int maxSize = 100;
		DArray arr;
		arr = new DArray(maxSize);
		arr.insert(2);
		arr.insert(8);
		arr.insert(9);
		arr.insert(3);
		arr.insert(4);
		arr.insert(1);
		arr.insert(5);
		arr.insert(7);
		arr.insert(0);
		arr.insert(6);

		arr.display();
		arr.mergeSort();
		arr.display();
	}
}

/*
合併排序是一種通過遞迴而完成排序的方法，在遞迴的過程中，原本的陣列會一直被一分為二直到剩下兩位數才開始進行排序。當這兩位數排序完成時，
會在執行最接近的兩位數進行排列(在某些狀況下也有可能是一位，如果是一位的話就不進行排列。)，當這兩位數也排列完成時，兩組以排序好的兩個數字會回到上一層進行四個數字的排列，
依此類推一直回到最上層就是兩組陣列長度除以二的陣列進行排列。

public void mergeSort()
{
	long[] workSpace = new long[nElems];
	recMergeSort(workSpace, 0, nElems-1);
}

當執行合併排列時我們會需要一組和原本陣列長度一樣的得空陣列(workSpace)供我們儲存值已進行排列。

private void recMergeSort(long[] workSpace, int lowerBound, int upperBound)
{
	if(lowerBound == upperBound)//表示只有一組數字進入此函式，無法比較，所以return。
	{
		return;
	}else{
		int mid = (lowerBound+upperBound)/2;//取得中間值
		
		recMergeSort(workSpace, lowerBound, mid);//在取得中間值後將陣列範圍的前半部分在呼叫同函式進行分割。
		recMergeSort(workSpace, mid+1, upperBound);//在取得中間值後將陣列範圍的後半部分在呼叫同函式進行分割。
		merge(workSpace, lowerBound, mid+1, upperBound);//進行排序
	}
}

合併排序的第一步就是取得列長度的中間值(mid)，並且將陣列分為兩個部分並且再次執行同樣的函式在取得中間值再分為兩個部分，直到剩下最後的一位才停止。
然後會將這兩個只剩下一位被return數字進行最排列。當這兩個數字排序完成時，他們會回到上一層並且跟另一組已排序的兩個數字進行四個數字的排序。
也有可能是一個數字，如果是一個數字就進行三個數字的排列。當他們再回到更上層時，他們將會進行更多數字的排列。 


private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound)
{
	int j = 0;
	int lowerBound = lowPtr;//lowerBound 底限，lowPtr低指針
	int mid = highPtr-1;//高指針往後走 mid是低指針上限
	int n = upperBound-lowerBound+1;//組數

	while(lowPtr <= mid && highPtr <= upperBound)//lowPtr由lowerBound 走至mid，highPtr由自己的位置走向highBound
	{
		if(theArray[lowPtr] < theArray[highPtr])//如果lowPtr比較小
		{
			workSpace[j++] = theArray[lowPtr++];//lowPtr位置的值放入新陣列
		}else{//如果highPtr位置的值比較小
			workSpace[j++] = theArray[highPtr++];//highPtr位置的值放入陣列
		}
	}//其中一方走完全部組數lowPtr超過mid或著highPtr超過upperBound

	while(lowPtr <= mid)//如果lowPtr還沒超過mid就把剩下位置的組數放入新陣列
	{
		workSpace[j++] = theArray[lowPtr++];
	}

	while(highPtr <= upperBound)//如果highPtr還沒超過upperBound就把剩下位置的組數放入新陣列
	{
		workSpace[j++] = theArray[highPtr++];
	}

	for(j=0; j<n; j++)
	{
		theArray[lowerBound+j] = workSpace[j];//將新陣列中的值照原本範圍位置塞回原本陣列
	}

	// for(int q=lowerBound; q<upperBound; q++)
	// {
	// 	System.out.print(workSpace[q]+" ");
	// }
	// System.out.println("");
	// display();
}
merge函式是我們進行排序的地方，在這邊我們可以看到很多的變數，	分別為j，j的起始值為0，主要用來在迴圈中做遞增的工作;
lowerBound為底限，就是這組陣列範圍的最小值(最左邊的位置);
upperBound為上限，就是這組陣列範圍的最大值(最右邊的位置); 
lowPtr為低指針，他會由lowerBound的位置遞增向右移動，負責檢視陣列左半部分的數值。
mid為中間值，也是左指針的上限也是左指針可以移動的最後位置。
highPtr為高指針，會由mid+1的位置開始向右移動，負責檢視陣列右半部分的數值。
upperBound為這組函式中這組陣列的最後位置。

當開始進行排序時，我們會將這組陣列的範圍分成兩半，並且從他們的最左邊數字進行比較。
接著我們會將比較小的數字放入空陣列workspace。
假我的現在總共要比較的數字是 2 4 5 1 3 6。
一開始2會和1比較，1比較小，就放入workSpace。而此時因為是右半部的數字被放入workSpace中，所以高指針的直會增加，低指針的直不變。
再來就是比較2和3，此時2比較小，所以2會被放入workSpace中。然後再下一圈3會被留下來繼續比較。
這樣的比較會一直持續到左半部或右半部其中一方所有的值都被放入workSpace中。
接著我們會再把剩下得值依序放入workSpace中，最後再放入原本陣列的範圍中。
有些人可能會問，當前面的比較完畢時，後面的數字直接依序放入workSpace中難道不會造成沒有排序好的問題嗎？
其實不會，因為在合併排序中， 遞迴會一直將這些數字分割到最小部分做排序，所以在每一層中(除了最後一層一開始)，
我們將範圍中的數字分為兩半都可以發現他們已經是有序排列。就像 2 4 5 和 1 3 6，所以最後剩下沒有比較到的數字可以直接依序放入workSpace，
並不會造成亂序的問題。再將所有的數字放入workSpace後，我們會再將它們放入他原本陣列相對應的位置。
			
範例執行結果如下
以下為我們一開始輸入的值
2 8 9 3 4 1 5 7 0 6

經過第一層切割，我們所得到的兩組數是 2 8 9 3 4 和 1 5 7 0 6
經過第二層切割，我們所得到的四組數是 2 8 9 和 3 4 和 1 5 7 和 0 6。
其中 2 8 9 和 1 5 7 還會再進行切割為 2 8 和 9 以及 1 5 和 7。
最後終切割的樣子是(2 8)(9)(3 4)(1 5)(7)(0 6)。 
當切至最底層做先執行排序的是 2 8 這一組。當排序完成後返回至上一層和9進行排序，接者執行 3 4的排序，排序後再返回上一層和2 8 9進行排序。
當左邊組數都排續完時，就會接著由右半部最左邊的組數1 5進行排序。因為 7 只有一位不會進行排序而會直接被return 回上一層和排序後的1 5進行三個數字的排序。
當1 5 7排序完成後，會進行 0 6的排序，之後返回上一層和 1 5 7進行排序。到最後會由左半邊的已排序五個數字2 3 4 8 9 和右半邊的已排序五個數字 0 1 5 6 7進行最後的排序。
在這時候我們也可以很清楚的看得出來為什麼在排序的函式中，為什麼有些沒有被比較到的數字可以不經過比較而直接放入陣列中而不會造成亂序了。


*/
























