//package declaration
package ch.nolix.system.texture;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.image.Image;

//class
public abstract class TextureCreator {
	
	//constant
	private static final int TEXTURE_16x16_WIDTH = 16;
	
	//attribute for caching
	private Image texture16x16;
	
	//method
	public final Image createTexture(final int width, final int height) {
		
		final var texture = new Image(width, height);
		final var lTexture16x16 = getTexture16x16();
		
		if (!allowsTexture16x16Rotate180Degrees()) {
			for (var i = 1; i <= width; i++) {
				for (var j = 1; j <= height; j++) {
					texture.setPixel(i, j, lTexture16x16.getPixel(1 + i % TEXTURE_16x16_WIDTH, 1 + j % TEXTURE_16x16_WIDTH));
				}
			}
		}
		
		else {
			for (var i = 1; i <= width; i++) {
				if (i % 32 <= 16) {
					for (var j = 1; j <= height; j++) {
						texture.setPixel(i, j, lTexture16x16.getPixel(1 + i % TEXTURE_16x16_WIDTH, 1 + j % TEXTURE_16x16_WIDTH));
					}
				}
				else {
					for (var j = 1; j <= height; j++) {
						texture.setPixel(i, j, lTexture16x16.getPixel(16 - i % TEXTURE_16x16_WIDTH, 1 + j % TEXTURE_16x16_WIDTH));
					}
				}
			}
		}
		
		return texture;
	}
	
	//method
	public final Image getTexture16x16() {
		
		if (texture16x16 == null) {
			texture16x16 = createTexture16x16();
		}
		
		return texture16x16;
	}
	
	//abstract method
	public abstract boolean allowsTexture16x16Rotate180Degrees();
	
	//method declaration
	protected abstract void fillTexture16x16(Image texture);
	
	//method
	private Image createTexture16x16() {
		
		final var texture = new Image(TEXTURE_16x16_WIDTH, TEXTURE_16x16_WIDTH);
		fillTexture16x16(texture);
		validateTexture16x16(texture);
		
		return texture;
	}
	
	//method
	private void validateTexture16x16(final Image texture) {
		
		Validator.suppose(texture).thatIsNamed(VariableNameCatalogue.TEXTURE).isNotNull();
		Validator.suppose(texture.getWidth()).thatIsNamed("texture width").isEqualTo(TEXTURE_16x16_WIDTH);
		Validator.suppose(texture.getHeight()).thatIsNamed("texture height").isEqualTo(TEXTURE_16x16_WIDTH);
		
		if (!allowsTexture16x16Rotate180Degrees()) {
			for (var i = 1; i <= TEXTURE_16x16_WIDTH; i++) {
				Validator
				.suppose(texture.getPixel(TEXTURE_16x16_WIDTH, i))
				.thatIsNamed("pixel (" + TEXTURE_16x16_WIDTH + "," + i + ")")
				.isEqualTo(texture.getPixel(TEXTURE_16x16_WIDTH, i));
			}
		}
	}
}
