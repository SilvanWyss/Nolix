//package declaration
package ch.nolix.systemtest.guitest.widgettest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.gui.widget.CaptionPosition;

//class
public final class CaptionPositionTest extends Test {
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//execution & verification
		expect(CaptionPosition.LEFT_TOP.toString()).isEqualTo("LEFT_TOP");
		expect(CaptionPosition.LEFT.toString()).isEqualTo("LEFT");
		expect(CaptionPosition.LEFT_BOTTOM.toString()).isEqualTo("LEFT_BOTTOM");
		expect(CaptionPosition.RIGHT_TOP.toString()).isEqualTo("RIGHT_TOP");
		expect(CaptionPosition.RIGHT.toString()).isEqualTo("RIGHT");
		expect(CaptionPosition.RIGHT_BOTTOM.toString()).isEqualTo("RIGHT_BOTTOM");
		expect(CaptionPosition.TOP_LEFT.toString()).isEqualTo("TOP_LEFT");
		expect(CaptionPosition.TOP.toString()).isEqualTo("TOP");
		expect(CaptionPosition.TOP_RIGHT.toString()).isEqualTo("TOP_RIGHT");
		expect(CaptionPosition.BOTTOM_LEFT.toString()).isEqualTo("BOTTOM_LEFT");
		expect(CaptionPosition.BOTTOM.toString()).isEqualTo("BOTTOM");
		expect(CaptionPosition.BOTTOM_RIGHT.toString()).isEqualTo("BOTTOM_RIGHT");
	}
}
