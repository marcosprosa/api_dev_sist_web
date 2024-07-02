package java_sprint_boot.servico;

import java_sprint_boot.modelo.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinoServico {
    private List<Destino> destinos = new ArrayList<>();

    public Destino cadastrarDestino(Destino destino) {
        destinos.add(destino);
        return destino;
    }

    public List<Destino> listarDestinos() {
        return destinos;
    }

    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        return destinos.stream()
                .filter(d -> (nome.isEmpty() || d.getNome().toLowerCase().contains(nome.toLowerCase())) &&
                             (localizacao.isEmpty() || d.getLocalizacao().toLowerCase().contains(localizacao.toLowerCase())))
                .collect(Collectors.toList());
    }

    public Optional<Destino> obterDestinoPorId(Long id) {
        return destinos.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public boolean adicionarAvaliacaoAoDestino(Long id, int nota) {
        Optional<Destino> destinoOpt = obterDestinoPorId(id);
        if (destinoOpt.isPresent()) {
            destinoOpt.get().adicionarAvaliacao(nota);
            return true;
        }
        return false;
    }

    public Optional<Destino> atualizarDestino(Long id, Destino dadosAtualizados) {
        for (int i = 0; i < destinos.size(); i++) {
            if (destinos.get(i).getId().equals(id)) {
                Destino destinoExistente = destinos.get(i);
                destinoExistente.setNome(dadosAtualizados.getNome());
                destinoExistente.setLocalizacao(dadosAtualizados.getLocalizacao());
                destinoExistente.setDetalhes(dadosAtualizados.getDetalhes());
                destinoExistente.setAvaliacoes(dadosAtualizados.getAvaliacoes());
                return Optional.of(destinoExistente);
            }
        }
        return Optional.empty();
    }

    public boolean excluirDestino(Long id) {
        return destinos.removeIf(d -> d.getId().equals(id));
    }
}
