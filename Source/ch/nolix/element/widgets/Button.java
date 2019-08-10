//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnums.ContentPosition;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 190
 */
public final class Button extends TextLineWidget<Button, ButtonLook> {
	
	//default value
	public static final String DEFAULT_TEXT  = "-";
	
	//constant
	public static final String TYPE_NAME = "Button";
			
	//optional attribute
	private ButtonRole role;
	
	//constructor
	/**
	 * Creates a new {@link Button} with a default text.
	 */
	public Button() {
		
		//Calls other constructor.
		this(DEFAULT_TEXT);
	}
	
	//constructor
	/**
	 * Creates a new {@link Button} with the given text.
	 * 
	 * @param text
	 * @throws NullArgumentException if the given text is null.
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
		
		//Handles the case that the current Button has a role.
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
	 * @throws ArgumentMissesAttributeException if the current {@link Button} does not have a role.
	 */
	public ButtonRole getRole() {
		
		//Checks if the current Button has a role.
		supposeHasRole();
		
		return role;
	}
	
	//method
	/**
	 * @return true if the current {@link Button} has a role.
	 */
	public boolean hasRole() {
		return (role != null);
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRole(final String role) {	
		return (hasRole() && getRole().toString().equals(role));
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
		
		this.role = Validator.suppose(role).thatIsNamed(VariableNameCatalogue.ROLE).isNotNull().andReturn();
		
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
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		
		setMinWidth(100);
		setContentPosition(ContentPosition.Center);
		
		getRefBaseLook()
		.setBorderThicknesses(1)
		.setLeftBorderColor(Color.GREY)
		.setRightBorderColor(Color.BLACK)
		.setTopBorderColor(Color.GREY)
		.setBottomBorderColor(Color.BLACK)
		.setBackgroundColor(Color.WHITE_SMOKE)
		.setLeftPadding(10)
		.setRightPadding(10);
		
		getRefHoverLook().setBackgroundColor(Color.LIGHT_GREY);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ButtonLook createLook() {
		return new ButtonLook();
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link Button} does not have a role.
	 */
	private void supposeHasRole() {
		
		//Checks if the current Button has a role.
		if (!hasRole()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
