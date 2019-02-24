package com.cyeste.games.mastermind.adapters.api.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.GuessResult;
import com.cyeste.games.mastermind.domain.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Representación REST (Hateoas) de Player
 * @author Christian Yeste Vidal
 *
 */
@JsonRootName("board")
public class BoardResource extends ResourceSupport {

	private String identifier;
	private GuessResource[] guesses;
	private PatternResource code;
	private boolean solved;
	private boolean leftMoreGames;
	
	BoardResource(String identifier, GuessResource[] guesses, boolean solved, boolean leftMoreGames) {
		this.identifier = identifier;
		this.solved = solved;
		this.guesses = guesses;
		this.leftMoreGames = leftMoreGames;
	}
	
	
	@JsonProperty("guesses")
	public GuessResource[] getGuesses() {
		return guesses;
	}
	
	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}
	
	@JsonProperty("code")
	public PatternResource getCode() {
		return code;
	}
	
	@JsonProperty("leftMoreGames")
	public boolean isLeftMoreGames() {
		return leftMoreGames;
	}
	
	@JsonProperty("solved")
	public boolean isSolved() {
		return solved;
	}
			
	public static PlayerResourceAssembler builder(Class<?> controller) {
		return new PlayerResourceAssembler(controller);
	}
	
	public static class PlayerResourceAssembler extends ResourceAssemblerSupport<DecodingBoard,BoardResource>{

		private Class<?> controller;
		private List<Link> relatedLinks;
		private PatternResource code;
		
		public PlayerResourceAssembler(Class<?> controller) {
			super(controller,BoardResource.class);
			this.controller = controller;
			this.relatedLinks = new ArrayList<Link>();
		}
		
		@Override
		public BoardResource toResource(DecodingBoard entity) {
			BoardResource resource = new BoardResource(entity.getId().toString(), toResource(entity.games()), entity.isSolved(), entity.leftGames());
			resource.add(linkTo(controller).slash(entity.getId()).withSelfRel());
			resource.add(relatedLinks);
			resource.code = code;
			return resource;
		}
		
		private GuessResource[] toResource(Iterator<GuessResult> games) {
			List<GuessResource> resources = new LinkedList<GuessResource>();
			while(games.hasNext()) {
				GuessResult guessResult = games.next();
				PatternResource guessPattern = new PatternResource(guessResult.getGuess().toStringArray());
				resources.add(new GuessResource(guessPattern, guessResult.getColoredPegs(), guessResult.getWhitePegs(), guessResult.isExactMatch()));
			}
			return resources.toArray(new GuessResource[] {});
		}

		public PlayerResourceAssembler addLink(Class<?> controller, String path, String relName) {
			relatedLinks.add(linkTo(controller).slash(path).withRel(relName));
			return this;
		}
		
		public PlayerResourceAssembler addLink(Class<?> controller, String path, String id, String relName) {
			relatedLinks.add(linkTo(controller).slash(id).slash(path).withRel(relName));
			return this;
		}
		
		public PlayerResourceAssembler code(Pattern code) {
			this.code = new PatternResource(code.toStringArray());
			return this;
		}
		
	}
	
	@JsonRootName("code")
	public static final class PatternResource implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		PatternResource(String[] sequence) {
			this.sequence = sequence;
		}
		
		@JsonProperty("sequence")
		private final String sequence[];
		
	}
	
	@JsonRootName("guess")
	public static final class GuessResource implements Serializable{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		GuessResource(PatternResource guessPattern, int coloredPegs, int whitePegs, boolean solved) {
			this.guess = guessPattern;
			this.coloredPegs = coloredPegs;
			this.whitePegs = whitePegs;
			this.solved = solved;
		}

		@JsonProperty("guess")
		private PatternResource guess;
		
		@JsonProperty("exact")
		private int coloredPegs;
		
		@JsonProperty("near")
		private int whitePegs;
		
		@JsonProperty("solved")
		private boolean solved;
	}
}
