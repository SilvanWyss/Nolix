//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 220
 */
public final class Button extends TextLineWidget<Button, ButtonLook> {
	
	//constant
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
		
		setMinWidth(100);
		setContentPosition(ContentPosition.CENTER);
		
		getRefBaseLook()
		.setBorderThicknesses(1)
		.setLeftPadding(10)
		.setRightPadding(10);
		
		getRefHoverLook().setBackgroundColor(Color.GREY);
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
		
		//Asserts that the current Button has a role.
		assertHasRole();
		
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
	public void reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setText(DEFAULT_TEXT);
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
		
		//Asserts that the given role is not null.
		Validator.assertThat(role).thatIsNamed(VariableNameCatalogue.ROLE).isNotNull();
		
		//Sets the role of the current Button.
		this.role = role;
		
		return this;
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
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentAreaStage2(final IPainter painter, final ButtonLook buttonLook) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurationOnSelfStage3() {}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Button} does not have a role.
	 */
	private void assertHasRole() {
		if (!hasRole()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
