package ch.nolix.coreapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface IApplicationInstanceTarget extends IServerTarget {
  String getApplicationInstanceName();

  String getApplicationUrlInstanceName();
}
