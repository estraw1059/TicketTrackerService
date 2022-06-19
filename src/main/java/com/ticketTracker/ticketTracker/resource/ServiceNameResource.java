package com.ticketTracker.ticketTracker.resource;


import com.ticketTracker.ticketTracker.entity.ServiceName;
import com.ticketTracker.ticketTracker.mapper.ServiceMapper;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import java.util.List;

@Controller("/serviceName")
public class ServiceNameResource {
    static final Logger logger = LoggerFactory.getLogger(ServiceNameResource.class);

    @Autowired
    private ServiceMapper serviceMapper;

    @GET
    public void getServices() {
        try {
            List<ServiceName> serviceName = serviceMapper.getServices();
            System.out.println(serviceName);
        } catch (Exception ex) {
            System.out.println("Failure");
        }
    }
}
