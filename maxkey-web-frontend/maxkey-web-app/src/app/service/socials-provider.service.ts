/*
 * Copyright [2022] [MaxKey of copyright http://www.maxkey.top]
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

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NzSafeAny } from 'ng-zorro-antd/core/types';
import { Observable } from 'rxjs';

import { Message } from '../entity/Message';
import { SocialsProvider } from '../entity/SocialsProvider';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class SocialsProviderService extends BaseService<SocialsProvider> {
  constructor(private _httpClient: HttpClient) {
    super(_httpClient, '/config/socialsprovider');
  }

  authorize(provider: string): Observable<Message<SocialsProvider>> {
    return this.getByParams({}, `/logon/oauth20/authorize/${provider}?_allow_anonymous=true`);
  }

  scanqrcode(provider: string): Observable<Message<SocialsProvider>> {
    return this.getByParams({}, `/logon/oauth20/scanqrcode/${provider}?_allow_anonymous=true`);
  }

  callback(provider: string, params: NzSafeAny): Observable<Message<SocialsProvider>> {
    return this.getByParams(params, `/logon/oauth20/callback/${provider}?_allow_anonymous=true`);
  }

  bind(provider: string, params: NzSafeAny): Observable<Message<SocialsProvider>> {
    return this.getByParams(params, `/logon/oauth20/bind/${provider}?_allow_anonymous=true`);
  }
}
