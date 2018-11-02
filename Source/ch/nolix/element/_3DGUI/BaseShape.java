//package declaration
package ch.nolix.element._3DGUI;

//Java import
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;

//abstract class
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
	= new MutableProperty<Color>(
		PascalCaseNameCatalogue.DEFAULT_COLOR,
		c -> setDefaultColor(c),
		s -> Color.createFromSpecification(s),
		c -> c.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<Image> defaultTexture =
	new MutableOptionalProperty<Image>(
		PascalCaseNameCatalogue.DEFAULT_TEXTURE,
		t -> setDefaultTexture(t),
		s -> Image.createFromSpecification(s),
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
	 * @throws UnexistingAttribute if the current {@link BaseShape} has no default texture.
	 */
	public final BufferedImage getDefaultTextureAsBufferedImage() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return defaultTexture.getValue().toBufferedImage();
	}
	
	//method
	/**
	 * @return the default texture of the current {@link BaseShape}.
	 * @throws UnexistingAttribute if the current {@link BaseShape} has no default texture.
	 */
	public final Image getRefDefaultTexture() {
		return defaultTexture.getValue();
	}
	
	//method
	/**
	 * @return the shapes of the current {@link BaseShape}.
	 */
	public final ReadContainer<Shape<?>> getRefShapes() {
		return new ReadContainer<Shape<?>>();
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
		
		return getInstance();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public BS resetConfiguration() {
		
		setDefaultColor(DEFAULT_COLOR);
		removeDefaultTexture();
		
		return getInstance();
	}

	//method
	/**
	 * Sets the default color of the current {@link BaseShape}.
	 * 
	 * @param defaultColor
	 * @return the current {@link BaseShape}.
	 * @throws NullArgumentException if the given default color is null.
	 */
	public final BS setDefaultColor(final Color defaultColor) {
	
		this.defaultColor.setValue(defaultColor);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the default texture of the current {@link BaseShape}.
	 * 
	 * @param defaultTexture
	 * @return the current {@link BaseShape}.
	 * @throws NullArgumentException if the given default texture is null.
	 */
	public final BS setDefaultTexture(final Image defaultTexture) {
		
		this.defaultTexture.setValue(defaultTexture);
		
		return getInstance();
	}
}
