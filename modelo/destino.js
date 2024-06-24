// models/destino.js

class Destino {
    constructor(id, nome, localizacao, detalhes) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.detalhes = detalhes;
        this.avaliacoes = [];
    }

    adicionarAvaliacao(nota) {
        this.avaliacoes.push(nota);
    }

    calcularMediaAvaliacoes() {
        if (this.avaliacoes.length === 0) return 0;
        const totalNotas = this.avaliacoes.reduce((total, avaliacao) => total + avaliacao, 0);
        return totalNotas / this.avaliacoes.length;
    }

    toJSON() {
        return {
            id: this.id,
            nome: this.nome,
            localizacao: this.localizacao,
            detalhes: this.detalhes,
            avaliacoes: this.avaliacoes,
            mediaAvaliacoes: this.calcularMediaAvaliacoes()
        };
    }
}

module.exports = Destino;
