package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class IPokedexFactoryTest {

    IPokemonMetadataProvider provider = Mockito.mock(IPokemonMetadataProvider.class);
    IPokemonFactory factory = Mockito.mock(IPokemonFactory.class);
    IPokedex pokedex = Mockito.mock(IPokedex.class);
    IPokedexFactory pkdxFactory = Mockito.mock(IPokedexFactory.class);

    @Before
    public void setUp(){
        Mockito.when(pkdxFactory.createPokedex(provider, factory)).thenReturn(pokedex);
    }

    @Test
    public void testCreatePokedex() {
        assertEquals(pokedex, pkdxFactory.createPokedex(provider,factory));
    }
}
