package com.supinfo.notetonsta.Adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.entity.Campus;

public class CampusAdapter extends XmlAdapter<String , Campus>{

	@Override
	public String marshal(Campus campus) throws Exception {
		return campus.getName();
	}

	@Override
	public Campus unmarshal(String s) throws Exception {
		return CampusDAO.getInstance().findName(s);
	}
}
