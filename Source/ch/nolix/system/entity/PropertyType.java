//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.containers.List;
import ch.nolix.common.node.Node;
import ch.nolix.element.baseAPI.IElement;

//abstract class
public abstract class PropertyType<V> implements IElement {
	
	//attribute
	private final ValueClassBox<V> valueClassBox;
	
	//constructor
	public PropertyType(final Class<V> valueClass) {
		this.valueClassBox = new ValueClassBox<>(valueClass);
	}
	
	//abstract method
	public abstract boolean captionsPropertyThatCanReference(Entity entity);
	
	//method
	@Override
	public List<Node> getAttributes() {
		return new List<>(Node.fromString(getValueTypeName())	);
	}
	
	//abstract method
	public abstract PropertyCategory getPropertyKind();
	
	//method
	@Override
	public final String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	public final Class<V> getValueClass() {
		return getValueClassBox().getValueClass();
	}
	
	//method
	public final ValueClassBox<V> getValueClassBox() {
		return valueClassBox;
	}
	
	//method
	public final String getValueTypeName() {
		return getValueClassBox().getName();
	}
	
	//method
	public final boolean isAnyDataType() {
		switch (getPropertyKind()) {
			case VALUE:
			case OPTIONAL_VALUE:
			case MULTI_VALUE:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public final boolean isAnyDataOrReferenceType() {
		switch (getPropertyKind()) {
			case VALUE:
			case OPTIONAL_VALUE:
			case MULTI_VALUE:
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
		return (getPropertyKind() == PropertyCategory.VALUE);
	}
	
	//method
	public final boolean isIdType() {
		return (getPropertyKind() == PropertyCategory.ID);
	}
	
	//method
	public final boolean isOptionalDataType() {
		return (getPropertyKind() == PropertyCategory.OPTIONAL_VALUE);
	}
	
	//method
	public final boolean isOptionalReferenceType() {
		return (getPropertyKind() == PropertyCategory.OPTIONAL_REFERENCE);
	}
	
	//method
	public final boolean isMultiDataType() {
		return (getPropertyKind() == PropertyCategory.MULTI_VALUE);
	}
	
	//method
	public final boolean isMultiReferenceType() {
		return (getPropertyKind() == PropertyCategory.MULTI_REFERENCE);
	}
	
	//method
	public final boolean isReferenceType() {
		return (getPropertyKind() == PropertyCategory.REFERENCE);
	}
	
	//method
	public abstract boolean referencesEntitySet(final String name);
}
