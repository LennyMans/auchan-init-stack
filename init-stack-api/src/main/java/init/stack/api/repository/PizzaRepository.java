package init.stack.api.repository;

import init.stack.api.model.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "pizza", path = "pizza")
interface PizzaRepository extends MongoRepository<Pizza, String> {

    //List<Pizza> findByLastName(@Param("name") String name);

}
