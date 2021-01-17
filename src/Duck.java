
public abstract class Duck {
	
	public Duck() {}
	
	
	
	public abstract void display();
	
	
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	public void performFly()
	{
		flyBehavior.fly();
	}
	
	public void performQuack()
	{	
		quackBehavior.quack();
	}
	
	public void swim()
	{
		System.out.println("All ducks can swim.");
	}
	
	public void setFly(FlyBehavior fb)
	{
		flyBehavior = fb;
	}
	
	public void setQuack(QuackBehavior qb)
	{
		quackBehavior=qb;
	}

}
