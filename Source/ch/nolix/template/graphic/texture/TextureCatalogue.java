package ch.nolix.template.graphic.texture;

import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public final class TextureCatalogue {

  private static final TextureCreator TEXTURE_CREATOR = new TextureCreator();

  public static final IImage CONCRETE_TEXTURE = TEXTURE_CREATOR.createConcreteTexture();

  public static final IImage FIR_WOOD_TEXTURE = TEXTURE_CREATOR.createFirWoodTexture();

  public static final IImage JUTE_TEXTURE = TEXTURE_CREATOR.createJuteTexture();

  public static final IImage PARCHMENT_TEXTURE = TEXTURE_CREATOR.createParchmentTexture();

  public static final IImage WHITE_MARBLE_TEXTURE = TEXTURE_CREATOR.createWhiteMarbleTexture();

  private TextureCatalogue() {
  }
}
