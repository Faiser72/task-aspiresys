package com.aspiresys.task1.controller.DataFromWebSite;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspiresys.task1.beans.Channel.ChannelBean;
import com.aspiresys.task1.beans.Channel.ChannelContentBean;
import com.aspiresys.task1.model.service.channelContent.ChannelContentService;
import com.aspiresys.task1.model.service.dataFromWeb.DataFromWebService;

@RestController
@RequestMapping("/data")
public class TvsDataController {

	@Autowired
	private DataFromWebService service;

	@Autowired
	private ChannelContentService channelContentService;

//	@RequestMapping("/tvsdata")
	@Scheduled(cron = "0 0 10,16 * * *")
//	@Scheduled(initialDelay = 10000, fixedDelay = 30000)
	public void getData() {

		Document doc;
		try {
			doc = Jsoup.connect("https://tvscheduleindia.com/channel").get();// to connect with website and to gvet data
			String s = doc.select("div.card-body a").text();
			String[] channels = s.split("  ");// to split channels based on spaces
			Predicate<String> p = str -> str.equals(""); // predicate to check the channel is empty or not
			Function<String, String> f = (channel) -> channel.replace(" ", "-").toLowerCase(); // function to create url
																								// of inner channel
																								// content by replacing
																								// spaces with '-' and
																								// making lowercase
			for (String chan : channels) {

				if (!p.test(chan)) {

					if (service.isPresent(chan, "ChannelBean")) {

						ChannelBean channelBean = new ChannelBean();
						channelBean.setChannelName(chan);
						int id = service.save(channelBean);
						System.out.println(chan);
						System.out.println(id + "iddd");

						if (id != 0) {
							channelBean.setChannelId(id);
							System.out.println("**************&&&&&&" + chan + "&&&&&&&**********************");
							Document channelContent = Jsoup
									.connect("https://tvscheduleindia.com/channel" + "/" + f.apply(chan)).get(); // to
																													// get
																													// the
																													// channel
																													// content
//							Function<String,String> f1= (desc)->desc.substring(0, 300)

							for (org.jsoup.nodes.Element row : channelContent.select("table tr")) {

								ChannelContentBean ccBean = new ChannelContentBean();

								ccBean.setProgramee(row.select("td:nth-of-type(1)").text());
								ccBean.setStart(row.select("td:nth-of-type(2)").text());
								ccBean.setEnd(row.select("td:nth-of-type(3)").text());
								ccBean.setDescription(row.select("td:nth-of-type(4)").text());
								ccBean.setChannelId(channelBean);

								int ids = channelContentService.save(ccBean);
//				        		   System.out.println(row);
								System.out.println("Programee : " + row.select("td:nth-of-type(1)").text());
								System.out
										.println("==================================================================");

//								To check content is already there or not
//								if (channelContentService.isContentPresent(row.select("td:nth-of-type(1)").text(),
//										channelBean, "ChannelContentBean")) {} else {
//									System.out.println("the program is already there for channel : "
//											+ channelBean.getChannelName());
//								}
							}
						}
					} else {
						System.out.println("Channel is already Present");
					}

				} else {
					System.out.println("Empty channel");
				}
			}
			printNextTriggerTime("0 0 10,16 * * *", LocalDateTime.of(2021, 8, 31, 15, 30, 0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printNextTriggerTime(String cronExpression, LocalDateTime currentTime) {
		CronSequenceGenerator generator = new CronSequenceGenerator(cronExpression);
		Date d = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
		for (int i = 0; i < 10; i++) {
			d = generator.next(d);
			System.out.println(d);
		}
	}
}
