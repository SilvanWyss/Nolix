//package declaration
package ch.nolix.systemtest.texturetest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.system.texture.TextureCreator;

//class
public final class TextureCreatorTest extends Test {
	
	//static class
	private static class TestTextureCreator extends TextureCreator {
		
		//method
		@Override
		protected void fillTexture16x16(final Image texture) {}
	}
	
	//method
	@TestCase
	public void testCase_createTexture() {
		
		//setup
		final var testUnit = new TestTextureCreator();
		
		//execution
		final var result = testUnit.createTexture(200, 100);
		
		//verification
		expect(result.getWidth()).isEqualTo(200);
		expect(result.getHeight()).isEqualTo(100);
		for (var i = 1; i <= 100; i++) {
			for (var j = 1; j <= 100; j++) {
				expect(result.getPixel(i, j)).isEqualTo(Color.WHITE);
			}
		}		
	}
	
	//method
	@TestCase
	public void testCase_createTexture16x16() {
		
		//setup
		final var testUnit = new TestTextureCreator();
		
		//execution
		final var result = testUnit.createTexture16x16();
		
		//verification
		expect(result.getWidth()).isEqualTo(16);
		expect(result.getHeight()).isEqualTo(16);
		for (var i = 1; i <= 16; i++) {
			for (var j = 1; j <= 16; j++) {
				expect(result.getPixel(i, j)).isEqualTo(Color.WHITE);
			}
		}
	}
}
