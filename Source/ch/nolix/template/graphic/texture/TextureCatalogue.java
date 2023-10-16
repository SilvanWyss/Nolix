//package declaration
package ch.nolix.template.graphic.texture;

//own imports
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

//class
public final class TextureCatalogue {

  //constant
  private static final TextureCreator TEXTURE_CREATOR = new TextureCreator();

  //constant
  public static final IImage CONCRETE_TEXTURE = TEXTURE_CREATOR.createConcreteTexture();

  //constant
  public static final IImage FIR_WOOD_TEXTURE = TEXTURE_CREATOR.createFirWoodTexture();

  //constant
  public static final IImage JUTE_TEXTURE = TEXTURE_CREATOR.createJuteTexture();

  //constant
  public static final IImage PARCHMENT_TEXTURE = TEXTURE_CREATOR.createParchmentTexture();

  //constant
  public static final IImage WHITE_MARBLE_TEXTURE = TEXTURE_CREATOR.createWhiteMarbleTexture();

  //constructor
  private TextureCatalogue() {
  }
}
