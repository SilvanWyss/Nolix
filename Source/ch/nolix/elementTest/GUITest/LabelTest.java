//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.element.GUI.Label;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A {@link LabelTest} is a test for a {@link Label}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class LabelTest extends Test {
	
	//test method
	public void test_getRefBaseLook_setTextSize() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.getRefBaseLook().setTextSize(25);
		
		//verification
		expect(label.getRefBaseLook().getRecursiveOrDefaultTextSize()).isEqualTo(25);
	}
	
	//test method
	public void test_setText() {
		
		//setup
		final var label = new Label();
		
		//execution
		label.setText("Helix");
		
		//verification
		expect(label.getText()).isEqualTo("Helix");
	}
}
