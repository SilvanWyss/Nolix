//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.template.guilook.AnthrazitGUILook;

//class
public final class AnthrazitGUILookTest extends GUILookTest<AnthrazitGUILook> {
	
	//method
	@Override
	protected AnthrazitGUILook createTestUnit() {
		return new AnthrazitGUILook();
	}
}
