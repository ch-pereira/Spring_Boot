package Spring.Camilly.repository;

import Spring.Camilly.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Cliente, Long> {
}
