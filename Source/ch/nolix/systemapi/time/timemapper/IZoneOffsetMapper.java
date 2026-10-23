/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.time.timemapper;

import java.time.ZoneOffset;

import ch.nolix.systemapi.time.main.TimeZone;

/**
 * @author Silvan Wyss
 */
public interface IZoneOffsetMapper {
  /**
   * @param timeZone
   * @return the {@link ZoneOffset} for the given timeZone
   */
  ZoneOffset mapTimeZoneToZoneOffset(TimeZone timeZone);
}
