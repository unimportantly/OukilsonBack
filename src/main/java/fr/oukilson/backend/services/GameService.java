package fr.oukilson.backend.services;


import fr.oukilson.backend.dtos.GameDTO;
import fr.oukilson.backend.dtos.GameUuidDTO;
import fr.oukilson.backend.entities.Game;
import fr.oukilson.backend.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.*;

public class GameService {


    private GameRepository repository;
    private ModelMapper mapper;

    public GameService(GameRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    /**
     * Return a list of games
     * @return List<gameDTO>
     */
    public List<GameDTO> findAll() {
        List<GameDTO> gameDTOList = new ArrayList<>();
        this.repository.findAll().forEach(game -> {
            gameDTOList.add(mapper.map(game, GameDTO.class));
        });
        return gameDTOList;
    }


    /**
     * Return a game from its ID
     * @param id Long
     * @return Optional<GameDTO>
     */
    public Optional<GameDTO> findById(final Long id) throws NoSuchElementException {
        Optional<Game> game = this.repository.findById(id);
        return Optional.of(mapper.map(game.get(), GameDTO.class));
    }

    /**
     * Persists a game
     * @param gameDTO GameDTO
     * @return EmployeDTO
     */
    public GameDTO save(GameDTO gameDTO) {
        Game game = mapper.map(gameDTO, Game.class);
        Game gameSaving = this.repository.save(game);
        GameDTO response = mapper.map(gameSaving, GameDTO.class);
        return response;
    }

    /**
     * Gives a game in function of its uuid
     * @param uuid
     * @return
     */
    public GameUuidDTO findByUuid(String uuid) {
        // All the games are selected
        List<Game> games = repository.findAll();
        // Game targeted is instanced
        GameUuidDTO targetedGameUuidDTO = new GameUuidDTO();
        // Loop on all the games
        for ( Game game : games) {
            String testedUuid = game.getUuid();
            // Test on uuid
            if (testedUuid.equals(uuid)) {
                targetedGameUuidDTO = mapper.map(game, GameUuidDTO.class);
                //break;
            }
        }
        return targetedGameUuidDTO;
    }

    /**
     * Gives a game in function of its uuid
     * @param name
     * @return
     */
    public GameUuidDTO findByName(String name) {
        // All the games are selected
        List<Game> games = repository.findAll();
        // Game targeted is instanced
        GameUuidDTO targetedGameUuidDTO = new GameUuidDTO();
        // Loop on all the games
        for ( Game game : games) {
            String testedName = game.getName();
            // Test on uuid
            if (testedName.equals(name)) {
                targetedGameUuidDTO = mapper.map(game, GameUuidDTO.class);
                //break;
            }
        }
        return targetedGameUuidDTO;
    }

    public GameDTO DisplayByUuid(String uuid) {
        // All the games are selected
        List<Game> games = repository.findAll();
        // Game targeted is instanced
        GameDTO targetedGameDTO = new GameDTO();
        // Loop on all the games
        for ( Game game : games) {
            String testedUuid = game.getUuid();
            // Test on uuid
            if (testedUuid.equals(uuid)) {
                targetedGameDTO = mapper.map(game, GameDTO.class);
                //break;
            }
        }
        return targetedGameDTO;
    }


}
