package init.stack.api.controller;
import init.stack.api.exceptions.PizzaNotFoundException;
import init.stack.api.model.Pizza;
import init.stack.api.repository.PizzaRepository;
import init.stack.api.verif.PizzaVerifCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    private PizzaRepository ref_PizzaRepository;

    // -- GET
    @GetMapping("/pizzas")
    public List<Pizza> getAllPizza() {
        return ref_PizzaRepository.findAll();
    }

    @GetMapping("/pizzas/{id}")
    public ResponseEntity< Pizza > getEmployeeById(@PathVariable(value = "id") Long ref_Long_clubId)
            throws PizzaNotFoundException {
        Pizza ref_Pizza = ref_PizzaRepository.findById(ref_Long_clubId)
                .orElseThrow(() -> new PizzaNotFoundException("The pizza number" + ref_Long_clubId + "doesn't exist "));
        return ResponseEntity.ok().body(ref_Pizza);
    }

    // -- POST
    @PostMapping("/pizzaCreation")
    public Pizza createPizza(@RequestBody Pizza ref_NewPizza) {
        return ref_PizzaRepository.save(ref_NewPizza);
    }

    @PostMapping("/pizzaDecoration")
    public Pizza decoratePizza(@RequestBody Pizza ref_DecoratePizza) {
        return ref_PizzaRepository.save(ref_DecoratePizza);
    }

}
