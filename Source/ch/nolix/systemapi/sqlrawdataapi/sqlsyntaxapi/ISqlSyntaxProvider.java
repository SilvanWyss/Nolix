//package declaration
package ch.nolix.systemapi.sqlrawdataapi.sqlsyntaxapi;

//own imports
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;

//interface
public interface ISqlSyntaxProvider {

  //method declaration
  IEntityQueryCreator getEntityQueryCreator();

  //method declaration
  IEntityStatementCreator getEntityStatementCreator();

  //method declaration
  IMultiBackReferenceStatementCreator getMultiBackReferenceStatemeentCreator();

  //method declaration
  IMultiReferenceQueryCreator getMultiReferenceQueryCreator();

  //method declaration
  IMultiReferenceStatementCreator getMultiReferenceStatemeentCreator();

  //method declaration
  IMultiValueQueryCreator getMultiValueQueryCreator();

  //method declaration
  IMultiValueStatementCreator getMultiValueStatemeentCreator();
}
