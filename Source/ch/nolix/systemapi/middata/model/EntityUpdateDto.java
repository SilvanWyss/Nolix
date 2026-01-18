/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

import ch.nolix.coreapi.container.base.IContainer;

public record EntityUpdateDto(
String id,
String saveStamp,
IContainer<ValueStringFieldDto> updatedContentFields) {
}
