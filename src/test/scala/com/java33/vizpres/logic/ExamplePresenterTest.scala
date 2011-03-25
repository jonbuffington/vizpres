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

package com.java33.vizpres.logic

/*
 * Copyright (c) 2011 Jon Buffington. All rights reserved.
 *
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any damages
 * arising from the use of this software. Permission is granted to anyone to
 * use this software for any purpose, including commercial applications, and to
 * alter it and redistribute it freely, subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 * 3. This notice may not be removed or altered from any source
 *    distribution.
 */

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import com.google.inject.Guice
import com.java33.vizpres.client.presenter.MainPresenter
import com.google.gwt.user.client.ui.HasWidgets


/**
 * Is an example logic (JRE) logic test class using ScalaTest.
 */
@RunWith(classOf[JUnitRunner])
class ExamplePresenterTest extends FunSuite with ShouldMatchers {
  // Create an injection context that mocks the concrete view dependencies
  // to be used below in the tests.
  protected val injector = Guice.createInjector(new MockModule)

  test("example presenter was injected") {
    val defaultPresenter = injector.getInstance(classOf[MainPresenter]);
    defaultPresenter should not be(null)
  }

  test("presenter failed when passed a null container") {
    val defaultPresenter = injector.getInstance(classOf[MainPresenter]);
    evaluating { defaultPresenter.go(null) } should produce[NullPointerException]
  }

  test("presenter goes with mocked container") {
    val defaultPresenter = injector.getInstance(classOf[MainPresenter]);
    val container = injector.getInstance(classOf[HasWidgets])
    defaultPresenter.go(container)
  }
}
