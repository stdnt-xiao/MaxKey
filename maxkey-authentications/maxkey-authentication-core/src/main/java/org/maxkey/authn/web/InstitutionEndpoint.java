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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.maxkey.configuration.ApplicationConfig;
import org.maxkey.entity.Institutions;
import org.maxkey.entity.Message;
import org.maxkey.persistence.repository.InstitutionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/inst")
public class InstitutionEndpoint {
	private static final  Logger _logger = LoggerFactory.getLogger(InstitutionEndpoint.class);
	
	public final static String  HEADER_HOST 		= "host";
	
	public final static String  HEADER_HOSTNAME 	= "hostname";
	
	@Autowired
	InstitutionsRepository institutionsRepository;
	
	@Autowired
	ApplicationConfig applicationConfig;
	
 	@RequestMapping(value={"/get"}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> get(
			HttpServletRequest request,
			@RequestHeader("Origin") String originURL,
			@RequestHeader(HEADER_HOSTNAME) String headerHostName,
			@RequestHeader(HEADER_HOST) String headerHost) {
 		_logger.debug("get Institution" );
 		
		String host = headerHostName;
		_logger.trace("hostname {}",host);
		if(StringUtils.isEmpty(host)) {
			host = headerHost;
			_logger.trace("host {}",host);
		}
		
		if(StringUtils.isEmpty(host)) {
			host = applicationConfig.getDomainName();
			_logger.trace("config domain {}",host);
		}
		
		if(host.indexOf(":")> -1 ) {
			host = host.split(":")[0];
			_logger.trace("domain split {}",host);
		}
		
		Institutions inst = institutionsRepository.get(host);
		if(inst != null) {
			_logger.debug("inst {}",inst);
			return new Message<Institutions>(inst).buildResponse();
		}else {
			Institutions defaultInst = institutionsRepository.get("1");
			_logger.debug("default inst {}",inst);
			return new Message<Institutions>(defaultInst).buildResponse();
		}
 	}
}
