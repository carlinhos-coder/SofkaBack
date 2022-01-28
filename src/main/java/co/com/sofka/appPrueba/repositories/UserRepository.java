package co.com.sofka.appPrueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.sofka.appPrueba.models.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	public UserModel findByNombre(String name);
}
