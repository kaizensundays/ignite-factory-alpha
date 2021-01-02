package com.kaizensundays.particles.ignite.factory.alpha;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.IgniteException;
import org.apache.ignite.IgniteSpring;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created: Saturday 11/16/2019, 8:16 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
public class IgniteFactoryBean implements ApplicationContextAware, FactoryBean<Ignite> {

    private Ignite ignite;

    private IgniteConfiguration configuration;

    private ApplicationContext context;

    public void setConfiguration(IgniteConfiguration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings("RedundantThrows")
    public Ignite getObject() throws Exception {
        return ignite;
    }

    public Class<?> getObjectType() {
        return Ignite.class;
    }

    public boolean isSingleton() {
        return true;
    }

    @SuppressWarnings("unused")
    public ApplicationContext getApplicationContext() throws BeansException {
        return context;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public void start() {

        if (configuration == null) {
            throw new IllegalStateException();
        }

        try {
            ignite = IgniteSpring.start(configuration, context);
        }
        catch (IgniteCheckedException e) {
            throw new IgniteException("Failed to start Ignite", e);
        }

    }

    public void stop() {

        if (ignite != null) {
            Ignition.stop(ignite.name(), false);
        }

    }

}
