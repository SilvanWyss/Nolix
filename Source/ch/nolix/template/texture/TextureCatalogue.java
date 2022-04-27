//package declaration
package ch.nolix.template.texture;

//own imports
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//class
public final class TextureCatalogue {
	
	//constant
	public static final IImage<?> CONCRETE_TEXTURE = TextureCreator.INSTANCE.createConcreteTexture();
	
	//constant
	public static final IImage<?> JUTE_TEXTURE = TextureCreator.INSTANCE.createJuteTexture();
	
	//constant
	public static final IImage<?> WHITE_MARBLE_TEXTURE = TextureCreator.INSTANCE.createWhiteMarbleTexture();
	
	//constructor
	private TextureCatalogue() {}
}
