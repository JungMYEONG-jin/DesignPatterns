
public class DuckTest {

	public static void main(String[] args)
	{
		
		
		MallardDuck duck = new MallardDuck();
		WildTurkey turkey = new WildTurkey();
		
		Duck adpater = new TurkeyAdapter(turkey);
		
		System.out.println("Turkey says");
		turkey.gobble();
		turkey.fly();
		
		System.out.println("The Duck says");
		testDuck(duck);
		
		System.out.println("The adpater says...");
		testDuck(adpater);
		
		
		Turkey adapter2 = new DuckAdapter(duck);
		System.out.println();
		System.out.println("Duck adapter says...");
		
		adapter2.gobble();
		for(int i=0;i<11;i++)
			adapter2.fly();
		
		
	}
	
	static void testDuck(Duck duck)
	{
		duck.quack();
		duck.fly();
	}
}
