package org.Web.Util;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.persistenciaHerbario.Familia;

import com.system.engineer.ejb.FamiliaEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named(value = "familiaConverter")
@ApplicationScoped
public class FamiliaConverter implements Converter<Familia> {
	
	@EJB
	private FamiliaEJB familiaEJB;

	@Override
	public Familia getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && !value.trim().equals("")) {
			Integer idFamilia = Integer.parseInt(value);
			return familiaEJB.buscarFamiliaPorId(idFamilia);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Familia value) {
		return (value !=null)?String.format("%s", value.getId()): "";
	}

}
