//package declaration
package ch.nolix.elementtest.widgettest;

import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Area;

//class
/**
 * A {@link AreaTest} is a test for {@link Area}.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 80
 */
public final class AreaTest extends WidgetTest<Area> {
	
	//method
	@TestCase
	public void testCase_containsPoint() {
		
		//setup
		final var area = new Area().setSize(500, 200);
		area.recalculate();
		
		//execution & verification
		expect(area.containsPoint(0, 0));
		expect(area.containsPoint(0, 199));
		expect(area.containsPoint(499, 0));
		expect(area.containsPoint(499, 199));
		expectNot(area.containsPoint(-1, 0));
		expectNot(area.containsPoint(-1, 199));
		expectNot(area.containsPoint(1, -1));
		expectNot(area.containsPoint(1, 200));
		expectNot(area.containsPoint(500, -1));
		expectNot(area.containsPoint(500, 200));
		expectNot(area.containsPoint(500, 0));
		expectNot(area.containsPoint(500, 199));
	}
	
	//method
	@TestCase
	public void testCase_equals() {
		
		//setup 
		final var area = new Area().setSize(2000, 1000).setBackgroundColor(Color.GREEN);
		final var area2 = new Area().setSize(2000, 1000).setBackgroundColor(Color.GREEN);
		
		//execution & verification
		expect(area.equals(area2));
	}
	
	//method
	@TestCase
	public void testCase_removeBackgroundColor() {
		
		//setup
		final var area = new Area().setBackgroundColor(Color.GREEN);
		
		//setup verification
		expect(area.hasBackgroundColor());
		
		//execution
		area.removeBackgroundColor();
		
		//verification
		expectNot(area.hasBackgroundColor());
	}
	
	//method
	@TestCase
	public void testCase_setBackgroundColor() {
		
		//setup
		final var area = new Area();
		area.removeBackgroundColor();
		
		//setup verification
		expectNot(area.hasBackgroundColor());
		
		//execution
		area.setBackgroundColor(Color.GREEN);
		
		//verification
		expect(area.getBackgroundColor()).isEqualTo(Color.GREEN);
	}
	
	//method
	@Override
	protected Area createTestUnit() {
		return new Area();
	}
}
