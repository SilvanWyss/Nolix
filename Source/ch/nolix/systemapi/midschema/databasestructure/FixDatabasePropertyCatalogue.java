package ch.nolix.systemapi.midschema.databasestructure;

/**
 * Of the {@link FixDatabasePropertyCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class FixDatabasePropertyCatalogue {
  public static final int NUMBER_OF_ENTITY_META_FIELDS;

  static {
    NUMBER_OF_ENTITY_META_FIELDS = EntityMetaField.values().length;
  }

  /**
   * Prevents that an instance of the {@link FixDatabasePropertyCatalogue} can be
   * created.
   */
  private FixDatabasePropertyCatalogue() {
  }
}
