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

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.java33.vizpres.client.presenter.MainPresenter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Is an example logic (JRE) logic test class using JUnit.
 */
public class ExampleTest {
  private final Injector injector;

  public ExampleTest() {
    // Create an injection context that mocks the concrete view dependencies
    // to be used below in the tests.
    injector = Guice.createInjector(new MockModule());
  }

  @Test
  public void testPresenterInjection() {
    final MainPresenter defaultPresenter = injector.getInstance(MainPresenter.class);
    Assert.assertNotNull("Failed to inject a DefaultPresenter instance.", defaultPresenter);
  }

  @Test(expected = NullPointerException.class)
  public void testGoWithInvalidContainer() {
    final MainPresenter defaultPresenter = injector.getInstance(MainPresenter.class);
    defaultPresenter.go(null);
    Assert.fail("Passing null to the go() method should result in an NPE.");
  }

  @Test
  public void testGoWithMockedContainer() {
    final MainPresenter defaultPresenter = injector.getInstance(MainPresenter.class);
    final HasWidgets container = injector.getInstance(HasWidgets.class);
    defaultPresenter.go(container);
  }

}
