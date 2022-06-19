package com.ticketTracker.ticketTracker.mapper;

import com.ticketTracker.ticketTracker.entity.ServiceName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ServiceMapper {
    @Select("SELECT * FROM service")
    List<ServiceName> getServices();
}
