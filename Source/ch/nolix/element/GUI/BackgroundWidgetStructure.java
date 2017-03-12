//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.BackgroundColor;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 140
 * @param <BWS> - The type of a background widget structure.
 */
public abstract class BackgroundWidgetStructure<BWS extends BackgroundWidgetStructure<BWS>>
extends WidgetStructure<BWS> {

	//optional attribute
	private BackgroundColor backgroundColor;
	
	//method
	/**
	 * @return the active background color of this background widget structure.
	 */
	public final BackgroundColor getActiveBackgroundColor() {
		
		//Handles the case if this  background widget structure has a background color.
		if (hasBackgroundColor()) {
			return backgroundColor.getCopy();
		}
		
		//Handles the case if this  background widget has no background color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBackgroundColor();
		}
		
		//Handles the case if this background widget structure has no background color and no normal structure.
		return new BackgroundColor(Color.WHITE);
	}
	
	//method
	/**
	 * @return true if this background widget structure has an active background color.
	 */
	public final boolean hasActiveBackgroundColor() {
		
		//Handles the case if this background widget structure has a background color.
		if (hasBackgroundColor()) {
			return true;
		}
		
		//Handles the case if this background widget structure has no background color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasActiveBackgroundColor();
		}
		
		//Handles the case if this background widget structure has no background color and no normal structure.
		return false;
	}
	
	//method
	/**
	 * @return true if this background widget structure has a background color.
	 */
	public final boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * Removes all attributes of this background widget structure.
	 */
	public void removeAttributes() {
		
		//Calls method of the base class.
		super.removeAttributes();
		
		removeBackgroundColor();
	}
	
	//method
	/**
	 * Removes the background color of this background widget structure.
	 */
	public final void removeBackgroundColor() {
		backgroundColor = null;
	}
	
	//method
	/**
	 * Sets the background color of this background color widget structure.
	 * @param backgroundColor
	 * @return this background color widget structure.
	 * @throws NullArgumentException if the given background color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBackgroundColor(final BackgroundColor backgroundColor) {
		
		//Checks if the given background color is not null.
		ZetaValidator.supposeThat(backgroundColor).thatIsInstanceOf(BackgroundColor.class).isNotNull();
		
		//Sets the background color of this background color widget structure.
		this.backgroundColor = backgroundColor;
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this background widget structure.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	protected void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case BackgroundColor.SIMPLE_CLASS_NAME:
				setBackgroundColor(new BackgroundColor(attribute.getOneAttributeToString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this background widget structure.
	 */
	protected List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(backgroundColor.getSpecification());
		}
		
		return attributes;
	}
}
