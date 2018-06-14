//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A {@link Container} is a {@link BorderWidget} that can contain other widgets.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 140
 * @param <C> The type of a {@link Container}.
 * @param <BWS> The type of the {@link BorderWidgetLook} of a {@link Container}.
 */
public abstract class Container<
	C extends Container<C, BWS>,
	BWS extends BorderWidgetLook<BWS>
>
extends BorderWidget<C, BWS> {
	
	//optional attribute
	private ContainerRole role;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link Container}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ROLE:
				setRole(ContainerRole.createFromSpecification(attribute));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Container}.
	 */
	public List<StandardSpecification> getAttributes() {
		
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
	 * @return the role of the current {@link Container}.
	 * @throws UnexistingAttributeException if the current {@link Container} has no role.
	 */
	public final ContainerRole getRole() {
		
		//Checks if the current container has a role.
		supposeHasRole();
		
		return role;
	}
	
	//method
	/**
	 * @return true if the current {@link Container} has a role.
	 */
	public final boolean hasRole() {
		return (role != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Container} has the given role.
	 */
	public final boolean hasRole(final String role) {
			
		//Handles the case that the current container has no role.
		if (!hasRole()) {
			return false;
		}
		
		//Handles the case that the current container has a role.
		return getRole().toString().equals(role);
	}
		
	//method
	/**
	 * Sets the role of the current {@link Container}.
	 * 
	 * @param role
	 * @return the current {@link Container}.
	 * @throws NullArgumentException if the given role is null.
	 */
	public final C setRole(final ContainerRole role) {
		
		//Checks if the given role is not null.
		Validator
		.suppose(role)
		.thatIsNamed(VariableNameCatalogue.ROLE)
		.isNotNull();
		
		//Sets the role of this container.
		this.role = role;
		
		return getInstance();
	}
	
	//method
	/**
	 * @throws UnexistingAttributeException if the current {@link Container} has no role.
	 */
	private void supposeHasRole() {
		
		//Checks if the current container has a role.
		if (!hasRole()) {
			throw new UnexistingAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
}
