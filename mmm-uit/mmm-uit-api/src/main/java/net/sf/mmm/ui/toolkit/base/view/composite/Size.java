/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.base.view.composite;

import net.sf.mmm.client.ui.api.attribute.AttributeReadSizeInPixel;

/**
 * This class is a simple container for the size of a {@link net.sf.mmm.ui.toolkit.api.view.UiElement
 * component}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class Size {

  /**
   * The horizontal size of the component.
   */
  public int width;

  /**
   * The vertical size of the component.
   */
  public int height;

  /**
   * The constructor for empty size.
   */
  public Size() {

    this(0, 0);
  }

  /**
   * The constructor.
   * 
   * @param size is a {@link AttributeReadSizeInPixel sized-object} that will be converted to a {@link Size}.
   */
  public Size(AttributeReadSizeInPixel size) {

    this(size.getWidthInPixel(), size.getHeightInPixel());
  }

  /**
   * The constructor.
   * 
   * @param w is the {@link #width}
   * @param h is the {@link #height}
   */
  public Size(int w, int h) {

    super();
    this.width = w;
    this.height = h;
  }

  /**
   * This method swaps {@link #width}/{@link #height}.
   */
  public void swap() {

    int swap = this.height;
    this.height = this.width;
    this.width = swap;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {

    return "[w=" + this.width + ",h=" + this.height + "]";
  }

}
