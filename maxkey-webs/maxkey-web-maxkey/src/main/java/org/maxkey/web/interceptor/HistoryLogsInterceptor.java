/*
 * Copyright [2020] [MaxKey of copyright http://www.maxkey.top]
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
 

package org.maxkey.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.maxkey.authn.web.AuthorizationUtils;
import org.maxkey.entity.HistorySystemLogs;
import org.maxkey.entity.UserInfo;
import org.maxkey.persistence.service.HistorySystemLogsService;
import org.maxkey.web.WebContext;
import org.maxkey.web.message.Message;
import org.maxkey.web.message.MessageScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contorller调用完成后进行日志操作
 * 日志处理需在parasec-servlet.xml中配置
 * mvc:interceptors  log
 * @author Crystal.Sea
 *
 */
@Component
public class HistoryLogsInterceptor  implements AsyncHandlerInterceptor  {

    private static final Logger _logger = LoggerFactory.getLogger(HistoryLogsInterceptor.class);

    @Autowired
    private HistorySystemLogsService historySystemLogsService;

    /**
     *  after the handler is executed.
     */
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, 
            Object handler,ModelAndView modelAndView) throws Exception {
        _logger.debug("postHandle");
        Message message = WebContext.getMessage();//读取session中message

        if (message != null) {
            //判断message类型
            if (message.getMessageScope() == MessageScope.DB
                    || message.getMessageScope() == MessageScope.DB_CLIENT) {
                UserInfo userInfo = AuthorizationUtils.getUserInfo();//取得当前用户信息

                //创建日志记录
                HistorySystemLogs historySystemLogs = new HistorySystemLogs();
                historySystemLogs.setInstId(userInfo.getInstId());
                _logger.debug("insert db historyLogs content : " + historySystemLogs);
                historySystemLogsService.insert(historySystemLogs);//日志插入数据库
                //message类型仅插入数据库
                if (message.getMessageScope() == MessageScope.DB) {
                    WebContext.clearMessage();//清除message
                }
            }
        }
    }
}
