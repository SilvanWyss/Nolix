//package declaration
package ch.nolix.core.databaseSchemaAdapter;

import ch.nolix.core.SQL.SQLDatabaseEngine;
//own imports
import ch.nolix.core.bases.HeaderedElement;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.PropertyKind;
import ch.nolix.core.databaseAdapter.PropertyoidType;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.specificationAPI.Specified;
import ch.nolix.core.validator2.Validator;

//class
public final class Column
extends HeaderedElement
implements Specified {
	
	//attribute
	private final EntitySet entitySet;
	private final PropertyoidType<?> valueType;
		
	//package-visible constructor
	Column(
		final EntitySet entitySet,
		final String header,
		final PropertyoidType<?> valueType
	) {
		
		super(header);

		Validator.suppose(entitySet).isInstanceOf(EntitySet.class);
		Validator.suppose(valueType).isInstanceOf(PropertyoidType.class);
		
		this.entitySet = entitySet;
		this.valueType = valueType;
	}
	
	//method
	public List<DocumentNode> getAttributes() {
		return 
		new List<DocumentNode>(
			new DocumentNode(PascalCaseNameCatalogue.HEADER, getHeader()),
			valueType.getSpecification()
		);
	}
	
	//method
	public PropertyKind getPropertyKind() {
		return valueType.getPropertyKind();
	}
	
	//method
	public EntitySet getRefEntitySet() {
		return entitySet;
	}
	
	//method
	public ColumnSQLHelper getSQLHelper(final SQLDatabaseEngine sQLDatabaseEngine) {
		switch (sQLDatabaseEngine) {
			case MSSQL:
				return new ColumnMSSQLHelper(this);
			default:
				//TODO: Create a column SQL helper for all database engines.
				return null;
		}
	}
	
	//method
	public String getType() {
		return PascalCaseNameCatalogue.COLUMN;
	}
	
	//method
	public Class<?> getValueClass() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return valueType.getValueClass();
	}
	
	//method
	public PropertyoidType<?> getValueType() {
		return valueType;
	}
	
	//method
	public boolean isDataColumn() {
		return valueType.isDataType();
	}
	
	//method
	public boolean isReferenceColumn() {
		return valueType.isReferenceType();
	}
	
	//method
	public boolean references(final EntitySet entitySet) {
		return valueType.referencesEntitySet(entitySet.getName());
	}
}
