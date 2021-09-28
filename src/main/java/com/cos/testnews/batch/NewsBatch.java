package com.cos.testnews.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.testnews.domain.News;
import com.cos.testnews.domain.NewsRepository;
import com.cos.testnews.util.NaverCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NewsBatch {

	private final NewsRepository newsRepository;
	private final NaverCraw naverCraw; // DI
	
	// 초 분 시 일 월 주
	// @Scheduled(cron = "0 2 * * * *", zone = "Asia/Seoul") // 특정시간 동작
	@Scheduled(fixedDelay=1000*60*1)
	public void newsCrawAndSave() {
	
		List<News> newsList = naverCraw.collect5();
		
		newsRepository.saveAll(newsList);
	}
}
