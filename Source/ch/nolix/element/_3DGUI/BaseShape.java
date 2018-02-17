//package declaration
package ch.nolix.element._3DGUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.entity.Property;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;

//abstract class
/**
 * A base shape contains no other shapes.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 100
 * @param <BS> The type of a base shape.
 */
public abstract class BaseShape<BS extends BaseShape<BS>>
extends Shape<BS> {

	//default value
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	//attribute
	private Property<Color> color
	= new Property<>(
		Color.TYPE_NAME,
		s -> Color.createFromSpecification(s),
		DEFAULT_COLOR
	);
	
	//method
	/**
	 * Adds or changes the given attribute to this base shape.
	 * 
	 * @param attribute
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case Color.TYPE_NAME:
				setColor(new Color(attribute.getOneAttributeToString()));
				break;
			default:
			
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this base shape.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		attributes.addAtEnd(getColor().getSpecification());
		
		return attributes;
	}
	
	//method
	/**
	 * @return the color of this base shape.
	 */
	public final Color getColor() {
		return color.getValue();
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
	 */
	public final void resetConfiguration() {
		setColor(DEFAULT_COLOR);
	}

	//method
	/**
	 * Sets the color of this base shape.
	 * 
	 * @param color
	 * @return this base shape.
	 * @throws NullArgumentException if the given color is null.
	 */
	public final BS setColor(final Color color) {
	
		this.color.setValue(color);
		
		return getInstance();
	}
}
