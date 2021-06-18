//package declaration
package ch.nolix.template.texturecreator;

import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.system.texture.TextureCreator;

//class
public final class JuteTextureCreator extends TextureCreator {
	
	//method
	@Override
	protected void fillTexture16x16(final MutableImage texture) {
		
		final var color1 = Color.fromValue(0xC0C0A0);
		final var color2 = Color.fromValue(0x808060);
		final var color3 = Color.fromValue(0xA0A080);
		
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
