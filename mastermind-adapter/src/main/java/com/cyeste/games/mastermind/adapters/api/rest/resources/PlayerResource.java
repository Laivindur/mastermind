package com.cyeste.games.mastermind.adapters.api.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.cyeste.games.mastermind.domain.Player;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Representación REST (Hateoas) de Player
 * @author Christian Yeste Vidal
 *
 */
@JsonRootName("player")
public class PlayerResource extends ResourceSupport {

	private String identifier;
	private String name;
	
	PlayerResource(String identifier, String name) {
		this.identifier = identifier;
		this.name = name;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	@JsonProperty("identifier")
	public String getIdentifier() {
		return identifier;
	}
			
	public static PlayerResourceAssembler builder(Class<?> controller) {
		return new PlayerResourceAssembler(controller);
	}
	
	public static class PlayerResourceAssembler extends ResourceAssemblerSupport<Player,PlayerResource>{

		private Class<?> controller;
		private List<Link> relatedLinks;
		
		public PlayerResourceAssembler(Class<?> controller) {
			super(controller,PlayerResource.class);
			this.controller = controller;
			this.relatedLinks = new ArrayList<Link>();
		}
		
		@Override
		public PlayerResource toResource(Player entity) {
			PlayerResource resource = new PlayerResource(entity.getId().toString(),entity.getName());
			resource.add(linkTo(controller).slash(entity.getId()).withSelfRel());
			resource.add(relatedLinks);
			return resource;
		}
		
		public PlayerResourceAssembler addLink(Class<?> controller, String path, String relName) {
			relatedLinks.add(linkTo(controller).slash(path).withRel(relName));
			return this;
		}
		
		public PlayerResourceAssembler addLink(Class<?> controller, String path, String id, String relName) {
			relatedLinks.add(linkTo(controller).slash(id).slash(path).withRel(relName));
			return this;
		}
	}
}
