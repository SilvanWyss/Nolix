package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;

public interface IApplication<AS> {

  IApplicationInstanceTarget asTarget();

  boolean belongsToServer();

  String getApplicationName();

  String getInstanceName();

  String getNameAddendum();

  AS getStoredApplicationService();

  String getUrlInstanceName();

  boolean hasNameAddendum();
}
