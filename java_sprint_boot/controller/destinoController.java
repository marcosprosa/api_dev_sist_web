package java_sprint_boot.controller;

import java_sprint_boot.modelo.Destino;
import java_sprint_boot.servico.DestinoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destino")
public class DestinoController {

    @Autowired
    private DestinoServico destinoServico;

    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        Destino novoDestino = destinoServico.cadastrarDestino(destino);
        return ResponseEntity.ok(novoDestino);
    }

    @GetMapping
    public ResponseEntity<List<Destino>> listarDestinos() {
        List<Destino> destinos = destinoServico.listarDestinos();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam Optional<String> nome, @RequestParam Optional<String> localizacao) {
        List<Destino> resultados = destinoServico.pesquisarDestinos(nome.orElse(""), localizacao.orElse(""));
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> obterDestinoPorId(@PathVariable Long id) {
        Optional<Destino> destino = destinoServico.obterDestinoPorId(id);
        return destino.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @PostMapping("/{id}/avaliacao")
    public ResponseEntity<String> adicionarAvaliacaoAoDestino(@PathVariable Long id, @RequestBody Integer nota) {
        if (nota < 1 || nota > 10) {
            return ResponseEntity.status(400).body("Nota deve ser entre 1 e 10");
        }

        boolean sucesso = destinoServico.adicionarAvaliacaoAoDestino(id, nota);
        if (sucesso) {
            return ResponseEntity.ok("Avaliação registrada com sucesso");
        } else {
            return ResponseEntity.status(404).body("Destino não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizarDestino(@PathVariable Long id, @RequestBody Destino dadosAtualizados) {
        Optional<Destino> destinoAtualizado = destinoServico.atualizarDestino(id, dadosAtualizados);
        return destinoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirDestino(@PathVariable Long id) {
        boolean sucesso = destinoServico.excluirDestino(id);
        if (sucesso) {
            return ResponseEntity.ok("Destino deletado");
        } else {
            return ResponseEntity.status(404).body("Destino não encontrado");
        }
    }
}
