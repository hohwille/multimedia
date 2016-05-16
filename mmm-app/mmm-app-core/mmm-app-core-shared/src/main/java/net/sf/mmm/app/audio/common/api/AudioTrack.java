/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.app.audio.common.api;

import java.util.Locale;
import java.util.Set;

import net.sf.mmm.app.general.common.api.MmmEntity;
import net.sf.mmm.music.datatype.api.MusicalKey;

/**
 * This interface is an {@link MmmEntity entity} that represents an audio track.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface AudioTrack extends MmmEntity {

  /**
   * This method gets the (main) {@link MusicalKey} of this audio track.
   *
   * @return the {@link MusicalKey} or <code>null</code> if undefined (unknown, no music at all, not diatonic, etc.)
   */
  MusicalKey getKey();

  /**
   * This method gets the {@link Locale} representing the primary language of this audio track (for the lyrics).
   *
   * @return the {@link Locale} representing the primary language or <code>null</code> for an audio track without human
   *         language (e.g. an instrumental). The default is {@link Locale#ROOT} indicating that the language is unknown
   *         or artificial.
   */
  Locale getPrimaryLanguage();

  /**
   * This method gets the {@link Locale} representing the secondary language of this audio track. E.g. for the song
   * "Time to say goodbye" from "Andrea Bocelli" the {@link #getPrimaryLanguage() primary language} would be Italian
   * ("it"), and the {@link #getSecondaryLanguage() secondary language} English ("en").
   *
   * @return the {@link Locale} representing the secondary language or <code>null</code> if there is no secondary
   *         language involved (default).
   */
  Locale getSecondaryLanguage();

  /**
   * This method gets the tags associated with this audio track. A <em>tag</em> is an arbitrary {@link String}
   * representing some attribute of the track. It is recommended to use English and well established terms as tags.
   * However, you can use whatever fits for your personal interest. Examples for tags are "religious", "fun", "sad",
   * etc.<br/>
   * <b>NOTE:</b><br/>
   * Please avoid adding tags that are redundant to explicit fields of an {@link AudioTrack audio track} like
   * {@link #getGenre() genre}, {@link #getPrimaryLanguage() language}, instrumental, etc.
   *
   * @return the {@link Set} of tags. Empty {@link Set} by default.
   */
  Set<String> getTags();

  /**
   * @return the {@link AudioArtist}.
   */
  AudioArtist getArtist();

  /**
   * @param artist is the new value of {@link #getArtist()}.
   */
  void setArtist(AudioArtist artist);

  /**
   * @return the {@link AudioGenre}.
   */
  AudioGenre getGenre();

  /**
   * @param genre is the new value of {@link #getGenre()}.
   */
  void setGenre(AudioGenre genre);

}
