class StackX
{
	private final int SIZE = 20;
	private int[] st;
	private int top;

	public StackX()
	{
		st = new int[SIZE];
		top = -1;
	}

	public void push(int j)
	{
		st[++top] = j;
	}

	public int pop()
	{
		return st[top--];
	}

	public int peek()
	{
		return st[top];
	}

	public boolean isEmpty()
	{
		return (top == -1);
	}
}

class Vertex
{
	public char label;
	public boolean wasVisited;

	public Vertex(char lab)
	{
		label = lab;
		wasVisited = false;
	}
}

class Graph
{
	private final int MAX_VERTS = 20;//最大頂點數
	private Vertex vertexList[];//頂點列表
	private int adjMat[][];//連接頂點的邊
	private int nVerts;//頂點數量
	private StackX theStack;//儲列

	public Graph()
	{
		vertexList = new Vertex[MAX_VERTS];

		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS;j++)
		{
			for(int k=0; k<MAX_VERTS; k++)
			{
				adjMat[j][k] = 0;
			}
		}
		theStack = new StackX();
	}

	public void addVertex(char lab)
	{
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start , int end)
	{
		adjMat[start][end]=1;
		adjMat[end][start] = 1;
	}

	public void displayVertex(int v)
	{
		System.out.print(vertexList[v].label);
	}

	public void dfs()
	{
		vertexList[0].wasVisited = true;//將第一頂點設為已拜訪
		displayVertex(0);//印出第一頂點
		theStack.push(0);//將第一頂點的位置放入儲列

		while(!theStack.isEmpty())//當儲列還有東西時執行
		{
			int v = getAdjUnvistedVertex(theStack.peek());//取得臨邊未拜訪的頂點位置
			if(v==-1)
			{
				theStack.pop();
			}
			else
			{
				vertexList[v].wasVisited = true;//將頂v點設為已拜訪
				displayVertex(v);//印出頂點v
				theStack.push(v);//將頂點v放入儲列
			}
		}

		for(int j=0; j<nVerts; j++)
		{
			vertexList[j].wasVisited = false;//將所有頂點設為未拜訪
		}
	}

	public int getAdjUnvistedVertex(int v)
	{
		for(int j=0; j<nVerts; j++)
		{
			if(adjMat[v][j] == 1 && vertexList[j].wasVisited == false)//當v和j的邊存在並且頂點j為未拜訪
			{
				return j;//回傳j
			}
		}
		return -1;//沒有任何符合的條件回傳-1

	}
}

class DFSDemo
{
	public static void main(String[] args)
	{
		Graph theGraph = new Graph();
		theGraph.addVertex('A');
		theGraph.addVertex('B');
		theGraph.addVertex('C');
		theGraph.addVertex('D');
		theGraph.addVertex('E');

		theGraph.addEdge(0,1);
		theGraph.addEdge(1,2);
		theGraph.addEdge(0,3);
		theGraph.addEdge(3,4);

		System.out.print("Visits: ");
		theGraph.dfs();
		System.out.println();


	}
}

/*
圖是一種跟樹相似的資料結構，或著我們可以說樹是圖的一種，但是樹跟圖的應用方式不同。樹可以用來當做儲存數據的資料結構，
不過圖卻不用來當作儲存數據的資料結構，而是用來處理一些其他問題。

圖通常有一個固定的形狀，這通常取決於要處理的問題本身的狀況，例如我們可以用圖來表示一個地區中的城市和路線。
圖中的每一個頂點都表示一個城市，而連結頂點的邊就表示連接這些城市的道路。

在這篇範例中藥演示的是深度優先搜尋，深度優先搜尋就是從一個起點開始，
沿著與起點連接的頂點一直走到底直到路徑中沒有未經過的頂點為止，然後再換另一個與起點連結的頂點走另一條路，依此類推。
public static void main(String[] args)
{
	Graph theGraph = new Graph();
	theGraph.addVertex('A');
	theGraph.addVertex('B');
	theGraph.addVertex('C');
	theGraph.addVertex('D');
	theGraph.addVertex('E');

	theGraph.addEdge(0,1);
	theGraph.addEdge(1,2);
	theGraph.addEdge(0,3);
	theGraph.addEdge(3,4);

	System.out.print("Visits: ");
	theGraph.dfs();
	System.out.println();


}

在範例的一開始我們先呼叫一個圖，並且建立他的頂點以及連接頂點的邊。

private final int MAX_VERTS = 20;//最大頂點數
private Vertex vertexList[];//頂點列表
private int adjMat[][];//連接頂點的邊
private int nVerts;//頂點數量
private StackX theStack;//儲列

public Graph()
{
	vertexList = new Vertex[MAX_VERTS];

	adjMat = new int[MAX_VERTS][MAX_VERTS];
	nVerts = 0;
	for(int j=0; j<MAX_VERTS;j++)
	{
		for(int k=0; k<MAX_VERTS; k++)
		{
			adjMat[j][k] = 0;
		}
	}
	theStack = new StackX();
}

呼叫圖時我們會需要先建立圖的基本設置，並且將途中所有路徑adjMat[j][k]先設為0表示路徑不通。
theGraph.addEdge(?,?)為設置路徑的函示，將頂點參數帶入後這兩個點頂的邊將會被設為1表示兩個頂點相通。

public int getAdjUnvistedVertex(int v)
{
	for(int j=0; j<nVerts; j++)
	{
		if(adjMat[v][j] == 1 && vertexList[j].wasVisited == false)//當v和j的邊存在並且頂點j為未拜訪
		{
			return j;//回傳j
		}
	}
	return -1;//沒有任何符合的條件回傳-1

}
函式getAdjUnvistedVertex可以幫我們取得與頂點v相臨並且未經拜訪的頂點。

public void dfs()
{
	vertexList[0].wasVisited = true;//將第一頂點設為已拜訪
	displayVertex(0);//印出第一頂點
	theStack.push(0);//將第一頂點的位置放入儲列

	while(!theStack.isEmpty())//當儲列還有東西時執行
	{
		int v = getAdjUnvistedVertex(theStack.peek());//取得臨邊未拜訪的頂點位置
		if(v==-1)
		{
			theStack.pop();
		}
		else
		{
			vertexList[v].wasVisited = true;//將頂v點設為已拜訪
			displayVertex(v);//印出頂點v
			theStack.push(v);//將頂點v放入儲列
		}
	}

	for(int j=0; j<nVerts; j++)
	{
		vertexList[j].wasVisited = false;//將所有頂點設為未拜訪
	}
}


函式dfs負責執行深度優先搜尋，他將會由第一個頂點開始出發
/*












































