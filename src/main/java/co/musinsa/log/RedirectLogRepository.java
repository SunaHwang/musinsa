package co.musinsa.log;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedirectLogRepository extends CrudRepository<RedirectLogs,Long> {

	Optional<RedirectLogs> findByShortenUrl(String shortenUrl);
	
}
