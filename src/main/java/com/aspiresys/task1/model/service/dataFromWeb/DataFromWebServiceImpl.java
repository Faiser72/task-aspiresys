package com.aspiresys.task1.model.service.dataFromWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspiresys.task1.model.dao.dataFromWeb.DataFromWebDao;

@Service
public class DataFromWebServiceImpl implements DataFromWebService {

	@Autowired
	private DataFromWebDao dataFromWebDao;

	@Override
	public int save(Object object) {
		return dataFromWebDao.save(object);
	}

	@Override
	public Boolean isPresent(String chan, String bean) {
		return dataFromWebDao.isPresent(chan, bean);
	}

}
