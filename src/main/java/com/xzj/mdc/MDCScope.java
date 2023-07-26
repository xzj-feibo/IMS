package com.xzj.mdc;

import com.google.common.base.Preconditions;
import org.slf4j.MDC;

import java.util.Map;

/**
 * @author 夏子健
 * @version 1.0
 * @date 2023/7/8 15:44
 */
public class MDCScope implements AutoCloseable{
    private final Map<String,String> map = MDC.getCopyOfContextMap();

    public MDCScope put(String key,String value){
        Preconditions.checkNotNull(key,"the key is null");
        if (value != null){
            MDC.put(key,value);
        }else if (MDC.get(key) != null){
            MDC.remove(key);
        }
        return this;
    }
    @Override
    public void close() throws Exception {
        if (this.map!= null){
            MDC.setContextMap(this.map);
        }else{
            MDC.clear();
        }
    }
}
