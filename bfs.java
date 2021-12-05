/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/

import java.util.*;

public class Main
{
    public static boolean[] visited = new boolean[100000];
    public static int[] db = new int[100000];
    public static int n, m, limit;
    
    public static int doTransform(int x)
    {
        int num = 2*x;
        if(num==0)
            return 0;
        int cnt = 0;
        int first_num = 0;
        int temp = num;
        while(temp>0)
        {
            temp = temp/10;
           
            cnt++;
        }
        int div = (int)Math.pow(10, cnt-1);
        int rem = num%div;
        
        if(cnt<=1)
        {
          if(num-1>=0)
          {
              return num-1;
          }else
            return 0;
        }else
        {
            
            first_num = num/div;
            return (first_num-1)*div+rem;
        }
        
        
    }
    
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt();
	    limit = sc.nextInt();
	    m = sc.nextInt();
	    
	    Queue<Pair> q = new LinkedList<>();
	    q.add(new Pair(n, 0));
	    int ans = -1;
	    visited[n] = true;
	    
	    while(!q.isEmpty())
	    {
	        
	        Pair it = q.poll();
	        int cur = it.getX();
	        int cnt = it.getY();
	        
	        if(cur==m)
	        {
	            ans = cnt;
	            break;
	        }
	        
	        if(cur+1<=99999 && !visited[cur+1])
	        {
	            visited[cur+1] = true;
	            q.add(new Pair(cur+1, cnt+1));
	        }
	        
	        if(2*cur<=99999 )
	        {
	            
	            int num = doTransform(cur);
	            if(!visited[num])
	            {
	                q.add(new Pair(num, cnt+1));
	                visited[num] = true;
	            }
	           

	        }
	        
	        
	        
	        
	    }
	    
	    
	    if(ans>-1 && ans<=limit)
	        System.out.println(ans);
	    else
	        System.out.println("ANG");
	    
	    
	    
	
	}
}


class Pair{
    
    private int x, y;
    
    void setX(int x)
    {
        this.x = x;
    }
    
    void setY(int y)
    {
        this.y = y;
    }
    
    
    Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    int getX()
    {
        return x;
    }
    
    int getY()
    {
        return y;
    }
    
    
}
