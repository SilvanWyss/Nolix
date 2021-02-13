//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.element.configuration.Configuration;
import ch.nolix.template.guilook.AnthrazitGUILookCreator;

//class
public final class AnthrazitGUILookCreatorTest extends GUILookTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return new AnthrazitGUILookCreator().createGUILook();
	}
}
