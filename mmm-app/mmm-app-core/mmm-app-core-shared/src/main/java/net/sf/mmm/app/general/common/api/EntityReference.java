/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.app.general.common.api;

import net.sf.mmm.util.lang.api.AbstractSimpleDatatype;

/**
 * The datatype for a reference on an {@link MmmEntity entity} via its {@link MmmEntity#getId() primary key}.
 *
 * @param <E> is the generic type of the referenced {@link MmmEntity entity}.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class EntityReference<E extends MmmEntity> extends AbstractSimpleDatatype<Long> {

  private static final long serialVersionUID = 1L;

  /**
   * The constructor for de-serialization in GWT.
   */
  protected EntityReference() {

    super();
  }

  /**
   * The constructor.
   *
   * @param value is the ID of the referenced entity.
   */
  public EntityReference(Long value) {

    super(value);
  }

  /**
   * Creates a new {@link EntityReference} for the given {@code entity}.
   *
   * @param <E> is the generic type of the {@link MmmEntity entity}.
   * @param entity is the entity to create the reference for.
   * @return the {@link EntityReference} for the given {@code entity}. Will be {@code null} if {@code entity} was
   *         {@code null}.
   */
  public static <E extends MmmEntity> EntityReference<E> valueOf(MmmEntity entity) {

    if (entity == null) {
      return null;
    }
    return new EntityReference<>(entity.getId());
  }

}
