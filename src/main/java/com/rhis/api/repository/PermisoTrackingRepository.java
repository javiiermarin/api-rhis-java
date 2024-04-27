package com.rhis.api.repository;

import com.rhis.api.model.PermisoTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoTrackingRepository extends JpaRepository<PermisoTracking, String> {


List<PermisoTracking> findAllByEstado(boolean estado);

}
