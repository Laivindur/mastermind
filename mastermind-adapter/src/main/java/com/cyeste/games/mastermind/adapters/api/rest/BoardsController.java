package com.cyeste.games.mastermind.adapters.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyeste.games.mastermind.usescases.command.CreateBoard;
import com.cyeste.games.mastermind.usescases.query.FindBoard;
import com.cyeste.games.mastermind.usescases.query.FindPlayerBoards;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
@RestController("boardsController")
@RequestMapping(value = "/boards", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class BoardsController {

	private FindBoard findBoardUseCaseHandler;
	private FindPlayerBoards findBoardsUseCaseHandler;
	private CreateBoard createBoardUseCaseHandler;
	
	
	@Autowired
	public BoardsController(FindBoard findBoardUseCaseHandler, FindPlayerBoards findBoardsUseCaseHandler,
			CreateBoard createBoardUseCaseHandler) {
		this.findBoardUseCaseHandler = findBoardUseCaseHandler;
		this.findBoardsUseCaseHandler = findBoardsUseCaseHandler;
		this.createBoardUseCaseHandler = createBoardUseCaseHandler;
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<ResourceSupport> findAll() {
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/{boardId}", method = RequestMethod.GET)
	public ResponseEntity<ResourceSupport> find(@PathVariable("boardId") final String boardId) {
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<ResourceSupport> create(
			@RequestParam("codeMakerId") final String codeMakerId,
			@RequestParam("code")  final String[] code) {
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/{boardId}/boards/guess", method = RequestMethod.PUT)
	public ResponseEntity<ResourceSupport> boardPlayers(
			@PathVariable("codeBreakerId") final String codeBreakerId,
			@PathVariable("boardId") final String boardId,
			@RequestParam("guess") final String[] guess) {
		throw new UnsupportedOperationException();
	}
}
