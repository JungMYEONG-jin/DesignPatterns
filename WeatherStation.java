
public class WeatherStation {
 public static void main(String[] args)
 {
	 
	 WeatherData w = new WeatherData();
	 CurrentConditionalDisplay curDis = new CurrentConditionalDisplay(w);
	 HeatIndexDisplay heat = new HeatIndexDisplay(w);
	 w.setMeasurement(80, 65, 34.f);
	 w.setMeasurement(82, 70, 29.2f);
	w.setMeasurement(78, 90, 29.2f); 
	 
	 
 }

}
