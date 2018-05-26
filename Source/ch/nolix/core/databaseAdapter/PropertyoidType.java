//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Specified;

//abstract class
public abstract class PropertyoidType<V> implements Specified {
	
	//attribute
	private final ValueType<V> valueType;
	
	//constructor
	public PropertyoidType(final Class<V> valueClass) {
		this.valueType = new ValueType<V>(valueClass);
	}
	
	//abstract method
	public abstract boolean captionsPropertyThatCanReference(Entity entity);
	
	//method
	public List<StandardSpecification> getAttributes() {
		return
		new List<StandardSpecification>(
			new StandardSpecification(
				getValueTypeName()
			)
		);
	}
	
	//abstract method
	public abstract PropertyKind getPropertyKind();
	
	//method
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	public final Class<V> getValueClass() {
		return getValueType().getValueClass();
	}
	
	//method
	public final ValueType<V> getValueType() {
		return valueType;
	}
	
	//method
	public final String getValueTypeName() {
		return getValueType().getName();
	}
	
	//method
	public final boolean isDataType() {
		return (getPropertyKind() == PropertyKind.DATA);
	}
	
	//method
	public final boolean isIdType() {
		return (getPropertyKind() == PropertyKind.ID);
	}
	
	//method
	public final boolean isOptionalDataType() {
		return (getPropertyKind() == PropertyKind.OPTIONAL_DATA);
	}
	
	//method
	public final boolean isOptionalReferenceType() {
		return (getPropertyKind() == PropertyKind.OPTIONAL_REFERENCE);
	}
	
	//method
	public final boolean isMultiDataType() {
		return (getPropertyKind() == PropertyKind.MULTI_DATA);
	}
	
	//method
	public final boolean isMultiReferenceType() {
		return (getPropertyKind() == PropertyKind.MULTI_REFERENCE);
	}
	
	//method
	public final boolean isReferenceType() {
		return (getPropertyKind() == PropertyKind.REFERENCE);
	}
}
