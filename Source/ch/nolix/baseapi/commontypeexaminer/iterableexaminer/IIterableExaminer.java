/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontypeexaminer.iterableexaminer;

/**
 * @author Silvan Wyss
 */
public interface IIterableExaminer
extends
IIterableContainExaminer,
IIterableContainMatchingExaminer,
IIterableContainMultipleObjectExaminer,
IIterableContainObjectExaminer {
  //This interface is a dedicated union of other interfaces.
}
