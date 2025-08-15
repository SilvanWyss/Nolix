package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.objectschema.model.OptionalValueModel;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;
import ch.nolix.systemapi.objectdata.modelmapper.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class OptionalValueToContentModelMapper implements IFieldToContentModelMapper<IOptionalValueField<?>> {

  @Override
  public IContentModel mapFieldToContentModel(
    final IOptionalValueField<?> field,
    final IContainer<ITable> referencedTables) {

    final var valueType = field.getValueType();
    final var dataType = DataType.forType(valueType);

    return OptionalValueModel.forDataType(dataType);
  }
}
