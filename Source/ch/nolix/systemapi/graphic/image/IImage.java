/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.graphic.image;

import java.awt.image.BufferedImage;

import ch.nolix.coreapi.container.matrix.IMatrix;
import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.graphic.color.IColor;

/**
 * @author Silvan Wyss
 */
public interface IImage extends IElement {
  IColor getBottomLeftPixel();

  IColor getBottomRightPixel();

  int getHeight();

  IColor getPixel(int xPosition, int yPosition);

  int getPixelCount();

  IMatrix<IColor> getPixels();

  IMutableImage<?> getSection(int xPosition, int yPosition, int width, int height);

  IColor getTopLeftPixel();

  IColor getTopRightPixel();

  int getWidth();

  String toBase64Jpg();

  String toBase64Png();

  BufferedImage toBufferedImage();

  IImage toImmutableImage();

  byte[] toJpg();

  IMutableImage<?> toLeftRotatedImage();

  byte[] toPng();

  IMutableImage<?> toRepeatedImage(int width, int height);

  IMutableImage<?> toRightRotatedImage();

  IMutableImage<?> toScaledImage(double factor);

  IMutableImage<?> toScaledImage(double widthFactor, double heightFactor);

  IMutableImage<?> withAlphaValue(double alphaValue);

  IMutableImage<?> withWidthAndHeight(int width, int height);
}
