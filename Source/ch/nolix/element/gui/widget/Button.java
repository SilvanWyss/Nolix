//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 210
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
		
		reset();
		
		setMinWidth(100);
		setContentPosition(ContentPosition.CENTER);
		
		getRefLook()
		.setBorderThicknessForState(WidgetLookState.BASE, 1)
		.setBackgroundColorForState(WidgetLookState.BASE, Color.GREY)
		.setLeftPaddingForState(WidgetLookState.BASE,10)
		.setRightPaddingForState(WidgetLookState.BASE, 10);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseCatalogue.ROLE:
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
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//Handles the case that the current Button has a role.
		if (role != null) {
			list.addAtEnd(role.getSpecificationAs(PascalCaseCatalogue.ROLE));
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
		Validator.assertThat(role).thatIsNamed(LowerCaseCatalogue.ROLE).isNotNull();
		
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
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
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
	protected void paintTextLineWidgetContentArea(final IPainter painter, final ButtonLook buttonLook) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidget() {
		setText(DEFAULT_TEXT);
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Button} does not have a role.
	 */
	private void assertHasRole() {
		if (role == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ROLE);
		}
	}
}
