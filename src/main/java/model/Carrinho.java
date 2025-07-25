package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Carrinho {

	private List<Produto> produtoList = new ArrayList<>();

	public Iterable<Produto> getItens() {

		produtoList.sort(Comparator.comparingLong(Produto::getId));

		return Collections.unmodifiableList(produtoList);
	}

	public void adicionar(Produto produto) {
		produtoList.add(produto);
	}

	public int getNumeroItens() {
		return produtoList.size();
	}

	public boolean isEmpty() {
		return produtoList.isEmpty();
	}

	public void remover(Long produtoId) {
		for (int i = 0; i < produtoList.size(); i++) {
			if (produtoList.get(i).getId() == produtoId) {
				produtoList.remove(i);
				return;
			}
		}
	}

}
