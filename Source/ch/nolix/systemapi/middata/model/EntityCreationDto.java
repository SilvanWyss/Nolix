/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

public record EntityCreationDto(String id, IWellOrderContainer<ValueStringFieldDto> contentFields) {
}
