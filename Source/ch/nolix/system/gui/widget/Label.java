//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class Label extends TextLineWidget<Label, LabelStyle> {
	
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
		Node::fromEnum
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
	 * {@inheritDoc}
	 */
	@Override
	public String getShownText() {
		return getText();
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
	protected LabelStyle createLook() {
		return new LabelStyle();
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
	protected void paintTextLineWidgetContentArea(final IPainter painter, final LabelStyle textLineWidgetLook) {
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
