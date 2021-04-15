package init.stack.api.repository;

import init.stack.api.model.Pizza;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository {
    Pizza findPizzaBy(String PizzaId);
}
