package com.mmorpg.mir.client.framework.net;

import com.mmorpg.mir.client.InitHandlerStrategy;
import com.mmorpg.mir.client.framework.net.enums.Handler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Slf4j
@Component
public class InitHandlerProcess extends ApplicationObjectSupport {

    @Autowired
    private ProtocolRegister protocolRegister;
    @Autowired
    private InitHandlerStrategy initHandlerStrategy;

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        Map<String, Object> beans = context.getBeansWithAnnotation(Handler.class);
        log.debug("find handlers : {}", beans);
        beans.entrySet().forEach(entry -> {
            Object bean = entry.getValue();
            protocolRegister.register(initHandlerStrategy.process(bean));
        });
        log.debug("create handlerMethods:{}", protocolRegister);
    }
}
