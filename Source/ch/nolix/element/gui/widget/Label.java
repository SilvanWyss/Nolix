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
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 190
 */
public final class Label extends TextLineWidget<Label, LabelLook> {
	
	//constants
	public static final String TYPE_NAME = "Label";
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//optional attribute
	private LabelRole role;
	
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
			case PascalCaseCatalogue.ROLE:
				setRole(LabelRole.fromSpecification(attribute));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
		
	//method
	//For a better performance, this method does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//Handles the case that the current Label has a role.
		if (role != null) {
			list.addAtEnd(role.getSpecificationAs(PascalCaseCatalogue.ROLE));
		}
	}
	
	//method
	/**
	 * @return the role of the current {@link Label}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Label} does not have a role.
	 */
	public LabelRole getRole() {
		
		//Asserts that the current label has a Role.
		assertHasRole();
		
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
	 * @throws ArgumentIsNullException if the given role is null.
	 */
	public Label setRole(final LabelRole role) {
		
		//Asserts that the given role is not null.
		Validator
		.assertThat(role)
		.thatIsNamed(LowerCaseCatalogue.ROLE)
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
	protected LabelLook createLook() {
		return new LabelLook();
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
	protected void paintTextLineWidgetContentArea(final IPainter painter, final LabelLook textLineWidgetLook) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidget() {
		setText(DEFAULT_TEXT);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Label} does not have a role.
	 */
	private void assertHasRole() {
		
		//Asserts that the current {@link Label} has a role.
		if (!hasRole()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ROLE);
		}
	}
}
