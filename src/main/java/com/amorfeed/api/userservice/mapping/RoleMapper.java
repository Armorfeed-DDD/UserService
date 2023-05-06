package com.amorfeed.api.userservice.mapping;

import com.amorfeed.api.userservice.entity.Enum.Roles;
import com.amorfeed.api.userservice.entity.Role;
import com.amorfeed.api.userservice.resource.RoleResource;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RoleMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Roles, String> rolesToStringConverter=new AbstractConverter<Roles, String>(){
        @Override
        protected String convert(Roles roles){
            return roles==null?null:roles.name();
        }
    };

    // Object Mapping
    public RoleResource toResource(Role model){
        mapper.addConverter(rolesToStringConverter);
        return mapper.map(model, RoleResource.class);
    }

    public Page<RoleResource> modelListToPage(List<Role> modelList, Pageable pageable){
        mapper.addConverter(rolesToStringConverter);
        return new PageImpl<>(mapper.mapList(modelList, RoleResource.class), pageable, modelList.size());
    }
}
