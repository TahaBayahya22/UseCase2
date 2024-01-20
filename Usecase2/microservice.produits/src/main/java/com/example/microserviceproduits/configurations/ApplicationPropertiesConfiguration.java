package com.example.microserviceproduits.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-config-ms")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    // correspond à la propriété « mes-configs.maxPrice » dans le fichier de configuration du MS
    private int maxPrice;

    public int getMaxPrice() {
        System.out.println("### limitDeProduits : " + maxPrice);
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
}
