//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.controlrole.ButtonRole;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class Button extends TextLineWidget<Button, ButtonLook> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//attribute
	private final MutableOptionalValue<ButtonRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		ButtonRole::fromSpecification,
		Node::fromEnum
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
		.setBorderThicknessForState(ControlState.BASE, 1)
		.setBackgroundColorForState(ControlState.BASE, Color.LIGHT_GREY)
		.setBackgroundColorForState(ControlState.HOVER, Color.DARK_GREY)
		.setLeftPaddingForState(ControlState.BASE,10)
		.setRightPaddingForState(ControlState.BASE, 10);
	}
	
	//method
	/**
	 * @return the role of the current {@link Button}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Button} does not have a role.
	 */
	public ButtonRole getRole() {
		return role.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getShownText() {
		return getText();
	}
	
	//method
	/**
	 * @return true if the current {@link Button} has a role.
	 */
	public boolean hasRole() {
		return role.hasValue();
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
		role.clear();
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
		
		this.role.setValue(role);
		
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
	protected String getDefaultText() {
		return DEFAULT_TEXT;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getTextWidthAddition() {
		return 0;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintTextLineWidgetContentArea(final IPainter painter, final ButtonLook buttonLook) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfiguration() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetTextLineWidget() {
		//Does nothing.
	}
}
