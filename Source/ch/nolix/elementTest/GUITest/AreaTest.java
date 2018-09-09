//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.element.GUI.Area;
import ch.nolix.element.color.Color;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A {@link AreaTest} is a test for a {@link Area}.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 60
 */
public final class AreaTest extends Test {
	
	//test case
	public void testCase_equals() {
		
		//setup part 1
		final Area area1 =
		new Area()
		.setWidth(2000)
		.setHeight(1000)
		.setBackgroundColor(Color.GREEN);
		
		//setup part 2
		final Area area2 =
		new Area()
		.setWidth(2000)
		.setHeight(1000)
		.setBackgroundColor(Color.GREEN);
		
		//verification
		expect(area1).isEqualTo(area2);
		expect(area2).isEqualTo(area1);
	}
	
	//test case
	public void testCase_removeBackgroundColor() {
		
		//setup
		final Area area = new Area().setBackgroundColor(Color.GREEN);
		
		//execution
		area.removeBackgroundColor();
		
		//verification
		expectNot(area.hasBackgroundColor());
	}
	
	//test case
	public void testCase_setBackgroundColor() {
		
		//setup
		final Area area = new Area().removeBackgroundColor();
		
		//execution
		area.setBackgroundColor(Color.GREEN);
		
		//verification
		expect(area.hasBackgroundColor());
		expect(area.getBackgroundColor()).isEqualTo(Color.GREEN);
	}
}
