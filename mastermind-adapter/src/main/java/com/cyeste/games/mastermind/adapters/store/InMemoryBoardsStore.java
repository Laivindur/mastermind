package com.cyeste.games.mastermind.adapters.store;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;

/**
 * Board's in-memomry store candidate to BoardsRepository.
 * Repositories are intrinsecally linked to the persitence. Persistence is hortogonal to the architecture. Since persistence is hortogonal, 
 * ideally we should segregate the domain data model from the persistence data model, so that domain is never affected by changes on the persistence layer
 * and viceversa.
 * For instance, we could implement POJOS or DTOS as representations of Board and to persist such representation instead of the exact Board data structure.
 * For simplicity, I have decided to persist in-memory the same Entity (Board)
 * @author Christian Yeste Vidal
 *
 */
public class InMemoryBoardsStore extends AbstractInMemoryStore implements DecodingBoardsRepository{

	public InMemoryBoardsStore() {
		super();
	}
	
	@Override
	public void store(DecodingBoard board) {		
		getDatasource().put(board.getId(), board);
	}

	@Override
	public Collection<DecodingBoard> getAllBoards() {
		return getDatasource()
				.values()
					.stream()
						.map(obj-> DecodingBoard.class.cast(obj))
							.collect(Collectors.toList());
	}

	@Override
	public DecodingBoard findBoardById(Serializable id) {
		Optional<Object> entity = Optional.ofNullable(getDatasource().get(id));
		if(entity.isPresent()) {
			return DecodingBoard.class.cast(entity.get());
		}
		return null;
	}

}
