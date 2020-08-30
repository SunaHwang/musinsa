package co.musinsa.chars;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharRepository extends CrudRepository<Chars,Integer>{

	@Query("SELECT chars FROM Chars")
	List<Character> findAllChars();
}
