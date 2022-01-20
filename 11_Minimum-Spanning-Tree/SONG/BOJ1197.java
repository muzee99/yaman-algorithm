package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최소 스패닝 트리
public class BOJ1197 {
	static int parent[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Edge arr[] = new Edge[E];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[i] = new Edge(a,b,c);
		}
		
		Arrays.sort(arr);
		
		parent = new int[V+1]; // vertex 1~
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		
		int answer = 0;
		int cnt = 0;
		for(Edge e : arr) {
			if(find(e.A) != find(e.B)) {
				union(e.A, e.B);
				answer += e.C;
			}
			if(cnt == V-1) break;
		}
		
		System.out.print(answer);
	}
	
	public static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	public static void union(int a, int b) { // small, big
		parent[find(b)] = parent[find(a)];
	}
	
	public static class Edge implements Comparable<Edge> {
		int A, B, C;
		
		public Edge(int a, int b, int c) {
			A = a;
			B = b;
			C = c; //cost
		}
		
		@Override
		public int compareTo(Edge o) {
			return C - o.C;
		}
		
	}
}
