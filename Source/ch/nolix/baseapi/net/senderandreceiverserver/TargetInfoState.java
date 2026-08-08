/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.senderandreceiverserver;

/**
 * A {@link TargetInfoState} defines whether a {@link Object} either has
 * received a target info or is waiting to a target info.
 * 
 * @author Silvan Wyss
 */
public enum TargetInfoState {
  WAITING_TO_TARGET_INFO,
  RECEIVED_TARGET_INFO
}
