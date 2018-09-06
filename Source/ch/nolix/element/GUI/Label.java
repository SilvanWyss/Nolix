//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 170
 */
public final class Label extends TextLineWidget<Label> {
	
	//type name
	public static final String TYPE_NAME = "Label";
	
	//optional attribute
	private LabelRole role;
	
	//constructor
	/**
	 * Creates a new {@link Label}.
	 */
	public Label() {	
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link Label} with the given text.
	 * 
	 * @param text
	 * @throws NullArgumentException if the given text is not an instance.
	 */
	public Label(final String text) {
		
		//Calls other constructor.
		this();
		
		//Sets the text of the current label.
		setText(text);
	}
	
	//method
	/**
	 * Adds or change the given attribute to the current {@link Label}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ROLE:
				setRole(LabelRole.createFromSpecification(attribute));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Label}.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that the current label has a role.
		if (hasRole()) {
			attributes.addAtEnd(
				role.getSpecificationAs(PascalCaseNameCatalogue.ROLE)
			);
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the role of the current {@link Label}.
	 * @throws UnexistingAttributeExcetpion if the current {@link Label} has no role.
	 */
	public LabelRole getRole() {
		
		//Checks if the current label has a role.
		supposeHasRole();
		
		return role;
	}
	
	//method
	/**
	 * @return true if the current {@link Label} has a role.
	 */
	public boolean hasRole() {
		return (role != null);
	}
	
	//method
	/**
	 * @param role
	 * @return true if the current {@link Label} has the given role.
	 */
	public boolean hasRole(final String role) {
		
		//Handles the case that the current label has no role.
		if (!hasRole()) {
			return false;
		}
		
		//Handles the case that the current label has a role.
		return getRole().toString().equals(role);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean keepsFocus() {
		return false;
	}
	
	//method
	/**
	 * Sets the role of the current {@link Label}.
	 * 
	 * @param role
	 * @return the current {@link Label}.
	 * @throws NullArgumentException if the given role is not an instance.
	 */
	public Label setRole(final LabelRole role) {
		
		//Checks if the given role is an instance.
		Validator
		.suppose(role)
		.thatIsNamed(VariableNameCatalogue.ROLE)
		.isInstance();

		//Sets the role of the current label.
		this.role = role;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {}
	
	//method
	/**
	 * @throws UnexistingAttributeException if the current {@link Label} has no role.
	 */
	private void supposeHasRole() {
		
		//Checks if the current {@link Label} has a role.
		if (!hasRole()) {
			throw new UnexistingAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
