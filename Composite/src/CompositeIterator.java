import java.util.*;


public class CompositeIterator implements Iterator<MenuComponent> {
	Stack<Iterator<MenuComponent>> stack = new Stack<Iterator<MenuComponent>>();
	
	public CompositeIterator(Iterator<MenuComponent> iter)
	{
		stack.push(iter);
	}
	
	public MenuComponent next()
	{
		if(hasNext())
		{
			Iterator<MenuComponent> iter = stack.peek();
			
			MenuComponent comp = iter.next();
			stack.push(comp.createIterator());
			return comp;
		}else
			return null;
	}
	
	public boolean hasNext()
	{
		if(stack.empty())
			return false;
		else
		{
			Iterator<MenuComponent> iter = stack.peek();
			
			if(!iter.hasNext())
			{
				stack.pop();
				return hasNext();
			}else
				return true;
		}
	}
	
	

}
