package ch.nolix.coreapi.netapi.targetapi;

public interface IApplicationInstanceTarget extends IServerTarget {

  String getApplicationInstanceName();

  String getApplicationUrlInstanceName();
}
