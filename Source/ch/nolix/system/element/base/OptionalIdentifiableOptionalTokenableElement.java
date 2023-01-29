//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalIdentifiableByString;
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalTokenable;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;

//class
public abstract class OptionalIdentifiableOptionalTokenableElement<E extends StylableElement<E>>
extends MutableElement
implements FluentOptionalIdentifiableByString<E>, FluentOptionalTokenable<E> {
	
	//constant
	private static final String ID_HEADER = PascalCaseCatalogue.ID;
	
	//constant
	private static final String TOKEN_HEADER = PascalCaseCatalogue.TOKEN;
	
	//attribute
	private final MutableOptionalValue<String> id = MutableOptionalValue.forString(ID_HEADER, this::setId);
	
	//attribute
	private final MutableOptionalValue<String> token = MutableOptionalValue.forString(TOKEN_HEADER, this::setToken);
	
	//method
	@Override
	public final String getId() {
		return id.getValue();
	}
	
	//method
	@Override
	public final String getIdInQuotes() {
		return GlobalStringHelper.getInQuotes(getId());
	}
	
	//method
	@Override
	public final String getToken() {
		return token.getValue();
	}
	
	//method
	@Override
	public final boolean hasId() {
		return id.hasValue();
	}
	
	//method
	@Override
	public final boolean hasId(final String id) {
		return (hasId() && getId().equals(id));
	}
	
	//method
	@Override
	public final boolean hasToken() {
		return token.hasValue();
	}
	
	//method
	@Override
	public final boolean hasToken(final String token) {
		return (hasToken() && getToken().equals(token));
	}
	
	//method
	@Override
	public final void removeId() {
		id.clear();
	}
	
	//method
	@Override
	public final void removeToken() {
		token.clear();
	}
	
	//method
	@Override
	public final void reset() {
		
		removeId();
		removeToken();
		
		resetElement();
	}
	
	//method
	@Override
	public final E setId(final String id) {
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id.setValue(id);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final E setToken(final String token) {
		
		GlobalValidator.assertThat(token).thatIsNamed(LowerCaseCatalogue.TOKEN).isNotBlank();
		
		this.token.setValue(token);
		
		return asConcrete();
	}
	
	//method
	@SuppressWarnings("unchecked")
	protected final E asConcrete() {
		return (E)this;
	}
	
	//method declaration
	protected abstract void resetElement();
}
