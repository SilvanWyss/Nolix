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
  ZoneOffset mapTimeZoneToZoneOffset(TimeZone timeZone);
}
