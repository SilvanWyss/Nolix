//package declaration
package ch.nolix.common.caching;

//own imports
import ch.nolix.common.functionAPI.IBooleanGetter;
import ch.nolix.common.functionAPI.IElementGetter;
import ch.nolix.common.validator.Validator;

//class
public final class CachingProperty<V> {
	
	//attribute
	private final IElementGetter<V> valueCreator;
	
	//optional attributes
	private final IBooleanGetter needToRecreateValueFunction;
	private V value;
	
	//constructor
	public CachingProperty(final IElementGetter<V> valueCreator) {
		
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.valueCreator = valueCreator;
		needToRecreateValueFunction = null;
	}
	
	//constructor
	public CachingProperty(final IElementGetter<V> valueCreator, final IBooleanGetter needToRecreateValueFunction) {
		
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		Validator.assertThat(needToRecreateValueFunction).thatIsNamed("need-to-recreate-value-function").isNotNull();
		
		this.valueCreator = valueCreator;
		this.needToRecreateValueFunction = needToRecreateValueFunction;
	}
	
	//method
	public V getValue() {
		
		createValueIfNeeded();
		
		return value;
	}
	
	//method
	private void createValueIfNeeded() {
		if (value == null || (needToRecreateValueFunction != null && needToRecreateValueFunction.getOutput())) {
			value = valueCreator.getOutput();
		}
	}
}
