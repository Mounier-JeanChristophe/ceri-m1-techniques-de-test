package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class IPokedexTest {

    IPokedex pokedex;
    IPokedexFactory pokedexFactory;
    IPokemonFactory pokemonFactory;
    IPokemonMetadataProvider pokemonMetadataProvider;
    Pokemon bulbi, aqua;

    @Before
    public void setUp() {
        pokedexFactory = new PokedexFactory();
        pokemonFactory = new PokemonFactory();
        pokemonMetadataProvider = new PokemonMetadataProvider();
        pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);

        bulbi = pokemonFactory.createPokemon(0, 10, 56, 12, 2);
        aqua = pokemonFactory.createPokemon(133, 13, 49, 7, 1);

        pokedex.addPokemon(bulbi);
        pokedex.addPokemon(aqua);
    }

    @Test
    public void sizeTest(){
        assertEquals(2,pokedex.size());
    }

    @Test
    public void addPokemonTest(){
        Pokemon aquaTest = pokemonFactory.createPokemon(133, 10, 50, 6, 1);
        assertEquals(2, pokedex.addPokemon(aquaTest));
    }

    @Test
    public void getPokemonTest() throws PokedexException {
        assertEquals(bulbi, pokedex.getPokemon(0));
    }

    @Test
    public void getPokemonsTest(){
        List<Pokemon> res = pokedex.getPokemons();

        assertEquals(bulbi, res.get(0));
        assertEquals(aqua, res.get(1));
    }

    @Test
    public void shouldRaisePokedexExceptionWhenBadID(){
        Exception exception = assertThrows(PokedexException.class, () -> pokedex.getPokemon(6));

        String expectedMsg = "ID "+ 6 +" introuvable";
        String actualMsg = exception.getMessage();
        assertTrue(actualMsg.contains(expectedMsg));
    }
}
