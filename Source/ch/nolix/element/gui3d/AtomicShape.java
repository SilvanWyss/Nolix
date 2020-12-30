//package declaration
package ch.nolix.element.gui3d;

//Java import
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;

//class
/**
 * A {@link AtomicShape} is a {@link Shape} that does not contain other shapes.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 140
 * @param <BS> The type of a {@link AtomicShape}.
 */
public abstract class AtomicShape<BS extends AtomicShape<BS>> extends Shape<BS> {
	
	//constant
	public static final Color DEFAULT_COLOR = Color.LIGHT_GREY;
	
	//attribute
	private MutableValue<Color> defaultColor =
	new MutableValue<>(
		PascalCaseNameCatalogue.DEFAULT_COLOR,
		DEFAULT_COLOR,
		this::setDefaultColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<Image> defaultTexture =
	new MutableOptionalValue<>(
		PascalCaseNameCatalogue.DEFAULT_TEXTURE,
		this::setDefaultTexture,
		Image::fromSpecification,
		Image::getSpecification
	);
	
	//method
	/**
	 * @return the default color of the current {@link AtomicShape}.
	 */
	public final Color getDefaultColor() {
		return defaultColor.getValue();
	}
	
	//method
	/**
	 * @return the default texture of the current {@link AtomicShape} as {@link BufferedImage}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link AtomicShape} does not have a default texture.
	 */
	public final BufferedImage getDefaultTextureAsBufferedImage() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return defaultTexture.getValue().toBufferedImage();
	}
	
	//method
	/**
	 * @return the default texture of the current {@link AtomicShape}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link AtomicShape} does not have a default texture.
	 */
	public final Image getRefDefaultTexture() {
		return defaultTexture.getValue();
	}
	
	//method
	/**
	 * @return the shapes of the current {@link AtomicShape}.
	 */
	@Override
	public final ReadContainer<Shape<?>> getRefShapes() {
		return new ReadContainer<>();
	}
	
	//method
	/**
	 * @return true of the current {@link AtomicShape} has a default texture.
	 */
	public final boolean hasDefaultTexture() {
		return defaultTexture.containsAny();
	}
	
	//method
	/**
	 * Removes the default texture of the current {@link AtomicShape}.
	 * 
	 * @return the current {@link AtomicShape}.
	 */
	public final BS removeDefaultTexture() {
		
		defaultTexture.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void resetConfigurationOnSelf() {
		setDefaultColor(DEFAULT_COLOR);
		removeDefaultTexture();
	}
	
	//method
	/**
	 * Sets the default color of the current {@link AtomicShape}.
	 * 
	 * @param defaultColor
	 * @return the current {@link AtomicShape}.
	 * @throws ArgumentIsNullException if the given default color is null.
	 */
	public final BS setDefaultColor(final Color defaultColor) {
	
		this.defaultColor.setValue(defaultColor);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the default texture of the current {@link AtomicShape}.
	 * 
	 * @param defaultTexture
	 * @return the current {@link AtomicShape}.
	 * @throws ArgumentIsNullException if the given default texture is null.
	 */
	public final BS setDefaultTexture(final Image defaultTexture) {
		
		this.defaultTexture.setValue(defaultTexture);
		
		return asConcrete();
	}
}
