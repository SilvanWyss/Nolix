//package declaration
package ch.nolix.elementTest.imageTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;

//class
public final class ImageTest extends Test {
	
	//method
	@TestCase
	public void testCase_toRepeatedImage() {
		
		//setup
		final var image =
		new Image(2, 2)
		.setPixel(1, 1, Color.YELLOW)
		.setPixel(1, 2, Color.RED)
		.setPixel(2, 1, Color.GREEN)
		.setPixel(2, 2, Color.BLUE);
		
		//execution
		final var result = image.toRepeatedImage(4, 4);
		
		//verification
		expect(result.getWidth()).isEqualTo(4);
		expect(result.getHeight()).isEqualTo(4);
		expect(result.getPixel(1, 1)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(1, 2)).isEqualTo(Color.RED);
		expect(result.getPixel(1, 3)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(1, 4)).isEqualTo(Color.RED);
		expect(result.getPixel(2, 1)).isEqualTo(Color.GREEN);
		expect(result.getPixel(2, 2)).isEqualTo(Color.BLUE);
		expect(result.getPixel(2, 3)).isEqualTo(Color.GREEN);
		expect(result.getPixel(2, 4)).isEqualTo(Color.BLUE);
		expect(result.getPixel(3, 1)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(3, 2)).isEqualTo(Color.RED);
		expect(result.getPixel(3, 3)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(3, 4)).isEqualTo(Color.RED);
		expect(result.getPixel(4, 1)).isEqualTo(Color.GREEN);
		expect(result.getPixel(4, 2)).isEqualTo(Color.BLUE);
		expect(result.getPixel(4, 3)).isEqualTo(Color.GREEN);
		expect(result.getPixel(4, 4)).isEqualTo(Color.BLUE);
	}
	
	//method
	@TestCase
	public void testCase_toScaledImage() {
		
		//setup
		final var image =
		new Image(2, 2)
		.setPixel(1, 1, Color.YELLOW)
		.setPixel(1, 2, Color.RED)
		.setPixel(2, 1, Color.GREEN)
		.setPixel(2, 2, Color.BLUE);
		
		//execution
		final var result = image.toScaledImage(2.0);
		
		//verification
		expect(result.getWidth()).isEqualTo(4);
		expect(result.getHeight()).isEqualTo(4);
		expect(result.getPixel(1, 1)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(1, 2)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(1, 3)).isEqualTo(Color.RED);
		expect(result.getPixel(1, 4)).isEqualTo(Color.RED);
		expect(result.getPixel(2, 1)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(2, 2)).isEqualTo(Color.YELLOW);
		expect(result.getPixel(2, 3)).isEqualTo(Color.RED);
		expect(result.getPixel(2, 4)).isEqualTo(Color.RED);
		expect(result.getPixel(3, 1)).isEqualTo(Color.GREEN);
		expect(result.getPixel(3, 2)).isEqualTo(Color.GREEN);
		expect(result.getPixel(3, 3)).isEqualTo(Color.BLUE);
		expect(result.getPixel(3, 4)).isEqualTo(Color.BLUE);
		expect(result.getPixel(4, 1)).isEqualTo(Color.GREEN);
		expect(result.getPixel(4, 2)).isEqualTo(Color.GREEN);
		expect(result.getPixel(4, 3)).isEqualTo(Color.BLUE);
		expect(result.getPixel(4, 4)).isEqualTo(Color.BLUE);
	}
}
