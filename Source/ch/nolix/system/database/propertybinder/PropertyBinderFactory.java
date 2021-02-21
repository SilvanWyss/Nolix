//package declaration
package ch.nolix.system.database.propertybinder;

//own imports
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.entity.Property;

//class
public final class PropertyBinderFactory {
	
	//static attributes
	private static final ValueBinder valueBinder = new ValueBinder();
	private static final OptionalValueBinder optionalValueBinder = new OptionalValueBinder();
	private static final ReferenceBinder referenceBinder = new ReferenceBinder();
	private static final OptionalReferenceBinder optionalReferenceBinder = new OptionalReferenceBinder();
	
	//method
	@SuppressWarnings("unchecked")
	public <P extends Property<?>> PropertyBinder<P, ?> getPropertyBinderFor(final P property) {
		switch (property.getPropertyKind()) {
			case VALUE:
				return (PropertyBinder<P, ?>)valueBinder;
			case OPTIONAL_VALUE:
				return (PropertyBinder<P, ?>)optionalValueBinder;
			case REFERENCE:
				return (PropertyBinder<P, ?>)referenceBinder;
			case OPTIONAL_BACK_REFERENCE:
				return (PropertyBinder<P, ?>)optionalReferenceBinder;
			default:
				throw new InvalidArgumentException(property);
		}
	}
}
