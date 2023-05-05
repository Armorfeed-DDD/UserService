package com.amorfeed.api.userservice.mapping;

import com.amorfeed.api.userservice.resource.RegisterResource;
import com.amorfeed.api.userservice.resource.UpdateResource;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public Page<UserResource> modelListPage(List<User> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, UserResource.class), pageable, modelList.size());
    }
    public UserResource toResource(User model){
        return mapper.map(model, UserResource.class);
    }

    public User toModel(RegisterResource resource) {
        return mapper.map(resource, User.class);
    }

    public User toModel(UpdateResource resource) {
        return mapper.map(resource, User.class);
    }


}
