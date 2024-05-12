//package declaration
package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;

//interface
public interface IApplication<AC> {

  //method declaration
  IApplicationInstanceTarget asTarget();

  //method declaration
  boolean belongsToServer();

  //method declaration
  String getApplicationName();

  //method declaration
  String getInstanceName();

  //method declaration
  String getNameAddendum();

  //method declaration
  AC getStoredApplicationContext();

  //method declaration
  String getUrlInstanceName();

  //method declaration
  boolean hasNameAddendum();
}
