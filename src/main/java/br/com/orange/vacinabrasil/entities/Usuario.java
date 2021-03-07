package br.com.orange.vacinabrasil.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.orange.vacinabrasil.entities.validators.DateValidatorInt;

@Entity
@Table(name = "tb_usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(unique = true, nullable = false)
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Email(message = "Email Inválido")
	private String email;

	@Column(unique = true, nullable = false)
	@NotEmpty(message = "Preenchimento Obrigatório")
	@CPF
	private String numeroDoCpf;

	@Temporal(TemporalType.TIMESTAMP)	
	@DateValidatorInt(message = "Data de nascimento inválida")
	private Date dataDeNascimento;

	public Usuario() {
	}

	public Usuario(String nome, String email, String numeroDoCpf, Date dataDeNascimento) {
		this.nome = nome;
		this.email = email;
		this.numeroDoCpf = numeroDoCpf;
		this.dataDeNascimento = dataDeNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroDoCpf() {
		return numeroDoCpf;
	}

	public void setNumeroDoCpf(String numeroDoCpf) {
		this.numeroDoCpf = numeroDoCpf;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", numeroDoCpf=" + numeroDoCpf
				+ ", dataDeNascimento=" + dataDeNascimento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
