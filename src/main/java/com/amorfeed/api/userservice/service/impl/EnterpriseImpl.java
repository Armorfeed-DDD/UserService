package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.entity.Enterprise;
import com.amorfeed.api.userservice.repository.EnterpriseRepository;
import com.amorfeed.api.userservice.service.EnterpriseService;
import com.amorfeed.api.userservice.shared.exception.ResourceNotFoundException;
import com.amorfeed.api.userservice.shared.exception.ResourceValidationException;
import com.amorfeed.api.userservice.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EnterpriseImpl implements EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnhancedModelMapper mapper;

    private final Validator validator;
    private static final String ENTITY = "Enterprise";

    public EnterpriseImpl(Validator validator) {
        this.validator = validator;
    }


    @Override
    public Optional<Enterprise> update(Long id, Enterprise request) {
        Set<ConstraintViolation<Enterprise>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Enterprise enterpriseWithRUC = enterpriseRepository.findByRUC(request.getRuc());
        if (enterpriseWithRUC != null && !enterpriseWithRUC.getId().equals(id)){
            throw new ResourceValidationException(ENTITY,
                    "An user with the same RUC already exists");
        }
        return enterpriseRepository.findById(id).map(enterprise ->
                enterpriseRepository.save(enterprise.withImage(request.getImage()))
                        .withRuc(request.getRuc())
                        .withPhone(request.getPhone())
                        .withDescription(request.getDescription()));

    }

    @Override
    public List<Enterprise> getListbyScore(int Score) {
        List<Enterprise> enterprises = enterpriseRepository.findByScore(Score);
        if (enterprises.isEmpty()) {
            throw new ResourceNotFoundException("Enterprise", Score);
        } else {
            enterprises.forEach(System.out::println);
            return enterprises;
        }
    }
}
