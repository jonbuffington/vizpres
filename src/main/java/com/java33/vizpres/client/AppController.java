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

package com.java33.vizpres.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.java33.vizpres.client.event.DataEvent;
import com.java33.vizpres.client.gin.AppInjector;
import com.java33.vizpres.client.presenter.Presenter;
import com.java33.vizpres.client.view.visualization.IsDataSync;
import com.java33.vizpres.shared.service.PercentServiceAsync;
import com.java33.vizpres.shared.model.PercentValue;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import java.util.List;
import java.util.logging.Logger;

/**
 * Coordinate the application's various presenters using GWT's History support.
 * The new Activity and Places support in GWT 2.1 would be a better choice for a
 * larger application.
 *
 * @author Jon Buffington
 */
public class AppController implements Presenter, ValueChangeHandler<String> {
  private static final Logger logger = Logger.getLogger(AppController.class.getName());

  private static final String DEFAULT_TOKEN = "default";
  private final EventBus    eventBus;
  private final AppInjector injector;
  private       HasWidgets  container;

  @Inject
  public AppController(final EventBus eventBus, final AppInjector injector) {
    this.eventBus = eventBus;
    this.injector = injector;
    bind();
  }

  /**
   * Bind handlers to the application's events.
   */
  private void bind() {
    History.addValueChangeHandler(this);

    eventBus.addHandler(DataEvent.TYPE, new DataEvent.DataEventHandler() {
      public void onData(final DataEvent event, final IsDataSync dataSync) {
        doRequestData(dataSync);
      }
    });

  }

  /**
   * Performs the request for data and asynchronously receives the response or failure notice.
   * A response's data is pushed to the dataSync.
   *
   * @param dataSync Is the receiver of a data response's payload.
   */
  private void doRequestData(final IsDataSync dataSync) {
    final Resource resource = new Resource("/dataset");
    final PercentServiceAsync percentServiceAsync = GWT.create(PercentServiceAsync.class);
    ((RestServiceProxy) percentServiceAsync).setResource(resource);
    percentServiceAsync.getPercents(new MethodCallback<List<PercentValue>>() {
      @Override
      public void onFailure(final Method method, final Throwable throwable) {
        logger.warning(throwable.toString());
      }

      @Override
      public void onSuccess(final Method method, final List<PercentValue> values) {
        logger.fine(values.toString());
        dataSync.setData(values);
      }
    });

//    History.newItem(EXAMPLE_TOKEN);
  }

  /**
   * Capture the desired container and pump the history value.
   */
  public void go(final HasWidgets container) {
    this.container = container;

    if ("".equals(History.getToken())) {
      History.newItem(DEFAULT_TOKEN);
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  /**
   * Delegate the application state handling to the appropriate target.
   */
  public void onValueChange(final ValueChangeEvent<String> event) {
    final String token = event.getValue();

    if (token != null) {
      Presenter presenter = null;

      if (token.equals(DEFAULT_TOKEN)) {
        presenter = injector.getMainPresenter();
      }

      if (presenter != null) {
        // Pass the container to the current target.
        presenter.go(container);
      }
    }
  }
}
