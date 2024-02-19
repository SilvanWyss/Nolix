//package declaration
package ch.nolix.system.sqlrawdata.sqlsyntax;

//own imports
import ch.nolix.system.sqlrawdata.querycreator.EntityQueryCreator;
import ch.nolix.system.sqlrawdata.querycreator.MultiBackReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.querycreator.MultiReferenceQueryCreator;
import ch.nolix.system.sqlrawdata.querycreator.MultiValueQueryCreator;
import ch.nolix.system.sqlrawdata.statementcreator.EntityStatementCreator;
import ch.nolix.system.sqlrawdata.statementcreator.MultiBackReferenceStatementCreator;
import ch.nolix.system.sqlrawdata.statementcreator.MultiReferenceStatementCreator;
import ch.nolix.system.sqlrawdata.statementcreator.MultiValueStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi.ISqlSyntaxProvider;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;

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

  //constant
  private static final IMultiBackReferenceStatementCreator MULTI_BACK_REFERENCE_STATEMENT_CREATOR = //
  new MultiBackReferenceStatementCreator();

  //constant
  private static final IMultiBackReferenceQueryCreator MULTI_BACK_REFERENCE_QUERY_CREATOR = //
  new MultiBackReferenceQueryCreator();

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

  @Override
  public IMultiBackReferenceQueryCreator getMultiBackReferenceQueryCreator() {
    return MULTI_BACK_REFERENCE_QUERY_CREATOR;
  }

  //method
  @Override
  public IMultiBackReferenceStatementCreator getMultiBackReferenceStatemeentCreator() {
    return MULTI_BACK_REFERENCE_STATEMENT_CREATOR;
  }

  //method
  @Override
  public IMultiReferenceQueryCreator getMultiReferenceQueryCreator() {
    return MULTI_REFERENCE_QUERY_CREATOR;
  }

  //method
  @Override
  public IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator() {
    return MULTI_REFERENCE_STATEMENT_CREATOR;
  }

  //method
  @Override
  public IMultiValueQueryCreator getMultiValueQueryCreator() {
    return MULTI_VALUE_QUERY_CREATOR;
  }

  //method
  @Override
  public IMultiValueStatementCreator getMultiValueStatemeentCreator() {
    return MULTI_VALUE_STATEMENT_CREATOR;
  }
}
