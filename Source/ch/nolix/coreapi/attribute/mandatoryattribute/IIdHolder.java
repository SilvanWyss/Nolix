package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IIdHolder} has an id.
 * 
 * @author Silvan Wyss
 * @version 2019-06-10
 */
public interface IIdHolder {
  /**
   * @return the id of the current {@link IIdHolder}.
   */
  String getId();

  /**
   * @return the id of the current {@link IIdHolder} in quotes.
   */
  default String getIdInQuotes() {
    return ("'" + getId() + "'");
  }

  /**
   * @param id
   * @return true if the current {@link IIdHolder} has the given id.
   */
  default boolean hasId(final String id) {
    return getId().equals(id);
  }
}
