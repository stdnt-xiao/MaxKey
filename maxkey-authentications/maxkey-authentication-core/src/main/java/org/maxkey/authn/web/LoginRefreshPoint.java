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
 

package org.maxkey.authn.web;

import org.maxkey.authn.jwt.AuthJwt;
import org.maxkey.authn.jwt.AuthRefreshTokenService;
import org.maxkey.authn.jwt.AuthTokenService;
import org.maxkey.authn.session.Session;
import org.maxkey.authn.session.SessionManager;
import org.maxkey.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
public class LoginRefreshPoint {
	private static final  Logger _logger = LoggerFactory.getLogger(LoginRefreshPoint.class);
	
	@Autowired
	AuthTokenService authTokenService;
	
	@Autowired
	AuthRefreshTokenService refreshTokenService;
	
	@Autowired
	SessionManager sessionManager;
	
 	@RequestMapping(value={"/token/refresh"}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> refresh(
					@RequestHeader(name = "refresh_token", required = true) String refreshToken) {
 		_logger.debug("try to refresh token " );
 		_logger.trace("refresh token {} " , refreshToken);
 		try {
	 		if(refreshTokenService.validateJwtToken(refreshToken)) {
	 			String sessionId = refreshTokenService.resolveJWTID(refreshToken);
	 			_logger.trace("Try to  refresh sessionId [{}]" , sessionId);
		 		Session session = sessionManager.refresh(sessionId);
		 		if(session != null) {
		 			AuthJwt authJwt = authTokenService.genAuthJwt(session.getAuthentication());
		 			_logger.trace("Grant new token {}" , authJwt);
		 			return new Message<AuthJwt>(authJwt).buildResponse();
		 		}else {
		 			_logger.debug("Session is timeout , sessionId [{}]" , sessionId);
		 		}
	 		}else {
	 			_logger.debug("refresh token is not validate .");
	 		}
 		}catch(Exception e) {
 			_logger.error("Refresh Exception !",e);
 		}
 		return new ResponseEntity<>("Refresh Token Fail !", HttpStatus.UNAUTHORIZED);
 	}
}
