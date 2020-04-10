//package declaration
package ch.nolix.templates.textureCreators;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.system.texture.TextureCreator;

//class
public final class ConcreteTextureCreator extends TextureCreator {
	
	//method
	@Override
	public Color getBaseColor() {
		return new Color(0xA0A0A0);
	}
	
	//method
	@Override
	protected void fillTexture16x16(final Image texture) {
		for (var x = 1; x <= texture.getWidth(); x++) {
			for (var y = 1; y <= texture.getHeight(); y++) {
				
				if ((y + 1) * (x * y - y % 3) % 7 == (x * y - x % 5 + y % 2) % 11) {
					texture.setPixel(x, y, new Color(0x808080));
				}
				
				else if ((x * x + y * y) % 11 == (x * y) % 13) {
					texture.setPixel(x, y, new Color(0xC0C0C0));
				}
			}
		}
	}
}
