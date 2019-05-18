//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.elementEnums.ContentPosition;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 190
 */
public final class Button extends TextLineWidget<Button> {
	
	//constant
	public static final String TYPE_NAME = "Button";
	
	//optional attribute
	private ButtonRole role;
	
	//constructor
	/**
	 * Creates a new {@link Button}.
	 */
	public Button() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link Button} with the given text.
	 * 
	 * @param text
	 * @throws NullArgumentException
	 * if the given text is null.
	 */
	public Button(final String text) {
		
		resetAndApplyDefaultConfiguration();
		
		setText(text);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ROLE:
				setRole(ButtonRole.createFromSpecification(attribute));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current button has a role.
		if (hasRole()) {
			attributes.addAtEnd(
				getRole().getSpecificationAs(PascalCaseNameCatalogue.ROLE)
			);
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the role of the current {@link Button}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link Button} does not have a role.
	 */
	public ButtonRole getRole() {
		
		supposeHasRole();
		
		return role;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean hasRole() {
		return (role != null);
	}

	//method
	/**
	 * @param role
	 * @return true
	 * if the current {@link Button} has the given role.
	 */
	@Override
	public boolean hasRole(final String role) {
		
		//Handles the case that the current link button does not have a role.
		if (!hasRole()) {
			return false;
		}
		
		//Handles the case that the current link button has a role.
		return getRole().toString().equals(role);
	}
	
	/**
	 * Removes the role of the current {@link Button}.
	 * 
	 * @return the current {@link Button}.
	 */
	public Button removeRole() {
		
		role = null;
		
		return this;
	}
	
	/**
	 * Sets the role of the current {@link Button}.
	 * 
	 * @param role
	 * @return the current {@link Button}.
	 * @throws NullArgumentException if the given role is null.
	 */
	public Button setRole(final ButtonRole role) {
		
		//Checks if the given role is not null.
		Validator
		.suppose(role)
		.thatIsNamed(VariableNameCatalogue.ROLE)
		.isNotNull();
		
		//Sets the role of the current button.
		this.role = role;
		
		return this;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		
		setMinWidth(100);
		setContentPosition(ContentPosition.Center);
		
		getRefBaseLook()
		.setBackgroundColor(Color.LIGHT_GREY)
		.setLeftPadding(10)
		.setRightPadding(10);
		
		getRefHoverLook()
		.setBackgroundColor(Color.DARK_GREY);
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link Button} does not have a role.
	 */
	private void supposeHasRole() {
		
		//Checks if the current button has a role.
		if (!hasRole()) {
			throw 
			new ArgumentMissesAttributeException(
				this,
				VariableNameCatalogue.ROLE
			);
		}
	}
}
