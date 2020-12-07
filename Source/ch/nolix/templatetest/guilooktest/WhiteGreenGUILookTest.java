//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.template.guilook.WhiteGreenGUILook;

//class
public final class WhiteGreenGUILookTest extends GUILookTest<WhiteGreenGUILook> {
	
	//method
	@Override
	protected WhiteGreenGUILook createTestUnit() {
		return new WhiteGreenGUILook();
	}
}
