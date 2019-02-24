package com.cyeste.games.mastermind.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.cyeste.games.mastermind.adapters.generator.UUIDGenerator;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;
import com.cyeste.games.mastermind.usescases.command.CreateBoard;
import com.cyeste.games.mastermind.usescases.command.CreatePlayer;
import com.cyeste.games.mastermind.usescases.command.GuessBoard;
import com.cyeste.games.mastermind.usescases.command.JoinBoard;
import com.cyeste.games.mastermind.usescases.query.FindBoard;
import com.cyeste.games.mastermind.usescases.query.FindPlayer;
import com.cyeste.games.mastermind.usescases.query.FindPlayerBoards;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);
	private static final String USE_CASES_HANDLER_PATTERN = "[%s] handler initialized!";
	
	@Bean
	public IdGenerator idGenerator() {
		return new UUIDGenerator();
	}
	
	
	@Bean
	@Autowired
	public FindBoard gindBoardsHandler(DecodingBoardsRepository store) {
		FindBoard usecasesHandler = new FindBoard(store);
		LOGGER.info("{}",String.format(USE_CASES_HANDLER_PATTERN, usecasesHandler.getClass().getName()));
		return usecasesHandler;
	}
	
	@Bean
	@Autowired
	public FindPlayer findPlayersHandler(PlayersRepository store) {
		FindPlayer usecasesHandler = new FindPlayer(store);
		LOGGER.info("{}",String.format(USE_CASES_HANDLER_PATTERN, usecasesHandler.getClass().getName()));
		return usecasesHandler;
	}
	
	@Bean
	@Autowired
	public FindPlayerBoards findPlayerBoardsHandler(PlayerBoardsRepository store) {
		FindPlayerBoards usecasesHandler = new FindPlayerBoards(store);
		LOGGER.info("{}",String.format(USE_CASES_HANDLER_PATTERN, usecasesHandler.getClass().getName()));
		return usecasesHandler;
	}
	
	@Bean
	@Autowired
	public CreateBoard createBoardsHandler(DecodingBoardsRepository store,IdGenerator idGenerator) {
		CreateBoard usecasesHandler = new CreateBoard(store,idGenerator);
		LOGGER.info("{}",String.format(USE_CASES_HANDLER_PATTERN, usecasesHandler.getClass().getName()));
		return usecasesHandler;
	}
	
	@Bean
	@Autowired
	public CreatePlayer createPlayersHandler(PlayersRepository store,IdGenerator idGenerator) {
		CreatePlayer usecasesHandler = new CreatePlayer(store,idGenerator);
		LOGGER.info("{}",String.format(USE_CASES_HANDLER_PATTERN, usecasesHandler.getClass().getName()));
		return usecasesHandler;
	}
	
	@Bean
	@Autowired
	public GuessBoard guessBoardsHandler(DecodingBoardsRepository store) {
		GuessBoard usecasesHandler = new GuessBoard(store);
		LOGGER.info("{}",String.format(USE_CASES_HANDLER_PATTERN, usecasesHandler.getClass().getName()));
		return usecasesHandler;
	}
	
	@Bean
	@Autowired
	public JoinBoard joinBoardsHandler(PlayerBoardsRepository store,IdGenerator idGenerator) {
		JoinBoard usecasesHandler = new JoinBoard(store,idGenerator);
		LOGGER.info("{}",String.format(USE_CASES_HANDLER_PATTERN, usecasesHandler.getClass().getName()));
		return usecasesHandler;
	}
}
