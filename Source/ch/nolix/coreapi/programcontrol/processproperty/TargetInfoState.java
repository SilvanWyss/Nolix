package ch.nolix.coreapi.programcontrol.processproperty;

/**
 * A {@link TargetInfoState} defines whether a {@link Object} either has
 * received a target info or is waiting to a target info.
 * 
 * @author Silvan Wyss
 * @version 2020-05-03
 */
public enum TargetInfoState {
  RECEIVED_TARGET_INFO,
  WAITS_TO_TARGET_INFO
}
