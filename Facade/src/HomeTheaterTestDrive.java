
public class HomeTheaterTestDrive {

	public static void main(String[] args)
	{
		Amplifier amp = new Amplifier("AMP");
		Tuner tuner = new Tuner("AM/FM Tuner", amp);
		StreamingPlayer player = new StreamingPlayer("Streaming player", amp);
		CdPlayer cd = new CdPlayer("CD Player", amp);
		Projector projector = new Projector("Projector", player);
		TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
		Screen screen = new Screen("Theater Screen");
		PopcornPopper popper =new PopcornPopper("Popcorn Popper");
		
		HomeTheaterFacade home = new HomeTheaterFacade(amp, tuner, player, projector, screen, lights, popper);
		
		
		home.watchMovie("Glory of the war");
		home.endMovie();
		
		
	}
	
	
}
