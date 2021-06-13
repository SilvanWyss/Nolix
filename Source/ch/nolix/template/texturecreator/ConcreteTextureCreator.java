//package declaration
package ch.nolix.template.texturecreator;

import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.system.texture.TextureCreator;

//class
public final class ConcreteTextureCreator extends TextureCreator {
	
	//method
	@Override
	protected void fillTexture16x16(final MutableImage texture) {
		
		final var color1 = new Color(0x808080);
		final var color2 = new Color(0xC0C0C0);
		final var color3 = new Color(0xA0A0A0);
		
		for (var x = 1; x <= texture.getWidth(); x++) {
			for (var y = 1; y <= texture.getHeight(); y++) {
				
				if ((y + 1) * (x * y - y % 3) % 7 == (x * y - x % 5 + y % 2) % 11) {
					texture.setPixel(x, y, color1);
				} else if ((x * x + y * y) % 11 == (x * y) % 13) {
					texture.setPixel(x, y, color2);
				} else {
					texture.setPixel(x, y, color3);
				}
			}
		}
	}
}
