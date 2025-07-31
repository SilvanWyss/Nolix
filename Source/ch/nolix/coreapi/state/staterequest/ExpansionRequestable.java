package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link ExpansionRequestable} can be asked if it is expanded or collapsed.
 * 
 * @author Silvan Wyss
 * @version 2020-10-02
 */
public interface ExpansionRequestable {

  /**
   * @return true if the current {@link ExpansionRequestable} is collapsed.
   */
  default boolean isCollapsed() {
    return !isExpanded();
  }

  /**
   * @return true if the current {@link ExpansionRequestable} is expanded.
   */
  boolean isExpanded();
}
