//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.widget.BorderWidget;
import ch.nolix.element.gui.widget.BorderWidgetLook;

//class
/**
 * A {@link ContainerWidget} is a {@link BorderWidget} that can contain other {@link Widget}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 120
 * @param <CW> is the type of a {@link ContainerWidget}.
 * @param <BWS> is the type of the {@link BorderWidgetLook} of a {@link ContainerWidget}.
 */
public abstract class ContainerWidget<CW extends ContainerWidget<CW, BWS>, BWS extends BorderWidgetLook<BWS>>
extends BorderWidget<CW, BWS> implements Clearable {
	
	//constant
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	
	//attribute
	private final MutableOptionalValue<ContainerRole> role =
	new MutableOptionalValue<>(
		ROLE_HEADER,
		this::setRole,
		ContainerRole::fromSpecification,
		ContainerRole::getSpecification
	);
	
	//method
	/**
	 * @return the role of the current {@link ContainerWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ContainerWidget} does not have a role.
	 */
	public final ContainerRole getRole() {
		return role.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isEmpty() {
		return this.getChildWidgets().isEmpty();
	}
	
	//method
	/**
	 * @return true if the current {@link ContainerWidget} has a role.
	 */
	public final boolean hasRole() {
		return role.hasValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasRole(final String role) {
		
		//Handles the case that the current container does not have a role.
		if (!hasRole()) {
			return false;
		}
		
		//Handles the case that the current container has a role.
		return getRole().toString().equals(role);
	}
	
	//method
	/**
	 * Removes the role of the current {@link ContainerWidget}.
	 */
	public void removeRole() {
		role.clear();
	}
	
	//method
	/**
	 * Sets the role of the current {@link ContainerWidget}.
	 * 
	 * @param role
	 * @return the current {@link ContainerWidget}.
	 * @throws ArgumentIsNullException if the given role is null.
	 */
	public final CW setRole(final ContainerRole role) {
		
		this.role.setValue(role);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetBorderWidget() {
		
		removeRole();
		clear();
		
		resetContainerWidget();
	}
	
	//method declaration
	/**
	 * Resets the current {@link ContainerWidget}.
	 */
	protected abstract void resetContainerWidget();
}
