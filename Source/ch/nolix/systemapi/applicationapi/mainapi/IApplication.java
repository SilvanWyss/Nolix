package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;

public interface IApplication<AC> {

  IApplicationInstanceTarget asTarget();

  boolean belongsToServer();

  String getApplicationName();

  String getInstanceName();

  String getNameAddendum();

  AC getStoredApplicationContext();

  String getUrlInstanceName();

  boolean hasNameAddendum();
}
