package mybnb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HostpageRepository extends CrudRepository<Hostpage, Long> {

    List<Hostpage> findByBookId(Long bookId);

}