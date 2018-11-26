package be.ucll.cityquest.games;

import be.ucll.cityquest.games.model.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
import java.util.stream.Stream;

public interface GameRepository extends CrudRepository<Game, UUID> {

    @Query("select g from Game g")
    Stream<Game> findAllAndStream();

}
