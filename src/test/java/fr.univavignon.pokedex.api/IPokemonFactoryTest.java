package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IPokemonFactoryTest {

    private Pokemon pkm = new Pokemon(0,"Grokachu", 2,2,2,2,2,2,2,2.0);

    IPokemonFactory pkmFactory = Mockito.mock(IPokemonFactory.class);
    PokemonComparators pkmCompName = PokemonComparators.NAME;
    PokemonComparators pkmCompCP = PokemonComparators.CP;
    PokemonComparators pkmCompId = PokemonComparators.INDEX;

    @Before
    public void setUp(){
        Mockito.when(pkmFactory.createPokemon(0,2,2,2,2)).thenReturn(pkm);
    }

    @Test
    public void createPokemonTest(){
        assertEquals(pkm, pkmFactory.createPokemon(0,2,2,2,2));
    }

    @Test
    public void comparatorTestEqual(){
        Pokemon pkm2 = new Pokemon(0,"Grokachu", 2,2,2,2,2,2,2,2.0);
        assertEquals(0, pkmCompCP.compare(pkm, pkm2));
        assertEquals(0,pkmCompId.compare(pkm, pkm2));
        assertEquals(0, pkmCompName.compare(pkm,pkm2));
    }

    @Test
    public void testCompareWhenFirstPkmIsLower(){
        Pokemon pkm2 = new Pokemon(1, "Pikachu", 2,2,2,4,2,2,3,2);
        assertTrue(pkmCompCP.compare(pkm, pkm2) < 0);
        assertTrue(pkmCompId.compare(pkm, pkm2) < 0);
        assertTrue(pkmCompName.compare(pkm, pkm2) < 0);
    }

    @Test
    public void testCompareWhenFirstPkmIsUpper(){
        Pokemon pkm2 = new Pokemon(-1, "Brokachu", 2,2,2,1,2,2,3,2);
        assertTrue(pkmCompCP.compare(pkm, pkm2) > 0);
        assertTrue(pkmCompId.compare(pkm, pkm2) > 0);
        assertTrue(pkmCompName.compare(pkm, pkm2) > 0);
    }

    @Test
    public void getHpTest(){
        assertEquals(2, pkm.getHp());
    }

    @Test
    public void getDustTest(){
        assertEquals(2, pkm.getDust());
    }

    @Test
    public void getCandyTest(){
        assertEquals(2,pkm.getCandy());
    }

    @Test
    public void getIvTest(){
        assertEquals(2.0,pkm.getIv(),0);
    }
}
