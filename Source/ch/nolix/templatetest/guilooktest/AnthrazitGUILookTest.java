//package declaration
package ch.nolix.templatetest.guilooktest;

import ch.nolix.template.guilook.AnthrazitGUILook;

//class
public final class AnthrazitGUILookTest extends GUILookTest<AnthrazitGUILook> {
	
	//method
	@Override
	protected AnthrazitGUILook createTestUnit() {
		return new AnthrazitGUILook();
	}
}
