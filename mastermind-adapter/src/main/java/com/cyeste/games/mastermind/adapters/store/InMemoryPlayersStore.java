package com.cyeste.games.mastermind.adapters.store;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

/**
 * Player's in-memomry store candidate to PlayersRepository.
 * Repositories are intrinsecally linked to the persitence. Persistence is hortogonal to the architecture. Since persistence is hortogonal, 
 * ideally we should segregate the domain data model from the persistence data model, so that domain is never affected by changes on the persistence layer
 * and viceversa.
 * For instance, we could implement POJOS or DTOS as representations of Player and to persist such representation instead of the exact Player data structure.
 * For simplicity, I have decided to persist in-memory the same Entity (Player)
 * @author Christian Yeste Vidal
 *
 */
@Repository
public class InMemoryPlayersStore extends AbstractInMemoryStore implements PlayersRepository {
	
		
	public InMemoryPlayersStore() {
		super();
	}

	@Override
	public void store(Player player) {
		getDatasource().put(player.getId(), player);

	}

	@Override
	public Player findById(String id) {
		Optional<Object> entity = Optional.ofNullable(getDatasource().get(id));
		if(entity.isPresent()) {
			return Player.class.cast(entity.get());
		}
		return null;
	}

}
