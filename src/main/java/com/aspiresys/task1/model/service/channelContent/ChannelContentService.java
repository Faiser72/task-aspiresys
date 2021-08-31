package com.aspiresys.task1.model.service.channelContent;

import com.aspiresys.task1.beans.Channel.ChannelBean;

public interface ChannelContentService {

	int save(Object object);

	Boolean isContentPresent(String text, ChannelBean channelBean, String bean);

}
