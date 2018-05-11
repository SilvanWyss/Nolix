//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A label is a text line widget
 * that is supposed to show texts a user cannot edit.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 160
 */
public final class Label extends TextLineWidget<Label> {
	
	//type name
	public static final String TYPE_NAME = "Label";
	
	//attribute header
	private static final String ROLE_HEADER = "Role";
	
	//optional attribute
	private LabelRole role;
	
	//constructor
	/**
	 * Creates a new label with default values.
	 */
	public Label() {	
		reset();
		approveProperties();
	}
	
	//constructor
	/**
	 * Creates a new label with the given text.
	 * 
	 * @param text
	 * @throws NullArgumentException if the given text is null.
	 */
	public Label(final String text) {
		
		//Calls other constructor.
		this();
		
		//Sets the text of this label.
		setText(text);
	}
	
	//method
	/**
	 * Adds or change the given attribute to this label.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case ROLE_HEADER:
				setRole(LabelRole.valueOf(attribute.getOneAttributeAsString()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this label.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that this label has a role.
		if (hasRole()) {
			attributes.addAtEnd(role.getSpecificationAs(ROLE_HEADER));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the role of this label.
	 * @throws UnexistingAttributeExcetpion if this label has no role.
	 */
	public LabelRole getRole() {
		
		//Checks if this label has a role.
		supposeHasRole();
		
		return role;
	}
	
	//method
	/**
	 * @return true if this label has a role.
	 */
	public boolean hasRole() {
		return (role != null);
	}
	
	//method
	/**
	 * @param role
	 * @return true of this label has the given role.
	 */
	public boolean hasRole(final String role) {
		
		//Handles the case that this label has no role.
		if (!hasRole()) {
			return false;
		}
		
		//Handles the case that this label has a role.
		return getRole().equals(LabelRole.valueOf(role));
	}
	
	//method
	/**
	 * Sets the role of this label.
	 * 
	 * @param role
	 * @return this label.
	 * @throws NullArgumentException if the given role is null.
	 */
	public final Label setRole(final LabelRole role) {
		
		//Checks if the given role is not null.
		Validator
		.suppose(role)
		.thatIsNamed(VariableNameCatalogue.ROLE)
		.isNotNull();

		//Sets the role of this label.
		this.role = role;
		
		return this;
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if this label has no role.
	 */
	private void supposeHasRole() {
		
		//Checks if this label has a role.
		if (!hasRole()) {
			throw new UnexistingAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}

	@Override
	public void setCursorPositionOnContentArea(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
