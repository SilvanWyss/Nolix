package ch.nolix.system.sqlrawdata.sqlsyntax;

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

public final class SqlSyntaxProvider implements ISqlSyntaxProvider {

  private static final IEntityQueryCreator ENTITY_QUERY_CREATOR = new EntityQueryCreator();

  private static final IEntityStatementCreator ENTITY_STATEMENT_CREATOR = new EntityStatementCreator();

  private static final IMultiValueQueryCreator MULTI_VALUE_QUERY_CREATOR = new MultiValueQueryCreator();

  private static final IMultiValueStatementCreator MULTI_VALUE_STATEMENT_CREATOR = new MultiValueStatementCreator();

  private static final IMultiReferenceQueryCreator MULTI_REFERENCE_QUERY_CREATOR = new MultiReferenceQueryCreator();

  private static final IMultiReferenceStatementCreator MULTI_REFERENCE_STATEMENT_CREATOR = //
  new MultiReferenceStatementCreator();

  private static final IMultiBackReferenceStatementCreator MULTI_BACK_REFERENCE_STATEMENT_CREATOR = //
  new MultiBackReferenceStatementCreator();

  private static final IMultiBackReferenceQueryCreator MULTI_BACK_REFERENCE_QUERY_CREATOR = //
  new MultiBackReferenceQueryCreator();

  @Override
  public IEntityQueryCreator getEntityQueryCreator() {
    return ENTITY_QUERY_CREATOR;
  }

  @Override
  public IEntityStatementCreator getEntityStatementCreator() {
    return ENTITY_STATEMENT_CREATOR;
  }

  @Override
  public IMultiBackReferenceQueryCreator getMultiBackReferenceQueryCreator() {
    return MULTI_BACK_REFERENCE_QUERY_CREATOR;
  }

  @Override
  public IMultiBackReferenceStatementCreator getMultiBackReferenceStatemeentCreator() {
    return MULTI_BACK_REFERENCE_STATEMENT_CREATOR;
  }

  @Override
  public IMultiReferenceQueryCreator getMultiReferenceQueryCreator() {
    return MULTI_REFERENCE_QUERY_CREATOR;
  }

  @Override
  public IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator() {
    return MULTI_REFERENCE_STATEMENT_CREATOR;
  }

  @Override
  public IMultiValueQueryCreator getMultiValueQueryCreator() {
    return MULTI_VALUE_QUERY_CREATOR;
  }

  @Override
  public IMultiValueStatementCreator getMultiValueStatemeentCreator() {
    return MULTI_VALUE_STATEMENT_CREATOR;
  }
}
