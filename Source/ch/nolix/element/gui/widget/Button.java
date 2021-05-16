//package declaration
package ch.nolix.element.gui.widget;

import ch.nolix.common.constant.PascalCaseCatalogue;
//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 160
 */
public final class Button extends TextLineWidget<Button, ButtonLook> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//attribute
	private final MutableOptionalValue<ButtonRole> optionalRole =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		ButtonRole::fromSpecification,
		ButtonRole::getSpecification
	);
	
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
		.setBackgroundColorForState(WidgetLookState.BASE, Color.LIGHT_GREY)
		.setBackgroundColorForState(WidgetLookState.HOVER, Color.DARK_GREY)
		.setLeftPaddingForState(WidgetLookState.BASE,10)
		.setRightPaddingForState(WidgetLookState.BASE, 10);
	}
	
	//method
	/**
	 * @return the role of the current {@link Button}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Button} does not have a role.
	 */
	public ButtonRole getRole() {
		return optionalRole.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link Button} has a role.
	 */
	public boolean hasRole() {
		return optionalRole.hasValue();
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
	 */
	public void removeRole() {
		optionalRole.clear();
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
		
		this.optionalRole.setValue(role);
		
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
	protected String getDefaultText() {
		return DEFAULT_TEXT;
	}
	
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
	protected void resetTextLineWidget() {}
}
