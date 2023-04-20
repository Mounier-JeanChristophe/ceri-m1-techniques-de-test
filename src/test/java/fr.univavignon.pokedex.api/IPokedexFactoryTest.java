package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class IPokedexFactoryTest {

    IPokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
    IPokemonFactory pokemonFactory = new PokemonFactory();
    IPokedexFactory pokedexFactory;

    @Before
    public void setUp(){
        pokedexFactory = new PokedexFactory();
    }

    @Test
    public void testCreatePokedex() {
        Pokedex pokedex = new Pokedex(metadataProvider, pokemonFactory);
        assertEquals(pokedex.getPokemons(), pokedexFactory.createPokedex(metadataProvider,pokemonFactory).getPokemons());
    }
}
