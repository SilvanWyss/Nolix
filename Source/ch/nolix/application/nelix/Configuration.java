//package declaration
package ch.nolix.application.nelix;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.ArgumentName;
import ch.nolix.common.invalidStateException.UnexistingAttributeException;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.basic.MutableElement;

//package-visible class
/**
 * This class represents a configuration for a Nelix.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 130
 * 
 */
final class Configuration extends MutableElement {
	
	//attribute header
	private static final String DESIGN = "Design";

	//optional attribute
	private String design;
	
	//method
	/**
	 * Adds or change the given attribute to this configuration.
	 * 
	 * @param attribute
	 * @throws InvalidAttributeException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case DESIGN:
				setDesign(attribute.getOneAttributeToString());
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);
		}
	}
	
	//method
	/**
	 * @return the attributes of this configuration.
	 */
	public List<Specification> getAttributes() {
		
		final List<Specification> attributes = new List<Specification>();
		
		if (hasDesign()) {
			attributes.addAtEnd(new Specification(DESIGN, getDesign()));
		}
		
		return attributes;		
	}
	
	//method
	/**
	 * @return the design of this configuration.
	 * @throws UnexistingAttributeException if this configuration has no design.
	 */
	public String getDesign() {
		
		//Checks if this configuration has a design.
		if (!hasDesign()) {
			throw new UnexistingAttributeException(this, "design");
		}
		
		return design;
	}
	
	//method
	/**
	 * @return true if this configuration has a design.
	 */
	public boolean hasDesign() {
		return (design != null);
	}
	
	//method
	/**
	 * Resets this configuration.
	 */
	public void reset() {	
		removeDesign();
	}
	
	//method
	/**
	 * Removes the design of this configuration.
	 * 
	 * @return this configuration
	 */
	public Configuration removeDesign() {
		
		design = null;
		
		return this;
	}
	
	//method
	/**
	 * Sets the design of this configuration.
	 * 
	 * @param design
	 * @return this configuration.
	 * @throws NullArgumentException if the given design is null or an empty string.
	 */
	public Configuration setDesign(final String design) {
		
		//Checks if the given design is not null and no empty string.
		ZetaValidator.supposeThat(design).thatIsNamed("design").isNotEmpty();
		
		//Sets the design of this configuration.
		this.design = design;
		
		return this;
	}
}
