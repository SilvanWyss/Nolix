//package declaration
package ch.nolix.templatetest.guilooktest;

import ch.nolix.system.element.configuration.Configuration;
import ch.nolix.template.guilook.AnthrazitGUILookCreator;

//class
public final class AnthrazitGUILookCreatorTest extends GUILookCreatorTest {
	
	//method
	@Override
	protected Configuration createTestUnit() {
		return AnthrazitGUILookCreator.INSTANCE.createGUILook();
	}
}
