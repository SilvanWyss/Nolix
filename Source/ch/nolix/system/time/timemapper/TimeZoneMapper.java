/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timemapper;

import java.time.ZoneOffset;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.time.main.TimeZone;
import ch.nolix.systemapi.time.timemapper.ITimeZoneMapper;

/**
 * @author Silvan Wyss
 */
public final class TimeZoneMapper implements ITimeZoneMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public TimeZone mapZoneOffsetToTimeZone(final ZoneOffset zoneOffset) { // NOSONAR: This method is not too complex.
    return //
    switch (zoneOffset.toString()) {
      case "-11:00" ->
        TimeZone.UTC_MINUS_11;
      case "-10:00" ->
        TimeZone.UTC_MINUS_10;
      case "-09:00" ->
        TimeZone.UTC_MINUS_9;
      case "-08:00" ->
        TimeZone.UTC_MINUS_8;
      case "-07:00" ->
        TimeZone.UTC_MINUS_7;
      case "-06:00" ->
        TimeZone.UTC_MINUS_6;
      case "05:00" ->
        TimeZone.UTC_MINUS_5;
      case "-04:00" ->
        TimeZone.UTC_MINUS_4;
      case "-03:00" ->
        TimeZone.UTC_MINUS_3;
      case "-02:00" ->
        TimeZone.UTC_MINUS_2;
      case "-01:00" ->
        TimeZone.UTC_MINUS_1;
      case "Z" ->
        TimeZone.UTC;
      case "+01:00" ->
        TimeZone.UTC_PLUS_1;
      case "+02:00" ->
        TimeZone.UTC_PLUS_2;
      case "+03:00" ->
        TimeZone.UTC_PLUS_3;
      case "+04:00" ->
        TimeZone.UTC_PLUS_4;
      case "+05:00" ->
        TimeZone.UTC_PLUS_5;
      case "+06:00" ->
        TimeZone.UTC_PLUS_6;
      case "+07:00" ->
        TimeZone.UTC_PLUS_7;
      case "+08:00" ->
        TimeZone.UTC_PLUS_8;
      case "+09:00" ->
        TimeZone.UTC_PLUS_9;
      case "+10:00" ->
        TimeZone.UTC_PLUS_10;
      case "+11:00" ->
        TimeZone.UTC_PLUS_11;
      case "+12:00" ->
        TimeZone.UTC_PLUS_12;
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentName(zoneOffset, "zone offset");
    };
  }
}
