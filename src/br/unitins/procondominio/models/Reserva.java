package br.unitins.procondominio.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reserva extends DefaultEntity<Reserva> {

	private static final long serialVersionUID = -8769110898392274424L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area_comum")
	private AreaComum areaComum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_morador")
	private Morador morador;

	public AreaComum getAreaComum() {
		return areaComum;
	}

	public void setAreaComum(AreaComum areaComum) {
		this.areaComum = areaComum;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	
	
}
