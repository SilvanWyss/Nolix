/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.json;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 */
public interface JsonNumber extends JsonValue {
  /**
   * @return the number of the current {@link JsonNumber}
   */
  BigDecimal getNumber();
}
