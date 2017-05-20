/*
 * file:	TimerSession.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	110
 */

//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.system.GUIClient.GUIClient;
import ch.nolix.system.application.Session;

//package-visible class
public final class MainSession extends Session<GUIClient> {

	//attribute
	private final ch.nolix.core.util.Timer timer = new ch.nolix.core.util.Timer();
	
	//method
	/**
	 * Initializes this timer session.
	 */
	public final void initialize() {
		
		//Sets the title of the dialog of the dialog client of this main session.
		getRefClient().getRefDialog().setTitle("Timer");
		
		getRefClient().getRefDialog().setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.MainContainer)
			.addRectangle(
				new Label()
				.setText("00 : 00 : 00 : 000"),
				new HorizontalStack()
				.addRectangle(
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
		);
		
		//Sets the design of the dialog of the dialog client of this main session.
		getRefClient().getRefDialog().setConfiguration(new Design());

		Sequencer.afterAllMilliseconds(100).runInBackground(()->{getRefClient().runLocally("UpdateDialog");});
	}
	
	//method
	/**
	 * Resets the timer.
	 */
	public void ResetTimer() {
		timer.reset();
	}
	
	//method
	/**
	 * Starts the timer.
	 */
	public void StartTimer() {	
		timer.start();
	}
	
	//method
	/**
	 * Stops the timer.
	 */
	public void StopTimer() {
		timer.stop();
	}
	
	//method
	/**
	 * Updates the dialog of the dialog client of this timer session.
	 */
	public void UpdateDialog() {
		
		int hours = timer.getRunMilliseconds() / 3600000;
		int minutes = (timer.getRunMilliseconds() / 60000) % 60;
		int seconds = (timer.getRunMilliseconds() / 1000) % 60;
		int deciseconds = (timer.getRunMilliseconds() / 100) % 10;
		
		VerticalStack mainVerticalStack = (VerticalStack)getRefClient().getRefDialog().getRefRootWidget();
		Label timeLabel = (Label)mainVerticalStack.getRefRectangles().getRefFirst();
		timeLabel.setText(String.format("%02d : %02d : %02d : %d", hours, minutes, seconds, deciseconds));
	}
}
