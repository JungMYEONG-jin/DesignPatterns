import java.util.*;

public class Waitress {
	MenuComponent menu;
	
	public Waitress(MenuComponent all)
	{
		this.menu = all;
	}
	
	public void printMenu()
	{
		menu.print();
	}
	
	public void printVegetarianMenu()
	{
		Iterator<MenuComponent> iter = menu.createIterator();
		System.out.println("\nVegetarian Menu\n---------");
		while(iter.hasNext())
		{
			MenuComponent comp = iter.next();
			
			try
			{
				if(comp.isVegetarian())
					comp.print();
				
			}catch(UnsupportedOperationException e) {}
		}
		
	}
}
