package com.nelioalves.cursomc.serveces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositores.CategoriaRepository;
import com.nelioalves.cursomc.repositores.ProdutoRepository;
import com.nelioalves.cursomc.serveces.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id)  { 
		 Optional<Produto> obj = repo.findById(id); 
		 return obj.orElseThrow(() -> new ObjectNotFoundException( 
		  "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())); 
		 }
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String OrderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), OrderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDidtinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
