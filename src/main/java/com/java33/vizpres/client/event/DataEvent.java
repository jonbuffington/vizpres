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

package com.java33.vizpres.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.java33.vizpres.client.view.visualization.IsDataSync;

/**
 * Is a a custom event used to signal data needs to be requested.
 *
 * @author Jon Buffington
 */
public class DataEvent extends GwtEvent<DataEvent.DataEventHandler> {
  public interface DataEventHandler extends EventHandler {
    void onData(DataEvent event, IsDataSync dataSync);
  }

  public static Type<DataEventHandler> TYPE = new Type<DataEventHandler>();

  public IsDataSync target;

  @Override
  public Type<DataEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(final DataEventHandler handler) {
    handler.onData(this, target);
  }
}
