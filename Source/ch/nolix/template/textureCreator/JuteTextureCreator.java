//package declaration
package ch.nolix.template.textureCreator;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.system.texture.TextureCreator;

//class
public final class JuteTextureCreator extends TextureCreator {
	
	//method
	@Override
	public Color getBaseColor() {
		return new Color(0xA0A080);
	}
	
	//method
	@Override
	protected void fillTexture16x16(final Image texture) {
		for (var x = 1; x <= texture.getWidth(); x++) {
			for (var y = 1; y <= texture.getHeight(); y++) {
												
				if ((x * y) % 3 == (x + y) % 2) {
					texture.setPixel(x, y, new Color(0xC0C0A0));
				}
				
				else if ((x * x + y * y) % 5 == (x * y) % 7) {
					texture.setPixel(x, y, new Color(0x808060));
				}
			}
		}
	}
}
