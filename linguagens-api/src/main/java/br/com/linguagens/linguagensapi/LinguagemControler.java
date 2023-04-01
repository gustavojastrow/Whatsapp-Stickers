package br.com.linguagens.linguagensapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemControler {

    @Autowired
    private LinguagemRepository repository;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens(){
        List <Linguagem> linguagens = repository.findByOrderByRanking();
        return linguagens;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem){
        Linguagem linguagemSalva = repository.save(linguagem);
        return new ResponseEntity<>(linguagemSalva, HttpStatus.CREATED);
    }
    @GetMapping(value ="/linguagens/{id}")
    public Linguagem obterLinguagemPorId(@PathVariable String id){
        return repository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PutMapping(value = "/linguagens/{id}")
    public Linguagem atualizarLinguagem(@PathVariable String id ,@RequestBody Linguagem linguagem){
        repository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        linguagem.setId(id);
        return repository.save(linguagem);
    }
    @DeleteMapping(value = "/linguagens/{id}")
    public void deletarLinguagem(@PathVariable String id){
        repository.deleteById(id);
    }
}

