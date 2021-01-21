import java.util.*;



public class WeatherData implements Subject{
	
	private ArrayList observers;
	private float temperature;
	private float hum;
	private float press;
	
	public WeatherData()
	{
		observers = new ArrayList();
	}
	
	public void registerObserver(Observer o)
	{
		observers.add(o);
	}
	
	public void removeObserver(Observer o)
	{
		int i = observers.indexOf(o);
		if(i>=0)
			observers.remove(i);
	}
	
	public void notifyObservers()
	{
		
		for(int i =0;i<observers.size();i++)
		{
			Observer ob = (Observer)observers.get(i);
			ob.update(temperature, hum, press);
		}
		
		
		
	}
	
	public void measurementChanged()
	{
		notifyObservers();
	}
	
	public void setMeasurement(float temperature, float hum, float press)
	{
		this.temperature = temperature;
		this.hum = hum;
		this.press = press;
		// change notify
		measurementChanged();
		
	}
	
	public float getTemperature()
	{
		return temperature;
	}
	
	public float getHumidity()
	{
		return hum;
	}
	
	public float getPressure()
	{
		return press;
	}

}
