package co.musinsa.shortener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.musinsa.shortener.payload.InUrlPayload;

@Controller
public class ShortenerController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShortenerService shortenerService;
	
	@GetMapping("/")
	public String shortener() {
		return "shortener";
	}
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelMap returnUrl(@RequestBody InUrlPayload inUrl) {
		ModelMap map = new ModelMap();
		String url = "";
		
		try {
			url = shortenerService.createShortenUrl(inUrl.getUrl());

			map.put("status", "success");
			map.put("message", url);
		
		} catch (Exception e) {
			map.put("status", "fail");
			map.put("message", e.getMessage());
		}

		this.log.debug(map.toString());
		return map;
	}
	
	@RequestMapping(value="/{key}", method=RequestMethod.GET)
	public String redirectUrl(@PathVariable("key") String key) throws IOException {
		//입력된 URL이 변환된 URL인지 체크.
		int cnt = shortenerService.getshortenUrlCnt(key);
			
		if(cnt > 0) { //변환된 URL일 경우 원래의 주소로 redirect. (http://로 redirect)
			return "redirect:http://" + shortenerService.getOriginUrl(key);
		}
		// 변환되지 않은 URL일 경우 오류 페이지로 이동.
		return "error";
	}
	
}
