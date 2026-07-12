/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.time.moment;

import java.time.ZoneOffset;

import ch.nolix.systemapi.time.timestructure.TimeZone;

/**
 * @author Silvan Wyss
 */
public interface IZoneOffsetMapper {
  ZoneOffset mapTimeZoneToZoneOffset(TimeZone timeZone);
}
