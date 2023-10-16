//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

//own imports
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueStatementCreator;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.ISqlSyntaxProvider;

//class
public final class SqlSyntaxProvider implements ISqlSyntaxProvider {

  //constant
  private static final IEntityQueryCreator ENTITY_QUERY_CREATOR = new EntityQueryCreator();

  //constant
  private static final IEntityStatementCreator ENTITY_STATEMENT_CREATOR = new EntityStatementCreator();

  //constant
  private static final IMultiValueQueryCreator MULTI_VALUE_QUERY_CREATOR = new MultiValueQueryCreator();

  //constant
  private static final IMultiValueStatementCreator MULTI_VALUE_STATEMENT_CREATOR = new MultiValueStatementCreator();

  //constant
  private static final IMultiReferenceQueryCreator MULTI_REFERENCE_QUERY_CREATOR = new MultiReferenceQueryCreator();

  //constant
  private static final IMultiReferenceStatementCreator MULTI_REFERENCE_STATEMENT_CREATOR = //
      new MultiReferenceStatementCreator();

  //method
  @Override
  public IMultiReferenceQueryCreator getMultiReferenceQueryCreator() {
    return MULTI_REFERENCE_QUERY_CREATOR;
  }

  //method
  @Override
  public IMultiValueQueryCreator getMultiValueQueryCreator() {
    return MULTI_VALUE_QUERY_CREATOR;
  }

  //method
  @Override
  public IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator() {
    return MULTI_REFERENCE_STATEMENT_CREATOR;
  }

  //method
  @Override
  public IMultiValueStatementCreator getMultiValueStatemeentCreator() {
    return MULTI_VALUE_STATEMENT_CREATOR;
  }

  //method
  @Override
  public IEntityQueryCreator getEntityQueryCreator() {
    return ENTITY_QUERY_CREATOR;
  }

  //method
  @Override
  public IEntityStatementCreator getEntityStatementCreator() {
    return ENTITY_STATEMENT_CREATOR;
  }
}
