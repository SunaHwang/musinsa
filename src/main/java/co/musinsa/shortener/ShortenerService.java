package co.musinsa.shortener;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.musinsa.chars.CharRepository;
import co.musinsa.log.LogRepository;
import co.musinsa.log.Logs;

@Service
public class ShortenerService {

	private int KEY_MAX_LENGTH = 8; //key의 최대 길이.
	
	@Autowired
	private ShortenerRepository shortenerRepository;
	
	@Autowired
	private LogRepository logRepository;

	@Autowired
	private CharRepository charRepository;

	
	/*
	 * URL 줄이기.
	 * param : 원래의 URL
	 * return : 변환된 URL
	 * =======================
	 * URL 변환을 호출할 때마다 log 기록됨.
	 * 기록된 log는 변환 전의 URL과, 로그 생성시간을 저장.
	 */
	public String createShortenUrl(String originUrl) {
		String resultUrl;
		
		originUrl = optimizeUrl(originUrl);
		
		Shortener oldShortener = shortenerRepository.findByOriginUrl(originUrl);

		if(oldShortener != null) {  //기존에 shortening된 url이 있으면.
			resultUrl = oldShortener.getShortenUrl();
		} else { //없으면 새로 생성.
			Shortener newShortener = new Shortener();
			newShortener.setOriginUrl(originUrl);
			newShortener.setShortenUrl(createKey(originUrl));
			
			shortenerRepository.save(newShortener);
			
			resultUrl = newShortener.getShortenUrl();
		}

		//로그 생성.
		Logs log = new Logs();
		log.setOriginUrl(originUrl);
		logRepository.save(log);
		
		return resultUrl;
	}
	
	
	/*
	 * url의 http(또는 https) 와 마지막 / 제거.
	 * param : 원래의 URL
	 * return : 최적화된 URL (해당 값이 DB에 저장됨)
	 */
	private String optimizeUrl(String url) {
		if(url.length() > 6) { // 'http://'가 글자수가 7이어서, 7자리 이상만 체크.
			if (url.substring(0, 7).equals("http://")) {
				url = url.substring(7);
			} else if (url.substring(0, 8).equals("https://")) {
				url = url.substring(8);
			}
		}
		
		if (url.charAt(url.length() - 1) == '/') {
			url = url.substring(0, url.length() - 1);
		}
		
		return url;
	}
	
	/*
	 * URL을 줄여주는 key 값 만들기.
	 * param : 원래의 URL
	 * 	원래의 URL 길이를 판단하여, key 길이를 다르게 생성.
	 * 	(url이 4자 ~ 8자일경우, key도 url 길이에 맞게 생성.
	 * 	8자 초과일 경우, 무조건 8자로 생성.)
	 * return : 생성된 key
	 */
	private String createKey(String originUrl) {
		String key = "";
		List<Character> chars = charRepository.findAllChars(); //키로 가능한 모든 값 가져오기.
		char allChars[] = new char[62];
		
		//변환된 key가 db에 존재하지 않을 때까지 해당 while문 실행.
		//만약 생성할 key가 이미 존재할 경우, 다시 key 생성.
		do { 
			key = "";
			Random keyRand = new Random();
			int i = 0;
			
			for(Character c : chars) { //배열로 변환.
				allChars[i] = c;
				i++;
			}
			
			if (originUrl.length() < KEY_MAX_LENGTH) { //8자보다 URL 길이가 짧을 경우, key 길이를 URL 길이만큼 생성.
				for(int j = 0; j < originUrl.length() ; j++) {
					key += allChars[keyRand.nextInt(62)];
				}
			} else { //8자보다 URL 길이가 클 경우, key 길이를 8자로 생성.
				for(int j = 0; j < KEY_MAX_LENGTH ; j++) {
					key += allChars[keyRand.nextInt(62)];
				}
			}
		} while(getshortenUrlCnt(key) > 0);

		return key;
	}
	
	/*
	 * 입력된 URL이 변환한 URL인지 체크.
	 * param : 변환된 URL
	 * return : count 수
	 */
	public int getshortenUrlCnt(String shortenUrl) {
		return shortenerRepository.countByShortenUrl(shortenUrl);
	}
	
	
	/*
	 * 원래의 URL로 확장.
	 * 	http://localhost/ 는 붙여지지 않는다고 가정. 
	 * 	즉, AodQs09z과 같이 생성된 key 값만 들어왔을 때, 이를 판단하고 변환되었던 값이면 원래의 URL을 return. 
	 * param : 변환된 URL (key값)
	 * return : 원래의 URL (http:// 혹은 https:// 는 붙이지 않음. 저장하지 않았음.
	 */
	public String getOriginUrl(String shortenUrl) {
		/*
		if(inUrl.getUrl().length() > 16) { // 'http://localhost/'가 글자수가 17이어서, 17자리 이상만 체크.
			if (inUrl.getUrl().substring(0, 17).equals("http://localhost/")) {
				url = inUrl.getUrl().substring(17);
			} else if (inUrl.getUrl().substring(0, 18).equals("https://localhost/")) {
				url = inUrl.getUrl().substring(18);
			}
		}
		*/
		Shortener shortener = shortenerRepository.findByShortenUrl(shortenUrl).orElse(new Shortener());
		
		if(shortener.getOriginUrl().length()>0) {
			return shortener.getOriginUrl();
		} else {
			return "Redirect Error!!";
		}

	}
}
