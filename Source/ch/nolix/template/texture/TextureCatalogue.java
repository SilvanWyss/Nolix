//package declaration
package ch.nolix.template.texture;

import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//class
public final class TextureCatalogue {
	
	//constant
	public static final IImage CONCRETE_TEXTURE = TextureCreator.INSTANCE.createConcreteTexture();
	
	//constant
	public static final IImage FIR_WOOD_TEXTURE = TextureCreator.INSTANCE.createFirWoodTexture();
	
	//constant
	public static final IImage JUTE_TEXTURE = TextureCreator.INSTANCE.createJuteTexture();
	
	//constant
	public static final IImage PARCHMENT_TEXTURE = TextureCreator.INSTANCE.createParchmentTexture();
	
	//constant
	public static final IImage WHITE_MARBLE_TEXTURE = TextureCreator.INSTANCE.createWhiteMarbleTexture();
	
	//constructor
	private TextureCatalogue() {}
}
