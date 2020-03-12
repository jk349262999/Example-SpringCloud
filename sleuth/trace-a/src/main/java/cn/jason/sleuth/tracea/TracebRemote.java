package cn.jason.sleuth.tracea;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TraceB
 *
 * @author: Jason
 * @date: 2020/3/13
 */
@FeignClient(name = "trace-b")
public interface TracebRemote {

    /**
     * traceB
     * @return: java.lang.String
     */
    @GetMapping("/trace-b")
    String traceB();
}
