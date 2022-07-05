//package declaration
package ch.nolix.core.caching;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementGetter;

//class
public final class CachingProperty<V> {
	
	//attribute
	private final IElementGetter<V> valueCreator;
	
	//optional attribute
	private final IBooleanGetter needToRefreshFunction;
	
	//optional attribute
	private V value;
	
	//constructor
	public CachingProperty(final IElementGetter<V> valueCreator) {
		
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.valueCreator = valueCreator;
		needToRefreshFunction = null;
	}
	
	//constructor
	public CachingProperty(final IElementGetter<V> valueCreator, final IBooleanGetter needToRefreshFunction) {
		
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(needToRefreshFunction).thatIsNamed("need-to-refresh-function").isNotNull();
		
		this.valueCreator = valueCreator;
		this.needToRefreshFunction = needToRefreshFunction;
	}
	
	//method
	public V getValue() {
		
		refreshIfNeeded();
		
		return value;
	}
	
	//method
	private void refresh() {
		value = valueCreator.getOutput();
	}
	
	//method
	private void refreshIfNeeded() {
		if (refreshIsNeeded()) {
			refresh();
		}
	}
	
	//method
	private boolean refreshIsNeeded() {
		return (value == null || (needToRefreshFunction != null && needToRefreshFunction.getOutput()));
	}
}
