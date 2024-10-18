package ch.nolix.coreapi.netapi.targetapi;

public interface IAuthenticationServerTarget extends IServerTarget {

  String getLoginName();

  String getLoginPassword();
}
