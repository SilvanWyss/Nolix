//package declaration
package ch.nolix.systemTest.textureTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.system.texture.TextureCreator;

//test class
public final class TextureCreatorTest extends Test {
	
	//static class
	private static class TestTextureCreator extends TextureCreator {
		
		//method
		@Override
		public boolean allowsTexture16x16Rotate180Degrees() {
			return false;
		}
		
		//method
		@Override
		protected void fillTexture16x16(Image texture) {
			for (var i = 1; i <= 16; i++) {
				for (var j = 1; j <= 16; j++) {
					texture.setPixel(i, j, Color.RED);
				}
			}
		}
	}
	
	//method
	@TestCase
	public void testCase_createTexture() {
		
		//setup
		final var testUnit = new TestTextureCreator();
		
		//execution
		final var texture = testUnit.createTexture(200, 100);
		
		//verification
		expect(texture.getWidth()).isEqualTo(200);
		expect(texture.getHeight()).isEqualTo(100);
		for (var i = 1; i <= 100; i++) {
			for (var j = 1; j <= 100; j++) {
				expect(texture.getPixel(i, j)).isEqualTo(Color.RED);
			}
		}		
	}
	
	//method
	@TestCase
	public void testCase_getTexture16x16() {
		
		//setup
		final var testUnit = new TestTextureCreator();
		
		//execution
		final var texture16x16 = testUnit.getTexture16x16();
		
		//verification
		expect(texture16x16.getWidth()).isEqualTo(16);
		expect(texture16x16.getHeight()).isEqualTo(16);
		for (var i = 1; i <= 16; i++) {
			for (var j = 1; j <= 16; j++) {
				expect(texture16x16.getPixel(i, j)).isEqualTo(Color.RED);
			}
		}
	}
}
