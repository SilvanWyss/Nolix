//package declaration
package ch.nolix.templatetest.guilooktest;

//own import
import ch.nolix.template.GUILook.WhiteGreenGUILook;

//class
public final class WhiteGreenGUILookTest extends GUILookTest<WhiteGreenGUILook> {
	
	//method
	@Override
	protected WhiteGreenGUILook createTestUnit() {
		return new WhiteGreenGUILook();
	}
}
