//package declaration
package ch.nolix.system.gui.canvas;

//own imports
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.IBackground;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.structureproperty.BackgroundType;

//class
public final class Background extends MutableElement<Background> implements IBackground {
	
	//constant
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
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
		
		final var background = new Background();
		background.resetFromSpecification(specification);
		
		return background;
	}
	
	//attribute
	private final MutableOptionalValue<IColor> color =
	new MutableOptionalValue<>(
		COLOR_HEADER,
		this::setColor,
		Color::fromSpecification,
		IColor::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IColorGradient> colorGradient =
	new MutableOptionalValue<>(
		COLOR_GRADIENT_HEADER,
		this::setColorGradient,
		ColorGradient::fromSpecification,
		IColorGradient::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<Pair<IImage, ImageApplication>> mutableImage =
	new MutableOptionalValue<>(
		IMAGE_HEADER,
		this::setImage,
		s ->
		new Pair<>(
			MutableImage.fromSpecification(s.getRefChildNodeAt1BasedIndex(1)),
			ImageApplication.fromSpecification(s.getRefChildNodeAt1BasedIndex(2))
		),
		bi -> Node.withChildNode(bi.getRefElement1().getSpecification(), Node.fromEnum(bi.getRefElement2()))
	);
	
	//constructor
	public Background() {
		reset();
	}
	
	//method
	public IColor getColor() {
		return color.getValue();
	}
	
	//method
	public IColorGradient getColorGradient() {
		return colorGradient.getValue();
	}
	
	//method
	public IImage getImage() {
		return mutableImage.getValue().getRefElement1();
	}
	
	//method
	public ImageApplication getImageApplication() {
		return mutableImage.getValue().getRefElement2();
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
		return color.hasValue();
	}
	
	//method
	public boolean isColorGradient() {
		return colorGradient.hasValue();
	}
	
	//method
	public boolean isImage() {
		return mutableImage.hasValue();
	}
		
	//method
	@Override
	public void reset() {
		setColor(DEFAULT_COLOR);
	}
	
	//method
	public IBackground setColor(final IColor color) {
		
		clear();
		this.color.setValue(color);
		
		return this;
	}
	
	//method
	public IBackground setColorGradient(final IColorGradient backgroundColorGradient) {
		
		clear();
		this.colorGradient.setValue(backgroundColorGradient);
		
		return this;
	}
	
	//method
	@Override
	public IBackground setImage(final IImage image) {
		return setImage(image, DEFAULT_IMAGE_APPLICATION);
	}
	
	//method
	public IBackground setImage(final IImage image, final ImageApplication imageApplication) {
		
		setImage(new Pair<>(image, imageApplication));
		
		return this;
	}
	
	//method
	private void clear() {
		color.clear();
		colorGradient.clear();
		mutableImage.clear();
	}
	
	//method
	private void setImage(final Pair<IImage, ImageApplication> backgroundImage) {
		
		clear();
		
		this.mutableImage.setValue(backgroundImage);
	}
}
