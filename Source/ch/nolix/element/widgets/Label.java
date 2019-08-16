//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 170
 */
public final class Label extends TextLineWidget<Label, LabelLook> {
	
	//type name
	public static final String TYPE_NAME = "Label";
	
	//optional attribute
	private LabelRole role;
	
	//constructor
	/**
	 * Creates a new {@link Label}.
	 */
	public Label() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link Label} with the given text.
	 * 
	 * @param text
	 * @throws NullArgumentException if the given text is null.
	 */
	public Label(final String text) {
		
		resetAndApplyDefaultConfiguration();
		
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
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
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
	@Override
	public List<Node> getAttributes() {
		
		//Calls method of the base class.
		final List<Node> attributes = super.getAttributes();
		
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
	 * @throws UnexistingAttributeExcetpion if the current {@link Label} does not have a role.
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
	@Override
	public boolean hasRole(final String role) {
		
		//Handles the case that the current label does not have a role.
		if (!hasRole()) {
			return false;
		}
		
		//Handles the case that the current label has a role.
		return getRole().toString().equals(role);
	}
	
	//method
	/**
	 * Sets the role of the current {@link Label}.
	 * 
	 * @param role
	 * @return the current {@link Label}.
	 * @throws NullArgumentException if the given role is null.
	 */
	public Label setRole(final LabelRole role) {
		
		//Checks if the given role is not null.
		Validator
		.suppose(role)
		.thatIsNamed(VariableNameCatalogue.ROLE)
		.isNotNull();

		//Sets the role of the current label.
		this.role = role;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean shortensShownTextWhenHasLimitedWidth() {
		return getRefLook().getRecursiveOrDefaultShortensTextWhenLimitedFlag();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected LabelLook createLook() {
		return new LabelLook();
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link Label} does not have a role.
	 */
	private void supposeHasRole() {
		
		//Checks if the current {@link Label} has a role.
		if (!hasRole()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
