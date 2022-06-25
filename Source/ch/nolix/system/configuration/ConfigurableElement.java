//package declaration
package ch.nolix.system.configuration;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.MutableElement;
import ch.nolix.system.element.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;

//class
/**
 * A {@link ConfigurableElement} is configurable.
 * 
 * @author Silvan Wyss
 * @date 2016-02-01-01
 * @param <CE> is the type of a {@link ConfigurableElement}.
 */
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>> extends MutableElement<CE>
implements IConfigurableElement<CE> {
	
	//constants
	private static final String ID_HEADER = PascalCaseCatalogue.ID;
	private static final String TOKEN_HEADER = PascalCaseCatalogue.TOKEN;
	
	//attributes
	private final MutableOptionalValue<String> id = MutableOptionalValue.forString(ID_HEADER, this::setId);
	private final MutableOptionalValue<String> token = MutableOptionalValue.forString(TOKEN_HEADER, this::setToken);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsElement(final String id) {
		
		final var subConfiguratbles = getSubConfigurables();
		
		return 
		subConfiguratbles.containsAny(sc -> sc.hasId(id))
		||subConfiguratbles.containsAny(sc -> sc.containsElement(id));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {
		return id.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getIdInQuotes() {
		return GlobalStringHelper.getInQuotes(getId());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getToken() {
		return token.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasId() {
		return id.hasValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasId(final String id) {
		
		if (!hasId()) {
			return false;
		}
		
		return getId().equals(id);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasToken() {
		return token.hasValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeId() {
		id.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeToken() {
		token.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void reset() {
		
		removeId();
		removeToken();
		resetConfiguration();
		
		resetConfigurableElement();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void resetConfiguration() {
		
		resetElementConfiguration();
		
		getSubConfigurables().forEach(IConfigurableElement::resetConfiguration);
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
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id.setValue(id);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the token of the current {@link ConfigurableElement}.
	 * 
	 * @param token
	 * @throws ArgumentIsNullException if the given token is null.
	 * @throws EmptyArgumentException if the given token is blank.
	 */
	@Override
	public final CE setToken(final String token) {
		
		GlobalValidator.assertThat(token).thatIsNamed(LowerCaseCatalogue.TOKEN).isNotBlank();
		
		this.token.setValue(token);
		
		return asConcrete();
	}
	
	//method declaration
	/**
	 * Resets the current {@link ConfigurableElement}.
	 */
	protected abstract void resetConfigurableElement();
}
