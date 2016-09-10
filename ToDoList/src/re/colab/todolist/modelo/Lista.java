package re.colab.todolist.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//@Entity - Anotação para especificar que essa classe representará uma tabela no banco de dados

//@Id - Anotação especificando que o campo é o identificador da tabela

//@GeneratedValue - Auto gerar Id

//(strategy=GenerationType.IDENTITY) - Tornar a coluna como auto increment (IDENTITY)

//@Column - Edita os atributos da coluna no banco de dados

//@OneToMany - Estabelece relação de 1 para muitos (1:N)

//MappedBy - Especificar 1:N para que não seja criada tabela associativa sem necessidade (Busca a variavel na classe pai)

//Cascade - Gerenciar registros da tabela N ao editar a tabela 1

//orphanRemoval = Remove os registros soltos (sem id 'pai')

@Entity
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	private String titulo;

	@OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ItemLista> itens;

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<ItemLista> getItens() {
		return itens;
	}

	public void setItens(List<ItemLista> itens) {
		this.itens = itens;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isRealizada() {
		for (ItemLista item : itens) {
			if (!item.isFeito()) {
				return false;
			}
		}
		return true;
	}
}
