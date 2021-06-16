package br.unitins.procondominio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Bloco;
import br.unitins.procondominio.repository.BlocoRepository;



@FacesConverter(forClass = Bloco.class)
public class BlocoConversor implements Converter<Bloco> {
	 @Override
	  public Bloco getAsObject(FacesContext context, UIComponent component, String value) {
	    if (value == null || value.isEmpty()) return null;

	    BlocoRepository condominio = new BlocoRepository();
	    Bloco state = null;
	    try {
	      state = condominio.findById(Integer.valueOf(value.trim()));
	    } catch (RepositoryException e) {
	      e.printStackTrace();
	    }
	    return state;
	  }

	  @Override
	  public String getAsString(FacesContext context, UIComponent component, Bloco value) {
	    if (value == null || value.getId() == null)
	      return null;
	    return value.getId().toString();
	  }
}
