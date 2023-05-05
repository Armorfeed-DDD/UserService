package com.amorfeed.api.userservice.mapping;

import com.amorfeed.api.userservice.entity.Enterprise;
import com.amorfeed.api.userservice.resource.EnterpriseResource;
import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UpdateEnterpriseResource;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class EnterpriseMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public Page<EnterpriseResource> modelListPage(List<Enterprise> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, EnterpriseResource.class), pageable, modelList.size());
    }

    public EnterpriseResource toResource(Enterprise model) {return mapper.map(model, EnterpriseResource.class); }

    public Enterprise toModel(RegisterResource resource) {return mapper.map(resource, Enterprise.class);}

    public Enterprise tomodel(UpdateEnterpriseResource resource) {
        return mapper.map(resource, Enterprise.class);
    }
}
