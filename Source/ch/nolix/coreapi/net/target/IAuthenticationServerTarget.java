package ch.nolix.coreapi.net.target;

public interface IAuthenticationServerTarget extends IServerTarget {

  String getLoginName();

  String getLoginPassword();
}
