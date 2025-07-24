package ch.nolix.system.objectdata.fieldtocontentmodelmapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.system.objectschema.model.OptionalValueModel;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IFieldTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;
import ch.nolix.systemapi.objectdataapi.modelmapperapi.IFieldToContentModelMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class OptionalValueToContentModelMapper implements IFieldToContentModelMapper<IOptionalValueField<?>> {

  private static final IFieldTool FIELD_TOOL = new FieldTool();

  @Override
  public IContentModel mapFieldToContentModel(
    final IOptionalValueField<?> field,
    final IContainer<ITable> referencedTables) {

    final var dataType = DataType.forType(FIELD_TOOL.getDataType(field));

    return OptionalValueModel.forDataType(dataType);
  }
}
