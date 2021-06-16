package br.unitins.procondominio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Condominio;
import br.unitins.procondominio.repository.CondominioRepository;


@FacesConverter(forClass = Condominio.class)
public class CondominioConversor implements Converter<Condominio> {
	 @Override
	  public Condominio getAsObject(FacesContext context, UIComponent component, String value) {
	    if (value == null || value.isEmpty()) return null;

	    CondominioRepository condominio = new CondominioRepository();
	    Condominio state = null;
	    try {
	      state = condominio.findById(Integer.valueOf(value.trim()));
	    } catch (RepositoryException e) {
	      e.printStackTrace();
	    }
	    return state;
	  }

	  @Override
	  public String getAsString(FacesContext context, UIComponent component, Condominio value) {
	    if (value == null || value.getId() == null)
	      return null;
	    return value.getId().toString();
	  }
}
