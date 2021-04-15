//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.widget.BorderWidget;
import ch.nolix.element.gui.widget.BorderWidgetLook;
import ch.nolix.element.gui.widget.OldBorderWidgetLook;

//class
/**
 * A {@link ContainerWidget} is a {@link BorderWidget} that can contain other {@link Widget}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 170
 * @param <CW> is the type of a {@link ContainerWidget}.
 * @param <BWS> is the type of the {@link OldBorderWidgetLook} of a {@link ContainerWidget}.
 */
public abstract class ContainerWidget<CW extends ContainerWidget<CW, BWS>, BWS extends BorderWidgetLook<BWS>>
extends BorderWidget<CW, BWS> implements Clearable {
	
	//optional attribute
	private ContainerRole role;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link ContainerWidget}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseCatalogue.ROLE:
				setRole(ContainerRole.fromSpecification(attribute));
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
		
		//Handles the case that the current container has a role.
		if (role != null) {
			list.addAtEnd(role.getSpecificationAs(PascalCaseCatalogue.ROLE));
		}
	}
	
	//method
	/**
	 * @return the role of the current {@link ContainerWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ContainerWidget} does not have a role.
	 */
	public final ContainerRole getRole() {
		
		//Asserts that the current container has a role.
		assertHasRole();
		
		return role;
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
		return (role != null);
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
		role = null;
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
		
		//Asserts that the given role is not null.
		Validator.assertThat(role).thatIsNamed(LowerCaseCatalogue.ROLE).isNotNull();
		
		//Sets the role of the current ContainerWidget.
		this.role = role;
		
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
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ContainerWidget} does not have a role.
	 */
	private void assertHasRole() {
		if (!hasRole()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ROLE);
		}
	}
}
