//package declaration
package ch.nolix.element.gui.image;

//Java imports
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.matrix.Matrix;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.environment.runningjar.RunningJar;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementapi.IElement;
import ch.nolix.element.gui.color.Color;

//class
public final class Image implements IElement<Image>, IImage {
	
	//static method
	public static Image fromFile(final String filePath) {
		return new Image(MutableImage.fromFile(filePath));
	}
	
	//static method
	public static Image fromResource(final String path) {
		return new Image(MutableImage.fromBytes(RunningJar.getResourceAsBytes(path)));
	}
	
	//static method
	public static Image fromSpecification(final BaseNode specification) {
		return new Image(MutableImage.fromSpecification(specification));
	}
	
	//static method
	public static Image withPixels(Matrix<Color> pixels) {
		return new Image(MutableImage.withPixels(pixels));
	}
	
	//attribute
	private final MutableImage internalImage;
	
	//constructor
	private Image(final MutableImage internalImage) {
		
		Validator.assertThat(internalImage).thatIsNamed("internal image").isNotNull();
		
		this.internalImage = internalImage;
	}
	
	//method
	@Override
	public void fillUpAttributesInto(LinkedList<Node> list) {
		internalImage.fillUpAttributesInto(list);
	}
	
	//method
	@Override
	public Color getBottomLeftPixel() {
		return internalImage.getBottomLeftPixel();
	}
	
	//method
	@Override
	public Color getBottomRightPixel() {
		return internalImage.getBottomRightPixel();
	}
	
	//method
	@Override
	public int getHeight() {
		return internalImage.getHeight();
	}
	
	//method
	@Override
	public Color getPixel(int xPosition, int yPosition) {
		return internalImage.getPixel(xPosition, yPosition);
	}
	
	//method
	@Override
	public int getPixelCount() {
		return internalImage.getPixelCount();
	}
	
	//method
	@Override
	public MutableImage getSection(int xPosition, int yPosition, int width, int height) {
		return internalImage.getSection(xPosition, yPosition, width, height);
	}
	
	//method
	@Override
	public Color getTopLeftPixel() {
		return internalImage.getTopLeftPixel();
	}
	
	//method
	@Override
	public Color getTopRightPixel() {
		return internalImage.getTopRightPixel();
	}
	
	//method
	@Override
	public int getWidth() {
		return internalImage.getWidth();
	}
	
	//method
	@Override
	public BufferedImage toBufferedImage() {
		return internalImage.toBufferedImage();
	}
	
	//method
	@Override
	public Image toImmutableImage() {
		return this;
	}
	
	//method
	@Override
	public byte[] toJPG() {
		return internalImage.toJPG();
	}
	
	//method
	@Override
	public MutableImage toLeftRotatedImage() {
		return internalImage.toLeftRotatedImage();
	}
	
	//method
	public MutableImage toMutableImage() {
		return internalImage.getCopy();
	}
	
	//method
	@Override
	public byte[] toPNG() {
		return internalImage.toPNG();
	}
	
	//method
	@Override
	public MutableImage toRepeatedImage(int width, int height) {
		return internalImage.toRepeatedImage(width, height);
	}
	
	//method
	@Override
	public MutableImage toRightRotatedImage() {
		return internalImage.toRightRotatedImage();
	}
	
	//method
	@Override
	public MutableImage toScaledImage(double factor) {
		return internalImage.toScaledImage(factor);
	}
	
	//method
	@Override
	public MutableImage toScaledImage(double widthFactor, double heightFactor) {
		return internalImage.toScaledImage(widthFactor, heightFactor);
	}
	
	//method
	@Override
	public MutableImage toStretchedImage(final int width, final int height) {
		return internalImage.toStretchedImage(width, height);
	}
}
