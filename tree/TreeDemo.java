import java.io.*;
import java.util.*;
class Node
{
	public int iData;
	public double dData;
	public Node leftChild;
	public Node rightChild;

	public void displayNode()
	{
		System.out.print("{");
		System.out.print(iData);
		System.out.print(", ");
		System.out.print(dData);
		System.out.print("} ");
	}
}

class Tree
{
	private Node root;

	public Tree()
	{
		root = null;
	}

	public Node find(int key)
	{
		Node current = root;//將變數current指向root的位置
		while(current.iData != key)//當current的iData不等於目標值key
		{
			if(key<current.iData)//如果目標值小魚當前節點的iData
			{
				current = current.leftChild;//將當前節點指向當前節點的左子結點
			}
			else//如果目標值小魚當前節點的iData
			{
				current = current.rightChild;//將當前節點指向當前節點的右子結點
			}
			if(current == null)//如果當前節點已經是null表示沒有這個節點
			{
				return null;//返回null
			}
		}
		return current;
	}

	public void insert(int id, double dd)
	{
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;

		if(root ==null)//如果根節點為null表示這棵樹是空的
		{
			root = newNode;//將新結點放入根節點
		}
		else//如果這棵樹不為空	
		{
			Node current = root;//將當前節點指向跟節點所指向的位置
			Node parent;//宣告父結點
			while(true)
			{
				parent = current;//將父節點指向當前節點
				if(id<current.iData)//如果新數據的小於當前節點的iData
				{
					current = current.leftChild;//將變數current指向當前節點的左子結點
					if(current == null)//如果current為空
					{
						parent.leftChild = newNode;//將parent所指向位置的左子結點指向新結點
						return;
					}
				}
				else//如果新數據的大於當前節點的iData
				{
					current = current.rightChild;;//將變數current指向當前節點的右子結點
					if(current == null)//如果current為空
					{
						parent.rightChild = newNode;//將parent所指向位置的右子結點指向新結點
						return;
					}
				}
			}
		}
	}

	public boolean delete(int key)
	{
		Node current = root;//變數current為迴圈中的當前節點，一開始先指向根節點
		Node parent = root;//變數parent為迴圈中的當前節點的父節點，一開始先指向根節點
		boolean isLeftChild = true;//判斷是否為左子結點，一開始設為true

		while(current.iData != key)//當當前節點的iData不等於key值時迴圈執行
		{
			parent = current;//將變數parent指向current的位置
			if(key < current.iData)//如果key值小於current的iData
			{
				isLeftChild = true;//是否為左子結點設為true
				current = current.leftChild;//將current指向current的左子結點
			}
			else//如果key值大於current的iData
			{
				isLeftChild = false;//是否為左子結點設為false
				current = current.rightChild;//將current指向current的右子結點
			}
			if(current == null)//如果current為null，表示找不到目標節點
			{
				return false;//返回false
			}
		}

		if(current.leftChild==null && current.rightChild==null)//如果目標節點下沒有任何節點
		{
			if(current == root)//目標節點又是根節點
			{
				root =null;//直接將跟節點設為null
			}
			else if(isLeftChild)//如果目標節點是左子結點
			{
				parent.leftChild=null;//將目標節點的父節點的左子結點設為null
			}
			else//如果目標節點不是左子結點
			{
				parent.rightChild = null;//將目標節點的父節點的右子結點設為null
			}
		} 
		else if(current.rightChild== null)//如果目標節點的右子節點為空，左子結點有東西
		{
			if(current == root)//如果目標節點為根節點
			{
				root=current.leftChild;	//目標節點的左子結點設為跟節點
			}
			else if(isLeftChild)//如果目標節點為左子結點
			{
				parent.leftChild = current.leftChild;//將父節點的佐子結點街上自己的左子結點
			}
			else//如果目標節點為右子結點
			{
				parent.rightChild = current.leftChild;//將父節點的右子結點街上自己的左子結點
			}
		}
		else if(current.leftChild==null)//如果目標節點的左子節點為空，右子結點有東西
		{
			if(current == root)//如果目標節點為根節點
			{
				root = current.rightChild;//目標節點的右子結點設為跟節點
			}
			else if(isLeftChild)//如果目標節點為左子結點
			{
				parent.leftChild = current.rightChild;//將父節點的佐子結點街上自己的右子結點
			}
			else//如果目標節點為右子結點
			{
				parent.rightChild = current.rightChild;//將父節點的右子結點街上自己的右子結點
			}
		}
		else//如果目標節點有左右兩個子結點
		{
			Node successor = getSuccessor(current);//呼叫函式getSuccessor(current)取得繼承節點
			if(current == root)//如果目標節點為根節點
			{
				root = successor;//將繼承節點設為根節點
			}
			else if(isLeftChild)//如果目標結點是左子結點
			{
				parent.leftChild = successor;//將根父節點的左子結點設為繼承節點
			}
			else//如果目標結點是右子結點
			{
				parent.rightChild = successor;//將根父節點的右子結點設為繼承節點
			}
			successor.leftChild = parent.leftChild;//最後將繼承節點的左子結點設為當前節點父節點的左子結點
		}
		return true;
	}

	private Node getSuccessor(Node delNode)
	{
		Node successorParent = delNode;//將變數successorParent指向要刪無的節點
		Node successor = delNode;//將變數successor指向要刪除的節點
		Node current = delNode.rightChild;//將變數current指向要刪除節點的右節點

		while(current != null)//當變數current不為null時
		{
			successorParent = successor;//將變數successorParent指向successor
			successor = current;//將變數successor指向current
			current = current.leftChild;//將變數current指向current的右節點
		}

		if(successor!= delNode.rightChild)//如果繼承點為刪除點的右子節點
		{
			successorParent.leftChild = successor.rightChild;//繼承點的父節點設為繼承點的右子節點
			successor.rightChild = delNode.rightChild;//繼承點的右子節點設為刪除點的右子節點
		}
		return successor;//回傳繼承點
	}

	public void traverse(int traverseType)
	{
		switch(traverseType)
		{
			case 1: System.out.print("\nPreorder traversal");
					preOrder(root);
					break;
			case 2: System.out.print("\nInorder traversal");
					inOrder(root);
					break;
			case 3: System.out.print("\nPostorder traversal");
					postOrder(root);
					break;
		}
		System.out.println();
	}

	private void preOrder(Node localRoot)
	{
		System.out.print(localRoot.iData+" ");
		preOrder(localRoot.leftChild);
		preOrder(localRoot.rightChild);
	}

	private void inOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}

	private void postOrder(Node localRoot)
	{
		if(localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}

	public void displayTree()
	{
		Stack<Node> globalStack = new Stack<>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(".....................................");
		while(isRowEmpty == false)
		{
			Stack<Node> localStack = new Stack<>();
			isRowEmpty = true;
			for(int j=0; j<nBlanks; j++)
			{
				System.out.print(" ");
			}

			while(globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if(temp.leftChild != null || temp.rightChild != null)
					{
						isRowEmpty = false;
					}
				}
				else
				{
					System.out.print("..");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
				{
					System.out.print(" ");
				}
			}
			System.out.println();
			nBlanks /=2;
			while(localStack.isEmpty()==false)
			{
				globalStack.push(localStack.pop());
			}
		}
	}
}

class TreeDemo
{
	public static void main(String[] args) throws IOException
	{
		int value;
		Tree theTree = new Tree();

		theTree.insert(50, 1.5);
		theTree.insert(25, 1.2);
		theTree.insert(75, 1.7);
		theTree.insert(12, 1.5);
		theTree.insert(37, 1.2);
		theTree.insert(43, 1.7);
		theTree.insert(30, 1.5);
		theTree.insert(87, 1.7);
		theTree.insert(93, 1.5);
		theTree.insert(97, 1.5);

		while(true)
		{
			System.out.print("Enter first letter of show, ");
			System.out.print("insert, find, delete or traverse: ");
			int choice = getChar();
			switch(choice)
			{
				case 's':
					theTree.displayTree();
					break;
				case 'i':
					System.out.print("Enter value to insert:");
					value = getInt();
					theTree.insert(value, value + 0.9);
					break;
				case 'f':
					System.out.print("Enter value to find: ");
					value = getInt();
					Node found = theTree.find(value);
					if(found != null)
					{
						System.out.print("Found: ");
						found.displayNode();
						System.out.print("\n");
					}
					else
					{
						System.out.print("Could not find ");
						System.out.print(value + "\n");
					}
					break;
				case 'd':
					System.out.print("Enter value to delete: ");
					value = getInt();
					boolean didDelete = theTree.delete(value);
					if(didDelete)
					{
						System.out.print("Deleted "+ value + "\n");
					}
					else
					{
						System.out.print("Deleted "+ value +"\n");
						System.out.print(value + "\n");
					}
				case 't':
					System.out.print("Enter type 1, 2 or 3: ");
					value = getInt();
					theTree.traverse(value);
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


/*
二叉樹是一種資料結構，他同時擁有了另外兩種資料結構的優點：一種是有序陣列
，一種是鍊表。樹在搜尋資料時跟有序陣列一樣快，而在新增還有刪除資料時跟鍊表一樣快。

當我們在有序陣列搜尋資料時，我們可以用二分搜索法的方式很快的找到特定的值，
就是不斷地把這組有序列二分縮小範圍直到找到目標，這種方式搜尋所需要消耗的時間是O(Log N)。
但是，如果我們想要再有序陣列中新增或著刪除一個值，我們必須移動許多已經存在陣列中的值。
例如我們現在要新增一個值到有序陣列中，在找到他的位置之後，
我們必續把比她大的直都往後一個位置幫他騰出空間。刪除時我們則需要把比他大的直都往前一個位置。
這樣的移動很消耗時間，平均來說一次的新增或刪除要移動陣列中一半的數據(N/2次移動)。
所以當我們需要有很多的新增或刪除時，不應該使用有序陣列。

當我們使用鍊表時，新增和刪除節點的動作是很快的，不需要移動位置，只需要改變節點的引用值(previous或next)。
這些操作的時間複雜度是O(1)，是最優秀的時間等級。但是我們在練表中搜畃特定節點的動做卻很慢，
我們必須一個一個的檢查每一個節點，他所消耗的時間等級是O(N)。就算我們使用的是有序鍊表，
也無法解決這個問題，因為在鍊中表中我們就必須由一個節點通往下一個節點，一次只能走一步。

二叉樹從根節點開始(最上方的節點)，除了跟節點以外，每一個節點可以有一個父節點和兩個子結點，
當然一個節點可以有兩個子結點或沒有子結點或著只有一個節點，沒有子節點的節點就稱為葉節點，
因為是二叉樹，每一個節點可以有兩個子結點，所以子結點通常以左子結點和右子節點來稱呼。
而一個子結點包含她以下的子結點可以稱為子樹。

class Node
{
	public int iData;
	public double dData;
	public Node leftChild;
	public Node rightChild;

	public void displayNode()
	{
		System.out.print("{");
		System.out.print(iData);
		System.out.print(", ");
		System.out.print(dData);
		System.out.print("} ");
	}
}

這是樹結點的程式碼，一棵樹是由很多的樹結點所組成的，而組成的方式就是依照樹結點的特定數值作為依據決定節點的位置。
一個樹結點在這裡共有四個屬性，分別是iData、dData、leftChild(左子節點)、rightChild(右子節點)，而iData就是我們用來決定節點位置的依據。

public void insert(int id, double dd)
{
	Node newNode = new Node();
	newNode.iData = id;
	newNode.dData = dd;

	if(root ==null)//如果根節點為null表示這棵樹是空的
	{
		root = newNode;//將新結點放入根節點
	}
	else//如果這棵樹不為空	
	{
		Node current = root;//將當前節點指向根節點所指向的位置
		Node parent;//宣告父結點
		while(true)
		{
			parent = current;//將父節點指向當前節點
			if(id<current.iData)//如果新數據的小於當前節點的iData
			{
				current = current.leftChild;//將變數current指向當前節點的左子結點
				if(current == null)//如果current為空
				{
					parent.leftChild = newNode;//將parent所指向位置的左子結點指向新結點
					return;
				}
			}
			else//如果新數據的大於當前節點的iData
			{
				current = current.rightChild;;//將變數current指向當前節點的右子結點
				if(current == null)//如果current為空
				{
					parent.rightChild = newNode;//將parent所指向位置的右子結點指向新結點
					return;
				}
			}
		}
	}
}

樹在新增一項數據時的操作並不是非常複雜，其實就是用新數據從根節點的位置開始比大小，
如果新數據比較大，那就往右走，如果比較小，那就往左邊走。當走到下一個位置實在跟這個位置的節點做比較，
如果新數據比較大，那就往右走，如果比較小，那就往左邊走，一直走到沒有結點為止就將這個新數據放入這個位置。


public Node find(int key)
{
	Node current = root;//將變數current指向root的位置
	while(current.iData != key)//當current的iData不等於目標值key
	{
		if(key<current.iData)//如果目標值小魚當前節點的iData
		{
			current = current.leftChild;//將當前節點指向當前節點的左子結點
		}
		else//如果目標值小於當前節點的iData
		{
			current = current.rightChild;//將當前節點指向當前節點的右子結點
		}
		if(current == null)//如果當前節點已經是null表示沒有這個節點
		{
			return null;//返回null
		}
	}
	return current;
}

在樹的操作中，搜尋特定節點是最簡單的，因為只需要找到目標值就結束了。而尋找節點的方式就跟插入新結點一樣，
就是以目標值從根節點開始比較，如果比較大，那屆往右邊走，如果比較小，那就往左邊走。尋找的結果會有兩種，
一種是找到該節點返回，一種是一直走到葉節點的子結點返回null。

public boolean delete(int key)
{
	Node current = root;//變數current為迴圈中的當前節點，一開始先指向根節點
	Node parent = root;//變數parent在迴圈中會用來當作current的父節點位置，一開始先指向根節點
	boolean isLeftChild = true;//判斷是否為左子結點，一開始設為true

	while(current.iData != key)//當當前節點的iData不等於key值時迴圈執行
	{
		parent = current;//將變數parent指向current的位置
		if(key < current.iData)//如果key值小於current的iData
		{
			isLeftChild = true;//是否為左子結點設為true
			current = current.leftChild;//將current指向current的左子結點
		}
		else//如果key值大於current的iData
		{
			isLeftChild = false;//是否為左子結點設為false
			current = current.rightChild;//將current指向current的右子結點
		}
		if(current == null)//如果current為null，表示找不到目標節點
		{
			return false;//返回false
		}
	}

	//再刪除的一開始我們會先尋找我們需要刪除的節點，在尋找要刪除的節點時，我們也會用變數紀錄要刪除的節點(current)、
	//要刪除的節點的父節點(parent)和要刪除節點是否為左子節點(isLeftChild)。

	if(current.leftChild==null && current.rightChild==null)//如果目標節點下沒有任何節點
	{
		if(current == root)//目標節點又是根節點
		{
			root =null;//直接將跟節點設為null
		}
		else if(isLeftChild)//如果目標節點是左子結點
		{
			parent.leftChild=null;//將目標節點的父節點的左子結點設為null
		}
		else//如果目標節點不是左子結點
		{
			parent.rightChild = null;//將目標節點的父節點的右子結點設為null
		}
	} 

	//如果我們所要刪除的節點沒有任何的子節點，操作方式很簡單，就只要判斷他是父節點的左子結點或右子節點
	//如果他是左子結點，那就將赴節點的佐子結點設為null，如果是右子節點，那就將父節點的右子節點設為null。
	//如果他本身就是根節點，那就將跟節點設為null。
	else if(current.rightChild== null)//如果目標節點的右子節點為空，左子結點有東西
	{
		if(current == root)//如果目標節點為根節點
		{
			root=current.leftChild;	//目標節點的左子結點設為跟節點
		}
		else if(isLeftChild)//如果目標節點為左子結點
		{
			parent.leftChild = current.leftChild;//將父節點的左子結點街上自己的左子結點
		}
		else//如果目標節點為右子結點
		{
			parent.rightChild = current.leftChild;//將父節點的右子結點接上自己的左子結點
		}
	}
	else if(current.leftChild==null)//如果目標節點的左子節點為空，右子結點有東西
	{
		if(current == root)//如果目標節點為根節點
		{
			root = current.rightChild;//目標節點的右子結點設為跟節點
		}
		else if(isLeftChild)//如果目標節點為左子結點
		{
			parent.leftChild = current.rightChild;//將父節點的佐子結點街上自己的右子結點
		}
		else//如果目標節點為右子結點
		{
			parent.rightChild = current.rightChild;//將父節點的右子結點街上自己的右子結點
		}
	}
	//如果我們所要刪除的目標有一個子結點，酸然程式碼看起來很複雜，但是在概念上是很簡單的，
	//就是把這個唯一的子結點取代自己的位置。
	else//如果目標節點有左右兩個子結點
	{
		Node successor = getSuccessor(current);//呼叫函式getSuccessor(current)取得繼承節點
		if(current == root)//如果目標節點為根節點
		{
			root = successor;//將繼承節點設為根節點
		}
		else if(isLeftChild)//如果目標結點是左子結點
		{
			parent.leftChild = successor;//將根父節點的左子結點設為繼承節點
		}
		else//如果目標結點是右子結點
		{
			parent.rightChild = successor;//將根父節點的右子結點設為繼承節點
		}
		successor.leftChild = parent.leftChild;//最後將繼承節點的左子結點設為當前節點父節點的左子結點
	}
	//接下來是樹的刪除中最困難的部分，就是要刪除一個有兩個子節點的節點。因為我們要在所要刪除節點的子樹中尋找在值上最接近刪除節點的節點，
	//還要調整它子樹的位置接下來請看函式getSuccessor。
	return true;
}

private Node getSuccessor(Node delNode)
{
	Node successorParent = delNode;//將變數successorParent指向要刪除的節點
	Node successor = delNode;//將變數successor指向要刪除的節點
	Node current = delNode.rightChild;//將變數current指向要刪除節點的右節點

	while(current != null)//當變數current不為null時
	{
		successorParent = successor;//將變數successorParent指向successor
		successor = current;//將變數successor指向current
		current = current.leftChild;//將變數current指向current的右節點
	}

	if(successor!= delNode.rightChild)//如果繼承點為刪除點的右子節點
	{
		successorParent.leftChild = successor.rightChild;//繼承點的父節點設為繼承點的右子節點
		successor.rightChild = delNode.rightChild;//繼承點的右子節點設為刪除點的右子節點
	}
	return successor;//回傳繼承點
}

//繼承點的尋找雖然看起來很複雜，但是其實就只是在刪除點的子樹中尋找最接近的值，
//在本篇的範例會用刪除點的右子樹來尋找，當然用左子樹來尋找也可以，不過在這邊還是用右子樹為例子。
//再刪除點的右子樹中尋找在值上最接近自己的節點就必須往左邊走，找到所有比自己大的節點中的最小的那一個。
//然後用它來取代自己。最小的那一個節點一定不會有左子結點，因為佐子結點代表著更小的值，
//所以我們就在回圈中一直找到左子結點為null的節點，然後用它來取代自己。
//這個結點一定不會有左子結點，但是可能會有右子節點，所以在我們移動繼承點前先用他的柚子節點取代自己的位置。
//如果要刪除節點的右子節點沒有左子結點，這表示要刪除節點的右子結點就是在值上最接近要刪除節點的節點，
//那麼我們就不需要在移動他的子樹，植睫回傳他取代要刪除的結點就可以了。

//樹的刪除節點在程式執行上非常複雜，在某些刪除不常出現的狀況下其實我們可以用另外一種方法避開他而不是真的執行刪除節點，
//就是在節點上增加一個布林屬性isDelete，當這個節點被刪除時，我們就將isDelete設為true。然後讓數在查找搜尋時避開他們。
//當然這些節點還是保留在樹裡面，並不會減少節點，也不會上執行操作變快，所以只適合用在刪除結點不長發生的狀況下。


二叉樹再插入亂序的值或是隨機的值時會盡量保持平衡以及維持他的效率，但是如果插入的值為升冪或降冪時這棵樹就會變得不平衡，
他可能會往右邊長或往左邊長，這種狀況也會造成這棵樹在搜尋結點時的效率下降。

一棵滿樹的節點數量我們可以用數學公式2^(這棵樹的總層數)-1來計算，假設這棵滿樹有五層那麼他的節點數量就是2^5-1=31，
如果只有三層那就是3^3-1=8。一棵樹的節點數量和他的層數有關係，層數又和搜尋比較的步有關係，
所以可以知道樹在搜尋時的時間複雜度是O(log N)，不過這當然是在滿樹的狀況下，如果不是滿樹，那麼會相當難判斷他的狀況，
不過如果都以一棵樹的總層數來比較他們搜尋所耗費的時間，滿樹一定比非滿樹更耗時，因為他在低層節點的數量更少，
而低層節點又都是最耗時的位置。

在1000000個數據中，陣列和鏈表平均的查找次數需要在搜尋中平均需要500000，而樹只需要大概20次。
有序陣列可以很快的找到特定數據(也是O(logN))，但是但是無論是插入還是刪除，他們都需要移動平均500000個數據。
而樹再找到結點之後只需要接上節點或著移動很少量的節點處理後繼節點來完成工作。

當然輸在遍歷所有的數據上不像陣列或著鍊表那麼方便，但是在大量的數據中，很少時候會需要遍歷。	private void preOrder(Node localRoot)


樹在遍歷上有三總基本的方式，分別是前序(preOrder)、中序(inOrder)和後序(postOrder)。
三種方式都由不同的方式由根節點走向不同的子結點，所以遍歷出的節點順序也不同。
在範例中要顯示所以節點時，我們可以用前序(preOrder)、中序(inOrder)或後序(postOrder)遍歷整棵樹。
三種便利的方法都使用遞迴的方式一層一層的走向下一個節點。

private void inOrder(Node localRoot)
{
	if(localRoot != null)
	{
		inOrder(localRoot.leftChild);
		System.out.print(localRoot.iData + " ");
		inOrder(localRoot.rightChild);
	}
}

中序遍歷法是樹中最常用的遍歷方法，因為它可以讓一棵樹的直完全有序地呈現出來。
中序遍歷的方式
就是一直往樹的左邊走，走到底(最小值的位置)，沒有更小的值了就印出這個節點的值，
印出之後如果有右子節點就往右子節點走，一樣是要找出右子點樹中最小的值，如果沒有右子節點那就再返回上一層。

private void preOrder(Node localRoot)
{
	System.out.print(localRoot.iData+" ");
	preOrder(localRoot.leftChild);
	preOrder(localRoot.rightChild);
}

private void postOrder(Node localRoot)
{
	if(localRoot != null)
	{
		postOrder(localRoot.leftChild);
		postOrder(localRoot.rightChild);
		System.out.print(localRoot.iData + " ");
	}
}

前序和後序遍歷的方式和中序不同，所以便利每個節點的順序也不同，前序和後序遍歷無法使數中的所有節點的值完全有序地展現出來，
他們一般來說被用在其他用途，像是一個解析或分析代數表達式的工具。
二叉樹在執行搜尋時必須從跟結點一層一層往下，為結構的關係，在滿樹的狀況下樹的最底層擁有最多的節點，
所以經常都要查找到最底層的節點做操作。在搜尋結點時，每一層我們都要經過一個節點，所以只要知道數有幾層，
我們就可以知道搜尋需要花多少時間。

*/






































