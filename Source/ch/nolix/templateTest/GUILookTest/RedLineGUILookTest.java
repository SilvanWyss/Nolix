//package declaration
package ch.nolix.templateTest.GUILookTest;

//own import
import ch.nolix.template.GUILook.RedLineGUILook;

//class
public final class RedLineGUILookTest extends GUILookTest<RedLineGUILook> {
	
	//method
	@Override
	protected RedLineGUILook createTestUnit() {
		return new RedLineGUILook();
	}
}
