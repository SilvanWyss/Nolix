//package declaration
package ch.nolix.system.database.databaseadapter;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.parametrizeddatatype.ParametrizedDataType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class Column<C> implements Named {
	
	//attributes
	private final String name;
	private final ParametrizedDataType<C> parametrizedDataType;
	
	//constructor
	public Column(final String name, final ParametrizedDataType<C> dataType) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(dataType).isOfType(ParametrizedDataType.class);
		
		this.name = name;
		this.parametrizedDataType = dataType;
	}
	
	//method
	public boolean canReference(final Entity entity) {
		return parametrizedDataType.canReference(entity);
	}
	
	//method
	public <E extends Entity> boolean canReferenceBackEntityOfType(final Class<E> type) {
		return (parametrizedDataType.isAnyBackReferenceType() && parametrizedDataType.getRefContentClass() == type);
	}
	
	//method
	public <E extends Entity> boolean canReferenceEntityOfType(final Class<E> type) {
		return
		getDataType().getBaseType() == BasePropertyType.BASE_REFERENCE
		&& parametrizedDataType.getRefContentClass() == type;
	}
	
	//method
	public PropertyType getDataType() {
		return parametrizedDataType.getPropertyKind();
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	public ParametrizedDataType<C> getParametrizedDataType() {
		return parametrizedDataType;
	}
	
	//method
	public Class<C> getRefContentClass() {
		return parametrizedDataType.getRefContentClass();
	}
	
	//method
	public ch.nolix.system.objectschema.databaseschemaadapter.Column toSchemaColumn(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return
		new ch.nolix.system.objectschema.databaseschemaadapter.Column(getName(), getSchemaDataType(schemaEntitySets));
	}
	
	//method
	private ParametrizedPropertyType<?> getSchemaDataType(
		final IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> schemaEntitySets
	) {
		return parametrizedDataType.toSchemaDataType(schemaEntitySets);
	}
}
