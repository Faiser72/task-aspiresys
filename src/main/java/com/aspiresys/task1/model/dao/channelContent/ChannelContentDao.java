package com.aspiresys.task1.model.dao.channelContent;

import com.aspiresys.task1.beans.Channel.ChannelBean;

public interface ChannelContentDao {

	int save(Object object);

	Boolean isContentPresent(String text, ChannelBean channelBean, String bean);

}
