package benkinmat.github.io.springbootexceptionhandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import benkinmat.github.io.springbootexceptionhandler.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, String> {

}
