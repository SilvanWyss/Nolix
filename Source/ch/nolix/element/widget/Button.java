//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
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
 * @date 2016-01-01
 * @lines 200
 */
public final class Button extends TextLineWidget<Button, ButtonLook> {
	
	//constants
	public static final String TYPE_NAME = "Button";
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
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
		
		getRefHoverLook()
		.setBackgroundColor(Color.GREY);
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
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Handles the case that the current Button has a role.
		if (role != null) {
			list.addAtEnd(role.getSpecificationAs(PascalCaseNameCatalogue.ROLE));
		}
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
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRole(final String role) {	
		return (this.role != null && this.role.toString().equals(role));
	}
	
	//method
	/**
	 * Removes the role of the current {@link Button}.
	 */
	public void removeRole() {
		role = null;
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
		
		//Returns the current Button.
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
	 * {@inheritDoc}
	 */
	@Override
	protected void resetStage4() {
		setText(DEFAULT_TEXT);
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Button} does not have a role.
	 */
	private void assertHasRole() {
		if (role == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
