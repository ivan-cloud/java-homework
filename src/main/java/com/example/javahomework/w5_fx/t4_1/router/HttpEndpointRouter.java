package com.example.javahomework.w5_fx.t4_1.router;

import java.util.List;

public interface HttpEndpointRouter {
    
    String route(List<String> endpoints);
    
    // Load Balance
    // Random
    // RoundRibbon 
    // Weight
    // - server01,20
    // - server02,30
    // - server03,50
    
}
