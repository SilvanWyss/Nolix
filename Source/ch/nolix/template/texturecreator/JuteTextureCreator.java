//package declaration
package ch.nolix.template.texturecreator;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.system.texture.TextureCreator;

//class
public final class JuteTextureCreator extends TextureCreator {
	
	//method
	@Override
	protected void fillTexture16x16(final Image texture) {
		
		final var color1 = new Color(0xC0C0A0);
		final var color2 = new Color(0x808060);
		final var color3 = new Color(0xA0A080);
		
		for (var x = 1; x <= texture.getWidth(); x++) {
			for (var y = 1; y <= texture.getHeight(); y++) {							
				if ((x * y) % 3 == (x + y) % 2) {
					texture.setPixel(x, y, color1);
				} else if ((x * x + y * y) % 5 == (x * y) % 7) {
					texture.setPixel(x, y, color2);
				} else {
					texture.setPixel(x, y, color3);
				}
			}
		}
	}
}
