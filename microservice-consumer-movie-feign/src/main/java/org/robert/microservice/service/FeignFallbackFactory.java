package org.robert.microservice.service;

import feign.hystrix.FallbackFactory;
import org.robert.microservice.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignFallbackFactory implements FallbackFactory<UserFeignClient> {
    private final static Logger LOG = LoggerFactory.getLogger(FeignFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {

        UserFeignClient ufc = new UserFeignClient() {
            @Override
            public User findById(Long id) {
                LOG.info("fallback: reason was:" + throwable);

                User user = new User();
                user.setId(-1L);

                if (throwable instanceof IllegalArgumentException) {
                    user.setId(-2L);
                }

                user.setUsername("默认用户");

                return user;
            }
        };

        return ufc;
    }


}
