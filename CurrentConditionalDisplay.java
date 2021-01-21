
public class CurrentConditionalDisplay implements Observer, DisplayElement {

	private float temperature;
	private float hum;
	private Subject weather;
	
	
	public CurrentConditionalDisplay(Subject weatherData)
	{
		this.weather = weatherData;
		weather.registerObserver(this);
	}
	

	public void update(float temp, float hum, float press) {
		// TODO Auto-generated method stub
		this.temperature = temp;
		this.hum = hum;
		display();

	}
	
	public void display()
	{
		System.out.println("Current Conditions: "+temperature+"F degrees and "+hum+"% humidity");
	}

}
