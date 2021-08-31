package com.aspiresys.task1.beans.Channel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "channelcontent")
public class ChannelContentBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHANNEL_CONTENT_ID")
	private int channelContentId;

	@Column(name = "PROGRAMEE")
	private String programee;

	@Column(name = "START")
	private String start;

	@Column(name = "END")
	private String end;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinColumn(name = "CHANNEL_ID")
	private ChannelBean channelId;
}
