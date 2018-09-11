//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.element.GUI.Area;
import ch.nolix.element.color.Color;

//test class
/**
 * A {@link AreaTest} is a test for {@link Area}.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 70
 */
public final class AreaTest extends WidgetTest<Area> {
	
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
		
		//execution & verification
		expect(area1.equals(area2));
		expect(area2.equals(area1));
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
	
	//method
	protected Area createTestObject() {
		return new Area();
	}
}
