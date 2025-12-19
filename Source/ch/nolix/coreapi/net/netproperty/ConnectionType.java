package ch.nolix.coreapi.net.netproperty;

/**
 * @author Silvan Wyss
 */
public enum ConnectionType {
  LOCAL(BaseConnectionType.LOCAL),
  SOCKET(BaseConnectionType.NET),
  WEB_SOCKET(BaseConnectionType.NET);

  private final BaseConnectionType baseType;

  ConnectionType(final BaseConnectionType baseType) {
    this.baseType = baseType;
  }

  public BaseConnectionType getBaseType() {
    return baseType;
  }
}
