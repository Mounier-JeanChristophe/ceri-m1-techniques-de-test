package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class IPokedexTest {

    Pokemon pkm1 = new Pokemon(0,"Grokachu", 2,2,2,2,2,2,2,2.0);
    Pokemon pkm2 = new Pokemon(1,"Ptikachu", 1,1,1,1,1,1,1,1.0);
    List<Pokemon> pkmList = new ArrayList<>();
    IPokedex pkdx = Mockito.mock(IPokedex.class);

    @Before
    public void setUp() throws PokedexException {
        pkmList.add(pkm1);
        pkmList.add(pkm1);
        Mockito.when(pkdx.size()).thenReturn(2);
        Mockito.when(pkdx.addPokemon(pkm1)).thenReturn(0);
        Mockito.when(pkdx.getPokemon(0)).thenReturn(pkm1);
        Mockito.when(pkdx.getPokemons()).thenReturn(pkmList);
        Mockito.when(pkdx.getPokemon(-1)).thenThrow(PokedexException.class);
    }

    @Test
    public void sizeTest(){
        assertEquals(2,pkdx.size());
    }

    @Test
    public void addPokemonTest(){
        assertEquals(0, pkdx.addPokemon(pkm1));
    }

    @Test
    public void getPokemonTest() throws PokedexException {
        assertEquals(pkm1, pkdx.getPokemon(0));
    }

    @Test
    public void getPokemonsTest(){
        List<Pokemon> res = pkdx.getPokemons();
        for (Pokemon re : res) {
            assertEquals(pkm1, re);
        }
    }

    @Test
    public void shouldRaisePokedexExceptionWhenBadID() {
        assertThrows(PokedexException.class, () -> {
            pkdx.getPokemon(-1);
        });

    }
}
