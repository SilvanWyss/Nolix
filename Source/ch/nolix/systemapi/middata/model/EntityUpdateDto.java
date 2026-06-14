/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

public record EntityUpdateDto(
String id,
String saveStamp,
IWellOrderContainer<ValueStringFieldDto> updatedContentFields) {
}
