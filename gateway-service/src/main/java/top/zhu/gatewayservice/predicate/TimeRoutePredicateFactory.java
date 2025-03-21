package top.zhu.gatewayservice.predicate;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class TimeRoutePredicateFactory extends AbstractRoutePredicateFactory<TimeRoutePredicateFactory.Config> {

    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";

    public TimeRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(START_TIME, END_TIME);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                LocalTime time = LocalTime.now();
                LocalTime startTime = LocalTime.parse(config.getStartTime());
                LocalTime endTime = LocalTime.parse(config.getEndTime());
                if (startTime != null && endTime != null) {
                    return startTime.isBefore(time) && endTime.isAfter(time);
                } else {
                    return false;
                }
            }
        };
    }

    @Data
    public static class Config {
        private String startTime;
        private String endTime;
    }
}
