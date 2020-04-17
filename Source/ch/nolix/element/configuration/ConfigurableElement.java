//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalTokenable;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.baseAPI.IMutableElement;

//class
/**
 * A {@link ConfigurableElement} is configurable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 230
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>> extends Element<CE>
implements IConfigurableElement<CE>, IMutableElement<CE>, ISmartObject<CE>, OptionalTokenable<CE> {
	
	//optional attributes
	private String id;
	private String token;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ID:
				setId(attribute.getOneAttributeAsString());
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
	public LinkedList<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		//Handles the case that the current configurbale element has a id.
		if (hasId()) {
			attributes.addAtEnd(new Node(PascalCaseNameCatalogue.ID, id));
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
	public final String getId() {
		
		supposeHasId();
		
		return id;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToken() {
		
		//Asserts that the current configurable element has a token.
		supposeHasToken();
		
		return token;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasId() {
		return (id != null);
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
	public final CE removeId() {
		
		id = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CE removeToken() {
		
		token = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CE reset() {
		
		removeId();
		resetConfiguration();
		
		return asConcrete();
	}
	
	//method
	/**
	 * @param id
	 * @return the current {@link ConfigurableElement}.
	 * @throws ArgumentIsNullException if the given id is null.
	 * @throws InvalidArgumentException if the given id is blank.
	 */
	@Override
	public final CE setId(final String id) {
		
		Validator.assertThat(id).thatIsNamed(VariableNameCatalogue.ID).isNotBlank();
		
		this.id = id;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the token of the current {@link ConfigurableElement}.
	 * 
	 * @param token
	 * @throws ArgumentIsNullException if the given token is null.
	 * @throws EmptyArgumentException if the given token is empty.
	 */
	@Override
	public final CE setToken(final String token) {
		
		//Asserts that the given token is not null and not empty.
		Validator
		.assertThat(token)
		.thatIsNamed(VariableNameCatalogue.TOKEN)
		.isNotEmpty();
		
		//Sets the token of the current configurable element.
		this.token = token;
		
		return asConcrete();
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ConfigurableElement} does not have an id.
	 */
	private void supposeHasId() {
		
		//Asserts that the current configurable element has a token.
		if (!hasId()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ID);
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ConfigurableElement} does not have a token.
	 */
	private void supposeHasToken() {
		
		//Asserts that the current configurable element has a token.
		if (!hasToken()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.TOKEN);
		}
	}
}
