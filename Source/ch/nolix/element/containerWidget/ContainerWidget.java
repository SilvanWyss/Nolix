//package declaration
package ch.nolix.element.containerWidget;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.widget.BorderWidget;
import ch.nolix.element.widget.BorderWidgetLook;

//class
/**
 * A {@link ContainerWidget} is a {@link BorderWidget} that can contain other widgets.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 140
 * @param <C> The type of a {@link ContainerWidget}.
 * @param <BWS> The type of the {@link BorderWidgetLook} of a {@link ContainerWidget}.
 */
public abstract class ContainerWidget<
	C extends ContainerWidget<C, BWS>,
	BWS extends BorderWidgetLook<BWS>
>
extends BorderWidget<C, BWS> {
	
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
			case PascalCaseNameCatalogue.ROLE:
				setRole(ContainerRole.fromSpecification(attribute));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of the current {@link ContainerWidget}.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current container has a role.
		if (hasRole()) {
			attributes.addAtEnd(
				getRole().getSpecificationAs(PascalCaseNameCatalogue.ROLE)
			);
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the role of the current {@link ContainerWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ContainerWidget} does not have a role.
	 */
	public final ContainerRole getRole() {
		
		//Asserts that the current container has a role.
		supposeHasRole();
		
		return role;
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
	 * @return true if the current {@link ContainerWidget} has the given role.
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
	 * Sets the role of the current {@link ContainerWidget}.
	 * 
	 * @param role
	 * @return the current {@link ContainerWidget}.
	 * @throws ArgumentIsNullException if the given role is null.
	 */
	public final C setRole(final ContainerRole role) {
		
		//Asserts that the given role is not null.
		Validator
		.assertThat(role)
		.thatIsNamed(VariableNameCatalogue.ROLE)
		.isNotNull();
		
		//Sets the role of this container.
		this.role = role;
		
		return asConcrete();
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link ContainerWidget} does not have a role.
	 */
	private void supposeHasRole() {
		
		//Asserts that the current container has a role.
		if (!hasRole()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
