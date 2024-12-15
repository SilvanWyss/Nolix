package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.schemadto.BaseReferenceModelDto;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IAbstractValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;

public abstract class AbstractReferenceModel implements IAbstractReferenceModel {

  private static final DataType DATA_TYPE = DataType.STRING;

  private final ImmutableList<ITable> referencedTables;

  protected AbstractReferenceModel(final IContainer<ITable> referencedTables) {
    this.referencedTables = ImmutableList.forIterable(referencedTables);
  }

  @Override
  public final IAbstractBackReferenceModel asBaseParameterizedBackReferenceType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedBackReferenceType");
  }

  @Override
  public final IAbstractReferenceModel asBaseParameterizedReferenceType() {
    return this;
  }

  @Override
  public final IAbstractValueModel<?> asBaseParameterizedValueType() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParameterizedValueType");
  }

  @Override
  public final DataType getDataType() {
    return DATA_TYPE;
  }

  @Override
  public final IContainer<ITable> getReferencedTables() {
    return referencedTables;
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return getReferencedTables().contains(table);
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return false;
  }

  @Override
  public final IContentModelDto toDto() {

    final var referencedTableIds = getReferencedTables().to(ITable::getId);

    return new BaseReferenceModelDto(
      getContentType(),
      getDataType(),

      //TODO: Handle all referenced Tables
      referencedTableIds.getStoredFirst());
  }
}
