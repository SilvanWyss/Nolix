package ch.nolix.coreapi.math.number;

/**
 * The {@link IntCatalog} provides int constants.
 * 
 * Of the {@link IntCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class IntCatalog {
  public static final int THOUSAND = 1_000;

  public static final int MILLION = 1_000_000;

  public static final int BILLION = 1_000_000_000;

  /**
   * Prevents that an instance of the {@link IntCatalog} can be created.
   */
  private IntCatalog() {
  }
}
