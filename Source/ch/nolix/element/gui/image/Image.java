//package declaration
package ch.nolix.element.gui.image;

//Java imports
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.commontype.commontypehelper.GlobalBufferedImageHelper;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.container.matrix.Matrix;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.environment.filesystem.FileSystemAccessor;
import ch.nolix.common.environment.runningjar.RunningJar;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.Value;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.gui.color.Color;

//class
public final class Image extends Element<Image> implements IMutableElement<Image> {

	//constant
	public static final String TYPE_NAME = "Image";
	
	//constant
	private static final String PIXEL_ARRAY_HEADER = "PixelArray";
	
	//static method
	public static Image fromBytes(final byte[] bytes) {
		
		//TODO: Refactor this implementation.
		try {
			
			final var path = "tempImage" + System.currentTimeMillis();
			FileSystemAccessor.createFile(path).overwriteFile(bytes);			
			final var bufferedImage = ImageIO.read(new File(path));
			FileSystemAccessor.deleteFileSystemItem(path);
			
			return fromBufferedImage(bufferedImage);
			
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//static method
	public static Image fromBufferedImage(final BufferedImage bufferedImage) {
		
		final var image = new Image(bufferedImage.getWidth(), bufferedImage.getHeight());
		
		for (var i = 1; i <= image.getWidth(); i++) {
			for (var j = 1; j <= image.getHeight(); j++) {
				final var pixel = bufferedImage.getRGB(i - 1, j - 1);
				image.setPixel(i, j, new Color((pixel>>16) & 0xff, (pixel>>8) & 0xff, pixel & 0xFF, (pixel>>24) & 0xff));
			}
		}
		
		return image;
	}
	
	//static method
	public static Image fromFile(final String filePath) {
		
		final var bufferedImage = GlobalBufferedImageHelper.fromFile(filePath);
		
		return fromBufferedImage(bufferedImage);
	}
	
	//static method
	public static Image fromResource(final String path) {
		return fromBytes(RunningJar.getResourceAsBytes(path));
	}
	
	//static method
	public static Image fromSpecification(final BaseNode specification) {
		
		final var image =
		new Image(
			specification.getRefFirstAttribute(a -> a.hasHeader(PascalCaseCatalogue.WIDTH)).getOneAttributeAsInt(),
			specification.getRefFirstAttribute(a -> a.hasHeader(PascalCaseCatalogue.HEIGHT)).getOneAttributeAsInt()
		);
		
		image.setPixelArray(specification.getRefFirstAttribute(a -> a.hasHeader(PIXEL_ARRAY_HEADER)));
		
		return image;
	}
	
	//static method
	public static Image fromString(final String string) {
		return fromSpecification(Node.fromString(string));
	}
	
	//attribute
	private final Value<Integer> width =
	new Value<>(
		PascalCaseCatalogue.WIDTH,
		this::setWidth,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//attribute
	private final Value<Integer> height =
	new Value<>(
		PascalCaseCatalogue.HEIGHT,
		this::setHeight,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
		
	//attribute
	private final Matrix<Color> pixels;
	
	//optional attributes
	private Node pixelArraySpecification;
	private BufferedImage bufferedImage;
	
	//constructor
	public Image(final int width, final int height) {
		this(width, height, Color.WHITE);
	}
	
	//constructor
	public Image(final int width, final int height, final Color color) {
		
		Validator.assertThat(color).thatIsNamed(Color.class).isNotNull();
		
		setWidth(width);
		setHeight(height);
		
		pixels = new Matrix<>();
		
		if (width > 0 && height > 0) {
			
			final var row = new Color[width];
			for (var i = 0; i < width; i++) {
				row[i] = color;
			}
			
			for (var i = 1; i <= height; i++) {
				pixels.addRow(row);
			}
		}
	}
	
	//constructor
	private Image(final Matrix<Color> pixels) {
		
		setWidth(pixels.getColumnCount());
		setHeight(pixels.getRowCount());
		
		this.pixels = pixels;
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case PIXEL_ARRAY_HEADER:
				setPixelArray(attribute);
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		generatePixelArraySpecificationIfNeeded();
		
		list.addAtEnd(pixelArraySpecification);
	}
	
	//method
	public Color getBottomLeftPixel() {
		return getPixel(1, getHeight());
	}
	
	//method
	public Color getBottomRightPixel() {
		return getPixel(getWidth(), getHeight());
	}
	
	//method
	public int getHeight() {
		return height.getValue();
	}
	
	//method
	public Color getPixel(final int xPosition, final int yPosition) {
		return pixels.getRefAt(yPosition, xPosition);
	}
	
	//method
	public int getPixelCount() {
		return pixels.getElementCount();
	}
	
	//method
	public Color getTopLeftPixel() {
		return getPixel(1, 1);
	}
	
	//method
	public Color getTopRightPixel() {
		return getPixel(getWidth(), 1);
	}
	
	//method
	public int getWidth() {
		return width.getValue();
	}
	
	//method
	@Override
	public void reset() {
		
		deletePixelArraySpecificationAndBufferedImage();
		
		final var pixelCount = getPixelCount();
		for (var i = 1; i <= pixelCount; i++) {
			pixels.setAt(i, Color.WHITE);
		}
	}
	
	//method
	public void saveAsPNG() {
		try {
			ImageIO.write(toBufferedImage(), "PNG", new File(StringCatalogue.EMPTY_STRING));
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	public Image setPixel(int xPosition, int yPosition, final Color color) {
		
		deletePixelArraySpecificationAndBufferedImage();
		
		pixels.setAt(yPosition, xPosition, color);
		
		return this;
	}
	
	//method
	public void setPixelArray(final BaseNode pixelArray) {
		
		final var lPixelArray = pixelArray.getRefAttributes();
		
		Validator.assertThat(lPixelArray.getElementCount()).thatIsNamed("number of pixels").isEqualTo(getPixelCount());
		
		deletePixelArraySpecificationAndBufferedImage();
		
		var i = 1;
		for (final var p : lPixelArray) {
			this.pixels.setAt(i, Color.fromSpecification(p));
			i++;
		}
	}
	
	//method
	public void setPixelArray(final Iterable<Color> pixelArray) {
		
		final var lPixelArray = ReadContainer.forIterable(pixelArray);
		
		Validator.assertThat(lPixelArray.getElementCount()).thatIsNamed("number of pixels").isEqualTo(getPixelCount());
				
		deletePixelArraySpecificationAndBufferedImage();
		
		var i = 1;
		for (final var p : lPixelArray) {
			pixels.setAt(i, p);
			i++;
		}
	}
		
	//method
	public BufferedImage toBufferedImage() {
		
		generateBufferedImageIfNeeded();
				
		return bufferedImage;
	}
	
	//method
	public byte[] toJPG() {
		return to("jpg");
	}
	
	//method
	public Image toLeftRotatedImage() {
		return new Image(pixels.toLeftRotatedMatrix());
	}
	
	//method
	public byte[] toPNG() {
		return to("png");
	}
	
	//method
	public Image toRepeatedImage(final int width, final int height) {
		
		final var image = new Image(width, height);
		
		final var sourceWidth = getWidth();
		final var sourceHeight = getHeight();
		
		for (var x = 1; x <= width; x++) {
			for (var y = 1; y <= height; y++) {
				image.setPixel(x, y, getPixel((x - 1) % sourceWidth + 1, (y - 1) % sourceHeight + 1));
			}
		}
		
		return image;
	}
	
	//method
	public Image toRightRotatedImage() {
		return new Image(pixels.toRightRotatedMatrix());
	}
	
	//method
	public Image toScaledImage(final double factor) {
		
		Validator.assertThat(factor).thatIsNamed(LowerCaseCatalogue.FACTOR).isPositive();
		
		return toScaledImage(factor, factor);
	}
	
	//method
	public Image toScaledImage(final double widthFactor, final double heightFactor) {
		
		Validator.assertThat(widthFactor).thatIsNamed("width factor").isPositive();
		Validator.assertThat(heightFactor).thatIsNamed("height factor").isPositive();
		
		final var image = new Image((int)(widthFactor * getWidth()), (int)(heightFactor * getHeight()));
		final var reziprocalWidthFactor = 1.0 / widthFactor;
		final var reziprocalHeightFactor = 1.0 / heightFactor;
		
		//sourceYs[y] := the source Image's y for the new Image's y
		final var sourceYs = new int[image.getHeight() + 1];
		for (var i = 1; i <= image.getHeight(); i++) {
			sourceYs[i] = (int)((i - 1) * reziprocalHeightFactor) + 1;
		}
		
		for (var x = 1; x <= image.getWidth(); x++) {
			
			//sourceX := the source Image's x for the new Image's x
			final var sourceX = (int)((x - 1) * reziprocalWidthFactor) + 1;
			
			for (var  y = 1; y <= image.getHeight(); y++) {
				final var sourceY = sourceYs[y];
				image.setPixel(x, y, this.getPixel(sourceX, sourceY));
			}
		}
		
		return image;
	}
	
	//method
	private void deletePixelArraySpecificationAndBufferedImage() {
		pixelArraySpecification = null;
		bufferedImage = null;
	}
	
	//method
	private void generateBufferedImageIfNeeded() {
		if (bufferedImage == null) {
			generateBufferedImageWhenNeeded();
		}
	}
	
	//method
	private void generateBufferedImageWhenNeeded() {
		
		bufferedImage =	new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
		
		for (var y = 0; y < getHeight(); y++) {
			for (var x = 0; x < getWidth(); x++) {
				
				final var pixel = pixels.getRefAt(y + 1, x + 1);
				
				bufferedImage.setRGB(x, y, pixel.toAlphaRedGreenBlue());
			}
		}	
	}
	
	//method
	private void generatePixelArraySpecificationIfNeeded() {
		if (pixelArraySpecification == null) {
			generatePixelArraySpecificationWhenNeeded();
		}
	}
	
	//method
	private void generatePixelArraySpecificationWhenNeeded() {
		pixelArraySpecification = Node.withHeader(PIXEL_ARRAY_HEADER);
		pixels.forEach(p -> pixelArraySpecification.addAttribute(p.getHexadecimalValueAlwaysWithAlphaValue()));
	}
	
	//method
	private void setHeight(final int height) {
		
		Validator.assertThat(height).thatIsNamed(LowerCaseCatalogue.HEIGHT).isPositive();
		
		this.height.setValue(height);
	}
	
	//method
	private void setWidth(final int width) {
		
		Validator.assertThat(width).thatIsNamed(LowerCaseCatalogue.WIDTH).isPositive();
		
		this.width.setValue(width);
	}
	
	//method
	private byte[] to(final String fileFormat) {
		
		final var byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {
			ImageIO.write(toBufferedImage(), fileFormat, byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
}
