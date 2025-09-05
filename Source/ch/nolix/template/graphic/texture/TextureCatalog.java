package ch.nolix.template.graphic.texture;

import ch.nolix.systemapi.graphic.image.IImage;

public final class TextureCatalog {
  public static final IImage CONCRETE_TEXTURE = TextureCreator.createConcreteTexture();

  public static final IImage FIR_WOOD_TEXTURE = TextureCreator.createFirWoodTexture();

  public static final IImage JUTE_TEXTURE = TextureCreator.createJuteTexture();

  public static final IImage PARCHMENT_TEXTURE = TextureCreator.createParchmentTexture();

  public static final IImage WHITE_MARBLE_TEXTURE = TextureCreator.createWhiteMarbleTexture();

  private TextureCatalog() {
  }
}
