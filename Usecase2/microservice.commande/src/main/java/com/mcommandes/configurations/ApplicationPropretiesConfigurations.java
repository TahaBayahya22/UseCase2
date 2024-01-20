package com.mcommandes.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-config-ms")
@RefreshScope
public class ApplicationPropretiesConfigurations {
    private int commandesLast;
    public int getCommandes_last() {
        return commandesLast;
    }
    public void setCommandes_last(int commandes_last) {
        this.commandesLast = commandes_last;
    }
}
