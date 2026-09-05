package sptech.school.imobiliaria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.imobiliaria.model.Imovel;
import sptech.school.imobiliaria.service.ImovelService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    private final ImovelService service;

    public ImovelController(ImovelService service) {
        this.service = service;
    }

    // GET /imoveis -> 200
    @GetMapping
    public ResponseEntity<List<Imovel>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET /imoveis/{id} -> 200 ou 404
    @GetMapping("/{id}")
    public ResponseEntity<Imovel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // POST /imoveis -> 201
    @PostMapping
    public ResponseEntity<Imovel> cadastrar(@RequestBody Imovel imovel) {
        Imovel salvo = service.cadastrar(imovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
