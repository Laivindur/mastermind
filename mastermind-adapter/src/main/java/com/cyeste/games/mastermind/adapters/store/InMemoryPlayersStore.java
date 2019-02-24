package com.cyeste.games.mastermind.adapters.store;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
public class InMemoryPlayersStore implements PlayersRepository {
	
	private final Map<Serializable,Player> datasource;
	
	public InMemoryPlayersStore() {
		datasource = new HashMap<Serializable, Player>();
	}

	@Override
	public void store(Player player) {
		datasource.put(player.getId(), player);

	}

	@Override
	public Player findPlayer(Serializable id) {
		return datasource.get(id);
	}

}
