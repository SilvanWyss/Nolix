//package declaration
package ch.nolix.templatetest.guilooktest;

//own import
import ch.nolix.template.GUILook.AnthrazitGUILook;

//class
public final class AnthrazitGUILookTest extends GUILookTest<AnthrazitGUILook> {
	
	//method
	@Override
	protected AnthrazitGUILook createTestUnit() {
		return new AnthrazitGUILook();
	}
}
