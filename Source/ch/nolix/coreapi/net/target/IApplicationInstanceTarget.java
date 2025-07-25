package ch.nolix.coreapi.net.target;

public interface IApplicationInstanceTarget extends IServerTarget {

  String getApplicationInstanceName();

  String getApplicationUrlInstanceName();
}
