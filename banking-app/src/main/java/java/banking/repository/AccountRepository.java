package java.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.banking.entity.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long>{

}
