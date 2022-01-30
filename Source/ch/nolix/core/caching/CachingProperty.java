//package declaration
package ch.nolix.core.caching;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IBooleanGetter;
import ch.nolix.core.functionapi.IElementGetter;
import ch.nolix.core.skillapi.Refreshable;

//class
public final class CachingProperty<V> implements Refreshable {
	
	//attribute
	private final IElementGetter<V> valueCreator;
	
	//optional attributes
	private final IBooleanGetter needToRefreshFunction;
	private V value;
	
	//constructor
	public CachingProperty(final IElementGetter<V> valueCreator) {
		
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.valueCreator = valueCreator;
		needToRefreshFunction = null;
	}
	
	//constructor
	public CachingProperty(final IElementGetter<V> valueCreator, final IBooleanGetter needToRefreshFunction) {
		
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		Validator.assertThat(needToRefreshFunction).thatIsNamed("need-to-refresh-function").isNotNull();
		
		this.valueCreator = valueCreator;
		this.needToRefreshFunction = needToRefreshFunction;
	}
	
	//method
	public V getValue() {
		
		refreshIfNeeded();
		
		return value;
	}
	
	//method
	@Override
	public void refresh() {
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
