//package declaration
package ch.nolix.system.gui.canvas;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.web.css.CSSProperty;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.webapi.cssapi.CSSPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICSSProperty;
import ch.nolix.system.element.main.Element;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.ColorGradient;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

//class
public final class Background extends Element implements IBackground {
	
	//constant
	public static final ImageApplication DEFAULT_IMAGE_APPLICATION = ImageApplication.SCALE_TO_FRAME;
	
	//constant
	private static final String COLOR_HEADER = "Color";
	
	//constant
	private static final String COLOR_GRADIENT_HEADER = "ColorGradient";
	
	//constant
	private static final String IMAGE_HEADER = "Image";
	
	//static method
	public static Background fromSpecification(final INode<?> specification) {
		
		final var childNode = specification.getRefFirstChildNode();
		
		return
		switch (childNode.getHeader()) {
			case COLOR_HEADER ->
				withColor(Color.fromSpecification(childNode));
			case COLOR_GRADIENT_HEADER ->
				withColorGradient(ColorGradient.fromSpecification(childNode));
			case IMAGE_HEADER ->
				withImageAndImageApplication(
					Image.fromSpecification(specification.getRefChildNodeAt1BasedIndex(1)),
					ImageApplication.fromSpecification(specification.getRefChildNodeAt1BasedIndex(2))
				);
			default ->
				throw createExceptionForSpecificationDoesNotSpecifyBackground(specification);
		};
	}
	
	//static method
	public static Background withColor(final IColor color) {
		return new Background(color);
	}
	
	//static method
	public static Background withColorGradient(final IColorGradient colorGradient) {
		return new Background(colorGradient);
	}
	
	//static method
	public static Background withImage(final IImage image) {
		return withImageAndImageApplication(image, DEFAULT_IMAGE_APPLICATION);
	}
	
	//static method
	public static Background withImageAndImageApplication(
		final IImage image,
		final ImageApplication imageApplication
	) {
		return new Background(image, imageApplication);
	}
	
	//method
	private static UnrepresentingArgumentException createExceptionForSpecificationDoesNotSpecifyBackground(
		final INode<?> specification
	) {
		return
		UnrepresentingArgumentException.forArgumentNameAndArgumentAndType(
			LowerCaseCatalogue.SPECIFICATION,
			specification,
			Background.class
		);
	}
	
	//attribute
	private final IColor color;
	
	//attribute
	private final IColorGradient colorGradient;
	
	//attribute
	private final IImage image;
	
	//attribute
	private final ImageApplication imageApplication;
	
	//constructor
	private Background(final IColor color) {
		
		GlobalValidator.assertThat(color).thatIsNamed(IColor.class).isNotNull();
		
		this.color = color;
		colorGradient = null;
		image = null;
		imageApplication = null;
	}
	
	//constructor
	private Background(final IColorGradient colorGradient) {
		
		GlobalValidator.assertThat(colorGradient).thatIsNamed(IColorGradient.class).isNotNull();
		
		color = null;
		this.colorGradient = colorGradient;
		image = null;
		imageApplication = null;
	}
	
	//constructor
	private Background(final IImage image, final ImageApplication imageApplication) {
		
		GlobalValidator.assertThat(image).thatIsNamed(IImage.class).isNotNull();
		GlobalValidator.assertThat(imageApplication).thatIsNamed(ImageApplication.class).isNotNull();
		
		color = null;
		colorGradient = null;
		this.image = image;
		this.imageApplication = imageApplication;
	}
	
	//method
	@Override
	public IContainer<INode<?>> getAttributes() {
		return
		switch (getType()) {
			case COLOR ->
				LinkedList.withElements(getColor().getSpecification());
			case COLOR_GRADIENT ->
				LinkedList.withElements(getColorGradient().getSpecification());
			case IMAGE ->
				LinkedList.withElements(getImage().getSpecification(), Node.fromEnum(getImageApplication()));
			default ->
				throw InvalidArgumentException.forArgument(this);
		};
	}
	
	//method
	@Override
	public IColor getColor() {
		
		assertIsColor();
		
		return color;
	}
	
	//method
	@Override
	public IColorGradient getColorGradient() {
		
		assertIsColorGradient();
		
		return colorGradient;
	}
	
	//method
	@Override
	public IImage getImage() {
		
		assertIsImage();
		
		return image;
	}
	
	//method
	@Override
	public ImageApplication getImageApplication() {
		
		assertIsImage();
		
		return imageApplication;
	}
	
	//method
	@Override
	public BackgroundType getType() {
		
		if (isColor()) {
			return BackgroundType.COLOR;
		}
		
		if (isColorGradient()) {
			return BackgroundType.COLOR_GRADIENT;
		}
		
		return BackgroundType.IMAGE;
	}
	
	//method
	public boolean isColor() {
		return (color != null);
	}
	
	//method
	public boolean isColorGradient() {
		return (colorGradient != null);
	}
	
	//method
	public boolean isImage() {
		return (image != null);
	}
	
	//method
	@Override
	public IContainer<ICSSProperty> toCSSProperties() {
		switch (getType()) {
			case COLOR:
				
				final var colorCode = getColorCodeOfColor(color);
				
				return
				ImmutableList.withElements(CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.BACKGROUND, colorCode));
			case COLOR_GRADIENT:
				
				final var degreeCode = getDegreeCodeOfColorGradient(colorGradient);
				final var color1Code = getColorCodeOfColor(colorGradient.getColor1());
				final var color2Code = getColorCodeOfColor(colorGradient.getColor2());
				final var linearGradientCode = "linear-gradient(" + degreeCode + "," + color1Code + "," + color2Code + ")";
				
				return
				ImmutableList.withElements(
					CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.BACKGROUND_IMAGE, linearGradientCode)
				);
			case IMAGE:
				
				final var backgroundImage = "data:image/jpeg;base64," + image.toJPGString();
				
				return
				ImmutableList.withElements(		
					CSSProperty.withNameAndValue(
						CSSPropertyNameCatalogue.BACKGROUND_IMAGE,
						"url('" + backgroundImage + "')"
					),
					CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.BACKGROUND_SIZE, "100% 100%")
				);
			default:
				throw InvalidArgumentException.forArgument(this);
		}
	}
	
	//method
	private void assertIsColor() {
		if (!isColor()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IColor.class);
		}
	}
	
	//method
	private void assertIsColorGradient() {
		if (!isColorGradient()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IColorGradient.class);
		}
	}
	
	//method
	private void assertIsImage() {
		if (!isImage()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, IImage.class);
		}
	}
	
	//method
	private String getColorCodeOfColor(final IColor color) {
		return String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
	}
	
	//method
	private Object getDegreeCodeOfColorGradient(final IColorGradient pColorGradient) {
		return (getDegreeOfColorGradient(pColorGradient) + "deg");
	}
	
	//method
	private int getDegreeOfColorGradient(final IColorGradient pColorGradient) {
		return getDegreeOfDirection(pColorGradient.getDirection());
	}
	
	//method
	private int getDegreeOfDirection(final DirectionInRectangle direction) {
		return
		switch (direction) {
			case VERTICAL ->
				180;
			case HORIZONTAL ->
				90;
			case DIAGONAL_DOWN ->
				135;
			case DIAGONAL_UP ->
				45;
			default ->
				throw InvalidArgumentException.forArgument(direction);
		};
	}
}
