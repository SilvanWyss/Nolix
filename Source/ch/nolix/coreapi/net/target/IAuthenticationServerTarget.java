package ch.nolix.coreapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface IAuthenticationServerTarget extends IServerTarget {
  String getLoginName();

  String getLoginPassword();
}
