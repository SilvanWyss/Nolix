//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.input.Key;
import ch.nolix.element.keyBoard.KeyInput;

//class
public final class GUIController {
	
	//attribute
	private final ITestableGUI mGUI;
	
	//constructor
	public GUIController(final ITestableGUI pGUI) {
		
		Validator.assertThat(pGUI).thatIsNamed(VariableNameCatalogue.GUI).isNotNull();
		
		mGUI = pGUI;
	}
	
	//method
	public void enter() {
		mGUI.noteKeyTyping(Key.ENTER);
	}
	
	//method
	public void escape() {
		mGUI.noteKeyTyping(Key.ESCAPE);
	}
	
	//method
	public void typeText(final String text) {
		for (var i = 0; i < text.length(); i++) {
			for (final var ki : KeyInput.fromCharacter(text.charAt(i))) {
				mGUI.noteKeyInput(ki);
			}
		}
	}
}
