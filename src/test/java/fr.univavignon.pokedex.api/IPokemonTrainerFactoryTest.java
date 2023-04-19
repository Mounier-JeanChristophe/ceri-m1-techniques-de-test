package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

public class IPokemonTrainerFactoryTest {

    private IPokedexFactory pkdxFacotory = Mockito.mock(IPokedexFactory.class);
    private IPokedex pkdx = Mockito.mock(IPokedex.class);
    private PokemonTrainer pkmTrainer = new PokemonTrainer("Maurice", Team.MYSTIC, pkdx);
    private IPokemonTrainerFactory pkmTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);

    @Before
    public void setUp(){
        Mockito.when(pkmTrainerFactory.createTrainer("Maurice", Team.MYSTIC,pkdxFacotory)).thenReturn(pkmTrainer);
    }

    @Test
    public void createTrainerTest(){
        assertEquals(pkmTrainer, pkmTrainerFactory.createTrainer("Maurice",Team.MYSTIC, pkdxFacotory));
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
        assertEquals(pkdx, pkmTrainer.getPokedex());
    }
}
