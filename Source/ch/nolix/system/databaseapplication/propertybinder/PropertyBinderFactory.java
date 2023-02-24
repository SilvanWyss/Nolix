//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

//class
public final class PropertyBinderFactory {
	
	//static attributes
	private static final ValueBinder valueBinder = new ValueBinder();
	private static final OptionalValueBinder optionalValueBinder = new OptionalValueBinder();
	private static final ReferenceBinder referenceBinder = new ReferenceBinder();
	private static final OptionalReferenceBinder optionalReferenceBinder = new OptionalReferenceBinder();
	
	//method
	@SuppressWarnings("unchecked")
	public <P extends IProperty> PropertyBinder<P, ?> getPropertyBinderFor(final P property) {
		return
		switch (property.getType()) {
			case VALUE ->
				(PropertyBinder<P, ?>)valueBinder;
			case OPTIONAL_VALUE ->
				(PropertyBinder<P, ?>)optionalValueBinder;
			case REFERENCE ->
				(PropertyBinder<P, ?>)referenceBinder;
			case OPTIONAL_BACK_REFERENCE ->
				(PropertyBinder<P, ?>)optionalReferenceBinder;
			default ->
				throw InvalidArgumentException.forArgument(property);
		};
	}
}
