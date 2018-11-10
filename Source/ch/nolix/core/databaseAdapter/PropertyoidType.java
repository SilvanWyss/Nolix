//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.specificationAPI.Specified;

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
	public List<DocumentNode> getAttributes() {
		return
		new List<DocumentNode>(
			new DocumentNode(
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
	public final boolean isAnyDataType() {
		switch (getPropertyKind()) {
			case DATA:
			case OPTIONAL_DATA:
			case MULTI_DATA:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public final boolean isAnyDataOrReferenceType() {
		switch (getPropertyKind()) {
			case DATA:
			case OPTIONAL_DATA:
			case MULTI_DATA:
			case REFERENCE:
			case OPTIONAL_REFERENCE:
			case MULTI_REFERENCE:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public final boolean isAnyReferenceType() {
		switch (getPropertyKind()) {
			case REFERENCE:
			case OPTIONAL_REFERENCE:
			case MULTI_REFERENCE:
				return true;
			default:
				return false;
		}
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
	
	//method
	public abstract boolean referencesEntitySet(final String name);
}
