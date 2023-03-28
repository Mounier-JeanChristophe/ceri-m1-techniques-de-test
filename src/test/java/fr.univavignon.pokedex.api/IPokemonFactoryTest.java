package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class IPokemonFactoryTest {

    private Pokemon pkm = new Pokemon(0,"Grokachu", 2,2,2,2,2,2,2,2.0);

    IPokemonFactory pkmFactory = Mockito.mock(IPokemonFactory.class);

    @Before
    public void setUp(){
        Mockito.when(pkmFactory.createPokemon(0,2,2,2,2)).thenReturn(pkm);
    }

    @Test
    public void createPokemonTest(){
        assertEquals(pkm, pkmFactory.createPokemon(0,2,2,2,2));
    }
}
