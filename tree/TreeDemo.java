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

		if(successor!= delNode.rightChild)
		{
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
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






































