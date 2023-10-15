//package declaration
package ch.nolix.systemapi.graphicapi.imageapi;

//Java imports
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.coreapi.containerapi.matrixapi.IMatrix;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainapi.Specified;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

//interface
public interface IImage extends Specified {

  // method declaration
  IColor getBottomLeftPixel();

  // method declaration
  IColor getBottomRightPixel();

  // method declaration
  INode<?> getCompressedSpecification();

  // method declaration
  int getHeight();

  // method declaration
  IColor getPixel(int xPosition, int yPosition);

  // method declaration
  int getPixelCount();

  // method declaration
  IMatrix<IColor> getPixels();

  // method declaration
  IMutableImage<?> getSection(int xPosition, int yPosition, int width, int height);

  // method declaration
  IColor getTopLeftPixel();

  // method declaration
  IColor getTopRightPixel();

  // method declaration
  int getWidth();

  // method declaration
  BufferedImage toBufferedImage();

  // method declaration
  IImage toImmutableImage();

  // method declaration
  byte[] toJPG();

  // method declaration
  String toJPGString();

  // method declaration
  IMutableImage<?> toLeftRotatedImage();

  // method declaration
  byte[] toPNG();

  // method declaration
  String toPNGString();

  // method declaration
  IMutableImage<?> toRepeatedImage(int width, int height);

  // method declaration
  IMutableImage<?> toRightRotatedImage();

  // method declaration
  IMutableImage<?> toScaledImage(double factor);

  // method declaration
  IMutableImage<?> toScaledImage(double widthFactor, double heightFactor);

  // method declaration
  IMutableImage<?> withAlphaValue(final double alphaValue);

  // method declaration
  IMutableImage<?> withWidthAndHeight(int width, int height);
}
