/*
 * Copyright (c) 2012-2014 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.snowplowanalytics.snowplow.enrich.common
package adapters

// Scalaz
import scalaz._
import Scalaz._

// This project
import loaders.CollectorPayload

/**
 * The AdapterRegistry lets us convert a CollectorPayload
 * into one or more RawEvents, using a given adapter.
 */
object AdapterRegistry {

  def toRawEvents(payload: CollectorPayload): ValidatedRawEvents =
      List(RawEvent(
        timestamp    = payload.timestamp,
        vendor       = payload.vendor,
        version      = payload.version,
        parameters   = payload.querystring.map(p => (p.getName -> p.getValue)).toList.toMap,
        // body:     String,
        source       = payload.source,
        encoding     = payload.encoding,
        ipAddress    = payload.ipAddress,
        userAgent    = payload.userAgent,
        refererUri   = payload.refererUri,
        headers      = payload.headers,
        userId       = payload.userId
        )).success

}