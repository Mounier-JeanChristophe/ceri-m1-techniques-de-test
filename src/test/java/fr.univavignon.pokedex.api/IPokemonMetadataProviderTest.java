package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider pkmDataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    private PokemonMetadata pkmMetadata = new PokemonMetadata(0,"Bulbizarre", 126, 126, 90);

    @Before
    public void setUp() throws PokedexException {
        Mockito.when(pkmDataProvider.getPokemonMetadata(0)).thenReturn(pkmMetadata);
    }

    @Test
    public void getPokemonMetadataTest() throws PokedexException {
        assertEquals(pkmMetadata, pkmDataProvider.getPokemonMetadata(0));
    }

    @Test
    public void getAttackTest(){
        assertEquals(126, pkmMetadata.getAttack());
    }

    @Test
    public void getDefenseTest(){
        assertEquals(126, pkmMetadata.getDefense());
    }

    @Test
    public void getStaminaTest(){
        assertEquals(90, pkmMetadata.getStamina());
    }

}
