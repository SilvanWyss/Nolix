//package declaration
package ch.nolix.application.performanceDetector;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.util.Timer;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.system.GUIClient.GUIClient;
import ch.nolix.system.client.Session;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 80
 */
final class MainSession extends Session<GUIClient> {
	
	//constants
	private static final String TITLE = "Performance Detector";
	private static final int UPDATE_INTERVAL_IN_MILLISECONDS = 500;
	
	//attributes
	private final Worker worker = new Worker();
	private final Timer timer = new Timer();

	//method
	/**
	 * Initializes this main session.
	 */
	public void initialize() {
		
		//Setups the GUI.
		getRefClient().getRefGUI()
		.setTitle(TITLE)
		.setRootWidget(
			new VerticalStack()
			.addRectangle(
				new Label()
				.setName(WidgetNames.BENCHMARK_LABEL_NAME),
				new Label()
				.setName(WidgetNames.BENCHMARK_INFO_LABEL_NAME)
				.setText("polynom fits per second (n = 100 / degree = 3)")
			)
		)
		.setConfiguration(new Design());
		
		//Starts the timer and worker.
		timer.start();
		worker.start();
		
		//Fetches the client.
		final GUIClient client = getRefClient();
		
		//Starts the updateGUI method in background.	
		Sequencer
		.asLongAs(() -> client.isRunning())
		.afterAllMilliseconds(UPDATE_INTERVAL_IN_MILLISECONDS)
		.runInBackground(()->updateGUI());
	}
	
	//method
	/**
	 * Updates the GUI of this main session.
	 */
	public void updateGUI() {	
		if (timer.getRunMilliseconds() > 0) {
			
			//Calculates the number of polynom fits per second.
			final long polynomFitsPerSecond 
			= (long)(1000.0 * worker.getPolynomFitsCount()) / (timer.getRunMilliseconds());
	
			//Fetches the benchmark label.
			final Label benchmarkLabel
			= getRefClient().getRefGUI().getRefWidgetByNameRecursively(WidgetNames.BENCHMARK_LABEL_NAME);
			
			//Sets the text of the benchmark label.
			benchmarkLabel.setText(Long.toString(polynomFitsPerSecond));
		}
	}
}
