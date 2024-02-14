//package declaration
package ch.nolix.systemapi.rawdataapi.sqlsyntaxapi;

import ch.nolix.systemapi.rawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.rawdataapi.querycreatorapi.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.rawdataapi.querycreatorapi.IMultiValueQueryCreator;
import ch.nolix.systemapi.rawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.rawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.rawdataapi.statementcreatorapi.IMultiValueStatementCreator;

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
