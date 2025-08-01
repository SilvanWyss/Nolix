package ch.nolix.system.graphic.image;

import java.awt.image.BufferedImage;

import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.graphic.image.IMutableImage;

public final class Image extends AbstractElement implements IImage {

  private final MutableImage internalImage;

  private Image(final MutableImage internalImage) {

    Validator.assertThat(internalImage).thatIsNamed("internal image").isNotNull();

    this.internalImage = internalImage;
  }

  public static Image fromAnyImage(final IImage image) {

    if (image instanceof final Image lImage) {
      return lImage;
    }

    return new Image(MutableImage.fromAnyImage(image));
  }

  public static Image fromBytes(final byte[] bytes) {

    final var mutableImage = MutableImage.fromBytes(bytes);

    return fromAnyImage(mutableImage);
  }

  public static Image fromFile(final String filePath) {
    return new Image(MutableImage.fromFile(filePath));
  }

  public static Image fromResource(final String path) {
    return new Image(MutableImage.fromBytes(RunningJar.getResourceAsBytes(path)));
  }

  public static Image fromSpecification(final INode<?> specification) {
    return new Image(MutableImage.fromSpecification(specification));
  }

  public static Image withPixels(Matrix<IColor> pixels) {
    return new Image(MutableImage.withPixels(pixels));
  }

  @Override
  public IContainer<INode<?>> getAttributes() {
    return internalImage.getAttributes();
  }

  @Override
  public IColor getBottomLeftPixel() {
    return internalImage.getBottomLeftPixel();
  }

  @Override
  public IColor getBottomRightPixel() {
    return internalImage.getBottomRightPixel();
  }

  @Override
  public int getHeight() {
    return internalImage.getHeight();
  }

  @Override
  public IColor getPixel(int xPosition, int yPosition) {
    return internalImage.getPixel(xPosition, yPosition);
  }

  @Override
  public int getPixelCount() {
    return internalImage.getPixelCount();
  }

  @Override
  public Matrix<IColor> getPixels() {
    return internalImage.getPixels();
  }

  @Override
  public MutableImage getSection(int xPosition, int yPosition, int width, int height) {
    return internalImage.getSection(xPosition, yPosition, width, height);
  }

  @Override
  public IColor getTopLeftPixel() {
    return internalImage.getTopLeftPixel();
  }

  @Override
  public IColor getTopRightPixel() {
    return internalImage.getTopRightPixel();
  }

  @Override
  public int getWidth() {
    return internalImage.getWidth();
  }

  @Override
  public String toBase64Jpg() {
    return internalImage.toBase64Jpg();
  }

  @Override
  public String toBase64Png() {
    return internalImage.toBase64Png();
  }

  @Override
  public BufferedImage toBufferedImage() {
    return internalImage.toBufferedImage();
  }

  @Override
  public Image toImmutableImage() {
    return this;
  }

  @Override
  public byte[] toJpg() {
    return internalImage.toJpg();
  }

  @Override
  public MutableImage toLeftRotatedImage() {
    return internalImage.toLeftRotatedImage();
  }

  public MutableImage toMutableImage() {
    return internalImage.getCopy();
  }

  @Override
  public byte[] toPng() {
    return internalImage.toPng();
  }

  @Override
  public MutableImage toRepeatedImage(int width, int height) {
    return internalImage.toRepeatedImage(width, height);
  }

  @Override
  public MutableImage toRightRotatedImage() {
    return internalImage.toRightRotatedImage();
  }

  @Override
  public MutableImage toScaledImage(double factor) {
    return internalImage.toScaledImage(factor);
  }

  @Override
  public MutableImage toScaledImage(double widthFactor, double heightFactor) {
    return internalImage.toScaledImage(widthFactor, heightFactor);
  }

  @Override
  public IMutableImage<?> withAlphaValue(final double alphaValue) {
    return internalImage.withAlphaValue(alphaValue);
  }

  @Override
  public IMutableImage<?> withWidthAndHeight(final int width, final int height) {
    return internalImage.withWidthAndHeight(width, height);
  }
}
