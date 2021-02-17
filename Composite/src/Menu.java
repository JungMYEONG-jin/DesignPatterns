import java.util.ArrayList;
import java.util.Iterator;


public class Menu extends MenuComponent{
	ArrayList<MenuComponent> menu = new ArrayList<MenuComponent>();
	Iterator<MenuComponent> iter = null;
	String name;
	String desc;
	
	public Menu(String name, String desc)
	{
		this.name = name;
		this.desc = desc;
	}
	
	public void add(MenuComponent menuComponent)
	{
		menu.add(menuComponent);
	}
	
	public void remove(MenuComponent menuComponent)
	{
		menu.remove(menuComponent);
	}
	
	public MenuComponent getChild(int i)
	{
		return (MenuComponent)menu.get(i);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	public Iterator<MenuComponent> createIterator()
	{
		if(iter==null)
		{
			iter = new CompositeIterator(menu.iterator());
			
		}
		return iter;
	}
	
	
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("---------------------");
  
		Iterator<MenuComponent> iterator = menu.iterator();
		while (iterator.hasNext()) {
			MenuComponent menuComponent = 
				(MenuComponent)iterator.next();
			menuComponent.print();
		}
	}

} 
