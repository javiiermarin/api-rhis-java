package com.rhis.api.service;

import com.rhis.api.repository.PermisoRepository;
import org.springframework.stereotype.Service;

@Service
public class PermisoService {

    private final PermisoRepository permisoRepository;

    public PermisoService(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }
}
