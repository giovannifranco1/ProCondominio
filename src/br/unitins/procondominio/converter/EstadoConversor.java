package br.unitins.procondominio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.procondominio.application.RepositoryException;
import br.unitins.procondominio.models.Estado;
import br.unitins.procondominio.repository.EstadoRepository;

@FacesConverter(forClass = Estado.class)
public class EstadoConversor implements Converter<Estado> {
  @Override
  public Estado getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.isEmpty()) return null;

    EstadoRepository estado = new EstadoRepository();
    Estado state = null;
    try {
      state = estado.findById(Integer.valueOf(value.trim()));
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return state;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Estado value) {
    if (value == null || value.getId() == null)
      return null;
    return value.getId().toString();
  }
}
