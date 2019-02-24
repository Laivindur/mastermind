package com.cyeste.games.mastermind.usescases.command;

import java.io.Serializable;
import java.util.Arrays;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Pattern;
import com.cyeste.games.mastermind.domain.Pattern.PatternBuilder;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.utils.Validations;

public class CreateBoard {

	//TODO : Make this parametrizable
	private static final int DEFAULT_MAX_GAMES_LENGTH = 5; 
	private static final int DEFAULT_CODE_LENGTH = 5;
	
	private DecodingBoardsRepository store;
	private final IdGenerator<Serializable> idGenerator;

	public CreateBoard(DecodingBoardsRepository repository, IdGenerator<Serializable> idGenerator) {
		this.idGenerator = idGenerator;
		this.store = repository;
	}
	
	public DecodingBoard create(String[] colorSequence) {
		Validations.whenEmpty(colorSequence).throwIllegalArgumentException("A sequence of colors is required");
		Validations.when(colorSequence.length != DEFAULT_CODE_LENGTH).throwIllegalArgumentException("Code length should be of " + DEFAULT_CODE_LENGTH+ " colors length");
		PatternBuilder codeBuilder = Pattern.builder();
		Arrays.stream(colorSequence).forEach(color -> codeBuilder.addPeg(color));
		DecodingBoard board = DecodingBoard.createBoard(idGenerator.generate(), DEFAULT_MAX_GAMES_LENGTH, codeBuilder.build());
		store.store(board);
		return board;
	}
	
}
