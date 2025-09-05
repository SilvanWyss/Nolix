package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.objectschema.model.ValueModel;
import ch.nolix.systemapi.objectdata.model.IValueField;
import ch.nolix.systemapi.objectdata.modelmapper.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ValueToContentModelMapper implements IFieldToContentModelMapper<IValueField<?>> {
  @Override
  public IContentModel mapFieldToContentModel(
    final IValueField<?> field,
    final IContainer<ITable> referencedTables) {
    final var valueType = field.getValueType();
    final var dataType = DataType.forType(valueType);

    return ValueModel.forDataType(dataType);
  }
}
