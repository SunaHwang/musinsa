package co.musinsa.shortener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
		int cnt = 0;
		
		try {
			//입력된 URL이 변환된 URL인지 체크.
			cnt = this.shortenerService.getshortenUrlCnt(inUrl.getUrl());
			
			if(cnt == 0) { //변환된 URL이 아니면, URL 변환해서 return.
				url = this.shortenerService.createShortenUrl(inUrl.getUrl());
			} else { //변환된 URL일 경우, expand해서 return.
				url = this.shortenerService.getOriginUrl(inUrl.getUrl());
			}

			map.put("status", "success");
			map.put("message", url);
			
		} catch (Exception e) {
			map.put("status", "fail");
			map.put("message", e.getMessage());
		}
		

		this.log.debug(map.toString());
		return map;
	}
	
}
