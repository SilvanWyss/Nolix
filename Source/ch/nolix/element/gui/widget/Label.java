//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 160
 */
public final class Label extends TextLineWidget<Label, LabelLook> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.MINUS;

	//constant
	private static final String LABEL_ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//attribute
	private final MutableOptionalValue<LabelRole> role =
	new MutableOptionalValue<>(
		LABEL_ROLE_HEADER,
		this::setRole,
		LabelRole::fromSpecification,
		LabelRole::getSpecification
	);
	
	//constructor
	/**
	 * Creates a new {@link Label}.
	 */
	public Label() {
		reset();
	}
	
	//method
	/**
	 * @return the role of the current {@link Label}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Label} does not have a role.
	 */
	public LabelRole getRole() {
		return role.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link Label} has a role.
	 */
	public boolean hasRole() {
		return role.hasValue();
	}
	
	//method
	/**
	 * @param role
	 * @return true if the current {@link Label} has the given role.
	 */
	@Override
	public boolean hasRole(final String role) {
		
		//Handles the case that the current Label does not have a role.
		if (!hasRole()) {
			return false;
		}
		
		//Handles the case that the current Label has a role.
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
		
		this.role.setValue(role);
		
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
	protected void paintTextLineWidgetContentArea(final IPainter painter, final LabelLook textLineWidgetLook) {}
	
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
