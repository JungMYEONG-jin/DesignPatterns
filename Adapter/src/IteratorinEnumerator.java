import java.util.*;


public class IteratorinEnumerator implements Enumeration {
	
	Iterator iter;
	
	public IteratorinEnumerator(Iterator iter)
	{
		this.iter= iter;
	}
	
	public boolean hasMoreElements()
	{
		return iter.hasNext();
	}

	public Object nextElement()
	{
		return iter.next();
	}
	
	
}
