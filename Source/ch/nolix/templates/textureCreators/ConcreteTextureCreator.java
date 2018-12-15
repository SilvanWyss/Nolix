//package declaration
package ch.nolix.templates.textureCreators;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.systemAPI.textureAPI.ITextureCreator;

//class
public final class ConcreteTextureCreator implements ITextureCreator {

	//method
	@Override
	public Image createTexture(final int width,	final int height) {
				
		final var image = new Image(width, height);
		
		for (var x = 1; x <= image.getWidth(); x++) {
			for (var y = 1; y <= image.getHeight(); y++) {
				
				image.setPixel(x, y, new Color(0xA0A0A0));
				
				if ((x * x + y * y) % 11 == (x * y) % 13) {
					image.setPixel(x, y, new Color(0xC0C0C0));
				}
				
				if ((y + 1) * (x * y - y % 3) % 7== (x * y - x % 5 + y % 2) % 11) {
					image.setPixel(x, y, new Color(0x808080));
				}
			}
		}
		
		return image;
	}
}
