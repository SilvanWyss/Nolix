//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.attributeAPI.Titled;
import ch.nolix.common.skillAPI.IRequestableContainer;
import ch.nolix.element.input.IInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.keyBoard.KeyInput;

//interface
public interface ITestableGUI extends IInputTaker, IRequestableContainer, Titled {
	
	//method
	public default void enter() {
		noteKeyTyping(Key.ENTER);
	}
	
	//method declaration
	public abstract GUIElement getRefGUIElement(String id);
	
	//method
	public default void typeText(final String text) {
		for (var i = 0; i < text.length(); i++) {
			for (final var ki : KeyInput.fromCharacter(text.charAt(i))) {
				noteKeyInput(ki);
			}
		}
	}
}
