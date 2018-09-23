//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;

//abstract class
/**
 * A base shape contains no other shapes.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 90
 * @param <BS> The type of a base shape.
 */
public abstract class BaseShape<BS extends BaseShape<BS>>
extends Shape<BS> {

	//default value
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	//attribute
	private MutableProperty<Color> defaultColor
	= new MutableProperty<Color>(
		Color.TYPE_NAME,
		c -> setDefaultColor(c),
		s -> Color.createFromSpecification(s),
		c -> c.getSpecification()
	);
	
	//method
	/**
	 * @return the attributes of this base shape.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		attributes.addAtEnd(getDefaultColor().getSpecification());
		
		return attributes;
	}
	
	//method
	/**
	 * @return the default color of this base shape.
	 */
	public final Color getDefaultColor() {
		return defaultColor.getValue();
	}
	
	//method
	/**
	 * @return the shapes of this base shape.
	 */
	public final ReadContainer<Shape<?>> getRefShapes() {
		return new ReadContainer<>();
	}
	
	//method
	/**
	 * Resets the configuration of this base shape.
	 * 
	 * @return this base shape.
	 */
	public final BS resetConfiguration() {
		
		setDefaultColor(DEFAULT_COLOR);
		
		return getInstance();
	}

	//method
	/**
	 * Sets the default color of this base shape.
	 * 
	 * @param color
	 * @return this base shape.
	 * @throws NullArgumentException if the given color is not an instance.
	 */
	public final BS setDefaultColor(final Color color) {
	
		this.defaultColor.setValue(color);
		
		return getInstance();
	}
}
