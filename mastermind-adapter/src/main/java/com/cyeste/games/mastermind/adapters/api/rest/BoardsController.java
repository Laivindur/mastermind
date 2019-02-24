package com.cyeste.games.mastermind.adapters.api.rest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cyeste.games.mastermind.adapters.api.rest.command.PlayerCodeInput;
import com.cyeste.games.mastermind.adapters.api.rest.command.PlayerGuessInput;
import com.cyeste.games.mastermind.adapters.api.rest.exception.ResourceNotFoundException;
import com.cyeste.games.mastermind.adapters.api.rest.resources.BoardResource;
import com.cyeste.games.mastermind.adapters.api.rest.resources.PlayerResource;
import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.usescases.command.CreateBoard;
import com.cyeste.games.mastermind.usescases.command.GuessBoard;
import com.cyeste.games.mastermind.usescases.command.JoinBoard;
import com.cyeste.games.mastermind.usescases.query.FindBoard;
import com.cyeste.games.mastermind.usescases.query.FindPlayer;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
@RestController("boardsController")
@RequestMapping(value = "/boards", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class BoardsController {

	private FindPlayer findPlayerUseCaseHandler;
	private FindBoard findBoardUseCaseHandler;
	private CreateBoard createBoardUseCaseHandler;
	private JoinBoard joinBoardUseCaseHandler;
	private GuessBoard guessBoardUsecaseHandler;
	
	
	@Autowired
	public BoardsController(FindPlayer findPlayerUseCaseHandler, FindBoard findBoardUseCaseHandler,
			CreateBoard createBoardUseCaseHandler, JoinBoard joinBoardUseCaseHandler,
			GuessBoard guessBoardUsecaseHandler) {
		this.findPlayerUseCaseHandler = findPlayerUseCaseHandler;
		this.findBoardUseCaseHandler = findBoardUseCaseHandler;
		this.createBoardUseCaseHandler = createBoardUseCaseHandler;
		this.joinBoardUseCaseHandler = joinBoardUseCaseHandler;
		this.guessBoardUsecaseHandler = guessBoardUsecaseHandler;
	}

	@RequestMapping( method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<BoardResource>> all() {
		Iterator<DecodingBoard> boards = findBoardUseCaseHandler.findAll();
		List<BoardResource> resources = new LinkedList<BoardResource>();
		while(boards.hasNext()) {
			resources.add(BoardResource.toResource(boards.next()));
		}
		return ResponseEntity.ok(resources);
	}
	
	@RequestMapping(value = "/{boardId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BoardResource> find(@PathVariable("boardId") final String boardId) {
		DecodingBoard board = findBoardUseCaseHandler.find(boardId);
		if (board != null) {
			return ResponseEntity.ok(BoardResource.toResource(board));
		}
		throw new ResourceNotFoundException(BoardResource.class, boardId);
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BoardResource> create(@RequestBody @Valid final PlayerCodeInput input, UriComponentsBuilder uriBuiilder) {
		//XXX Dada la implicación de varios boundaries, deberíamos mover esto a un servicio de aplicación
		Player codeMaker = findPlayerUseCaseHandler.find(input.getCodeMakerId());
		if (codeMaker != null) {
			DecodingBoard board = createBoardUseCaseHandler.create(input.getCode());
			PlayerBoard join = joinBoardUseCaseHandler.joinAsCodeMaker(codeMaker, board);			
			return ResponseEntity.ok(BoardResource.toResource(join));
		}
		throw new ResourceNotFoundException(PlayerResource.class, input.getCodeMakerId());
		
		
		
	}
	
	@RequestMapping(value = "/{boardId}/boards/guess", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<BoardResource> guess(@RequestBody @Valid final PlayerGuessInput input, UriComponentsBuilder uriBuiilder) {
		
		Player codeBreaker = findPlayerUseCaseHandler.find(input.getCodeBreakerId());
		if (codeBreaker != null) {
			throw new ResourceNotFoundException(PlayerResource.class, input.getCodeBreakerId());
		}
		DecodingBoard board = findBoardUseCaseHandler.find(input.getBoardId());
		if (board != null) {
			throw new ResourceNotFoundException(BoardResource.class, input.getBoardId());
		}
		PlayerBoard join = joinBoardUseCaseHandler.joinAsCodeBreaker(codeBreaker, board);
		guessBoardUsecaseHandler.guess(join, input.getGuess());
		return ResponseEntity.ok(BoardResource.toResource(join));
		
	}
}
