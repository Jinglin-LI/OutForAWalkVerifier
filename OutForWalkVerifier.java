import java.util.*;

class OutForWalkVerifier {
  static int[][] AdjMat;
  static PriorityQueue <IntegerPair> pq;
  
  private static Vector <Integer> path;

  private static Vector < Integer > visited;

  private static void DFSrec(int u) {
    visited.set(u, 1);    
    for (int j = 0; j < AdjMat[u].length; j++)
      if (AdjMat[u][j] > 0 && visited.get(j) == 0)
        DFSrec(j);
  }
 
  public static void MyPrim (int source, int destination) {
	  visited.set(source, 1);
	  
      for (int i = 0; i < AdjMat[source].length; i++) {
		   if (AdjMat[source][i] > 0 && visited.get(i) == 0) {
			  
//			  System.out.println("check: source = " + source + " i = " + i + " destination = " + destination);
			  pq.add(new IntegerPair (i, AdjMat[source][i]));
			  
//			  for (IntegerPair q : pq) {
//			  System.out.println("pq include first: " + q.first() + ", second: " + q.second());
//			  }
		  }
	  }
	  
	  visited.set(pq.peek().first(), 1);
	  
	  path.add(pq.peek().second());
//	  System.out.println("path include: " + path);
	  
	  source = pq.poll().first();
	  
//	  if (pq.isEmpty())
//		  System.out.println ("pq is Empty!!!");
	  
//	  for (IntegerPair q : pq) {
//	  System.out.println("pq2 include first: " + q.first() + ", second: " + q.second());
//	  }
	  
//	  System.out.println ("source = " + source);
	  
	  if (source == destination) 
		  return;
	
	  else if (source != destination) {
	  
	//	  System.out.println ("source2 = " + source);
		  MyPrim (source, destination);
	  }
  }
	  
  public static int Query(int source, int destination) {
	  int ans = 0;
	  
	  path = new Vector<Integer>();
	  pq = new PriorityQueue <IntegerPair>();
	  
	  MyPrim (source, destination);
	  
	  if (!path.isEmpty())
		  ans = path.get(0);
	  for (int i = 0; i < path.size()-1; i++) {
		  if (path.get(i+1) > ans)
			  ans = path.get(i+1);
	  } 	  
	  return ans;
  }
 
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int i, j, k, w, TC = sc.nextInt(), V, Q;

    while (TC-- > 0) {
      V = sc.nextInt();
      if (V < 2 || V > 2000) {
        System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, V IN [2..2000], it is = " + V);
        return;
      }
      AdjMat = new int[V][V];
      for (i = 0; i < V; i++)
        for (j = 0; j < V; j++)
          AdjMat[i][j] = 0;

      for (i = 0; i < V; i++) {
        k = sc.nextInt();
        while (k-- > 0) {
          j = sc.nextInt();
          w = sc.nextInt();
          if (w < 0 || w > 1000) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, EFFORT RATING MUST BE IN [0..1000], it is = " + w);
            return;
          }
          AdjMat[i][j] = w;
        }
      }

      for (i = 0; i < V; i++)
        for (j = 0; j < V; j++)
          if (AdjMat[i][j] != AdjMat[j][i]) {
            System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, GRAPH MUST BE UNDIRECTED");
            return;
          }

      // Check if the graph is connected
      visited = new Vector < Integer > (V);
      visited.addAll(Collections.nCopies(V, 0));
      DFSrec(0);
      for (i = 0; i < V; i++)
        if (visited.get(i) == 0) {
          System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, GRAPH MUST BE CONNECTED");
          return;
        }
 
      Q = sc.nextInt();
      while (Q-- > 0) {
    	  
    	for (i = 0; i < V; i++)
              visited.set(i, 0);
    	  
        i = sc.nextInt();
        if (i < 0 || i > 9) {
          System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, SOURCE VERTEX MUST BE IN [0..9], it is = " + i);
          return;
        }
        j = sc.nextInt();
        if (j < 0 || j > V-1) {
          System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, DESTINATION VERTEX MUST BE IN [0..V-1], it is = " + j);
          return;
        }
        if (i == j) {
          System.out.println("ERROR AT " + TC + "-th/nd/st TESTCASE FROM END, SOURCE VERTEX MUST BE DIFFERENT THAN DESTINATION VERTEX, they are i = " + i + ", j = " + j);
          return;
        }
        System.out.println(Query(i, j));
      }
    }

//    System.out.println("Test data is valid :)");

    // Note, please check the positioning of blank lines to separate two test cases
    // as this verifier program does not do this check yet.  
  }
}

class IntegerPair implements Comparable < IntegerPair > {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
//    if (!this.first().equals(o.first()))
//      return this.first() - o.first();
//    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}
