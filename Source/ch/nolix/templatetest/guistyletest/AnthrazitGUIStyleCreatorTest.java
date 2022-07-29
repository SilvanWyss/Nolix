//package declaration
package ch.nolix.templatetest.guistyletest;

import ch.nolix.system.element.style.Style;
import ch.nolix.template.guistyle.AnthrazitGUIStyleCreator;

//class
public final class AnthrazitGUIStyleCreatorTest extends GUIStyleCreatorTest {
	
	//method
	@Override
	protected Style createTestUnit() {
		return AnthrazitGUIStyleCreator.INSTANCE.createGUIStyle();
	}
}
