
/*
 * Copyright (c) 2011 Jon Buffington. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.java33.vizpres.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.java33.vizpres.client.event.DataEvent;
import com.java33.vizpres.client.view.visualization.IsDataSync;

import java.util.logging.Logger;

/**
 * Is the presenter for the primary display.
 *
 * @author Jon Buffington
 */
public class MainPresenter implements Presenter {
  private static final Logger logger = Logger.getLogger(MainPresenter.class.getName());

  public interface Display extends IsWidget, IsDataSync {
    HasClickHandlers getRefreshButton();
  }

  private final EventBus eventBus;
  private final Display  display;

  @Inject
  public MainPresenter(final EventBus eventBus, final Display view) {
    this.eventBus = eventBus;
    display = view;
  }

  /**
   * Binds the refresh button click into a data request bus event.
   */
  protected void bind() {
    display.getRefreshButton().addClickHandler(new ClickHandler() {
      public void onClick(final ClickEvent event) {
        logger.fine("Request data from server using the event bus.");
        final DataEvent dataEvent = GWT.create(DataEvent.class);
        dataEvent.target = display;
        eventBus.fireEvent(dataEvent);
      }
    });
  }

  public void go(final HasWidgets container) {
    bind();
    container.clear();
    container.add(display.asWidget());
  }

  /**
   * Get the target's view instance. This method is primarily used by tests.
   *
   * @return Return the target's view.
   */
  public Display getDisplay() {
    return display;
  }
}
