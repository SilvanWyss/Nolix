/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.graphic.texture;

import ch.nolix.system.graphic.image.Image;

/**
 * @author Silvan Wyss
 */
public final class TextureCatalog {
  public static final Image CONCRETE_TEXTURE = TextureCreator.createConcreteTexture();

  public static final Image FIR_WOOD_TEXTURE = TextureCreator.createFirWoodTexture();

  public static final Image JUTE_TEXTURE = TextureCreator.createJuteTexture();

  public static final Image PARCHMENT_TEXTURE = TextureCreator.createParchmentTexture();

  public static final Image WHITE_MARBLE_TEXTURE = TextureCreator.createWhiteMarbleTexture();

  private TextureCatalog() {
  }
}
