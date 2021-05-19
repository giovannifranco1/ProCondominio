package br.unitins.procondominio.models;

public enum TipoUsuario {
	MORADOR(0,"Morador"),
	ADMIN(1,"Administrador");
	
	private int id;
	private String descricao;
    
    private TipoUsuario(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
    public static TipoUsuario valueOf(int id) {
		for (TipoUsuario perfil : TipoUsuario.values()) {
			if(id == perfil.getId()) {
				return perfil;
			}
		}
		return null;
	}
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
