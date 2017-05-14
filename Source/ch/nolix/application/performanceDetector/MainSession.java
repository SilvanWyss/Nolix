/*
 * file:	MainSession.java
 * author:	Silvan Wyss
 * month:	2016-07
 * lines:	70
 */

//package declaration
package ch.nolix.application.performanceDetector;

//own imports
import ch.nolix.core.application.Session;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.util.Timer;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.system.GUIClient.GUIClient;

//class
public final class MainSession extends Session<GUIClient> {
	
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
		
		//Sets the title of the dialog of the dialog client of this main session.
		getRefClient().getRefDialog()
		.setTitle(TITLE)
		.setConfiguration(new Design())
		.setRootWidget(
			new VerticalStack()
			.addRectangle(
				new Label()
				.setName("BenchmarkLabel"),
				new Label()
				.setName("BenchmarkInfoLabel")
				.setText("polynom fits per second (n = 100 / degree = 3)")
			)
		);
		
		//Starts timer and worker.
		timer.start();
		worker.start();
		
		//Starts update method in background.
		Sequencer
		.afterAllMilliseconds(UPDATE_INTERVAL_IN_MILLISECONDS)
		.runInBackground(()->getRefClient().runLocally("UpdateDialog"));
	}
	
	//method
	/**
	 * Updates the dialog client of this main session.
	 */
	public void UpdateDialog() {	
		if (timer.getRunMilliseconds() > 0) {
			
			//Calculates the number of polynom fits per second.
			final long polynomFitsPerSecond = (long)(1000.0 * worker.getPolynomFitsCount()) / (timer.getRunMilliseconds());
	
			//Fetches the benchmark label.
			Label benchmarkLabel = getRefClient().getRefDialog().getRefWidgetByNameRecursively("BenchmarkLabel");
			
			//Sets the text of the benchmark label.
			benchmarkLabel.setText(Long.toString(polynomFitsPerSecond));
		}
	}
}
