/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.app.audio.common.api;

import net.sf.mmm.app.general.common.api.EntityReference;
import net.sf.mmm.app.general.common.api.MmmEntity;

/**
 * This is the interface for an {@link MmmEntity entity} that represents the {@link AudioTrack#getArtist() artist} of an
 * {@link AudioTrack}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface AudioArtist extends MmmEntity {

  /**
   * @return the display name of the artist. Examples are "The Beatles" or "Cat Stevens".
   */
  String getName();

  /**
   * @param name is the new value of {@link #getName()}.
   */
  void setName(String name);

  // /**
  // * This method gets the members of this {@link AudioArtist}. This can be an entire list of persons if this
  // * {@link AudioArtist} represents a group (typically called a band). Each member can be
  // * {@link net.sf.mmm.data.api.link.Link#getClassifier() classified} with his role (e.g. "drums", "piano",
  // "keyboard",
  // * "guitar", "trumpet") if desired. If this is NOT a group, the {@link net.sf.mmm.data.api.link.LinkList} should
  // * contain a single {@link net.sf.mmm.data.api.link.Link} with no
  // * {@link net.sf.mmm.data.api.link.Link#getClassifier() classifier}.
  // *
  // * @return the members.
  // */
  // MutableLinkList<DataPerson> getMembers();

  /**
   * This method gets the default {@link AudioGenre} of this artist. This is used to initialize or suggest the
   * {@link AudioTrack#getGenre() genre} of individual {@link AudioTrack}s of this artist.
   *
   * @return the default genre, or <code>null</code> if undefined.
   */
  EntityReference<AudioGenre> getDefaultGenre();

  /**
   * @param genre is the new value of {@link #getDefaultGenre()}.
   */
  void setDefaultGenre(EntityReference<AudioGenre> genre);

}
