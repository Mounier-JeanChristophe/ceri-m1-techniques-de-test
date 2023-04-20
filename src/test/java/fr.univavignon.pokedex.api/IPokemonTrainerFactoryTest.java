package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IPokemonTrainerFactoryTest {

    private Pokemon bulbi;
    private Pokemon aqua;
    private final IPokedexFactory pokedexFactory = new PokedexFactory();
    private final IPokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
    private final IPokemonFactory pokemonFactory = new PokemonFactory();
    private final IPokemonTrainerFactory pokemonTrainerFactory = new PokemonTrainerFactory();

    private IPokedex pokedex;
    private PokemonTrainer pkmTrainer;


    @Before
    public void setUp(){
        bulbi = new Pokemon(0, "Bulbizarre", 126, 126, 90, 50, 140, 56, 12, 2);
        aqua = new Pokemon(133, "Aquali", 186, 168, 260, 100, 90, 49, 7, 1);
        pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);
        pkmTrainer = new PokemonTrainer("Maurice", Team.MYSTIC, pokedex);
    }

    @Test
    public void createTrainerTest(){
        PokemonTrainer trainerTest = pokemonTrainerFactory.createTrainer("Maurice",Team.MYSTIC, pokedexFactory);
        assertEquals(pkmTrainer.getName(), trainerTest.getName());
        assertEquals(pkmTrainer.getTeam(), trainerTest.getTeam());
        assertEquals(pkmTrainer.getPokedex().getPokemons(), trainerTest.getPokedex().getPokemons());
    }

    @Test
    public void getNameTest(){
        assertEquals("Maurice",pkmTrainer.getName());
    }

    @Test
    public void getTeamTest(){
        assertEquals(Team.MYSTIC, pkmTrainer.getTeam());
    }

    @Test
    public void getIPokedexTest(){
        assertEquals(pokedex.getPokemons(), pkmTrainer.getPokedex().getPokemons());
    }
}
