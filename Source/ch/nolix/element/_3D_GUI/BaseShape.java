//package declaration
package ch.nolix.element._3D_GUI;

//Java import
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.element.base.MutableOptionalProperty;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;

//class
/**
 * A {@link BaseShape} is a {@link Shape} that does not contain other shapes.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 130
 * @param <BS> The type of a {@link BaseShape}.
 */
public abstract class BaseShape<BS extends BaseShape<BS>> extends Shape<BS> {

	//default value
	public static final Color DEFAULT_COLOR = Color.LIGHT_GREY;
	
	//attribute
	private MutableProperty<Color> defaultColor
	= new MutableProperty<>(
		PascalCaseNameCatalogue.DEFAULT_COLOR,
		c -> setDefaultColor(c),
		s -> Color.fromSpecification(s),
		c -> c.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<Image> defaultTexture =
	new MutableOptionalProperty<>(
		PascalCaseNameCatalogue.DEFAULT_TEXTURE,
		t -> setDefaultTexture(t),
		s -> Image.fromSpecification(s),
		t -> t.getSpecification()
	);
	
	//method
	/**
	 * @return the default color of the current {@link BaseShape}.
	 */
	public final Color getDefaultColor() {
		return defaultColor.getValue();
	}
	
	//method
	/**
	 * @return the default texture of the current {@link BaseShape} as {@link BufferedImage}.
	 * @throws UnexistingAttribute if the current {@link BaseShape} does not have a default texture.
	 */
	public final BufferedImage getDefaultTextureAsBufferedImage() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return defaultTexture.getValue().toBufferedImage();
	}
	
	//method
	/**
	 * @return the default texture of the current {@link BaseShape}.
	 * @throws UnexistingAttribute if the current {@link BaseShape} does not have a default texture.
	 */
	public final Image getRefDefaultTexture() {
		return defaultTexture.getValue();
	}
	
	//method
	/**
	 * @return the shapes of the current {@link BaseShape}.
	 */
	@Override
	public final ReadContainer<Shape<?>> getRefShapes() {
		return new ReadContainer<>();
	}
	
	//method
	/**
	 * @return true of the current {@link BaseShape} has a default texture.
	 */
	public final boolean hasDefaultTexture() {
		return defaultTexture.containsAny();
	}
	
	//method
	/**
	 * Removes the default texture of the current {@link BaseShape}.
	 * 
	 * @return the current {@link BaseShape}.
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
	public BS resetConfiguration() {
		
		setDefaultColor(DEFAULT_COLOR);
		removeDefaultTexture();
		
		return asConcrete();
	}

	//method
	/**
	 * Sets the default color of the current {@link BaseShape}.
	 * 
	 * @param defaultColor
	 * @return the current {@link BaseShape}.
	 * @throws ArgumentIsNullException if the given default color is null.
	 */
	public final BS setDefaultColor(final Color defaultColor) {
	
		this.defaultColor.setValue(defaultColor);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the default texture of the current {@link BaseShape}.
	 * 
	 * @param defaultTexture
	 * @return the current {@link BaseShape}.
	 * @throws ArgumentIsNullException if the given default texture is null.
	 */
	public final BS setDefaultTexture(final Image defaultTexture) {
		
		this.defaultTexture.setValue(defaultTexture);
		
		return asConcrete();
	}
}
