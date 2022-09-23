//package declaration
package ch.nolix.system.gui.canvas;

//own imports
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
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.image.Image;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;

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
		
		switch (childNode.getHeader()) {
			case COLOR_HEADER:
				return withColor(Color.fromSpecification(childNode));
			case COLOR_GRADIENT_HEADER:
				return withColorGradient(ColorGradient.fromSpecification(childNode));
			case IMAGE_HEADER:				
				return
				withImageAndImageApplication(
					Image.fromSpecification(specification.getRefChildNodeAt1BasedIndex(1)),
					ImageApplication.fromSpecification(specification.getRefChildNodeAt1BasedIndex(2))
				);
			default:
				throw
				UnrepresentingArgumentException.forArgumentNameAndArgumentAndType(
					LowerCaseCatalogue.SPECIFICATION,
					specification,
					Background.class
				);
		}
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
		switch (getType()) {
			case COLOR:
				return LinkedList.withElements(getColor().getSpecification());
			case COLOR_GRADIENT:
				return LinkedList.withElements(getColorGradient().getSpecification());
			case IMAGE:
				return LinkedList.withElements(getImage().getSpecification(), Node.fromEnum(getImageApplication()));
			default:
				throw InvalidArgumentException.forArgument(this);
		}
	}
	
	//method
	public IColor getColor() {
		
		assertIsColor();
		
		return color;
	}
	
	//method
	public IColorGradient getColorGradient() {
		
		assertIsColorGradient();
		
		return colorGradient;
	}
	
	//method
	public IImage getImage() {
		
		assertIsImage();
		
		return image;
	}
	
	//method
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
	public ICSSProperty toCSSProperty() {
		switch (getType()) {
		case COLOR:
			
			final var colorCode =
			String.format("#%02x%02x%02x", color.getRedValue(), color.getGreenValue(), color.getBlueValue());
			
			return
			CSSProperty.withNameAndValue(CSSPropertyNameCatalogue.BACKGROUND, colorCode);
		case COLOR_GRADIENT:
			//TODO: Implement.
		case IMAGE:
			//TODO: Implement.
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
}
