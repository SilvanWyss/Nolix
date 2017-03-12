/*
 * file:	AreaTest.java
 * author:	Silvan Wyss
 * month:	2016-06
 * lines:	50
 */

//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.common.zetaTest.ZetaTest;
import ch.nolix.element.GUI.Area;
import ch.nolix.element.data.BackgroundColor;

//test class
/**
 * This class is a test class for the area class.
 */
public final class AreaTest extends ZetaTest {
	
	//test method
	public void testEquals() {
		
		//setup part 1
		final Area area1 = new Area();
		area1.setWidth(800);
		area1.setHeight(600);
		area1.setBackgroundColor(new BackgroundColor(BackgroundColor.GREEN_STRING));
		
		//setup part 2
		final Area area2 = new Area();
		area2.setWidth(800);
		area2.setHeight(600);
		area2.setBackgroundColor(new BackgroundColor(BackgroundColor.GREEN_STRING));
		
		//verification
		expectThat(area1).equals(area2);
		expectThat(area2).equals(area1);
		expectThat(area1).equalsNot(new Area());
		expectThat(area2).equalsNot(new Area());
	}
	
	//test method
	public void testRemoveBackgroundColor() {
		
		//setup
		final Area area = new Area();
		area.setBackgroundColor(new BackgroundColor(BackgroundColor.BLUE));
		
		//execution
		area.removeBackgroundColor();
		
		//verification
		expectThatNot(area.hasBackgroundColor());
	}
}
