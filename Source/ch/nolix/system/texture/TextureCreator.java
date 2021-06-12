//package declaration
package ch.nolix.system.texture;

import ch.nolix.element.gui.image.Image;

//class
public abstract class TextureCreator {
	
	//constant
	private static final int TEXTURE_16_16 = 16;
	
	//method
	public final Image createTexture(final int width, final int height) {
		return createTexture16x16().toRepeatedImage(width, height);
	}
	
	//method
	public final Image createTexture16x16() {
		
		final var texture = Image.withWidthAndHeight(TEXTURE_16_16, TEXTURE_16_16);
		fillTexture16x16(texture);
		
		return texture;
	}
	
	//method declaration
	protected abstract void fillTexture16x16(Image texture);
}
