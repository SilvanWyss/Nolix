//package declaration
package ch.nolix.templates.textureCreators;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.systemAPI.textureAPI.ITextureCreator;

//class
public final class JuteTextureCreator implements ITextureCreator {

	//method
	@Override
	public Image createTexture(
		final int width,
		final int height
	) {
		
		final var image = new Image(width, height);
		
		for (var x = 1; x <= image.getWidth(); x++) {
			for (var y = 1; y <= image.getHeight(); y++) {
				
				image.setPixel(x, y, new Color(0xA0A080));
								
				if ((x * y) % 3 == (x + y) % 2) {
					image.setPixel(x, y, new Color(0xC0C0A0));
				}
				
				if ((x * x + y * y) % 5 == (x * y) % 7) {
					image.setPixel(x, y, new Color(0x808060));
				}
			}
		}
		
		return image;
	}
}
