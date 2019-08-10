//package declaration
package ch.nolix.elementTest.widgetsTest;

import ch.nolix.element.color.Color;
import ch.nolix.element.widgets.Area;

//test class
/**
 * A {@link AreaTest} is a test for {@link Area}.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 80
 */
public final class AreaTest extends WidgetTest<Area> {
	
	//test case
	public void testCase_coversPoint() {
		
		//setup
		final var area = new Area(500, 200);
		area.recalculate();
		
		//execution & verification
		expect(area.containsPoint(1, 1));
		expect(area.containsPoint(1, 200));
		expect(area.containsPoint(500, 1));
		expect(area.containsPoint(500, 100));
		expectNot(area.containsPoint(0, 1));
		expectNot(area.containsPoint(0, 200));
		expectNot(area.containsPoint(1, 0));
		expectNot(area.containsPoint(1, 201));
		expectNot(area.containsPoint(500, 0));
		expectNot(area.containsPoint(500, 201));
		expectNot(area.containsPoint(501, 1));
		expectNot(area.containsPoint(501, 200));
	}
	
	//test case
	public void testCase_equals() {
		
		//setup 
		final var area = new Area(2000, 1000, Color.GREEN);
		final var area2 = new Area(2000, 1000, Color.GREEN);
		
		//execution & verification
		expect(area.equals(area2));
	}
	
	//test case
	public void testCase_removeBackgroundColor() {
		
		//setup
		final var area = new Area(Color.GREEN);
		
		//setup verification
		expect(area.hasBackgroundColor());
		
		//execution
		area.removeBackgroundColor();
		
		//verification
		expectNot(area.hasBackgroundColor());
	}
	
	//test case
	public void testCase_setBackgroundColor() {
		
		//setup
		final var area = new Area().removeBackgroundColor();
		
		//setup verification
		expectNot(area.hasBackgroundColor());
		
		//execution
		area.setBackgroundColor(Color.GREEN);
		
		//verification
		expect(area.getBackgroundColor()).isEqualTo(Color.GREEN);
	}
	
	//method
	@Override
	protected Area createTestObject() {
		return new Area();
	}
}
