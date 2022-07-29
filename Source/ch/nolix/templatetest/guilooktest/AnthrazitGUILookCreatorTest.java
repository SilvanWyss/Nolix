//package declaration
package ch.nolix.templatetest.guilooktest;

import ch.nolix.system.element.style.Style;
import ch.nolix.template.guilook.AnthrazitGUILookCreator;

//class
public final class AnthrazitGUILookCreatorTest extends GUILookCreatorTest {
	
	//method
	@Override
	protected Style createTestUnit() {
		return AnthrazitGUILookCreator.INSTANCE.createGUILook();
	}
}
