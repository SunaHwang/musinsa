package co.musinsa.util.chars;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharsService {

	@Autowired
	private CharRepository charRepository;
	
	@PostConstruct
	public void init() {
/*
		for(int i=0; i<=9; i++) {
			Chars num = new Chars();
			num.setChars((char) i);
			charRepository.save(num);
		}
*/
	}
}
