//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnums.ContentPosition;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 220
 */
public final class Button extends TextLineWidget<Button, ButtonLook> {
	
	//default value
	public static final String DEFAULT_TEXT = "-";
	
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
	 * @throws ArgumentIsNullException if the given text is null.
	 */
	public Button(final String text) {
		
		//Calls other constructor.
		this();
		
		setText(text);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ROLE:
				setRole(ButtonRole.fromSpecification(attribute));
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
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current Button has a role.
		if (hasRole()) {
			attributes.addAtEnd(getRole().getSpecificationAs(PascalCaseNameCatalogue.ROLE));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the role of the current {@link Button}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Button} does not have a role.
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
	
	//method
	/**
	 * Removes the role of the current {@link Button}.
	 * 
	 * @return the current {@link Button}.
	 */
	public Button removeRole() {
		
		role = null;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Button reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setText(DEFAULT_TEXT);
		
		return this;
	}
	
	//method
	/**
	 * Sets the role of the current {@link Button}.
	 * 
	 * @param role
	 * @return the current {@link Button}.
	 * @throws ArgumentIsNullException if the given role is null.
	 */
	public Button setRole(final ButtonRole role) {
		
		//Checks if the given role is not null.
		Validator.suppose(role).thatIsNamed(VariableNameCatalogue.ROLE).isNotNull();
		
		//Sets the role of the current Button.
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
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		
		setMinWidth(100);
		setContentPosition(ContentPosition.CENTER);
		
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
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Button} does not have a role.
	 */
	private void supposeHasRole() {
		
		//Checks if the current Button has a role.
		if (!hasRole()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
