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

package com.java33.vizpres.shared.service;

import com.java33.vizpres.shared.model.PercentValue;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Could be the most complex ReST-based service interface to date. No. Maybe not.
 *
 * @author Jon Buffington
 */
public interface PercentServiceAsync extends RestService {
  String PREFERRED_MEDIA_TYPE = "application/vnd.vizpres+json";

  @GET
  @Produces(PREFERRED_MEDIA_TYPE)
  void getPercents(MethodCallback<List<PercentValue>> values);
}
