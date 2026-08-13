/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.time.timemapper;

import java.time.ZoneOffset;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.time.main.TimeZone;
import ch.nolix.systemapi.time.timemapper.IZoneOffsetMapper;

/**
 * @author Silvan Wyss
 */
public final class ZoneOffsetMapper implements IZoneOffsetMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public ZoneOffset mapTimeZoneToZoneOffset(final TimeZone timeZone) { // NOSONAR: This method is not too complex.
    return //
    switch (timeZone) {
      case UTC_MINUS_11 ->
        ZoneOffset.ofHours(-11);
      case UTC_MINUS_10 ->
        ZoneOffset.ofHours(-10);
      case UTC_MINUS_9 ->
        ZoneOffset.ofHours(-9);
      case UTC_MINUS_8 ->
        ZoneOffset.ofHours(-8);
      case UTC_MINUS_7 ->
        ZoneOffset.ofHours(-7);
      case UTC_MINUS_6 ->
        ZoneOffset.ofHours(-6);
      case UTC_MINUS_5 ->
        ZoneOffset.ofHours(-5);
      case UTC_MINUS_4 ->
        ZoneOffset.ofHours(-4);
      case UTC_MINUS_3 ->
        ZoneOffset.ofHours(-3);
      case UTC_MINUS_2 ->
        ZoneOffset.ofHours(-2);
      case UTC_MINUS_1 ->
        ZoneOffset.ofHours(-1);
      case UTC ->
        ZoneOffset.ofHours(0);
      case UTC_PLUS_1 ->
        ZoneOffset.ofHours(1);
      case UTC_PLUS_2 ->
        ZoneOffset.ofHours(2);
      case UTC_PLUS_3 ->
        ZoneOffset.ofHours(3);
      case UTC_PLUS_4 ->
        ZoneOffset.ofHours(4);
      case UTC_PLUS_5 ->
        ZoneOffset.ofHours(5);
      case UTC_PLUS_6 ->
        ZoneOffset.ofHours(6);
      case UTC_PLUS_7 ->
        ZoneOffset.ofHours(7);
      case UTC_PLUS_8 ->
        ZoneOffset.ofHours(8);
      case UTC_PLUS_9 ->
        ZoneOffset.ofHours(9);
      case UTC_PLUS_10 ->
        ZoneOffset.ofHours(10);
      case UTC_PLUS_11 ->
        ZoneOffset.ofHours(11);
      case UTC_PLUS_12 ->
        ZoneOffset.ofHours(12);
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentType(timeZone, TimeZone.class);
    };
  }
}
