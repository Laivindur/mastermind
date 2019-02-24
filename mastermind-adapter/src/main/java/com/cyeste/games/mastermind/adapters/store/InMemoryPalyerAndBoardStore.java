package com.cyeste.games.mastermind.adapters.store;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;



/**
 * Player&Board's in-memomry store candidate to Player&BoardsRepository.
 * Repositories are intrinsecally linked to the persitence. Persistence is hortogonal to the architecture. Since persistence is hortogonal, 
 * ideally we should segregate the domain data model from the persistence data model, so that domain is never affected by changes on the persistence layer
 * and viceversa.
 * For instance, we could implement POJOS or DTOS as representations of Player&Board and to persist such representation instead of the exact Player&Board data structure.
 * For simplicity, I have decided to persist in-memory the same Entity (Player&Board)
 * @author Christian Yeste Vidal
 *
 */
@Repository("inPlayersAndBoardsStore")
public class InMemoryPalyerAndBoardStore extends AbstractInMemoryStore implements PlayerBoardsRepository {
	
		
	public InMemoryPalyerAndBoardStore() {
		super();
	}

	@Override
	public void store(PlayerBoard playerBoard) {
		getDatasource().put(playerBoard.getId(), playerBoard);
		
	}

	@Override
	public PlayerBoard findById(Serializable id) {
		Optional<Object> entity = Optional.ofNullable(getDatasource().get(id));
		if(entity.isPresent()) {
			return PlayerBoard.class.cast(entity.get());
		}
		return null;
	}

	@Override
	public PlayerBoard findPlayerBoard(Player player, DecodingBoard board) {
		return getDatasource()
				.values()
					.stream()
						.map(obj-> PlayerBoard.class.cast(obj))
							.filter(entry -> {
								//XXX : Podria moverse a un método equals o same()
								return entry.getBoard().getId().equals(board.getId()) &&
									entry.getPlayer().getId().equals(player.getId());
							})
							.findFirst().orElse(null);
	}

	@Override
	public Collection<PlayerBoard> findBoards(Player player) {
		return getDatasource()
				.values()
					.stream()
						.map(obj-> PlayerBoard.class.cast(obj))
							.filter(entry -> {
								return entry.getPlayer().getId().equals(player.getId());
							})
							.collect(Collectors.toList());
	}

	@Override
	public Collection<PlayerBoard> findPlayers(DecodingBoard board) {
		return getDatasource()
				.values()
					.stream()
						.map(obj-> PlayerBoard.class.cast(obj))
							.filter(entry -> {
								return 	entry.getBoard().getId().equals(board.getId());
							})
							.collect(Collectors.toList());
	}

}
