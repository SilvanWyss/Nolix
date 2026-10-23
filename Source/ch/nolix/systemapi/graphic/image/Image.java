/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.graphic.image;

import java.awt.image.BufferedImage;

import ch.nolix.baseapi.datastructure.matrix.IMatrix;
import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.graphic.color.IColor;

/**
 * @author Silvan Wyss
 */
public interface Image extends Element {
  /**
   * @return the bottom left pixel of the current {@link Image}
   */
  IColor getBottomLeftPixel();

  /**
   * @return the bottom right pixel of the current {@link Image}
   */
  IColor getBottomRightPixel();

  /**
   * @return the height of the current {@link Image} in pixels
   */
  int getHeight();

  /**
   * @param xPosition
   * @param yPosition
   * @return the pixel at the given xPosition and yPosition of the current
   *         {@link Image}
   * @throws RuntimeException if the current {@link Image} does not have a pixel
   *                          at the given xPosition and yPosition
   */
  IColor getPixel(int xPosition, int yPosition);

  /**
   * @return the number of pixels of the current {@link Image}
   */
  int getPixelCount();

  /**
   * @return the pixels of the current {@link Image}
   */
  IMatrix<IColor> getPixels();

  /**
   * @param xPosition
   * @param yPosition
   * @param width
   * @param height
   * @return a new image with the section of the current {@link Image} that starts
   *         at the given xPosition and yPosition and has the given width and
   *         height
   */
  IMutableImage<?> getSection(int xPosition, int yPosition, int width, int height);

  /**
   * @return the top left pixel of the current {@link Image}
   */
  IColor getTopLeftPixel();

  /**
   * @return the top right pixel of the current {@link Image}
   */
  IColor getTopRightPixel();

  /**
   * @return the width of the current {@link Image} in pixels
   */
  int getWidth();

  /**
   * @return a Base64 representation of the current {@link Image} in the JPG
   *         format
   */
  String toBase64Jpg();

  /**
   * @return a Base64 representation of the current {@link Image} in the PNG
   *         format
   */
  String toBase64Png();

  /**
   * @return a new {@link BufferedImage} from the current {@link Image}
   */
  BufferedImage toBufferedImage();

  /**
   * @return an immutable image with the pixels of the current {@link Image}
   */
  Image toImmutableImage();

  /**
   * @return the bytes of the current {@link Image} in the JPG format
   */
  byte[] toJpg();

  /**
   * @return a new image that is the current {@link Image} rotated to the left
   */
  IMutableImage<?> toLeftRotatedImage();

  /**
   * @return the bytes of the current {@link Image} in the PNG format
   */
  byte[] toPng();

  /**
   * @param width
   * @param height
   * @return a new image with the given width and height that is filled with the
   *         current {@link Image} repeatedly
   */
  IMutableImage<?> toRepeatedImage(int width, int height);

  /**
   * @return a new image that is the current {@link Image} rotated to the right
   */
  IMutableImage<?> toRightRotatedImage();

  /**
   * @param factor
   * @return a new image that is the current {@link Image} scaled by the given
   *         factor
   */
  IMutableImage<?> toScaledImage(double factor);

  /**
   * @param widthFactor
   * @param heightFactor
   * @return a new image that is the current {@link Image} scaled by the given
   *         widthFactor and heightFactor
   */
  IMutableImage<?> toScaledImage(double widthFactor, double heightFactor);

  /**
   * @param alphaValue
   * @return a new image with the pixels of the current {@link Image} and the
   *         given alphaValue
   */
  IMutableImage<?> withAlphaValue(double alphaValue);

  /**
   * @param width
   * @param height
   * @return a new image that is the current {@link Image} scaled to the given
   *         width and height
   */
  IMutableImage<?> withWidthAndHeight(int width, int height);
}
