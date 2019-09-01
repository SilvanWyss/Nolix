//package declaration
package ch.nolix.element.configuration;

import ch.nolix.common.attributeAPI.OptionalNamable;
import ch.nolix.common.attributeAPI.OptionalTokenable;
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.common.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.baseAPI.IMutableElement;

//abstract class
/**
 * A {@link ConfigurableElement} is a {@link OptionalNamableElement}
 * that is configurable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 160
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>> extends Element<CE>
implements IConfigurableElement<CE>, ISmartObject<CE>, OptionalNamable<CE>, OptionalTokenable<CE>, IMutableElement<CE> {
	
	//optional attributes
	private String name;
	private String token;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.NAME:
				setName(attribute.getOneAttributeAsString());
				break;
			case PascalCaseNameCatalogue.TOKEN:
				setToken(attribute.getOneAttributeAsString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		//Handles the case that the current configurbale element has a name.
		if (hasName()) {
			attributes.addAtEnd(new Node(PascalCaseNameCatalogue.NAME, name));
		}
		
		//Handles the case that the current configurbale element has a token.
		if (hasToken()) {
			attributes.addAtEnd(new Node(PascalCaseNameCatalogue.TOKEN, token));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToken() {
		
		//Checks if the current configurable element has a token.
		supposeHasToken();
		
		return token;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasName() {
		return (name != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasToken() {
		return (token != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasToken(String token) {
		return OptionalTokenable.super.hasToken(token);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CE removeName() {
		
		name = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CE removeToken() {
		
		token = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CE reset() {
		
		removeName();
		resetConfiguration();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @param name
	 * @return the current {@link ConfigurableElement}.
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 */
	@Override
	public final CE setName(final String name) {
		
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the token of the current {@link ConfigurableElement}.
	 * 
	 * @param token
	 * @throws NullArgumentException if the given token is null.
	 * @throws EmptyArgumentException if the given token is empty.
	 */
	@Override
	public final CE setToken(final String token) {
		
		//Checks if the given token is not null and not empty.
		Validator
		.suppose(token)
		.thatIsNamed(VariableNameCatalogue.TOKEN)
		.isNotEmpty();
		
		//Sets the token of the current configurable element.
		this.token = token;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link ConfigurableElement} does not have a token.
	 */
	private void supposeHasToken() {
		
		//Checks if the current configurable element has a token.
		if (!hasToken()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.TOKEN);
		}
	}
}
