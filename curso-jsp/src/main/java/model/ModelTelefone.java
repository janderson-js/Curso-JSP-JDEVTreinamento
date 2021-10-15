package model;

import java.io.Serializable;
import java.util.Objects;

public class ModelTelefone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private ModelLogin usuarioPaiId;
	private ModelLogin usuarioCadId;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		return Objects.equals(id, other.id);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public ModelLogin getUsuarioPaiId() {
		return usuarioPaiId;
	}
	public void setUsuarioPaiId(ModelLogin usuarioPaiId) {
		this.usuarioPaiId = usuarioPaiId;
	}
	public ModelLogin getUsuarioCadId() {
		return usuarioCadId;
	}
	public void setUsuarioCadId(ModelLogin usuarioCadId) {
		this.usuarioCadId = usuarioCadId;
	}
	

	
}
