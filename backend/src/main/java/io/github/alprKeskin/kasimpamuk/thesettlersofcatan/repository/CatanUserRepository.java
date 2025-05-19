package io.github.alprKeskin.kasimpamuk.thesettlersofcatan.repository;

import io.github.alprKeskin.kasimpamuk.thesettlersofcatan.model.authentication.CatanUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatanUserRepository extends CrudRepository<CatanUser, Long> {
	Optional<CatanUser> findByEmail(String email);
	void deleteByEmail(String email);
	List<CatanUser> findAll();
	boolean existsByEmail(String email);
}
