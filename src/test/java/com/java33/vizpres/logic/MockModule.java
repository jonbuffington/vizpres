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

package com.java33.vizpres.logic;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.AbstractModule;
import com.java33.vizpres.client.presenter.MainPresenter;
import org.mockito.Mockito;

/**
 * Is a Guice/GIN module used by logic tests to mock concrete view dependencies.
 */
public class MockModule extends AbstractModule {
  @Override
  protected void configure() {
    // Support injecting the HandlerManager into the Application scope.
    final EventBus mockedEventBus = Mockito.mock(EventBus.class);
    bind(EventBus.class).toInstance(mockedEventBus);

    // Mock the container that holds the target's views.
    final HasWidgets mockedContainer = Mockito.mock(HasWidgets.class);
    bind(HasWidgets.class).toInstance(mockedContainer);

    // Mock the target's view.
    final MainPresenter.Display mockedView = Mockito.mock(MainPresenter.Display.class);
    final HasClickHandlers mockButton = Mockito.mock(HasClickHandlers.class);
    Mockito.when(mockedView.getRefreshButton()).thenReturn(mockButton);

    // Bind the target's display to an instance of the mocked view.
    bind(MainPresenter.Display.class).toInstance(mockedView);
  }

}
