//package declaration
package ch.nolix.application.performanceDetector;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.util.Timer;
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.system.GUIClient.GUIBackClient;
import ch.nolix.system.client.Session;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 80
 */
final class MainSession extends Session<GUIBackClient> {
	
	//constants
	private static final String TITLE = "Performance Detector";
	private static final int UPDATE_INTERVAL_IN_MILLISECONDS = 500;
	
	//attributes
	private final Worker worker = new Worker(() -> getRefClient().isAlive());
	private final Timer timer = new Timer();
	private final Label benchmarkLabel = new Label().setName(WidgetNameManager.BENCHMARK_LABEL_NAME);

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
			.setRole(ContainerRole.MainContainer)
			.addWidget(
				benchmarkLabel,
				new Label()
				.setName(WidgetNameManager.BENCHMARK_INFO_LABEL_NAME)
				.setText("polynom fits per second (n = 100 / degree = 3)")
			)
		)
		.setConfiguration(new Design());
		
		//Starts the timer and worker.
		timer.start();
		worker.start();
		
		//Starts the updateGUI method in background.	
		Sequencer
		.asLongAs(() -> getRefClient().isAlive())
		.afterAllMilliseconds(UPDATE_INTERVAL_IN_MILLISECONDS)
		.runInBackground(()->getRefClient().runLocally("UpdateGUI"));
	}
	
	//method
	/**
	 * Updates the GUI of the client of this main session.
	 */
	public void UpdateGUI() {	
		if (timer.getRunMilliseconds() > 0) {
			
			//Calculates the number of polynom fits per second.
			final long polynomFitsPerSecond =
			(long)(1000.0 * worker.getPolynomFitsCount()) / (timer.getRunMilliseconds());
			
			//Sets the text of the benchmark label.
			benchmarkLabel.setText(Long.toString(polynomFitsPerSecond));			
		}
	}
}
