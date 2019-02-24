package com.cyeste.games.mastermind.usescases.command;

import java.io.Serializable;
import java.util.Optional;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;
import com.cyeste.games.mastermind.domain.utils.Validations;

public class CreatePlayerBoard {

	private final PlayerBoardsRepository repository;
	private final IdGenerator<Serializable> idGenerator;

	public CreatePlayerBoard(PlayerBoardsRepository repository, IdGenerator<Serializable> idGenerator) {
		this.repository = repository;
		this.idGenerator = idGenerator;
	}

	public PlayerBoard joinBoardAsCodeMaker(Player codeMaker, DecodingBoard board) {
		Validations.whenNull(codeMaker).throwIllegalArgumentException("Code maker is required");
		Validations.whenNull(board).throwIllegalArgumentException("Board is required");
		
		Optional<PlayerBoard> existingPlayerBoard = Optional.ofNullable(repository.findPlayerBoard(codeMaker, board));
		if (existingPlayerBoard.isPresent()) {

			Validations.when(!existingPlayerBoard.get().isCoder())
					.throwIllegalArgumentException("The board " + board.getId() + " already has a code maker");
			return existingPlayerBoard.get();

		}
		PlayerBoard playerBoard = PlayerBoard.playerCodeMaker(idGenerator.generate(), codeMaker, board);
		repository.store(playerBoard);
		return playerBoard;
	}

	public PlayerBoard joinBoardAsCodeBreaker(Player codeBreaker, DecodingBoard board) {
		Validations.whenNull(codeBreaker).throwIllegalArgumentException("Code maker is required");
		Validations.whenNull(board).throwIllegalArgumentException("Board is required");
		
		Optional<PlayerBoard> existingPlayerBoard = Optional.ofNullable(repository.findPlayerBoard(codeBreaker, board));
		if (existingPlayerBoard.isPresent()) {
			Validations.when(existingPlayerBoard.get().isCoder())
					.throwIllegalArgumentException("Code makers can not join to their own boards as code breakers");
			return existingPlayerBoard.get();

		}
		PlayerBoard playerBoard = PlayerBoard.playerCodeBreaker(idGenerator.generate(), codeBreaker, board);
		repository.store(playerBoard);
		return playerBoard;
	}
}
