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

import com.cyeste.games.mastermind.usescases.command.CreatePlayer;
import com.cyeste.games.mastermind.usescases.query.FindPlayer;
import com.cyeste.games.mastermind.usescases.query.FindPlayerBoards;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
@RestController("playersController")
@RequestMapping(value = "/players", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class PlayersController {

	private FindPlayer findPlayerUseCasehandler;
	private FindPlayerBoards findBoardsUseCasehandler;
	private CreatePlayer createUserUseCasehandler;
	
	
	@Autowired
	public PlayersController(FindPlayer findPlayerUseCasehandler, FindPlayerBoards findBoardsUseCasehandler,
			CreatePlayer createUserUseCasehandler) {
		this.findPlayerUseCasehandler = findPlayerUseCasehandler;
		this.findBoardsUseCasehandler = findBoardsUseCasehandler;
		this.createUserUseCasehandler = createUserUseCasehandler;
	}

	@RequestMapping(value = "/{playerId}", method = RequestMethod.GET)
	public ResponseEntity<ResourceSupport> find(@PathVariable("playerId") final String playerId) {
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/{playerId}/boards/", method = RequestMethod.GET)
	public ResponseEntity<ResourceSupport> playerBoards(@PathVariable("playerId") final String playerId) {
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/{playerId}/boards-as-codemaker/", method = RequestMethod.GET)
	public ResponseEntity<ResourceSupport> playerBoardsAsCodeMaker(@PathVariable("playerId") final String playerId) {
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/{playerId}/boards-as-codebreaker/", method = RequestMethod.GET)
	public ResponseEntity<ResourceSupport> playerBoardsAsCodeBreaker(@PathVariable("playerId") final String playerId) {
		throw new UnsupportedOperationException();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<ResourceSupport> create(@RequestParam("name") final String userName) {
		throw new UnsupportedOperationException();
	}
}
