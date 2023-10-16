//package declaration
package ch.nolix.coreapi.netapi.netproperty;

//enum
public enum ConnectionType {
  LOCAL(BaseConnectionType.LOCAL),
  SOCKET(BaseConnectionType.NET),
  WEB_SOCKET(BaseConnectionType.NET);

  //attribute
  private final BaseConnectionType baseType;

  //constructor
  ConnectionType(final BaseConnectionType baseType) {
    this.baseType = baseType;
  }

  //method
  public final BaseConnectionType getBaseType() {
    return baseType;
  }
}
