package ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi;

import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;

public interface ISqlSyntaxProvider {

  IEntityQueryCreator getEntityQueryCreator();

  IEntityStatementCreator getEntityStatementCreator();

  IMultiBackReferenceQueryCreator getMultiBackReferenceQueryCreator();

  IMultiBackReferenceStatementCreator getMultiBackReferenceStatemeentCreator();

  IMultiReferenceQueryCreator getMultiReferenceQueryCreator();

  IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator();

  IMultiValueQueryCreator getMultiValueQueryCreator();

  IMultiValueStatementCreator getMultiValueStatemeentCreator();
}
