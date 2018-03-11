//package declaration
package ch.nolix.elementTest.GUITest;

import ch.nolix.element.GUI.Area;
import ch.nolix.element.color.Color;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * An area test is a test for the area class.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 50
 */
public final class AreaTest extends Test {
	
	//test method
	public void test_equals() {
		
		//setup part 1
		final Area area1 = new Area()
		.setWidth(800)
		.setHeight(600)
		.setBackgroundColor(new BackgroundColor(BackgroundColor.GREEN_INT));
		
		//setup part 2
		final Area area2 = new Area()
		.setWidth(800)
		.setHeight(600)
		.setBackgroundColor(new BackgroundColor(BackgroundColor.GREEN_INT));
		
		//verification
		expect(area1).isEqualTo(area2);
		expect(area2).isEqualTo(area1);
	}
	
	//test method
	public void test_removeBackgroundColor() {
		
		//setup
		final Area area = new Area();
		area.setBackgroundColor(Color.BLUE);
		
		//execution
		area.removeBackgroundColor();
		
		//verification
		expectNot(area.hasBackgroundColor());
	}
}
