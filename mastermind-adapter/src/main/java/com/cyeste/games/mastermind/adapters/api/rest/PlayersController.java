package com.cyeste.games.mastermind.adapters.api.rest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyeste.games.mastermind.adapters.api.rest.exception.ResourceNotFoundException;
import com.cyeste.games.mastermind.adapters.api.rest.resources.BoardResource;
import com.cyeste.games.mastermind.adapters.api.rest.resources.PlayerResource;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.usescases.command.CreatePlayer;
import com.cyeste.games.mastermind.usescases.query.FindPlayer;
import com.cyeste.games.mastermind.usescases.query.FindPlayerBoards;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
@RestController("playersController")
@RequestMapping(value = "/players", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
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
	@ResponseBody
	public ResponseEntity<PlayerResource> find(@PathVariable("playerId") final String playerId) {
		Player player = findPlayerUseCasehandler.find(playerId);
		if (player != null) {
			return ResponseEntity.ok(PlayerResource.toResource(player));
		}
		throw new ResourceNotFoundException(PlayerResource.class, playerId);
	}

	@RequestMapping(value = "/{playerId}/boards/", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<BoardResource>> playerBoards(@PathVariable("playerId") final String playerId) {
		Player player = findPlayerUseCasehandler.find(playerId);
		if (player == null) {
			throw new ResourceNotFoundException(PlayerResource.class, playerId);
		}
		List<BoardResource> resources = new LinkedList<BoardResource>();
		Iterator<PlayerBoard> playerBoards = findBoardsUseCasehandler.findAllPlayersBoard(player);
		while (playerBoards.hasNext()) {
			resources.add(BoardResource.toResource(playerBoards.next()));
		}
		return ResponseEntity.ok(resources);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<PlayerResource> create(@RequestParam("name") final String userName) {
		Player player = createUserUseCasehandler.create(userName);
		return ResponseEntity.ok(PlayerResource.toResource(player));
	}
}
