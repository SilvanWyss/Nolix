//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.template.guilook.RedLineGUILook;

//class
public final class RedLineGUILookTest extends GUILookTest<RedLineGUILook> {
	
	//method
	@Override
	protected RedLineGUILook createTestUnit() {
		return new RedLineGUILook();
	}
}
