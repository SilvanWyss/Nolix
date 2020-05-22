//package declaration
package ch.nolix.system.texture;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;

//class
public abstract class TextureCreator {
	
	//constant
	private static final int TEXTURE_16x16_WIDTH = 16;
	
	//method
	public final Image createTexture(final int width, final int height) {
		return createTexture16x16().toRepeatedImage(width, height);
	}
	
	//method
	public final Image createTexture16x16() {
		
		final var texture = new Image(TEXTURE_16x16_WIDTH, TEXTURE_16x16_WIDTH, getBaseColor());
		fillTexture16x16(texture);
		
		return texture;
	}
	
	//method declaration
	public abstract Color getBaseColor();
	
	//method declaration
	protected abstract void fillTexture16x16(Image texture);
}
