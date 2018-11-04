import java.io.*;

class DataItem
{
	public long dData;

	public DataItem(long dd)
	{
		dData = dd;
	}

	public void displayItem()
	{
		System.out.print('/'+dData);
	}
}

class Node
{
	private static final int ORDER = 4;
	private int numItems;//數據數量
	private Node parent;//父結點
	private Node childArray[] = new Node[ORDER];//子結點陣列
	private DataItem itemArray[] = new DataItem[ORDER-1];//節點數據陣列

	public void connectChild(int childNum, Node child)
	{
		childArray[childNum] = child;//將子結點放入子結點陣列
		if(child != null)
		{
			child.parent = this;//將子結點的parent屬性連結父結點
		}
	}

	public Node disconnectChild(int childNum)
	{
		Node tempNode = childArray[childNum];//將要分離的子結點放入變數tempNode
		childArray[childNum] = null;//將原本他在陣列中的位置設為null
		return tempNode;//回傳要刪除的子結點
	}

	public Node getChild(int childNum)
	{
		return childArray[childNum];//回傳子結點
	}

	public Node getParent()
	{
		return parent;	//回傳父節點
	}

	public boolean isLeaf()
	{
		return (childArray[0]==null)?true:false;//回傳是否為葉節點
	}

	public int getNumItems()
	{
		return numItems;//回傳節點數據數量
	}

	public DataItem getItem(int index)
	{
		return itemArray[index];//取得特定數據
	}

	public boolean isFull()
	{
		return (numItems == ORDER-1)?true:false;//回傳節點是否滿了
	}

	public int findItem(long key)
	{
		for(int j=0; j<ORDER; j++)
		{
			if(itemArray[j]==null)//如果陣列中數據的值為null表示後面都沒值了	
			{
				break;//停止迴圈
			}
			else if (itemArray[j].dData ==key)//如果找到目標值
			{
				return j;//回傳找到的位置
			}
		}
		return -1;//回傳-1表示沒找到
	}

	public int insertItem(DataItem newItem)
	{
		numItems++;//結點數據量增加
		long newKey = newItem.dData;

		for(int j=ORDER-2; j>=0;j--)//在數據陣列中由後往前找進行比較
		{
			if(itemArray[j]==null)//還沒找到
			{
				continue;
			}
			else//找到有值得數據
			{
				long itsKey = itemArray[j].dData;
				if(newKey < itsKey)//如果新數據比較小
				{
					itemArray[j+1] = itemArray[j];//把原本的數據往後移
				}
				else//如果新數據比較大
				{
					itemArray[j+1] = newItem;//比新數據排在這項數據後面
					return j+1;//回傳新數據的位置
				}
			}
		}
		itemArray[0] = newItem;//將新數據放入陣列中0的位置
		return 0;//回傳0
	}

	public DataItem removeItem()
	{
		DataItem temp = itemArray[numItems-1];//將陣列中最後一個位置放入變數temp
		itemArray[numItems-1] = null;//將最後一個位置設為null
		numItems--;//將數量減1
		return temp;//回傳temp
	}

	public void displayNode()
	{
		for(int j=0; j<numItems; j++)
		{
			itemArray[j].displayItem();
		}
		System.out.println("/");
	}
}

class Tree234
{
	private Node root = new Node();
	public int find(long key)
	{
		Node curNode = root;//將根節點設為一開始的curNode
		int childNumber;//宣告childNumber
		while(true)
		{
			if((childNumber=curNode.findItem(key))!=-1)//如果childNumber不等於-1表示找到目標值的位置
			{
				return childNumber;//回傳目標值在陣列中的索引
			}
			else if(curNode.isLeaf())//如果現在的節點為葉節點，表示後面已經沒有節點
			{
				return -1;//沒找到回傳-1
			}
			else
			{
				curNode = getNextChild(curNode, key);//將curNode社為下一個子結點
			}
		}
	}

	public Node getNextChild(Node theNode, long theValue)
	{
		int j;
		int numItems = theNode.getNumItems();//取得數據項數量
		for(j=0; j<numItems;j++)//從數據項第一個位置開始
		{
			if(theValue <theNode.getItem(j).dData)//如果數據向值大於新數據值
			{
				return theNode.getChild(j);//回傳他的子結點(0~2)
			}
		}
		return theNode.getChild(j);//回傳最後一項子結點(3)
	}

	public void insert(long dValue)
	{
		Node curNode = root;//將根節點設為一開始的curNode
		DataItem tempItem = new DataItem(dValue);//將tempItem設為新數據節點

		while(true)
		{
			if(curNode.isFull())//如果節點已經滿了
			{
				split(curNode);//進行節點分裂
				curNode = curNode.getParent();
				curNode = getNextChild(curNode,dValue);
			}
			else if(curNode.isLeaf())//如果節點事葉節點而且沒有滿
			{
				break;//停止，只有在葉節點停止後才插入新數據
			}
			else//結果節點沒有滿也不是葉節點
			{
				curNode = getNextChild(curNode, dValue);
			}
		}

		curNode.insertItem(tempItem);//將新數據插入節點
	}

	public void split(Node thisNode)
	{
		DataItem itemB, itemC;
		Node parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem();//從最後面開始刪，回傳位置3
		itemB = thisNode.removeItem();//從最後面開始刪，回傳位置2
		child2 = thisNode.disconnectChild(2);//切斷位置2的子結點並放入child2
		child3 = thisNode.disconnectChild(3);//切斷位置3的子結點並放入child3
		Node newRight = new Node();//宣告新的右節點

		if(thisNode == root)//如果當前節點是根節點
		{
			root = new Node();//將跟節點設為新結點
			parent = root;//將parrent設為根節點
			root.connectChild(0, thisNode);//將跟節點的第1個子結點設為這個節點
		}
		else
		{
			parent = thisNode.getParent();
		}

		itemIndex = parent.insertItem(itemB);//將itemB插入父結點並環傳他的位置
		int n = parent.getNumItems();//將n設為父節點的數據數量

		for(int j=n-1; j>itemIndex; j--)
		{
			Node temp = parent.disconnectChild(j);//將子結點斷開並放入temp
			parent.connectChild(j+1, temp);//將子結點設為原本的位置+1
		}

		parent.connectChild(itemIndex+1, newRight);

		newRight.insertItem(itemC);//將itemC插入新節點
		newRight.connectChild(0, child2);//將原本的child2連接至新節點
		newRight.connectChild(1, child3);//將原本的child3連接至新節點
	}

	

	public void displayTree()
	{
		recDisplayTree(root, 0, 0);
	}

	private void recDisplayTree(Node thisNode, int level, int childNumber)
	{
		System.out.print("level="+level+" child="+ childNumber+ " ");
		thisNode.displayNode();

		int numItems = thisNode.getNumItems();
		for(int j=0; j<numItems+1; j++)
		{
			Node nextNode = thisNode.getChild(j);
			if(nextNode != null)
			{
				recDisplayTree(nextNode, level+1, j);
			}
			else
			{
				return;
			}
		}
	}
}

class Tree234Demo
{
	public static void main(String[] args) throws IOException
	{
		long value;
		Tree234 theTree = new Tree234();

		theTree.insert(50);
		theTree.insert(40);
		theTree.insert(60);
		theTree.insert(30);
		theTree.insert(70);
		
		while(true)
		{
			System.out.print("Enter first letter of ");
			System.out.print("show, insert, or find: ");
			char choice = getChar();
			switch(choice)
			{
				case 's':
					theTree.displayTree();
					break;
				case 'i':
					System.out.print("Enter value to insert: ");
					value = getInt();
					theTree.insert(value);
					break;
				case 'f':
					System.out.print("Enter value to find: ");
					value = getInt();
					int found = theTree.find(value);
					if(found != -1)
					{
						System.out.print("Found "+value);
					}
					else
					{
						System.out.println("Cound not find "+value);
					}
					break;
				default:
					System.out.print("Invalid entry\n");
			}
		}
	
	}

	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}

}


























