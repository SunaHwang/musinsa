package co.musinsa.shortener;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends CrudRepository<Shortener,Long> {

	Shortener findByOriginUrl(String originUrl);
	
	Optional<Shortener> findByShortenUrl(String shortenUrl);
	
	int countByShortenUrl(String shortenUrl);
	
}
