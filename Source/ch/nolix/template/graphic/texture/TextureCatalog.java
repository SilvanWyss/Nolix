/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.graphic.texture;

import ch.nolix.system.graphic.image.ImmutableImage;

/**
 * @author Silvan Wyss
 */
public final class TextureCatalog {
  public static final ImmutableImage CONCRETE_TEXTURE = TextureCreator.createConcreteTexture();

  public static final ImmutableImage FIR_WOOD_TEXTURE = TextureCreator.createFirWoodTexture();

  public static final ImmutableImage JUTE_TEXTURE = TextureCreator.createJuteTexture();

  public static final ImmutableImage PARCHMENT_TEXTURE = TextureCreator.createParchmentTexture();

  public static final ImmutableImage WHITE_MARBLE_TEXTURE = TextureCreator.createWhiteMarbleTexture();

  private TextureCatalog() {
  }
}
