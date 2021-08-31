package com.aspiresys.task1.model.dao.dataFromWeb;

public interface DataFromWebDao {

	int save(Object object);

	Boolean isPresent(String chan, String bean);

}
