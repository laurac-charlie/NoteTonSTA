package com.supinfo.notetonsta.Adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.supinfo.notetonsta.dao.InterventionDAO;
import com.supinfo.notetonsta.entity.Intervention;

public class InterventionAdapter  extends XmlAdapter<Integer , Intervention>{

	@Override
	public Integer marshal(Intervention intervention) throws Exception {
		return intervention.getId();
	}

	@Override
	public Intervention unmarshal(Integer entier) throws Exception {
		return InterventionDAO.getInstance().findId(entier);
	}

}
