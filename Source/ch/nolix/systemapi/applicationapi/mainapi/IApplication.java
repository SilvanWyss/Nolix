package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;

public interface IApplication<S> {

  IApplicationInstanceTarget asTarget();

  boolean belongsToServer();

  String getApplicationName();

  String getInstanceName();

  String getNameAddendum();

  S getStoredApplicationService();

  String getUrlInstanceName();

  boolean hasNameAddendum();
}
