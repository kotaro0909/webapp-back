package com.example.backed.libs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProfile implements ApplicationContextAware {
    private AppProfile() {}

    @Autowired
    private IProfile profile;

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static final AppProfile getInstance() {
        return context.getBean(AppProfile.class);
    }

    public ActiveProfile getProfile() {
        return profile.getProfile();
    }

    public String getEnv(String key) {
        return profile.getEnv(key);
    }

    public interface IProfile {
        public ActiveProfile getProfile();
        public String getEnv(String key);
    }

    @Component
    @org.springframework.context.annotation.Profile("dev")
    public static class DevProfile extends AbstractAppProfile {

        protected DevProfile(Environment environment) {
            super(environment);
        }

        @Override
        public ActiveProfile getProfile() {
            return ActiveProfile.DEV;
        }
    }

    @Component
    @org.springframework.context.annotation.Profile("prod")
    public static class ProdProfile extends AbstractAppProfile {
        protected ProdProfile(Environment environment) {
            super(environment);
        }

        @Override
        public ActiveProfile getProfile() {
            return ActiveProfile.PROD;
        }
    }

    public static abstract class AbstractAppProfile implements IProfile {

        //汎用的なプロパティ取得のgetter１つを設けるパターン
        private final Environment environment;

        protected AbstractAppProfile(Environment environment) {
            this.environment = environment;
        }

        public String getEnv(String key) {
            String ret = environment.getProperty(key);
            return ret;
        }
    }
}
