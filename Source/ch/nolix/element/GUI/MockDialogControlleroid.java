/*
 * file:	MockDialogControlleroid.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	20
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.controllerAPI.IController;
import ch.nolix.core.documentNode.Statement;

//class
final class MockDialogControlleroid implements IController {

	//error messages
	private static final String MOCK_DIALOG_CONTROLLEROID_PROVIDES_NO_CALLS = "Mock dialog controlleroid provides no calls.";

	//method
	/**
	 * Runs the given command.
	 * @throws Exception if the given command is not valid
	 */
	@Override
	public final void run(Statement command) {
		throw new RuntimeException(MOCK_DIALOG_CONTROLLEROID_PROVIDES_NO_CALLS);
	}
}
