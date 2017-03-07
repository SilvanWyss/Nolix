/*
 * file:	TimerSession.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	110
 */

//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.common.application.Session;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.dialog.Button;
import ch.nolix.element.dialog.ContainerRole;
import ch.nolix.element.dialog.HorizontalStack;
import ch.nolix.element.dialog.Label;
import ch.nolix.element.dialog.VerticalStack;
import ch.nolix.system.dialogClient.DialogClient;

//package-visible class
public final class MainSession extends Session<DialogClient> {

	//attribute
	private final ch.nolix.common.util.Timer timer = new ch.nolix.common.util.Timer();
	
	//method
	/**
	 * Initializes this timer session.
	 */
	public final void initialize() {
		
		//Sets the title of the dialog of the dialog client of this main session.
		getRefClient().getRefDialog().setTitle("Timer");
		
		getRefClient().getRefDialog().setRootRectangle(
			new VerticalStack()
			.setRole(ContainerRole.MainContainer)
			.addRectangle(
				new Label()
				.setText("00 : 00 : 00 : 000"),
				new HorizontalStack()
				.addRectangle(
					new Button()
					.setText("Start")
					.setLeftMouseButtonPressCommand("Start Timer"),
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
		
		VerticalStack mainVerticalStack = (VerticalStack)getRefClient().getRefDialog().getRefRootRectangle();
		Label timeLabel = (Label)mainVerticalStack.getRefRectangles().getRefFirst();
		timeLabel.setText(String.format("%02d : %02d : %02d : %d", hours, minutes, seconds, deciseconds));
	}
}
