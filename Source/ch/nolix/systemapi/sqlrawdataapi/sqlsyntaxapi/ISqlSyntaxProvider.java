//package declaration
package ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi;

import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;

//interface
public interface ISqlSyntaxProvider {

  //method declaration
  IEntityQueryCreator getEntityQueryCreator();

  //method declaration
  IEntityStatementCreator getEntityStatementCreator();

  //method declaration
  IMultiReferenceQueryCreator getMultiReferenceQueryCreator();

  //method declaration
  IMultiValueQueryCreator getMultiValueQueryCreator();

  //method declaration
  IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator();

  //method declaration
  IMultiValueStatementCreator getMultiValueStatemeentCreator();
}
