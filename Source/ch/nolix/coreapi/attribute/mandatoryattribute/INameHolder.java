package ch.nolix.coreapi.attribute.mandatoryattribute;

import ch.nolix.coreapi.structure.typemarker.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link INameHolder} has a name.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface INameHolder {

  /**
   * @return the name of the current {@link INameHolder}.
   */
  String getName();

  /**
   * @return the name of the current {@link INameHolder} in quotes.
   */
  default String getNameInQuotes() {
    return ("'" + getName() + "'");
  }

  /**
   * @param name
   * @return true if the current {@link INameHolder} has the given name.
   */
  default boolean hasName(final String name) {
    return getName().equals(name);
  }

  /**
   * @param nameHolder
   * @return true if the current {@link INameHolder} has the same name as the
   *         given nameHolder.
   */
  default boolean hasSameNameAs(final INameHolder nameHolder) {

    //Handles the case that the given nameHolder is null.
    if (nameHolder == null) {
      return false;
    }

    //Handles the case that the given nameHolder is not null.
    return hasName(nameHolder.getName());
  }
}
