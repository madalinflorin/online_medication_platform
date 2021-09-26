package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Effect;

import java.util.List;
import java.util.UUID;

public interface EffectRepository extends JpaRepository<Effect, UUID> {

    List<Effect> findAllById(UUID id);

}