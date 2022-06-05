//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.system.configuration.Configuration;
import ch.nolix.template.guilook.AnthrazitGUILookCreator;

//class
public final class AnthrazitGUILookCreatorTest extends GUILookCreatorTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return AnthrazitGUILookCreator.INSTANCE.createGUILook();
	}
}
