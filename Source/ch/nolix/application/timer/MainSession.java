//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.system.GUIClient.GUIBackClient;
import ch.nolix.system.client.Session;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 110
 */
final class MainSession extends Session<GUIBackClient> {

	//constants
	private static final String TITLE = "Timer";
	private static final int UPDATE_INTERVAL_IN_MILLISECONDS = 100;
	
	//attribute
	private final ch.nolix.core.util.Timer timer = new ch.nolix.core.util.Timer();
	
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
				new Label()
				.setName(WidgetNameManager.TIME_LABEL_NAME)
				.setText("00 : 00 : 00 : 000"),
				new HorizontalStack()
				.addWidget(
					new Button()
					.setText("Start")
					.setLeftMouseButtonPressCommand("StartTimer"),
					new Button()
					.setText("Stop")
					.setLeftMouseButtonPressCommand("StopTimer"),
					new Button()
					.setText("Reset")
					.setLeftMouseButtonPressCommand("ResetTimer")
				)
			)
		)
		.setConfiguration(new Design());

		//Starts the update GUI method in background.
		Sequencer
		.asLongAs(() -> getRefClient().isAlive())
		.afterAllMilliseconds(UPDATE_INTERVAL_IN_MILLISECONDS)
		.runInBackground(() -> getRefClient().runLocally("UpdateDialog"));
	}
	
	//method
	/**
	 * Resets the timer of this main session.
	 */
	public void ResetTimer() {
		timer.reset();
	}
	
	//method
	/**
	 * Starts the timer of this main session.
	 */
	public void StartTimer() {	
		timer.start();
	}
	
	//method
	/**
	 * Stops the timer of this main session.
	 */
	public void StopTimer() {
		timer.stop();
	}
	
	//method
	/**
	 * Updates the GUI of the client of this main session.
	 */
	public void UpdateDialog() {
		
		//Calculates the time the timer of has run.
		final int hours = timer.getRunMilliseconds() / 3600000;
		final int minutes = (timer.getRunMilliseconds() / 60000) % 60;
		final int seconds = (timer.getRunMilliseconds() / 1000) % 60;
		final int deciseconds = (timer.getRunMilliseconds() / 100) % 10;
		
		//Fetches the time label.
		final Label timeLabel
		= getRefClient().getRefGUI().getRefWidgetByNameRecursively(WidgetNameManager.TIME_LABEL_NAME);
		
		//Sets the text of the time label.
		timeLabel.setText(String.format("%02d : %02d : %02d : %d", hours, minutes, seconds, deciseconds));
	}
}
