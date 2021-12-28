package hello.core;

import java.util.*;

public class bfs2 {

    public static ArrayList<Integer>[] graph = new ArrayList[3001];
    public static boolean[] visited = new boolean[3001];
    public static boolean cycle = false;
    public static boolean[] is_cycle = new boolean[3001];


    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i=0;i<=n;i++)
            graph[i] = new ArrayList<>();


        for(int i=0;i<n;i++)
        {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);

        }

        for(int i=1;i<=n;i++)
        {
            cycle = false;
            Arrays.fill(visited, false);
            dfs(i, i, 0);
            if(cycle)
                is_cycle[i] = true;
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            if(is_cycle[i])
            {
                res.add(0);
                continue;
            }
            res.add(bfs(i));
        }


        for(int num : res)
            System.out.print(num+" ");



    }

    public static void dfs(int cur, int start, int cnt)
    {
        if(cur==start && cnt>=2)
        {
            cycle = true;
            return;
        }

        visited[cur] = true;
        for(int num : graph[cur])
        {
            if(!visited[num])
            {
                dfs(num, start, cnt+1);
            }else
            {
                if(num==start && cnt>=2)
                {
                    dfs(num, start, cnt);
                }
            }
        }

        if(cycle)
            return;
    }

    public static int bfs(int x)
    {
        Arrays.fill(visited, false);
        visited[x] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, 0));
        while(!q.isEmpty())
        {
            Pair it = q.poll();
            int cur = it.getCur();
            int cnt = it.getCnt();

            if(is_cycle[cur])
                return cnt;

            for(int num : graph[cur])
            {
                if(!visited[num])
                {
                    visited[num] = true;
                    q.add(new Pair(num, cnt+1));
                }
            }
        }
        return 0;
    }

}


class Pair
{
    private int cur, cnt;

    public Pair(int cur, int cnt) {
        this.cur = cur;
        this.cnt = cnt;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
