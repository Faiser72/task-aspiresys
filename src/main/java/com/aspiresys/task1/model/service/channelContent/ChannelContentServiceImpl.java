package com.aspiresys.task1.model.service.channelContent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspiresys.task1.beans.Channel.ChannelBean;
import com.aspiresys.task1.model.dao.channelContent.ChannelContentDao;

@Service
public class ChannelContentServiceImpl implements ChannelContentService {

	@Autowired
	private ChannelContentDao channelContectDao;

	@Override
	public int save(Object object) {
		return channelContectDao.save(object);
	}

	@Override
	public Boolean isContentPresent(String text, ChannelBean channelBean, String bean) {
		return channelContectDao.isContentPresent(text, channelBean, bean);
	}

}
