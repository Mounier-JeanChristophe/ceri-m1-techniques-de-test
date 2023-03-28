package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider pkmDataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    private PokemonMetadata pkmMetadata = new PokemonMetadata(1,"Grokachu", 2, 2, 2);

    @Before
    public void setUp() throws PokedexException {
        Mockito.when(pkmDataProvider.getPokemonMetadata(1)).thenReturn(pkmMetadata);
    }

    @Test
    public void getPokemonMetadataTest() throws PokedexException {
        assertEquals(pkmMetadata, pkmDataProvider.getPokemonMetadata(1));
    }

}
