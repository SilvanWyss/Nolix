//package declaration
package ch.nolix.elementtest.widgettest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.element.widget.CaptionPosition;

//class
/**
 * A {@link CaptionPositionTest} is a test for {@link CaptionPosition}.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 70
 */
public final class CaptionPositionTest extends Test {
	
	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//execution & verification
			expect(CaptionPosition.LEFT_TOP.getSpecification().toString())
			.isEqualTo("CaptionPosition(LeftTop)");
			
			expect(CaptionPosition.LEFT.getSpecification().toString())
			.isEqualTo("CaptionPosition(Left)");
			
			expect(CaptionPosition.LEFT_BOTTOM.getSpecification().toString())
			.isEqualTo("CaptionPosition(LeftBottom)");
			
			expect(CaptionPosition.RIGHT_TOP.getSpecification().toString())
			.isEqualTo("CaptionPosition(RightTop)");
			
			expect(CaptionPosition.RIGHT.getSpecification().toString())
			.isEqualTo("CaptionPosition(Right)");
			
			expect(CaptionPosition.RIGHT_BOTTOM.getSpecification().toString())
			.isEqualTo("CaptionPosition(RightBottom)");
			
			expect(CaptionPosition.TOP_LEFT.getSpecification().toString())
			.isEqualTo("CaptionPosition(TopLeft)");
			
			expect(CaptionPosition.TOP.getSpecification().toString())
			.isEqualTo("CaptionPosition(Top)");
			
			expect(CaptionPosition.TOP_RIGHT.getSpecification().toString())
			.isEqualTo("CaptionPosition(TopRight)");
			
			expect(CaptionPosition.BOTTOM_LEFT.getSpecification().toString())
			.isEqualTo("CaptionPosition(BottomLeft)");
			
			expect(CaptionPosition.BOTTOM.getSpecification().toString())
			.isEqualTo("CaptionPosition(Bottom)");
			
			expect(CaptionPosition.BOTTOM_RIGHT.getSpecification().toString())
			.isEqualTo("CaptionPosition(BottomRight)");
	}
	
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
