package org.layton.SpringBootThymeleaf;

import org.layton.SpringBootThymeleaf.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestClientService {

    private String url = "https://gamesrepo.herokuapp.com/games";

    @Autowired
    private RestTemplate restTemplate;

    public Game[] getAllGames() {
        //List<Game> games = restTemplate.getForObject(url, Game[].class);
        Game[] games = restTemplate.getForObject(url, Game[].class);
        return games;
    }

    public Game getGame(String id) {
        Game game = restTemplate.getForObject(url + "/" + id, Game.class);
        return game;
    }

    public void addGame(Game game) {
        // Data attached to the request.
        HttpEntity<Game> requestBody = new HttpEntity<>(game);

        // Send request with POST method.
        ResponseEntity<Game> result = restTemplate.postForEntity(url, requestBody, Game.class);

        System.out.println("Status code:" + result.getStatusCode());

        // Code = 200.
        if (result.getStatusCode() == HttpStatus.OK) {
            Game g = result.getBody();
            System.out.println("(client side) Game Created: " + g.getId());
        }
    }

    public void updateGame(String id, Game game) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        // Data attached to the request.
        HttpEntity<Game> requestBody = new HttpEntity<>(game, headers);

        // Send request with PUT method.
        restTemplate.put(url + "/" + id, requestBody, new Object[] {});
    }

    public void deleteGame(String id) {
        // Send request with DELETE method.
        String resourceUrl = url + "/" + id;

        restTemplate.delete(resourceUrl);

        Game g = restTemplate.getForObject(resourceUrl, Game.class);

        if (g != null) {
            System.out.println("(Client side) Game after delete: ");
            System.out.println("Game: " + g.getId() + " - " + g.getName());
        } else {
            System.out.println("Game not found!");
        }
    }
}