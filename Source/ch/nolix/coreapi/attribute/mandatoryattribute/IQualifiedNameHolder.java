package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IQualifiedNameHolder} has a qualified name. The qualified name of a
 * {@link IQualifiedNameHolder} is its name headed by its qualifying prefix.
 * 
 * @author Silvan Wyss
 * @version 2024-01-19
 */
public interface IQualifiedNameHolder extends INameHolder {
  /**
   * @return the qualified name of the current {@link IQualifiedNameHolder}.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  /**
   * @return the qualifying prefix of the current {@link IQualifiedNameHolder}.
   */
  String getQualifyingPrefix();
}
