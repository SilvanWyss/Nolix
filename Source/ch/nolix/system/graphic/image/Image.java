//package declaration
package ch.nolix.system.graphic.image;

//Java imports
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;

//class
public final class Image extends Element implements IImage {

  // static method
  public static Image fromAnyImage(final IImage image) {

    if (image instanceof Image lImage) {
      return lImage;
    }

    return new Image(MutableImage.fromAnyImage(image));
  }

  // static method
  public static Image fromBytes(final byte[] bytes) {

    final var mutableImage = MutableImage.fromBytes(bytes);

    return fromAnyImage(mutableImage);
  }

  // static method
  public static Image fromFile(final String filePath) {
    return new Image(MutableImage.fromFile(filePath));
  }

  // static method
  public static Image fromResource(final String path) {
    return new Image(MutableImage.fromBytes(RunningJar.getResourceAsBytes(path)));
  }

  // static method
  public static Image fromSpecification(final INode<?> specification) {
    return new Image(MutableImage.fromSpecification(specification));
  }

  // static method
  public static Image withPixels(Matrix<IColor> pixels) {
    return new Image(MutableImage.withPixels(pixels));
  }

  // attribute
  private final MutableImage internalImage;

  // constructor
  private Image(final MutableImage internalImage) {

    GlobalValidator.assertThat(internalImage).thatIsNamed("internal image").isNotNull();

    this.internalImage = internalImage;
  }

  // method
  @Override
  public IContainer<INode<?>> getAttributes() {
    return internalImage.getAttributes();
  }

  // method
  @Override
  public IColor getBottomLeftPixel() {
    return internalImage.getBottomLeftPixel();
  }

  // method
  @Override
  public IColor getBottomRightPixel() {
    return internalImage.getBottomRightPixel();
  }

  // method
  @Override
  public Node getCompressedSpecification() {
    return internalImage.getCompressedSpecification();
  }

  // method
  @Override
  public int getHeight() {
    return internalImage.getHeight();
  }

  // method
  @Override
  public IColor getPixel(int xPosition, int yPosition) {
    return internalImage.getPixel(xPosition, yPosition);
  }

  // method
  @Override
  public int getPixelCount() {
    return internalImage.getPixelCount();
  }

  // method
  @Override
  public Matrix<IColor> getPixels() {
    return internalImage.getPixels();
  }

  // method
  @Override
  public MutableImage getSection(int xPosition, int yPosition, int width, int height) {
    return internalImage.getSection(xPosition, yPosition, width, height);
  }

  // method
  @Override
  public IColor getTopLeftPixel() {
    return internalImage.getTopLeftPixel();
  }

  // method
  @Override
  public IColor getTopRightPixel() {
    return internalImage.getTopRightPixel();
  }

  // method
  @Override
  public int getWidth() {
    return internalImage.getWidth();
  }

  // method
  @Override
  public BufferedImage toBufferedImage() {
    return internalImage.toBufferedImage();
  }

  // method
  @Override
  public Image toImmutableImage() {
    return this;
  }

  // method
  @Override
  public byte[] toJPG() {
    return internalImage.toJPG();
  }

  // method
  @Override
  public String toJPGString() {
    return internalImage.toJPGString();
  }

  // method
  @Override
  public MutableImage toLeftRotatedImage() {
    return internalImage.toLeftRotatedImage();
  }

  // method
  public MutableImage toMutableImage() {
    return internalImage.getCopy();
  }

  // method
  @Override
  public byte[] toPNG() {
    return internalImage.toPNG();
  }

  // method
  @Override
  public String toPNGString() {
    return internalImage.toPNGString();
  }

  // method
  @Override
  public MutableImage toRepeatedImage(int width, int height) {
    return internalImage.toRepeatedImage(width, height);
  }

  // method
  @Override
  public MutableImage toRightRotatedImage() {
    return internalImage.toRightRotatedImage();
  }

  // method
  @Override
  public MutableImage toScaledImage(double factor) {
    return internalImage.toScaledImage(factor);
  }

  // method
  @Override
  public MutableImage toScaledImage(double widthFactor, double heightFactor) {
    return internalImage.toScaledImage(widthFactor, heightFactor);
  }

  // method
  @Override
  public IMutableImage<?> withAlphaValue(final double alphaValue) {
    return internalImage.withAlphaValue(alphaValue);
  }

  // method
  @Override
  public IMutableImage<?> withWidthAndHeight(final int width, final int height) {
    return internalImage.withWidthAndHeight(width, height);
  }
}
