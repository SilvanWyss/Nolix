//package declaration
package ch.nolix.elementTest.widgetTest;

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
			expect(CaptionPosition.LeftTop.getSpecification().toString())
			.isEqualTo("CaptionPosition(LeftTop)");
			
			expect(CaptionPosition.Left.getSpecification().toString())
			.isEqualTo("CaptionPosition(Left)");
			
			expect(CaptionPosition.LeftBottom.getSpecification().toString())
			.isEqualTo("CaptionPosition(LeftBottom)");
			
			expect(CaptionPosition.RightTop.getSpecification().toString())
			.isEqualTo("CaptionPosition(RightTop)");
			
			expect(CaptionPosition.Right.getSpecification().toString())
			.isEqualTo("CaptionPosition(Right)");
			
			expect(CaptionPosition.RightBottom.getSpecification().toString())
			.isEqualTo("CaptionPosition(RightBottom)");
			
			expect(CaptionPosition.TopLeft.getSpecification().toString())
			.isEqualTo("CaptionPosition(TopLeft)");
			
			expect(CaptionPosition.Top.getSpecification().toString())
			.isEqualTo("CaptionPosition(Top)");
			
			expect(CaptionPosition.TopRight.getSpecification().toString())
			.isEqualTo("CaptionPosition(TopRight)");
			
			expect(CaptionPosition.BottomLeft.getSpecification().toString())
			.isEqualTo("CaptionPosition(BottomLeft)");
			
			expect(CaptionPosition.Bottom.getSpecification().toString())
			.isEqualTo("CaptionPosition(Bottom)");
			
			expect(CaptionPosition.BottomRight.getSpecification().toString())
			.isEqualTo("CaptionPosition(BottomRight)");
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//execution & verification
		expect(CaptionPosition.LeftTop.toString()).isEqualTo("LeftTop");
		expect(CaptionPosition.Left.toString()).isEqualTo("Left");
		expect(CaptionPosition.LeftBottom.toString()).isEqualTo("LeftBottom");
		expect(CaptionPosition.RightTop.toString()).isEqualTo("RightTop");
		expect(CaptionPosition.Right.toString()).isEqualTo("Right");
		expect(CaptionPosition.RightBottom.toString()).isEqualTo("RightBottom");
		expect(CaptionPosition.TopLeft.toString()).isEqualTo("TopLeft");
		expect(CaptionPosition.Top.toString()).isEqualTo("Top");
		expect(CaptionPosition.TopRight.toString()).isEqualTo("TopRight");
		expect(CaptionPosition.BottomLeft.toString()).isEqualTo("BottomLeft");
		expect(CaptionPosition.Bottom.toString()).isEqualTo("Bottom");
		expect(CaptionPosition.BottomRight.toString()).isEqualTo("BottomRight");
	}
}
